package com.thetminko.gettogether.config;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by developer on 11/6/16.
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = false)
@DependsOn("hazelcastInstance")
@EnableJpaRepositories("com.cab.repository")
public class HibernateConfig {

  private String hibernateAuto = "create-drop";

  private String
      hibernateCacheRegionFactoryClass =
      "com.hazelcast.hibernate.HazelcastCacheRegionFactory";

  private String hibnernateDialect = "org.hibernate.dialect.MySQLDialect";

  private String driverClassName = "com.mysql.jdbc.Driver";

  private String
      datasourceUrl =
      "jdbc:mysql://localhost:3306/Get-Together?characterEncoding=UTF-8&autoReconnect=true&useSSL=false";

  private String datasourceUserName = "root";

  private String datasourcePassword = "Thetminko1990";

  private int maxPoolSize = 15;

  private String initSql = "sql/init.sql";

  /**
   * Setup the entityManagerFactory for hibernate.
   * Also it will scan the package "com.ufinity.common.model"
   */
  @Bean(name = "entityManagerFactory")
  public LocalContainerEntityManagerFactoryBean emf() {
    LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
    emf.setDataSource(dataSource());
    emf.setPackagesToScan("com.thetminko.gettogether.common", "com.thetminko.gettogether.repository.model");
    emf.setJpaVendorAdapter(hibernateJpaVendorAdapter());
    emf.setJpaProperties(hibernateProperties());
    return emf;
  }

  private Properties hibernateProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.hbm2ddl.auto", hibernateAuto);
    properties.put("hibernate.hbm2ddl.import_files", initSql);
    properties.put("hibernate.format_sql", "true");
    properties.put("hibernate.enable_lazy_load_no_trans", "true");
    properties.put("hibernate.connection.CharSet", "utf8");
    properties.put("hibernate.connection.characterEncoding", "utf8");
    properties.put("hibernate.connection.useUnicode", "true");
    properties.put("hibernate.cache.use_second_level_cache", "true");
    properties.put("hibernate.cache.use_query_cache", "true");
    properties.put("hibernate.cache.use_minimal_puts", "true");
    properties.put("hibernate.cache.region.factory_class", hibernateCacheRegionFactoryClass);
    properties.put("hibernate.cache.hazelcast.instance_name", HazelCastConfig.HZ_INSTANCE_NAME);
    properties.put("hibernate.cache.hazelcast.shutdown_on_session_factory_close", "false");
    properties.put("hibernate.cache.hazelcast.use_native_client", "false");

    return properties;
  }

  private HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
    HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
    jpaVendorAdapter.setShowSql(true);
    jpaVendorAdapter.setGenerateDdl(true);
    jpaVendorAdapter.setDatabasePlatform(hibnernateDialect);
    return jpaVendorAdapter;
  }

  /**
   * Setup the HikariDataSource for the application
   *
   * @return the application datasource
   */
  @Bean(name = "dataSource", destroyMethod = "close")
  public DataSource dataSource() {
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setDriverClassName(driverClassName);
    dataSource.setJdbcUrl(datasourceUrl);
    dataSource.setUsername(datasourceUserName);
    dataSource.setPassword(datasourcePassword);
    dataSource.setMaximumPoolSize(maxPoolSize);
    return dataSource;
  }

  /**
   * Setup the jpa transaction manager of the application
   *
   * @param emf entityManagerFactory will be autowired
   * @return the jpaTransactionManager of the application
   */
  @Bean
  @Autowired
  public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(emf);
    return txManager;
  }
}

