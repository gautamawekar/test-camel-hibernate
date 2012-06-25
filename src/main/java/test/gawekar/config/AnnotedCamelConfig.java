package test.gawekar.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value={"classpath:my-routes-context.xml"})
public class AnnotedCamelConfig extends AnnotedSpringConfig{
	@Bean()
	public EntityManagerFactory entityManagerFactory(){
		EntityManager em = null;
		return Persistence.createEntityManagerFactory("test-gawekar-mysql-local",createUnitTestDBConnectionProperties()); 
	}
	
	private  Map createUnitTestDBConnectionProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/test_gawekar_db");
		properties.put("hibernate.connection.username", "gawekar");
		properties.put("hibernate.connection.password", "test123");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		
		Map pp = new HashMap();
		for(Entry<Object, Object> entry: properties.entrySet()) {
			pp.put(entry.getKey(), entry.getValue());
		}
		
		return pp;
	}
}
