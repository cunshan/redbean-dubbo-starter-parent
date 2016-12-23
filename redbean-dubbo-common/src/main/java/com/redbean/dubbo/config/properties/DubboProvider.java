package com.redbean.dubbo.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "dubbo.provider")
public class DubboProvider {

  private static final String defaultFilters = "";

  /**
   * 服务的超时时间,单位毫秒
   */
  private int timeout = 10000;

  /**
   * 调用失败重试次数
   */
  private int retries = 0;

  /**
   * 是否延迟暴露,-1表示不延迟暴露
   */
  private int delay = -1;

  /**
   * 默认dubbo版本号 1.0
   */
  private String version = "1.0";

  /**
   * .
   * dubbo分组
   */
  private String group;

  /**
   * .
   * dubbo的provider拦截器
   * 例如: "xxx,yyy"
   */
  private String filter = defaultFilters;

  public String getFilter() {
    return filter;
  }

  public void setFilter(String filter) {
    this.filter = filter;
  }

  public int getTimeout() {
    return timeout;
  }

  public void setTimeout(int timeout) {
    this.timeout = timeout;
  }

  public int getRetries() {
    return retries;
  }

  public void setRetries(int retries) {
    this.retries = retries;
  }

  public int getDelay() {
    return delay;
  }

  public void setDelay(int delay) {
    this.delay = delay;
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
