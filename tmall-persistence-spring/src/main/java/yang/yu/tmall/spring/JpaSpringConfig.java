package yang.yu.tmall.spring;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan("yang.yu.tmall")
@EnableJpaRepositories(basePackages = {"yang.yu.tmall.repository"})
@EnableTransactionManagement
@PropertySource("/jdbc.properties")
public class JpaSpringConfig {

    private Environment env;

    public JpaSpringConfig(Environment env) {
        this.env = env;
    }

    @Bean(destroyMethod = "close")
    public ComboPooledDataSource dataSource() throws Exception {
        ComboPooledDataSource result = new ComboPooledDataSource();
        result.setDriverClass(env.getProperty("jdbc.driverClassName"));
        result.setJdbcUrl(env.getProperty("jdbc.url"));
        result.setUser(env.getProperty("jdbc.username"));
        result.setPassword(env.getProperty("jdbc.password", ""));
        return result;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter result = new HibernateJpaVendorAdapter();
        result.setDatabase(Database.H2);
        result.setDatabasePlatform(env.getProperty("hibernate.dialect"));
        result.setGenerateDdl(true);
        result.setShowSql(true);
        return result;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter adapter) {
        LocalContainerEntityManagerFactoryBean result = new LocalContainerEntityManagerFactoryBean();
        result.setDataSource(dataSource);
        result.setJpaVendorAdapter(adapter);
        result.setPackagesToScan("yang.yu.tmall.domain");
        result.setJpaPropertyMap(hibernateProperties());
        return result;
    }

    private Map<String, String> hibernateProperties() {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.implicit_naming_strategy", "jpa");
        return props;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
