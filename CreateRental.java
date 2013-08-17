
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class CreateRental {
    public static void main(String args[]) {

        String url =	"jdbc:mysql://127.0.0.1:3306/librarytest";

        Connection con;
        String createString;
        createString = "Create table Rental  (CID INTEGER , " +" VID INTEGER , " +
                "DueDate varchar(150))" ;

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
