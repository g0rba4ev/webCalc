package apps.g0rba4ev.DAO;

import apps.g0rba4ev.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

    private static DB instance;

    private Connection conn;

    private DB() {
        try {

            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.out);
        }
        try {

            String db = "jdbc:hsqldb:file:testDB";
            String dbUser = "SA";
            String dbPassword = "";

            conn = DriverManager.getConnection(db, dbUser, dbPassword);

            //init for local DB
            try{
                Statement stmt = conn.createStatement();

                stmt.execute("CREATE TABLE USERS(login VARCHAR(20), password VARCHAR(50))");
                stmt.execute("INSERT INTO users (login, password) values ('admin', 'admin')");


                stmt.close();
            } catch (SQLException e){
                System.out.println("Error in method DB.getUserByLogin");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public static DB getInstance(){
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }

    public boolean addUser(final String login, final String password) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeQuery("INSERT INTO users (login, password) values ('" + login + "', '" + password + "')");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return true;
    }

    public User getUserByLogin(final String login) {
        User user = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * " +
                    "from USERS " +
                    "where LOGIN = '" + login + "'");

            if (rs.next()) {
                user = new User(login, rs.getString("PASSWORD"));
            }

            rs.close();
            stmt.close();


        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return user;
    }

    public static void main(String[] args) {

        DB database = DB.getInstance();
        database.addUser("Ilya", "Gorbachev");
        User user = database.getUserByLogin("admin");

        User user1 = database.getUserByLogin("Ilya");
        if(user != null){
            System.out.println(user.getLogin());
            System.out.println(user.getPassword());
        }


    }

}
