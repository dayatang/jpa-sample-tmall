package yang.yu.tmall.spring;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = {"yang.yu.tmall.repository"})
@EnableTransactionManagement
public class JpaSpringConfig {

    @Bean(destroyMethod = "close")
    public ComboPooledDataSource dataSource() throws Exception {
        ComboPooledDataSource result = new ComboPooledDataSource();
        result.setDriverClass("org.h2.Driver");
        result.setJdbcUrl("jdbc:h2:mem:tmall;DB_CLOSE_DELAY=-1");
        result.setUser("sa");
        result.setPassword("");
        return result;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter result = new HibernateJpaVendorAdapter();
        result.setDatabase(Database.MYSQL);
        result.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
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
        return result;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
