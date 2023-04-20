package projektpack;
import java.sql.*;
import java.util.TreeMap;

public class DatabazeSQL {
  public static void vytvorSQL(){
      Connection conn = null;
      Statement stmt = null;
      try {
         Class.forName("org.sqlite.JDBC");
         conn = DriverManager.getConnection("jdbc:sqlite:Filmy.db");
         System.out.println("Opened database successfully");

         stmt = conn.createStatement();
         String sql = "CREATE TABLE Film " +
                        "(ID INT PRIMARY KEY     NOT NULL," + //Dokončit, vypisHercu
                        " NAME           TEXT    NOT NULL, " +
                        " REZISER        TEXT    NOT NULL, " +
                        " HODNOCENI      REAL)";
         stmt.executeUpdate(sql);
         stmt.close();
         conn.close();
      } catch (Exception e) {
         System.err.println(e.getClass().getName() + ": " + e.getMessage());
         System.exit(0);
      }
      System.out.println("Table created successfully");
   //}
}
  //import java.sql.*;

     public static void vlozMapu(TreeMap<String,Film> mapa) {
    	 
        Connection conn = null;
        Statement stmt = null;
        try {
           Class.forName("org.sqlite.JDBC");
           conn = DriverManager.getConnection("jdbc:sqlite:Filmy.db");
           conn.setAutoCommit(false);
           System.out.println("Opened database successfully");

           stmt = conn.createStatement();
           for(Film film:mapa.values())
           {   
           if(film instanceof HranyFilm) //for each Film z mapy
           {
        	   System.out.println("test");
           String sql = "INSERT INTO Film (JMENO, REZISER, ROK, HERCI, HODNOCENI) " +
                          "VALUES (film.getName(), film.getRezie(), film.getRok(), 'test', film.getVypisHodnoceni());";
           
           stmt.executeUpdate(sql);
           }
           }
           stmt.close();
           conn.commit();
           conn.close();
        } catch (Exception e) {
           System.err.println(e.getClass().getName() + ": " + e.getMessage());
           System.exit(0);
        }
        System.out.println("Records created successfully");
     
  }

  //import java.sql.*;

     public static void nactiData() {
        Connection conn = null;
        Statement stmt = null;
        try {
           Class.forName("org.sqlite.JDBC");
           conn = DriverManager.getConnection("jdbc:sqlite:Filmy.db");
           conn.setAutoCommit(false);
           System.out.println("Opened database successfully");

           stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery( "SELECT * FROM Film;" );
           while ( rs.next() ) {
              int id = rs.getInt("ID");
              String name = rs.getString("NAME");
              String reziser = rs.getString("REZISER");
              double hodnoceni = rs.getDouble("HODNOCENI");
              System.out.println( "ID = " + id );
              System.out.println( "NAME = " + name );
              System.out.println( "REZISER = " + reziser );
              System.out.println( "HODNOCENI = " + hodnoceni );
              System.out.println();
           }
           rs.close();
           stmt.close();
           conn.close();
        } catch (Exception e) {
           System.err.println(e.getClass().getName() + ": " + e.getMessage());
           System.exit(0);
        }
        System.out.println("Operation done successfully");
     }
  
}