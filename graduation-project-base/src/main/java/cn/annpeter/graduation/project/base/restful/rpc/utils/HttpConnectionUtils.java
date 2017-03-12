package cn.annpeter.graduation.project.base.restful.rpc.utils;

import cn.annpeter.graduation.project.base.common.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.HttpMethod;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created on 2017/02/20
 *
 * @author annpeter.it@gmail.com
 */
public class HttpConnectionUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpConnectionUtils.class);


    private static final String HEADER_CONTENT_TYPE = "Content-type";
    private static final String HEADER_ACCEPT = "Accept";

    private static final byte[] EMPTY_BYTE = "".getBytes();

    public static byte[] loadJson(String url, Map<String, String> queryParams, Map<String, Object> bodyParams,
                                  Map<String, String> header, String method, String mediaType) {
        return loadJson(url, queryParams, bodyParams, header, method, mediaType, 5000);
    }

    public static byte[] loadJson(String url, Map<String, String> queryParams, Map<String, Object> bodyParams,
                                  Map<String, String> header, String method, String mediaType, Integer timeOut) {
        HttpURLConnection conn;

        try {
            StringBuilder queryParam = new StringBuilder();
            for (Entry<String, String> entry : queryParams.entrySet()) {
                queryParam.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
            logger.info("ready to send post http request:{}, params:{}, body:{}, header:{}.",
                    new Object[]{url, queryParam.length() > 0 ? queryParam.deleteCharAt(0) : queryParam,
                            JsonUtil.parseObject2String(bodyParams), header});

            String targetUrl = url;
            if (!queryParams.isEmpty()) {
                if (url.indexOf("?") > 0) {
                    targetUrl += queryParam.toString();
                } else {
                    targetUrl += "?" + queryParam.toString();
                }
            }
            URL addressUrl = new URL(targetUrl);
            conn = (HttpURLConnection) addressUrl.openConnection();
            conn.setRequestMethod(method);
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setReadTimeout(timeOut);
            conn.setConnectTimeout(timeOut);
            conn.setInstanceFollowRedirects(true);

            boolean isPostJson = isPostJson(mediaType);
            if (isPostJson) {
                conn.addRequestProperty(HEADER_CONTENT_TYPE, mediaType);
            } else {
                if (HttpMethod.POST.equals(method)) {
                    conn.addRequestProperty(HEADER_CONTENT_TYPE, "application/x-www-form-urlencoded;charset=UTF-8");
                } else {
                    conn.addRequestProperty(HEADER_CONTENT_TYPE, "text/plain;charset=UTF-8");
                }
            }

            conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36" +
                    " (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            if (header != null && !header.isEmpty()) {
                for (Entry<String, String> entry : header.entrySet()) {
                    conn.addRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            String body = "";
            if (bodyParams != null && !bodyParams.isEmpty()) {
                String targetMediaType = conn.getRequestProperty(HEADER_CONTENT_TYPE);
                if (isFormSubmit(targetMediaType)) {
                    StringBuilder sb = new StringBuilder();
                    Map<String, String> paramMap = new HashMap<>();
                    for (Entry<String, Object> entry : bodyParams.entrySet()) {
                        paramMap.putAll(generateParam(entry.getKey(), entry.getValue().getClass(), entry.getValue()));
                    }
                    for (Entry<String, String> entry : paramMap.entrySet()) {
                        if (entry.getKey() != null && entry.getKey().length() > 0) {
                            sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                        }
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(0);
                    }
                    body = sb.toString();
                } else if (isPostJson(targetMediaType)) {
                    for (Entry<String, Object> entry : bodyParams.entrySet()) {
                        body = JsonUtil.parseObject2String(entry.getValue());
                    }
                }

            }
            conn.connect();
            OutputStream os = conn.getOutputStream();
            os.write(body.getBytes("UTF-8"));

            InputStream is = conn.getInputStream();
            byte[] result = readResult(is);
            logger.info("http response :{}.", new String(result));
            return result;
        } catch (Exception e) {
            logger.error("send request error: ", e);
        }
        return EMPTY_BYTE;
    }

    /**
     * 使用POST方式提交请求,解析body对象的参数
     */
    private static Map<String, String> generateParam(String paramName, Class<?> paramClass, Object paramValue) throws Exception {
        Map<String, String> result = new HashMap<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (paramValue != null) {
            if (paramClass.isPrimitive() || Number.class.isAssignableFrom(paramClass) || String.class.isAssignableFrom(paramClass)) {
                result.put(paramName, paramValue + "");
            } else if (paramValue instanceof Collection || paramClass.isArray()) {
                Object[] array = null;
                if (paramValue instanceof Collection) {
                    Collection<Object> collection = (Collection) paramValue;
                    array = collection.toArray(new Object[0]);
                }
                if (paramClass.isArray()) {
                    array = (Object[]) paramValue;
                }
                if (array != null) {
                    for (int i = 0; i < array.length; i++) {
                        Object item = array[i];
                        if (item.getClass().isPrimitive() || item instanceof Number || item instanceof Character || item instanceof String) {
                            String str = result.get(paramName);
                            if (str == null || str.trim().length() <= 0) {
                                str = item + "";
                            } else {
                                str += "," + item;
                            }
                            result.put(paramName, str);
                        } else {
                            Field[] fieldArray = item.getClass().getDeclaredFields();
                            for (Field field : fieldArray) {
                                field.setAccessible(true);
                                String str = result.get(field.getName());
                                if (str == null || str.trim().length() <= 0) {
                                    str = field.get(item) + "";
                                } else {
                                    str += "," + field.get(item);
                                }
                                result.put(field.getName(), str);
                            }
                        }
                    }
                }
            } else if (Date.class.isAssignableFrom(paramClass)) {
                result.put(paramName, format.format((Date) paramValue));
            } else if (paramClass.isEnum()) {
                result.put(paramName, ((Enum) paramValue).name());
            } else {
                Field[] fieldArray = paramClass.getDeclaredFields();
                for (Field field : fieldArray) {
                    field.setAccessible(true);
                    Object fieldValue = field.get(paramValue);
                    if (fieldValue != null) {
                        String str = result.get(field.getName());

                        if (str == null || str.trim().length() <= 0) {
                            str = field.get(paramValue) + "";
                        } else {
                            str += "," + field.get(paramValue);
                        }

                        result.put(field.getName(), str);
                    }
                }
                Class<?> paramClazz = paramClass.getSuperclass();
                while (!paramClazz.equals(Object.class)) {
                    result.putAll(generateParam(null, paramClazz, paramValue));
                    paramClazz = paramClazz.getSuperclass();
                }
            }
        }
        return result;
    }

    private static byte[] readResult(InputStream is) throws Exception {
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        if (is != null) {
            byte[] byteArray = new byte[2048];
            int count;
            while ((count = is.read(byteArray)) > 0) {
                ba.write(byteArray, 0, count);
            }
            is.close();
            return ba.toByteArray();
        }
        return EMPTY_BYTE;
    }

    private static boolean isFormSubmit(String mediaType) {
        return mediaType.contains("x-www-form-urlencoded");
    }

    private static boolean isPostJson(String mediaType) {
        return mediaType.contains("application/json");
    }
}
