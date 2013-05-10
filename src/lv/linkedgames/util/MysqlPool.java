package lv.linkedgames.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: tera
 * Date: 5/10/13
 * Time: 7:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class MysqlPool {

    private ComboPooledDataSource cpds;
    private static volatile MysqlPool instance;

    private MysqlPool(){

        cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass(MysqlConnect.DRIVER); //loads the jdbc driver

            cpds.setJdbcUrl(MysqlConnect.URL + MysqlConnect.DB_NAME);
            cpds.setUser(MysqlConnect.USER_NAME);
            cpds.setPassword(MysqlConnect.PASSWORD);

            cpds.setMinPoolSize(5);
            cpds.setAcquireIncrement(5);
            cpds.setMaxPoolSize(20);
        } catch (PropertyVetoException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static MysqlConnect getConnection() throws SQLException {
        MysqlPool localInstance = instance;
        if (localInstance == null) {
            synchronized (MysqlPool.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new MysqlPool();
                }
            }
        }
        return new MysqlConnect(localInstance.cpds.getConnection());
    }


}
