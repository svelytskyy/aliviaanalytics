package com.addressbook;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

public class DatabaseConnectionPool {

    private static HikariDataSource dataSource;

    static {
        try {
			initializeDataSource();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    private static void initializeDataSource() throws IOException {
        HikariConfig config = new HikariConfig();
        Properties props = new Properties();
        props.load(DatabaseConnectionPool.class.getClassLoader().getResourceAsStream("database.properties"));

        //config.setJdbcUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");  // H2 in-memory DB
        //config.setUsername("sa");                                   // Default username
        //config.setPassword("");                                     // No password
        config.setJdbcUrl("jdbc:oracle:thin:@prod");
        config.setUsername("admin");                                   
        config.setPassword("beefhead"); 
        config.setDriverClassName(props.getProperty("jdbc.driverClassName"));
        config.setMaximumPoolSize(10);                              // Maximum pool size
        config.addDataSourceProperty("cachePrepStmts", "true");     // Improved performance by caching
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void closeDataSource() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
}
