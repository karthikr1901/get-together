package com.thetminko.gettogether.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by developer on 11/6/16.
 */
@EnableCaching
@Configuration
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = false)
public class HazelCastConfig {
  public static final String HZ_INSTANCE_NAME = "GET-TOGETHER_CACHE_INSTANCE";

  @Value("${hz.group.name}")
  private String groupName;

  @Value("${hz.group.password}")
  private String groupPassword;


  private Config hazelcastConfig() {

    XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();
    Config config = xmlConfigBuilder.build();

    config.setInstanceName(HZ_INSTANCE_NAME);
    GroupConfig groupConfig = config.getGroupConfig();
    groupConfig.setName(groupName);
    groupConfig.setPassword(groupPassword);

    // disable multicastConfig
    NetworkConfig network = config.getNetworkConfig();
    JoinConfig join = network.getJoin();
    join.getTcpIpConfig().setEnabled(false);
    join.getAwsConfig().setEnabled(false);
    join.getMulticastConfig().setEnabled(false);

    config.setProperty("hazelcast.logging.type", "slf4j");
    config.setProperty("hazelcast.jmx", "true");
    config.setProperty("hazelcast.jmx.detailed", "true");

    setupMapConfigs(config);

    return config;
  }

  private void setupMapConfigs(Config config){
    //setup cache for objects
  }

  /**
   * Initialize the hazelcast instance for the application
   *
   * @return the hazelcast instances of the system
   */
  @Bean
  public HazelcastInstance hazelcastInstance() {
    return Hazelcast.newHazelcastInstance(hazelcastConfig());
  }


  /**
   * Setup the spring cache manager
   *
   * @param hazelcastInstance hazelcastInstance to be autowired
   * @return the application cacheManager
   */
  @Bean
  @Autowired
  public CacheManager cacheManager(HazelcastInstance hazelcastInstance) {
    return new HazelcastCacheManager(hazelcastInstance);
  }
}