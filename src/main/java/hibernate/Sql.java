package hibernate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;


public class Sql {

    static Connection conn;
    String ip ="";
    int port ;
    String user="";
    String db="";
    private static final Logger logger = LogManager.getLogger(Sql.class);
    Sql() throws IOException, SQLException  {
        System.out.println("Start");
        conn = null;

        File file = new File("settings.xml");
        if (!file.exists()) {
            ip = "127.0.0.1";
            port = 3306;
            user = "root";
            db = "sport";
        }
        else {
            /*
            Scanner odczyt = new Scanner(file);

            odczyt.nextLine();
            odczyt.nextLine();
            String _xml = odczyt.nextLine();
            for (int i = 8; i < 17; i++) {
                ip += String.valueOf(_xml.charAt(i));
            }
            _xml = odczyt.nextLine();
            String s_port = "";
            for (int i = 10; i < 14; i++) {
                s_port += String.valueOf(_xml.charAt(i));
                //System.out.println(s_port);
            }
            port = Integer.parseInt(s_port);
            _xml = odczyt.nextLine();
            for (int i = 8; i < 13; i++) {
                db += String.valueOf(_xml.charAt(i));
                System.out.println(db);
            }
            _xml = odczyt.nextLine();
            for (int i = 10; i < 14; i++) {
                user += String.valueOf(_xml.charAt(i));
                System.out.println(user);
            }

             */
            ReadXMLFile xmlFile = new ReadXMLFile(file);//
            ip=xmlFile.read[0];
            //System.out.println(ip);
            port= Integer.parseInt(xmlFile.read[1]);
            //System.out.println(port);
            db = xmlFile.read[2];

           // System.out.println(db);
            user = xmlFile.read[3];

        //    System.out.println(user);
        }
       // user="root";
       // db ="sport";
      //"jdbc:mysql://127.0.0.1:3306/sport?" +" user=root & serverTimezone=UTC"

            String servsql = String.format("jdbc:mysql://%s:%d/%s?user=%s & serverTimezone=UTC",ip,port,db,user);
            conn =
                    DriverManager.getConnection(servsql);

            System.out.println("Jest połączenie :)");
    }

    void sqlend() throws SQLException {
        if(conn!=null)
        conn.close();
        System.out.println("koncze polaczenie");
    }
    static ResultSet select(String query) {
        System.out.println(query);
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {

            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return rs;
    }

       public static ArrayList<UserToString> getUser(String sql) {
        ArrayList<UserToString> user = new ArrayList<>();
        Statement stmt = null;
         String query="Select * from user";
         query+=sql;
        try {
            stmt = conn.createStatement();
            ResultSet rs = select(query);
            while (rs.next()) {
                UserToString dept = new UserToString(rs.getInt(1), rs.getString(2), rs.getString(3));
                user.add(dept);
                //System.out.println(dept);
            }
           // System.out.println(departments);
            return user;
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }

    public static String[] getCity() {

        Statement stmt = null;
        String query2 ="SELECT COUNT(*) FROM cities";
        int number=2;
        try {

            stmt = conn.createStatement();
            ResultSet rs = select(query2);
            while (rs.next()) {
                number = rs.getInt(1);
               // System.out.println(number);
            }
        }catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        String city[] = new String[number];

        String query="Select * from cities";
        try {
            stmt = conn.createStatement();
            ResultSet rs = select(query);
            int i=0;
            while (rs.next()) {
                city[i]=rs.getInt(1)+" " +rs.getString(2);
               // System.out.println(city[i]);
                 i++;
            }
            return city;
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }

    void insert(String query) throws SQLException {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    void update(String query) throws SQLException {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    void delete(String query) throws SQLException {
       // System.out.println(query);
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }
}
