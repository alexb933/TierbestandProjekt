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
	         String sql = "INSERT INTO bestandu30 (id,anzahl,code, hinweis, datum, bestand) "
	            + "VALUES (1,200,1, 'Zugang', current_timestamp, 5);";
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
