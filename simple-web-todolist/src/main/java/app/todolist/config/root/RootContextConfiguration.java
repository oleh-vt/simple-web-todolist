package app.todolist.config.root;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"app.todolist.service", "app.todolist.repository"})
public class RootContextConfiguration {
	
	@Bean(name = "dataSource")
	public DataSource getDataSource(){
		SimpleDriverDataSource ds = new SimpleDriverDataSource();
		ds.setDriverClass(com.mysql.jdbc.Driver.class);
		ds.setUrl("jdbc:mysql://localhost:3306/angular_db");
        ds.setUsername("angular_user");
        ds.setPassword("angular");	
		return ds;
	}
	
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(){
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(getDataSource());
		emf.setPackagesToScan("app.todolist.model");
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		
		Map<String, Object> jpaProperties = new HashMap<String, Object>();
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        jpaProperties.put("hibernate.show_sql", "true");
        jpaProperties.put("hibernate.format_sql", "true");
        //jpaProperties.put("hibernate.use_sql_comments", "true");
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        emf.setJpaPropertyMap(jpaProperties);
		return emf;
	}
	
	@Bean(name = "transactionManager")
	public PlatformTransactionManager getPlatformTransactionManager(){
		return new JpaTransactionManager(getEntityManagerFactory().getObject());
	}

}
