package de.alexanderbeck.TierbestandProjekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class addValue_class {

	public static void main(String args[]) {
	      Connection c = null;
	      Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/postgres",
	            "postgres", "mbtrac1600!");
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = "INSERT INTO bestand_u_30 (id,anzahl,zuab,datum) "
	            + "VALUES (2,200,1,current_timestamp);";
	         stmt.executeUpdate(sql);



	         stmt.close();
	         c.commit();
	         c.close();
	      } catch (Exception e) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Records created successfully");
	   }


}
