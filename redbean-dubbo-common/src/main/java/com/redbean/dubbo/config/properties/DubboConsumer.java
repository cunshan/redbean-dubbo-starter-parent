package com.redbean.dubbo.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by GL on 2016/11/24.
 */
@Configuration
@ConfigurationProperties("dubbo.consumer")
public class DubboConsumer {

  /**
   * .
   * 调用版本号
   */
  private String version = "1.0";

  /**
   * .
   * 分组
   */
  private String group;

  /**
   * .
   * dubbo消费者全局拦截器
   */
  private String filter;

  public String getFilter() {
    return filter;
  }

  public void setFilter(String filter) {
    this.filter = filter;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }
}
