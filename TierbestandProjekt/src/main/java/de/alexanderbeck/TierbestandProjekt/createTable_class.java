package de.alexanderbeck.TierbestandProjekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class createTable_class {

	   public static void main( String args[] ) {
		      Connection c = null;
		      Statement stmt = null;
		      try {
		         Class.forName("org.postgresql.Driver");
		         c = DriverManager
		            .getConnection("jdbc:postgresql://localhost:5432/postgres",
		            "postgres", "mbtrac1600!");
		         System.out.println("Opened database successfully");

		         stmt = c.createStatement();
		         String sql = "CREATE TABLE COMPANY " +
		            "(ID INT PRIMARY KEY     NOT NULL," +
		            " NAME           TEXT    NOT NULL, " +
		            " AGE            INT     NOT NULL, " +
		            " ADDRESS        CHAR(50), " +
		            " SALARY         REAL)";
		         stmt.executeUpdate(sql);
		         stmt.close();
		         c.close();
		      } catch ( Exception e ) {
		         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		         System.exit(0);
		      }
		      System.out.println("Table created successfully");
		   }

}
