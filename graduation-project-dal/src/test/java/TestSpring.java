import cn.annpeter.graduation.project.dal.dao.UserMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created on 2017/03/11
 *
 * @author annpeter.it@gmail.com
 */
public class TestSpring {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-dao.xml");
        UserMapper userMapper = (UserMapper) ac.getBean("userMapper");

        System.out.println(userMapper);
    }
}
