package com.addressbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressDb {
	
	private static final Logger logger = LoggerFactory.getLogger(AddressDb.class);
	   
	
    private DataSource dataSource;

    public AddressDb(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addPerson(Person person) {
        String sql = "insert into AddressEntry values (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setLong(1, System.currentTimeMillis());
			statement.setString(2, person.getName());
			statement.setString(3, person.getPhoneNumber().getNumber());
			statement.executeUpdate();
        } catch (SQLException e) {
        	 logger.error("Error adding person: {}", e.getMessage(), e);
        }
    }

    public Person findPerson(String name) {
        String sql = "SELECT * FROM persons WHERE name = ?";
        try (Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Person(rs.getString("name"),rs.getString("phoneNumber"));
            }
        } catch (SQLException e) {
        	 logger.error("Error finding person: {}", e.getMessage(), e);
        }
        return null;
    }
}
