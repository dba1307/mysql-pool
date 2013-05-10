package lv.linkedgames;

import lv.linkedgames.util.MysqlConnect;
import lv.linkedgames.util.MysqlPool;


import java.sql.SQLException;


public class Main {

    public static void main(String[] args) {

        /// Mysql connection
        MysqlConnect connect = MysqlConnect.getConnection();
        try {
            connect.insert("INSERT INTO test (text) VALUES ('mysql connection is working!')");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connect.closeConnection();
        }


        /// Mysql pool connection
        MysqlConnect poolConnect = null;
        try {
            poolConnect = MysqlPool.getConnection();
            poolConnect.insert("INSERT INTO test (text) VALUES ('mysql pool connection is working!')");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (poolConnect != null){
                poolConnect.closeConnection();
            }
        }
    }





}
