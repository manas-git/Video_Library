 import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class Video {

    String url = "jdbc:mysql://127.0.0.1:3306/librarytest";

    public void add(String Title,String Year,String Genre,String Producer) {


        Connection con;
        Statement stmt;
        String query = "insert into Video " +
                "values(0,'"+Title+"','"+Year+"','"+Genre+"','"+Producer+"')";

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }
        try {
            con = DriverManager.getConnection(url, "root", "admin");

            stmt = con.createStatement();
            stmt.executeUpdate(query);


            stmt.close();
            con.close();
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

    public ArrayList retrieve(int VID,String Title) {


        Connection con;
        Statement stmt;
        ResultSet rs =null;
        ArrayList ab= new ArrayList();

        String query1 = "select * from Video where Title like  '%"+Title+"%' and VID = "+VID ;

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }
        try {
            con = DriverManager.getConnection(url, "root", "admin");

            stmt = con.createStatement();

            rs = stmt.executeQuery(query1);

            System.out.println(" Title Year Genre Producer");
            if(rs!=null){
                while (rs.next()) {
                    int i = rs.getInt("VID");
                    String s = rs.getString("Title");
                    //int i = rs.getInt("C_ID");
                    String s1 = rs.getString("Year");
                    String s2 = rs.getString("Genre");
                    String s3 = rs.getString("Producer");

                    System.out.println(s + " " + s1 +
                            " " + s2 + " " + s3);

                    ab.add(0,s);
                    ab.add(1,s1);
                    ab.add(2,s2);
                    ab.add(3,s3);

                }
            }
            stmt.close();
            con.close();
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return ab;
    }

    public void update(int VID, String Title,String Year,String Genre,String Producer) {


        Connection con;
        Statement stmt;


        String query = "Update VIDEO set Title='"+Title+"' , Year='"+Year+"', genre = '"+Genre+"' , "+
                " Producer='"+Producer+"' where vid="+VID;
        System.out.println(query);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //

        } catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }
        try {
            con = DriverManager.getConnection(url, "root", "admin");

            stmt = con.createStatement();
            stmt.executeUpdate(query);


            stmt.close();
            con.close();
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }


    public void delete(int VID) {


        Connection con;
        Statement stmt;


        String query = "delete from VIDEO " +
                "where vid="+VID;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //

        } catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }
        try {
            con = DriverManager.getConnection(url, "root", "admin");

            stmt = con.createStatement();
            stmt.executeUpdate(query);


            stmt.close();
            con.close();
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
}
