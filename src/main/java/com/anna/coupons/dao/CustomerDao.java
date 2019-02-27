package com.anna.coupons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.anna.coupons.beans.Customer;
import com.anna.coupons.enums.ErrorType;
import com.anna.coupons.exceptions.ApplicationException;
import com.anna.coupons.utils.JdbcUtils;

@Repository
public class CustomerDao {

	public void createCustomer(Customer customer) throws ApplicationException {

		java.sql.PreparedStatement preparedStatement = null;
		Connection connection = null;

		try {
			connection = JdbcUtils.getConnection();

			String sql = "insert into customer (customerId, customerName, customerPassword, customerEmail) values (?,?,?,?)";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setLong(1, customer.getCustomerId());
			preparedStatement.setString(2, customer.getCustomerName());
			preparedStatement.setString(3, customer.getCustomerPassword());
			preparedStatement.setString(4, customer.getCustomerEmail());

			preparedStatement.executeUpdate();
		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in customerDao, createCustomer(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}

	public Customer getCustomer(long customerId) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Customer customer = null;

		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT * FROM customer WHERE customerId = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, customerId);
			resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next()) {
				return null;
			}
			customer = extractCustomerFromResultSet(resultSet);
			System.out.println(customer);

		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in customerDao, getCustomer(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return customer;
	}

	private Customer extractCustomerFromResultSet(ResultSet resultSet) throws SQLException {
		Customer customer = new Customer();
		customer.setCustomerId(resultSet.getLong("customerId"));
		customer.setCustomerName(resultSet.getString("customerName"));
		customer.setCustomerPassword(resultSet.getString("customerPassword"));
		customer.setCustomerEmail(resultSet.getString("customerEmail"));

		return customer;
	}

	public void deleteCustomer(long customerId) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = JdbcUtils.getConnection();
			String sql = "DELETE FROM customer WHERE customerId = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, customerId);
			preparedStatement.executeUpdate();
		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in customerDao, deleteCustomer(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}

	}

	public void updateCustomer(Customer customer) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = JdbcUtils.getConnection();
			String sql = "UPDATE customer SET customerName = ?, customerPassword = ?, customerEmail = ? WHERE customerId = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getCustomerName());
			preparedStatement.setString(2, customer.getCustomerPassword());
			preparedStatement.setString(3, customer.getCustomerEmail());
			preparedStatement.setLong(4, customer.getCustomerId());

			preparedStatement.executeUpdate();
		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in customerDao, updateCustomer(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}

	}

	public List<Customer> getAllCustomers() throws ApplicationException {

		List<Customer> customerList = new ArrayList<>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Customer customer = null;

		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT * FROM customer";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				customer = extractCustomerFromResultSet(resultSet);
				customerList.add(customer);
			}
		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in customerDao, getAllCustomers(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return customerList;
	}

	public Long login(String customerEmail, String customerPassword) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		long customerId = 0;

		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT customerId FROM customer WHERE customerEmail = ? AND customerPassword = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customerEmail);
			preparedStatement.setString(2, customerPassword);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				customerId = resultSet.getLong("customerId");
			} else {
				return null;
			}
		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in customerDao, login(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return customerId;
	}

	public boolean isCustomerExistByEmail(String customerEmail) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int counter = 0;

		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT * FROM customer WHERE customerEmail = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customerEmail);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				counter++;
			}
		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in customerDao, isCustomerExistByEmail(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}

		if (counter != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isCustomerExistByName(String customerName) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int counter = 0;

		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT * FROM customer WHERE customerName = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customerName);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				counter++;
			}
		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in customerDao, isCustomerExistByEmail(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}

		if (counter != 0) {
			return true;
		} else {
			return false;
		}
	}

}