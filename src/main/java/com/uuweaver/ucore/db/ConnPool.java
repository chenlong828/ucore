package com.uuweaver.ucore.db;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Description：
 * Author: ChenLong
 * Date: 12-9-19
 * Time: 下午3:26
 */
public class ConnPool {

    private static ConnPool DBCONN = new ConnPool();

    private BasicDataSource dataSource;

    private ConnPool()
    {
        Properties p = new Properties();
        InputStream is;
        try {
            is = new FileInputStream(System.getProperty("user.dir")
                    + "/resources/db.conf");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            p.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        // dbcp configuration: http://commons.apache.org/dbcp/configuration.html
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(p.getProperty("driverClassName"));
        ds.setUsername(p.getProperty("username"));
        ds.setPassword(p.getProperty("password"));
        ds.setUrl(p.getProperty("url"));
        ds.setInitialSize(Integer.parseInt(p.getProperty("initialSize")));// Init
        // the
        // number
        // of
        // connections
        ds.setMaxActive(Integer.parseInt(p.getProperty("maxActive")));// Max
        // active
        // connections
        ds.setMinIdle(Integer.parseInt(p.getProperty("minIdle")));// Min idle
        // connections
        ds.setMaxIdle(Integer.parseInt(p.getProperty("maxIdle")));// Max idle
        // connections
        ds.setMaxWait(Integer.parseInt(p.getProperty("maxWait")));// Max wait
        // connections
        ds.setDefaultAutoCommit(Boolean.parseBoolean(p
                .getProperty("defaultAutoCommit")));
        ds.setRemoveAbandoned(Boolean.parseBoolean(p
                .getProperty("removeAbandoned")));// if remove abandoned
        // connections
        ds.setRemoveAbandonedTimeout(Integer.parseInt(p
                .getProperty("removeAbandonedTimeout")));
        ds.setPoolPreparedStatements(Boolean.parseBoolean(p
                .getProperty("poolPreparedStatements")));
        ds.setLogAbandoned(Boolean.parseBoolean(p.getProperty("logAbandoned")));
        ds.setValidationQuery(p.getProperty("validationQuery"));
        ds.setTestOnBorrow(Boolean.parseBoolean(p.getProperty("testOnBorrow")));
        dataSource = ds;
    }

    public static ConnPool GetInstance()
    {
        return DBCONN;
    }

    public Connection GetConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void ReleaseConnection(Connection conn) throws SQLException {
        conn.close();
    }

}
