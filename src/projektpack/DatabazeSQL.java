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

         stmt = conn.createStatement();
         String sql = "CREATE TABLE HranyFilm " + 
                 
				 "(JMENO          TEXT    NOT NULL, " +
                 " REZIE          TEXT    NOT NULL, " +
                 " ROK            REAL    NOT NULL, " +
                 " HERCI          TEXT    NOT NULL, " +
                 " HODNOCENI      TEXT" +
                 ")";
         String sql2 = "CREATE TABLE AnimovanyFilm "+
        		 "(JMENO          TEXT    NOT NULL, " +
                 " REZIE          TEXT    NOT NULL, " +
                 " ROK            REAL    NOT NULL, " +
                 " VEK            REAL    NOT NULL, " +
                 " ANIMATORI      TEXT    NOT NULL, " +
                 " HODNOCENI      TEXT" +
                 ")";
         stmt.executeUpdate(sql);
         stmt.executeUpdate(sql2);
         stmt.close();
         conn.close();
      } catch (Exception e) {
         System.err.println(e.getClass().getName() + ": " + e.getMessage());
         System.exit(0);
      }
}


     public static void vlozMapu(TreeMap<String,Film> mapa) {
    	 
        Connection conn = null;
        Statement stmt = null;
        try {
           Class.forName("org.sqlite.JDBC");
           conn = DriverManager.getConnection("jdbc:sqlite:Filmy.db");
           conn.setAutoCommit(false);
           

           stmt = conn.createStatement();
           for(Film film:mapa.values())
           {   
        
           if(film instanceof HranyFilm) 
           {
        	   
        	   String sql = "INSERT INTO HranyFilm (JMENO, REZIE, ROK, HERCI, HODNOCENI) " +
        	             "VALUES ('" + film.getNazev() + "', '" + film.getRezie() + "', " +
        	             film.getRok() + ", '" + ((HranyFilm) film).getHerciVypis() + "', '" + film.getHodnoceniVypis() + "')";

           
           stmt.executeUpdate(sql);
           }
           else if(film instanceof AnimovanyFilm) 
           {
        	   
        	   String sql2 = "INSERT INTO AnimovanyFilm (JMENO, REZIE, ROK, VEK, ANIMATORI, HODNOCENI) " +
        	             "VALUES ('" + film.getNazev() + "', '" + film.getRezie() + "', " +
        	             film.getRok() + ", '" + ((AnimovanyFilm) film).getDoporucenyVek() + "', '" + ((AnimovanyFilm) film).getAnimatoriVypis() + "', '" + film.getHodnoceniVypis() + "')";
        	   
           
           stmt.executeUpdate(sql2);
           
           }
           }
           stmt.close();
           conn.commit();
           conn.close();
        } catch (Exception e) {
           System.err.println(e.getClass().getName() + ": " + e.getMessage());
           System.exit(0);
        }
        
     
  }

 

     public static void nactiData(DatabazeFilmu databaze) {
        Connection conn = null;
        Statement stmt = null;
        try {
           Class.forName("org.sqlite.JDBC");
           conn = DriverManager.getConnection("jdbc:sqlite:Filmy.db");
           conn.setAutoCommit(false);
 

           stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery( "SELECT * FROM HranyFilm;" );
           while ( rs.next() ) {             
              HranyFilm hfilm = new HranyFilm(rs.getString("JMENO"),rs.getString("REZIE"),rs.getInt("ROK"),rs.getString("HERCI"),rs.getString("HODNOCENI"));
              databaze.addFilm(hfilm);
           }
           ResultSet rs2 = stmt.executeQuery( "SELECT * FROM AnimovanyFilm;" );
           while ( rs2.next() ) {              
               AnimovanyFilm afilm = new AnimovanyFilm(rs2.getString("JMENO"),rs2.getString("REZIE"),rs2.getInt("ROK"),rs2.getInt("VEK"),rs2.getString("ANIMATORI"),rs2.getString("HODNOCENI"));
               databaze.addFilm(afilm);
            }
           rs.close();
           stmt.close();
           conn.close();
        } catch (Exception e) {
           System.err.println(e.getClass().getName() + ": " + e.getMessage());
           System.exit(0);
        }
     }
  

    	    public static void SmazatObsahDatabaze() {
    	        Connection conn = null;
    	        Statement stmt = null;
    	        try {
    	            Class.forName("org.sqlite.JDBC");
    	            conn = DriverManager.getConnection("jdbc:sqlite:Filmy.db");    	        
    	            stmt = conn.createStatement();
    	            String sql = "DELETE FROM HranyFilm";
    	            stmt.executeUpdate(sql);
    	            String sql2 = "DELETE FROM AnimovanyFilm";
    	            stmt.executeUpdate(sql2);    	            
    	            stmt.close();
    	            conn.close();
    	        } catch (Exception e) {
    	            System.err.println(e.getClass().getName() + ": " + e.getMessage());
    	            System.exit(0);
    	        }   	        
    	    
    	}
}