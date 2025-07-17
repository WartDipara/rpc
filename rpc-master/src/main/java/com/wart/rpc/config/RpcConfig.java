package com.wart.rpc.config;

import lombok.Data;

/**
 * RPC 框架
 */
@Data
public class RpcConfig {
  private String name = "rpc-master";
  private String serverHost="localhost";
  private Integer serverPort = 8080;
  private String version = "1.0";
}
