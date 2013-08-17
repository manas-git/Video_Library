
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateVideo {

    public static void main(String args[]) {

        String url ="jdbc:mysql://127.0.0.1:3306/librarytest";
        Connection con;
        String createString;
        createString = "CREATE TABLE Video(VID INTEGER UNSIGNED NOT NULL AUTO_INCREMENT  ," + "Title VARCHAR(45),"+"Year VARCHAR(45),"+
                "Genre VARCHAR(45),"+
                "Producer VARCHAR(45)," +
                " PRIMARY KEY (`VID`))";
        Statement stmt;

        try {
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
            Class.forName("com.mysql.jdbc.Driver");
        } catch(Exception e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }
        try {
            con = DriverManager.getConnection(url, "root", "admin");

            stmt = con.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            con.close();
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
