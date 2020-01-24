package apps.g0rba4ev.DAO;

import apps.g0rba4ev.model.User;

import java.sql.*;

public class DB {

    String DB_URL = "jdbc:hsqldb:file:../testDB/testDB;ifexists=true";
    String DB_User = "SA";
    String DB_Password = "";
    String DB_Driver = "org.hsqldb.jdbcDriver";

    /**
     * Constructor for default DB
     */
    public DB(){

    }

    public DB(String DB_URL, String DB_User, String DB_Password, String DB_Driver) {
        this.DB_URL = DB_URL;
        this.DB_User = DB_User;
        this.DB_Password = DB_Password;
        this.DB_Driver = DB_Driver;
    }

    public void addUser(final String login, final String password) {
        Connection conn = null;
        PreparedStatement statement = null;

        loadDriver();

        try {
            conn = DriverManager.getConnection(DB_URL, DB_User, DB_Password);

            statement = conn.prepareStatement("INSERT INTO users (login, password) values (?, ?)");
            statement.setString(1, login);
            statement.setString(2, password);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try{
                if(statement != null)
                    statement.close();
                if(conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public User getUserByLogin(final String login) {
        User user = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        loadDriver();

        try {

            conn = DriverManager.getConnection(DB_URL, DB_User, DB_Password);
            statement = conn.prepareStatement("SELECT * FROM USERS WHERE LOGIN = ?");
            statement.setString(1, login);
            rs = statement.executeQuery();

            if (rs.next()) {
                user = new User(login, rs.getString("PASSWORD"));
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try{
                if(rs != null)
                    rs.close();
                if(statement != null)
                    statement.close();
                if(conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }

        return user;
    }

    /**
     * dynamic load database's driver to the classpath
     */
    private void loadDriver(){
        try {
            Class.forName(DB_Driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.out);
        }
    }

}
