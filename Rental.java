

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class Rental {

    String url = "jdbc:mysql://127.0.0.1:3306/librarytest";

    public void add(int cid,int vid,String Duedate) {

        Connection con;
        Statement stmt;
        String query = "insert into Rental " +
                "values("+cid+","+vid+",'"+Duedate+"')";

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


    public void add(int cid,int vid) {

        Connection con;
        Statement stmt;
        String query = "insert into Rental " +
                "values("+cid+","+vid+",date_add(curdate(),interval 7 day))";

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

    public ArrayList retrieve(int CID, int VID) {
        ResultSet rs =null;
        ArrayList ab= new ArrayList();

        Connection con;
        Statement stmt;
        String query1 = "select * from Rental where VID like  '%"+VID+"%' and CID = "+CID ;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Class.forName("org.gjt.mm.mysql.Driver").newInstance();


        } catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }
        try {
            con = DriverManager.getConnection(url, "root", "admin");




            stmt = con.createStatement();

            rs = stmt.executeQuery(query1);

            System.out.println("Duedate");
            if (rs!=null){
                while (rs.next()) {


                    int i = rs.getInt("CID");
                    String s1 = rs.getString("VID");
                    String s2 = rs.getString("Duedate");

                    System.out.println(s2);
                    ab.add(0,s2);

                }
            }
            stmt.close();
            con.close();
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return ab;
    }


    public ArrayList retrieveOverdue() {
        ResultSet rs =null;
        ArrayList ab= new ArrayList();
        Connection con;
        Statement stmt;
        String query1 = "select c.Name,v.Title, DueDate from Rental r, video v, customer c where r.vid=v.vid and r.cid=c.cid and duedate<curdate()";



        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Class.forName("org.gjt.mm.mysql.Driver").newInstance();


        } catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }
        try {
            con = DriverManager.getConnection(url, "root", "admin");




            stmt = con.createStatement();

            rs = stmt.executeQuery(query1);

            System.out.println("Duedate");
            if (rs!=null){
                while (rs.next()) {


                    //int i = rs.getInt("CID");
                    String i = rs.getString("Name");
                    String s1 = rs.getString("Title");
                    String s2 = rs.getString("Duedate");
                    ab.add(i);
                    ab.add(s1);
                    ab.add(s2);


                }
            }
            stmt.close();
            con.close();
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return ab;
    }

    public void update(int cid,int vid,String Duedate) {


        Connection con;
        Statement stmt;
        String query = "update RENTAL set DueDate ='"+Duedate+"'" +
                "  where cid="+cid +" and vid= "+vid;



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

    public void delete(int cid,int vid,String Duedate) {


        Connection con;
        Statement stmt;


        String query = "delete from RENTAL " +
                "where vid="+ vid+" and cid="+cid+ " and duedate='"+Duedate+"'";
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
