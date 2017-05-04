package cn.annpeter.graduation.project.core.config;

import cn.annpeter.graduation.project.base.common.exception.FileIoException;
import cn.annpeter.graduation.project.base.mybatis.page.MyBatisConfiguration;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import javaslang.control.Try;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

import static cn.annpeter.graduation.project.base.mybatis.page.DB_Type.MYSQL;

/**
 * Created on 2017/03/16
 *
 * @author annpeter.it@gmail.com
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {
        "cn.annpeter.graduation.project.dal.dao"
})
public class DataSourceConfig {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws IOException, SQLException {
        logger.info("==== sqlSessionFactory init ====");

        VFS.addImplClass(SpringBootVFS.class);

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();

        // 添加数据源
        bean.setDataSource(dataSource);
        // 注册 *Mapper.xml 配置文件
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath:/mapper/*Mapper.xml"));
        MyBatisConfiguration config = new MyBatisConfiguration();
        config.setDbType(MYSQL);
        bean.setConfiguration(config);

        SqlSessionFactory factory;
        try {
            factory = bean.getObject();
        } catch (Exception e) {
            throw new RuntimeException("SqlSessionFactory 创建失败", e);
        }
        return factory;
    }

    @Bean
    public DataSource dataSource() throws SQLException {
        logger.info("==== dataSource init ====");

        DataSource dataSource = Try.of(() -> DruidDataSourceFactory.createDataSource(GlobalConfig.datasource))
                .getOrElseThrow((e) -> new FileIoException("数据源配置错误", e));
        dataSource.getConnection().close(); // 测试数据库的配置是否能够正常连接

        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) throws SQLException {
        logger.info("==== transactionManager init ====");

        return new DataSourceTransactionManager(dataSource);
    }

}
