
        import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class Customer {

    String url = "jdbc:mysql://127.0.0.1:3306/librarytest";

    public int add(String name, String address, String phone) {

        Connection con;
        Statement stmt;
        int ii=0;
        String query = "insert into CUSTOMER " +
                "values(0,'"+name+"','"+address+"','"+ phone+ "')";




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

            int count =stmt.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);


            try{
                ResultSet rr = stmt.getGeneratedKeys();

                rr.first();
                ii=rr.getInt(1);
                rr.close();}
            catch(SQLException eee ){
                eee.printStackTrace();
            }



            stmt.close();

            con.close();
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
        return ii;
    }

    public void backUp() {


        Connection con;
        Statement stmt;
        int ii=0;
        String query = "select * into outfile "+"'"+"c"+":"+"/"+"manas"+"/"+"Cust"+".txt"+"'"
                + " from customer" ;


        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }
        try {
            con = DriverManager.getConnection(url, "root", "admin");

            stmt = con.createStatement();

            stmt.executeQuery(query);
            stmt.close();

            con.close();
        }

        catch(SQLException eee ){
            eee.printStackTrace();
        }}

    public void delete(String name, String address, String phone) {
        Connection con;
        Statement stmt;
        String query = "delete from CUSTOMER " +
                "where name='"+name+"' and address='"+address+"' and phone='"+ phone+ "'";

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


    public void retrieve(String name, int cid) {


        Connection con;
        Statement stmt;
        String query1 = "select * from CUSTOMER where name like  '%"+name+"%'";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }
        try {
            con = DriverManager.getConnection(url, "root", "admin");



            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(query1);

            System.out.println("NAME ID ADDRESS PHONE");
            while (rs.next()) {
                String s = rs.getString("NAME");
                int i = rs.getInt("CID");
                String s1 = rs.getString("ADDRESS");
                String s2 = rs.getString("PHONE");
                System.out.println(s + " " + i +
                        " " + s1 + " " + s2);


            }
            stmt.close();
            con.close();
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

    public ArrayList retrieve(int cid) {


        Connection con;
        Statement stmt;
        ResultSet rs =null;
        ArrayList ab= new ArrayList();
        String query1 = "select * from CUSTOMER where cid ="+cid;

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

            System.out.println("NAME ID ADDRESS PHONE");
            if(rs!=null){
                while (rs.next()) {
                    String s = rs.getString("NAME");
                    int i = rs.getInt("CID");
                    String s1 = rs.getString("ADDRESS");
                    String s2 = rs.getString("PHONE");
                    System.out.println(s + " " + i +
                            " " + s1 + " " + s2);

                    ab.add(0,s);
                    ab.add(1,s1);
                    ab.add(2,s2);
                }
            }

            stmt.close();
            con.close();
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        return ab;
    }

    public void update(int cid, String name, String address, String phone) {


        Connection con;
        Statement stmt;
        String query = "update CUSTOMER SET name = '"+name+"' ,address='"+address+"' , phone='"+phone+ "'"+
                " where cid= "+cid;



        System.out.println(query);

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
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
