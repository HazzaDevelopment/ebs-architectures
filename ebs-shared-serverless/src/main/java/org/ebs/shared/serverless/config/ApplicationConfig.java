package org.ebs.shared.serverless.config;


import java.util.HashMap;
import java.util.Map;


public class ApplicationConfig {

  private ApplicationConfig() {
  }

  public static Map<ConfigKey, String> getConfig(String env) {
    if(env == null){
      throw new RuntimeException("You need to set the Environment variable \"ENVIRONMENT\"");
    }
    Environment environment = Environment.valueOf(env.toUpperCase());
    Map<ConfigKey, String> config = getDefaultConfig();
    switch (environment) {
      case DEV:
        config.putAll(getDefaultConfig());
        break;
      case DOCKER:
        config.putAll(getDockerConfig());
      case LIVE:
        config.putAll(getLiveConfig());
        break;
      default:
        return config;
    }
    return config;
  }

  private static Map<ConfigKey, String> getDefaultConfig() {
    Map<ConfigKey, String> defaultConfig = new HashMap<>();

    defaultConfig.put(ConfigKey.JDBC_URL, "jdbc:postgresql://localhost:5434/postgres");
    defaultConfig.put(ConfigKey.JDBC_USERNAME, "postgres");
    defaultConfig.put(ConfigKey.JDBC_PASSWORD, "postgres");
    defaultConfig.put(ConfigKey.BUCKET_NAME, "renderer-bucket");

    return defaultConfig;
  }

  private static Map<ConfigKey, String> getDockerConfig() {
    Map<ConfigKey, String> defaultConfig = new HashMap<>();
    defaultConfig.put(ConfigKey.JDBC_URL, "jdbc:postgresql://postgres:5432/postgres");

    return defaultConfig;
  }


  private static Map<ConfigKey, String> getLiveConfig() {
    Map<ConfigKey, String> liveConfig = new HashMap<>();
    String dbPassword = System.getenv("DB_PASSWORD");
    liveConfig.put(ConfigKey.JDBC_URL, "jdbc:postgresql://ebs.c9ojfw5sbqqb.us-east-2.rds.amazonaws.com:5432/postgres");
    liveConfig.put(ConfigKey.JDBC_PASSWORD, dbPassword);
    liveConfig.put(ConfigKey.BUCKET_NAME, "ebs-serverless-bucket");

    return liveConfig;
  }
}
