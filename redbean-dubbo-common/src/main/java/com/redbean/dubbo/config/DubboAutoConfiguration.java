package com.redbean.dubbo.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.alibaba.dubbo.rpc.Filter;
import com.redbean.dubbo.config.properties.DubboApplication;
import com.redbean.dubbo.config.properties.DubboConsumer;
import com.redbean.dubbo.config.properties.DubboProtocol;
import com.redbean.dubbo.config.properties.DubboProvider;
import com.redbean.dubbo.config.properties.DubboRegistry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(Service.class)
@EnableConfigurationProperties({DubboApplication.class, DubboProtocol.class, DubboRegistry.class,
    DubboProvider.class, DubboConsumer.class})
public class DubboAutoConfiguration {

  @Autowired
  private DubboApplication dubboApplication;

  @Autowired
  private DubboProtocol dubboProtocol;

  @Autowired
  private DubboProvider dubboProvider;

  @Autowired
  private DubboRegistry dubboRegistry;

  @Autowired
  private DubboConsumer dubboConsumer;

  @Bean
  public static AnnotationBean annotationBean(
      @Value("${dubbo.annotation.package}") String packageName) {
    AnnotationBean annotationBean = new AnnotationBean();
    annotationBean.setPackage(packageName);
    return annotationBean;
  }

  @Bean
  public ApplicationConfig applicationConfig() {
    ApplicationConfig applicationConfig = new ApplicationConfig();
    applicationConfig.setName(dubboApplication.getName());
    applicationConfig.setLogger(dubboApplication.getLogger());
    return applicationConfig;
  }

  @Bean
  public ProtocolConfig protocolConfig() {
    ProtocolConfig protocolConfig = new ProtocolConfig();
    protocolConfig.setName(dubboProtocol.getName());
    protocolConfig.setPort(dubboProtocol.getPort());
    protocolConfig.setAccesslog(String.valueOf(dubboProtocol.isAccessLog()));
    return protocolConfig;
  }

  @Bean
  public ProviderConfig providerConfig(
      ApplicationConfig applicationConfig, RegistryConfig registryConfig,
      ProtocolConfig protocolConfig) {
    ProviderConfig providerConfig = new ProviderConfig();
    providerConfig.setTimeout(dubboProvider.getTimeout());
    providerConfig.setRetries(dubboProvider.getRetries());
    providerConfig.setDelay(dubboProvider.getDelay());
    providerConfig.setApplication(applicationConfig);
    registryConfig.setClient(dubboRegistry.getClient());
    providerConfig.setRegistry(registryConfig);
    providerConfig.setProtocol(protocolConfig);
    providerConfig.setVersion(dubboProvider.getVersion());
    providerConfig.setGroup(dubboProvider.getGroup());
    providerConfig.setFilter(dubboProvider.getFilter());
    return providerConfig;
  }

  @Bean
  public RegistryConfig registryConfig() {
    RegistryConfig registryConfig = new RegistryConfig();
    registryConfig.setProtocol(dubboRegistry.getProtocol());
    registryConfig.setAddress(dubboRegistry.getAddress());
    registryConfig.setRegister(dubboRegistry.isRegister());
    registryConfig.setSubscribe(dubboRegistry.isSubscribe());
    return registryConfig;
  }

  @Bean
  public ConsumerConfig consumerConfig(
      ApplicationConfig applicationConfig, RegistryConfig registryConfig) {
    ConsumerConfig consumerConfig = new ConsumerConfig();
    consumerConfig.setApplication(applicationConfig);
    consumerConfig.setRegistry(registryConfig);
    consumerConfig.setVersion(dubboConsumer.getVersion());
    consumerConfig.setGroup(dubboConsumer.getGroup());
    consumerConfig.setFilter(dubboConsumer.getFilter());
    return consumerConfig;
  }
}