package cn.annpeter.graduation.project.base.common.util;

import cn.annpeter.graduation.project.base.mybatis.page.model.Page;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created on 2016/12/20
 *
 * @author annpeter.it@gmail.com
 */
public class PageUtils {
    public static Map<String, Object> getPageInfo(Page page) {
        Map<String, Object> map = new HashMap<>();

        map.put("prePage", page.getPrePageNo());
        map.put("nextPage", page.getNextPageNo());
        map.put("currPage", page.getCurrPageNo());

        map.put("sliderList", getSliderList(page, 4));
        map.put("dataList", page);
        return map;
    }

    private static List<String> getSliderList(Page page, int maxItems) {
        int currPage = page.getCurrPageNo();
        int totalPage = page.getTotalPage();

        List<String> sliderList = new LinkedList<>();
        if (currPage != 1 && currPage >= maxItems && totalPage != maxItems) {
            sliderList.add("1");
        }
        if (currPage - 2 > 2 && currPage <= totalPage && totalPage > maxItems + 1) {
            sliderList.add("...");
        }

        int start = currPage - 2;
        int end = currPage + 2;
        if ((start > 1 && currPage < maxItems) || currPage == 1) {
            end++;
        }
        if (currPage > totalPage - maxItems && currPage >= totalPage) {
            start--;
        }
        for (; start <= end; start++) {
            if (start <= totalPage && start >= 1) {
                sliderList.add(String.valueOf(start));
            }
        }
        if (currPage + 2 < totalPage - 1 && currPage >= 1 && totalPage > maxItems + 1) {
            sliderList.add("...");
        }
        if (currPage != totalPage && currPage < totalPage - 2 && totalPage != maxItems) {
            sliderList.add(String.valueOf(totalPage));
        }
        return sliderList;
    }
}
