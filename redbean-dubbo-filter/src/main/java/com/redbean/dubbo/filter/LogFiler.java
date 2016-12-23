package com.redbean.dubbo.filter;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志filter
 * Created by GL on 2016/12/21.
 */
@Activate
public class LogFiler implements Filter {

  private static final Logger logger = LoggerFactory.getLogger(LogFiler.class);



  @Override
  public Result invoke(
      Invoker<?> invoker, Invocation invocation) throws RpcException {
    logger.info("=====before my filter====");

//    logger.info(JSON.toJSONString(invocation));
    Result result = invoker.invoke(invocation);

    logger.info(result.hasException() ? "true" : "false");
//    logger.info(JSON.toJSONString(result));
    logger.info("=====after my filter====");

    return result;
  }



}
