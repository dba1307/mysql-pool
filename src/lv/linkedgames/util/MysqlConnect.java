package lv.linkedgames.util;


import java.sql.*;


public class MysqlConnect {

    private Connection conn;
    private static volatile MysqlConnect instance;
    public static final String URL = "jdbc:mysql://localhost:3306/";
    public static final String DB_NAME = "java";
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";


    private MysqlConnect() {
        try {
            Class.forName(DRIVER);
            this.conn = DriverManager.getConnection(URL+DB_NAME, USER_NAME, PASSWORD);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    MysqlConnect(Connection connection){
        this.conn = connection;
    }


    public static MysqlConnect getConnection() {
//        MysqlConnect localInstance = instance;
//        if (localInstance == null) {
//            synchronized (MysqlConnect.class) {
//                localInstance = instance;
//                if (localInstance == null) {
//                    instance = localInstance = new MysqlConnect();
//                }
//            }
//        }
//        return localInstance;
        return new MysqlConnect();
    }

    public void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public ResultSet query(String query) throws SQLException{
        if (conn == null){
            throw new SQLException("No connection is set");
        }

        Statement statement = conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }


    public int insert(String insertQuery) throws SQLException {
        if (conn == null){
            throw new SQLException("No connection is set");
        }

        Statement statement = conn.createStatement();
        int result = statement.executeUpdate(insertQuery);
        return result;
    }

}