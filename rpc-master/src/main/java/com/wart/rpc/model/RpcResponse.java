package com.wart.rpc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Rpc response
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RpcResponse implements Serializable {
  /**
   * response data
   */
  private Object data;
  /**
   * response type
   */
  private Class<?> dataType;
  /**
   * response msg
   */
  private String message;
  /**
   * Exception
   */
  private Exception exception;
}
