package cn.annpeter.graduation.project.core.quartz;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * Created on 2017/03/13
 *
 * @author annpeter.it@gmail.com
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class TestQuartz {
    @Test
    public void quartzTest() throws Exception {
        Thread.currentThread().join();
    }

    @Test
    public void treeMapTest() throws Exception {

        TreeMap<String, Integer> treeMap = new TreeMap<>(Comparator.comparing(StringUtils::reverse));

        treeMap.put("北京市", 1234);
        treeMap.put("四川省", 456);
        treeMap.put("上海市", 789);
        treeMap.put("重庆市", 2123);
        treeMap.put("浙江省", 231);

        treeMap.forEach((key, value)->{
            System.out.println("key = " + key);
        });
    }

}
