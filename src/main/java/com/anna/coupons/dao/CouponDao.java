package com.anna.coupons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.anna.coupons.beans.Coupon;
import com.anna.coupons.enums.CouponType;
import com.anna.coupons.enums.ErrorType;
import com.anna.coupons.exceptions.ApplicationException;
import com.anna.coupons.utils.JdbcUtils;

@Repository
public class CouponDao {

	public Long createCoupon(Coupon coupon) throws ApplicationException {

		java.sql.PreparedStatement preparedStatement = null;
		Connection connection = null;

		try {
			connection = JdbcUtils.getConnection();

			String sql = "insert into coupon (couponTitle, couponStartDate, couponEndDate, couponAmount, couponType, couponMessage, couponPrice ,couponImage, companyId) values (?,?,?,?,?,?,?,?,?)";

			preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, coupon.getCouponTitle());
			preparedStatement.setString(2, coupon.getCouponStartDate());
			preparedStatement.setString(3, coupon.getCouponEndDate());
			preparedStatement.setInt(4, coupon.getCouponAmount());
			preparedStatement.setString(5, coupon.getCouponType().name());
			preparedStatement.setString(6, coupon.getCouponMessage());
			preparedStatement.setInt(7, coupon.getCouponPrice());
			preparedStatement.setString(8, coupon.getCouponImage());
			preparedStatement.setLong(9, coupon.getCompanyId());

			preparedStatement.executeUpdate();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {

				long couponId = resultSet.getLong(1);
				System.out.println(couponId);
				return couponId;
			}
			return null;
		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in couponDao, createCoupon(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public Coupon getCoupon(long couponId) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = null;

		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT * FROM coupon WHERE couponId = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, couponId);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			coupon = extractCouponFromResultSet(resultSet);

		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in couponDao, getCoupon(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return coupon;
	}

	private Coupon extractCouponFromResultSet(ResultSet resultSet) throws SQLException {
		Coupon coupon = new Coupon();
		coupon.setCouponId(resultSet.getLong("couponId"));
		coupon.setCouponTitle(resultSet.getString("couponTitle"));
		coupon.setCouponStartDate(resultSet.getString("couponStartDate"));
		coupon.setCouponEndDate(resultSet.getString("couponEndDate"));
		coupon.setCouponAmount(resultSet.getInt("couponAmount"));
		coupon.setCouponType(CouponType.valueOf(resultSet.getString("couponType")));
		coupon.setCouponMessage(resultSet.getString("couponMessage"));
		coupon.setCouponPrice(resultSet.getInt("couponPrice"));
		coupon.setCouponImage(resultSet.getString("couponImage"));
		coupon.setCompanyId(resultSet.getInt("companyId"));
		return coupon;
	}

	public void deleteCoupon(long couponId) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			deleteCouponFromCouponCustomer(couponId);
			connection = JdbcUtils.getConnection();
			String sql = "DELETE FROM coupon WHERE couponId = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, couponId);
			preparedStatement.executeUpdate();
		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in couponDao, deleteCoupon(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}

	}

