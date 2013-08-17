
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class CreateCustomer {
    public static void main(String args[]) {

        String url ="jdbc:mysql://127.0.0.1:3306/librarytest";

        Connection con;
        String createString;
        createString = "create table CUSTOMER (CID INTEGER UNSIGNED NOT NULL AUTO_INCREMENT, " +" NAME varchar(30), " +
                "ADDRESS varchar(150), " +
                "PHONE varchar(10) ," +
                " PRIMARY KEY (`CID`))";


        //String query1 = "select * from Rental where CID="+CID+" and VID="+VID;

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

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
