package org.ebs.shared.serverless;



import org.ebs.shared.serverless.config.ConfigKey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class ConnectionFactory {

    private final Map<ConfigKey, String> config;

    public ConnectionFactory(Map<ConfigKey, String> config){
        this.config = config;
    }

    public Connection getConnection(){
        String jdbcUrl = config.get(ConfigKey.JDBC_URL);
        String jdbcUsername = config.get(ConfigKey.JDBC_USERNAME);
        String jdbcPassword = config.get(ConfigKey.JDBC_PASSWORD);

        Connection conn;
        try {
            conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            throw new RuntimeException("Could not connect to DB: Please make sure the DB is connected and running on mysql://localhost/smartcity");
        }
        return conn;
    }


}
