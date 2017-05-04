package cn.annpeter.graduation.project.base.common.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created on 2017/04/05
 *
 * @author annpeter.it@gmail.com
 */
public class VerifyCodeUtils {

    //使用到Algerian字体，系统里没有的话需要安装字体，字体只显示大写，去掉了0,o,O,1,i,I,2,z,Z,l,L几个容易混淆的字符
    public static final String VERIFY_CODES = "abcdefghjkmnpqrstuvwxy3456789ABCDEFGHJKMNPQRSTUVWXY";
    private static final Random random = ThreadLocalRandom.current();

    /**
     * 使用系统默认字符源生成验证码
     *
     * @param verifySize 验证码长度
     */
    private static String generateVerifyCode(int verifySize) {
        int codesLen = VERIFY_CODES.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for (int i = 0; i < verifySize; i++) {
            verifyCode.append(VERIFY_CODES.charAt(rand.nextInt(codesLen - 1)));
        }
        return verifyCode.toString();
    }

    /**
     * 输出随机验证码图片流,并返回验证码值
     */
    public static String outputVerifyImage(int width, int height, OutputStream outputStream, int verifySize) throws IOException {
        String verifyCode = generateVerifyCode(verifySize);
        outputVerifyImage(width, height, outputStream, verifyCode);
        return verifyCode;
    }

    /**
     * 输出指定验证码图片流
     */
    public static void outputVerifyImage(int width, int height, OutputStream outputStream, String code) throws IOException {
        int verifySize = code.length();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Random rand = new Random();
        Graphics2D graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color[] colors = new Color[5];
        Color[] colorSpaces = new Color[]{Color.WHITE, Color.CYAN,
                Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
                Color.PINK, Color.YELLOW};
        float[] fractions = new float[colors.length];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
            fractions[i] = rand.nextFloat();
        }
        Arrays.sort(fractions);

        graphics.setColor(Color.GRAY);    // 设置边框色
        graphics.fillRect(0, 0, width, height);

        Color c = getRandColor(200, 250);
        graphics.setColor(c);     // 设置背景色
        graphics.fillRect(0, 2, width, height - 4);

        //绘制干扰线
        graphics.setColor(getRandColor(160, 200));    // 设置线条的颜色
        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            graphics.drawLine(x, y, x + xl + 40, y + yl + 20);
        }

        // 添加噪点
        float noiseRate = 0.05f;// 噪声率
        int area = (int) (noiseRate * width * height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int rgb = getRandomIntColor();
            image.setRGB(x, y, rgb);
        }

        shear(graphics, width, height, c);        // 使图片扭曲

        graphics.setColor(getRandColor(100, 160));
        int fontSize = height - 4;
        Font font = new Font("Algerian", Font.ITALIC, fontSize);
        graphics.setFont(font);
        char[] chars = code.toCharArray();
        for (int i = 0; i < verifySize; i++) {
            AffineTransform affine = new AffineTransform();
            affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1),
                    (width / verifySize) * i + fontSize / 2, height / 2);
            graphics.setTransform(affine);
            graphics.drawChars(chars, i, 1, ((width - 10) / verifySize) * i + 5, height / 2 + fontSize / 2 - 10);
        }

        graphics.dispose();
        ImageIO.write(image, "png", outputStream);
    }

    private static Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    private static int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    private static int[] getRandomRgb() {
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }

    private static void shear(Graphics graphics, int w1, int h1, Color color) {
        shearX(graphics, w1, h1, color);
        shearY(graphics, w1, h1, color);
    }

    private static void shearX(Graphics graphics, int w1, int h1, Color color) {
        int period = random.nextInt(2);

        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            graphics.copyArea(0, i, w1, 1, (int) d, 0);

            graphics.setColor(color);
            graphics.drawLine((int) d, i, 0, i);
            graphics.drawLine((int) d + w1, i, w1, i);
        }

    }

    private static void shearY(Graphics graphics, int w1, int h1, Color color) {
        int period = random.nextInt(40) + 10;

        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            graphics.copyArea(i, 0, 1, h1, 0, (int) d);

            graphics.setColor(color);
            graphics.drawLine(i, (int) d, i, 0);
            graphics.drawLine(i, (int) d + h1, i, h1);
        }
    }
}