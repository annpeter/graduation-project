import cn.annpeter.graduation.project.core.config.ContextConfig;
import cn.annpeter.graduation.project.core.config.DataSourceConfig;
import cn.annpeter.graduation.project.core.config.RedisConfig;
import cn.annpeter.graduation.project.web.config.MvcConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created on 2017/03/16
 *
 * @author annpeter.it@gmail.com
 */
@SpringBootApplication(
        scanBasePackageClasses = {
                MvcConfig.class,
                ContextConfig.class,
                RedisConfig.class,
                DataSourceConfig.class
        }
)
public class Bootstrap {

    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }

}
