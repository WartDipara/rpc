package com.wart.rpc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * RPC request
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcRequest implements Serializable {
  /**
   * service name
   */
  private String serviceName;
  /**
   * method name
   */
  private String methodName;
  /**
   * parameter types
   */
  private Class<?>[] parameterTypes;
  /**
   *  parameters
   */
  private Object[] args;
}
