package com.wg.banking.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.wg.banking.config.DatabaseConnection;

public abstract class GenericDAO<T> {
	
	private	Connection connection;
	
	public GenericDAO()  {
		try {
			this.connection = DatabaseConnection.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 

    protected abstract T mapResultSetToEntity(ResultSet resultSet) throws SQLException;

    public T get(String query) throws SQLException, ClassNotFoundException {
    	T entity = null;
    	try (PreparedStatement preparedStatement = connection.prepareStatement(query)) { 
    		ResultSet resultSet = preparedStatement.executeQuery();

    		if (resultSet.next()) {
                entity = mapResultSetToEntity(resultSet);    		
            }
       }
    	return entity;
    }
    
    public List<T> getAll(String query) throws SQLException, ClassNotFoundException {
    	List<T> entities = new ArrayList<>();
    	try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    		ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                entities.add(mapResultSetToEntity(resultSet));
            }
       }
    	return entities;
    }
    
    public boolean update(String query) throws SQLException, ClassNotFoundException {
    	try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			return preparedStatement.executeUpdate() > 0;
		}
    }
}