	public void deleteCouponFromCouponCustomer(long couponId) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = JdbcUtils.getConnection();
			String sql = "DELETE FROM customer_coupon WHERE couponId = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, couponId);
			preparedStatement.executeUpdate();
		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in couponDao, deleteCouponFromCouponCustomer(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}

	}

	public void deleteCouponByCompany(long companyId) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = JdbcUtils.getConnection();
			String sql = "DELETE FROM coupon WHERE companyId = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, companyId);
			preparedStatement.executeUpdate();
		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in couponDao, deleteCouponByCompany(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}

	}

	public void deleteCouponsByEndDate(String couponEndDate) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = JdbcUtils.getConnection();
			String sql = "DELETE FROM coupon WHERE couponEndDate < ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, couponEndDate);
			preparedStatement.executeUpdate();
		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in couponDao, deleteCouponsByEndDate(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	public void updateCoupon(Coupon coupon) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = JdbcUtils.getConnection();
			String sql = "UPDATE coupon SET couponTitle = ?, couponStartDate = ?, couponEndDate = ?, couponAmount = ?, couponType = ?, couponMessage = ?, couponPrice = ?, couponImage = ?, companyId = ? WHERE couponId = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, coupon.getCouponTitle());
			preparedStatement.setString(2, coupon.getCouponStartDate());
			preparedStatement.setString(3, coupon.getCouponEndDate());
			preparedStatement.setInt(4, coupon.getCouponAmount());
			preparedStatement.setString(5, coupon.getCouponType().name());
			preparedStatement.setString(6, coupon.getCouponMessage());
			preparedStatement.setInt(7, coupon.getCouponPrice());
			preparedStatement.setString(8, coupon.getCouponImage());
			preparedStatement.setLong(9, coupon.getCompanyId());
			preparedStatement.setLong(10, coupon.getCouponId());
			preparedStatement.executeUpdate();
		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in couponDao, updateCoupon(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}

	}

	public List<Coupon> getAllCoupons() throws ApplicationException {

		List<Coupon> couponList = new ArrayList<>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = null;

		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT * FROM coupon";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				coupon = extractCouponFromResultSet(resultSet);
				couponList.add(coupon);
			}
		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in couponDao, getAllCoupons(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return couponList;

	}

	public List<Coupon> getCouponByCouponType(CouponType couponType) throws ApplicationException {

		List<Coupon> couponTypeList = new ArrayList<>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = null;

		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT * FROM coupon WHERE couponType = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, couponType.toString());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				coupon = extractCouponFromResultSet(resultSet);
				couponTypeList.add(coupon);
			}

		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in couponDao, getCouponByCouponType(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return couponTypeList;
	}

	public List<Coupon> getCouponsUpToPrice(int couponPrice) throws ApplicationException {

		List<Coupon> couponPriceList = new ArrayList<>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = null;

		try {

			connection = JdbcUtils.getConnection();
			String sql = "SELECT * FROM coupon WHERE couponPrice <= ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, couponPrice);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				coupon = extractCouponFromResultSet(resultSet);
				couponPriceList.add(coupon);
			}

		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in couponDao, getCouponUpToPrice(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return couponPriceList;
	}

	public List<Coupon> getCouponUpToDate(String couponEndDate) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = null;
		List<Coupon> allCouponsByDate = new ArrayList<>();

		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT * FROM coupon WHERE couponEndDate <= ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, couponEndDate);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				coupon = extractCouponFromResultSet(resultSet);
				allCouponsByDate.add(coupon);
			}

		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in couponDao, getCouponUpToDate(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return allCouponsByDate;
	}

	public List<Coupon> getAllExpiredCoupons() throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = null;
		List<Coupon> allExpiredCoupons = new ArrayList<>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.now();
		String currentTime = dtf.format(localDate);

		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT * FROM coupon WHERE couponEndDate < ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, currentTime);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				coupon = extractCouponFromResultSet(resultSet);
				allExpiredCoupons.add(coupon);
			}

		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in couponDao, getAllExpiredCoupons(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return allExpiredCoupons;
	}

	public List<Coupon> getCouponByCustomerId(long customerId) throws ApplicationException {

		List<Coupon> couponByCustomerId = new ArrayList<>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = null;

		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT couponTitle, c.couponId, companyId, couponStartDate, couponEndDate, couponAmount, couponType, couponMessage, couponPrice, couponImage "
					+ " FROM database.coupon c " + " INNER JOIN database.customer_coupon cc ON c.couponId=cc.couponId "
					+ " WHERE cc.customerId =?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, customerId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				coupon = extractCouponFromResultSet(resultSet);
				couponByCustomerId.add(coupon);
			}

		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in couponDao, getCouponByCustomerId(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return couponByCustomerId;

	}

	public List<Coupon> getCouponByCompanyId(long companyId) throws ApplicationException {

		List<Coupon> couponByCompanyId = new ArrayList<>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = null;

		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT * FROM coupon WHERE companyId = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, companyId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				coupon = extractCouponFromResultSet(resultSet);
				couponByCompanyId.add(coupon);
			}

		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in couponDao, getCouponByCompanyId(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return couponByCompanyId;
	}

	public boolean isCouponExistByTitle(String couponTitle) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT * FROM coupon WHERE couponTitle = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, couponTitle);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return true;
			}

		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in couponDao, isCouponExistByTitle(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return false;

	}

	public void purchase(long customerId, long couponId) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			String sql = "INSERT INTO customer_coupon (couponId, customerId)  VALUES (?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, couponId);
			preparedStatement.setLong(2, customerId);
			preparedStatement.executeUpdate();
		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in couponDao, purchase(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement);

		}
	}

}
