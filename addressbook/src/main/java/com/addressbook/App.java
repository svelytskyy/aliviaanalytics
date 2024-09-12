package com.addressbook;

import javax.sql.DataSource;

public class App {
    
	  public static void main(String[] args) {
	       DataSource dataSource = null;
	        try {
	            dataSource = DatabaseConnectionPool.getDataSource();
	            AddressDb addressDb = new AddressDb(dataSource);

	            // Adding a new person
	            Person newPerson = new Person("John Johnson", "07012345678");
	            addressDb.addPerson(newPerson);

	            // Finding a person
	            Person foundPerson = addressDb.findPerson("Doe");
	            if (foundPerson != null) {
	                System.out.println("Found: " + foundPerson.getName());
	            }
	        } catch (Exception e) {
	            e.printStackTrace();  // Log or handle the error properly
	        } finally {
	            // Ensure the DataSource is closed properly
	            if (dataSource != null) {
	                DatabaseConnectionPool.closeDataSource();
	            }
	        }
	    }
    
}
