package ru.kors.util;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionManager {
    private static final System.Logger logger = System.getLogger(ConnectionManager.class.getName());
    private static final String URL = "db.url";
    private static final String USERNAME = "db.username";
    private static final String PASSWORD = "db.password";
    private static final int DEFAULT_POOL_SIZE = 10;
    private static final String POOL_SIZE = "db.pool.size";
    private static BlockingQueue<Connection> connectionPool;

    static {
        try {
            initConnectionPool();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private ConnectionManager(){}

    public static Connection get() throws InterruptedException {
        return connectionPool.take();
    }

    private static void initConnectionPool() throws InterruptedException {
        String poolSize = PropertiesUtil.get(POOL_SIZE);
        int size;
        if (poolSize == null) size = DEFAULT_POOL_SIZE;
        else size = Integer.parseInt(poolSize);

        connectionPool = new ArrayBlockingQueue<>(size);

        for (int i = 0; i < size; i++) {
            Connection connection = open();
            Connection proxyConnection = (Connection) Proxy.newProxyInstance(ConnectionManager.class.getClassLoader(),
                    new Class[]{Connection.class}, (proxy, method, args) -> method.getName().equals("close") ?
                    connectionPool.add((Connection) proxy) : method.invoke(connection, args));
            connectionPool.add(proxyConnection);
        }
    }

    private static Connection open() {
        try {
            Driver driver = new org.postgresql.Driver();
            return DriverManager.getConnection(PropertiesUtil.get(URL),
                    PropertiesUtil.get(USERNAME),
                    PropertiesUtil.get(PASSWORD));
        } catch (SQLException e) {
            logger.log(System.Logger.Level.ERROR, "Unable to connect to the Database");
            throw new RuntimeException(e);
        }
    }
}
