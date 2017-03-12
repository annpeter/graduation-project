import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/01/16
 *
 * @author annpeter.it@gmail.com
 */
public class MybatisGeneratorBootstrap {
    private static final Logger logger = LoggerFactory.getLogger(MybatisGeneratorBootstrap.class);

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(MybatisGeneratorBootstrap.class.getClassLoader()
                .getResourceAsStream("generator-config.xml"));

        DefaultShellCallback shellCallback = new DefaultShellCallback(true);

        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, shellCallback, warnings);
        myBatisGenerator.generate(null);
        for (String string : warnings) {
            logger.warn(string);
        }


        if (warnings.size() == 0) {
            logger.info("MyBatis Generator finished successfully.");
        } else {
            logger.info("MyBatis Generator finished successfully, there were warnings.");
        }
    }

}
