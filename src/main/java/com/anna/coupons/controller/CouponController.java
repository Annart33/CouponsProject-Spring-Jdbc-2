package com.anna.coupons.controller;

import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.anna.coupons.beans.Coupon;
import com.anna.coupons.dao.CouponDao;
import com.anna.coupons.enums.CouponType;
import com.anna.coupons.enums.ErrorType;
import com.anna.coupons.exceptions.ApplicationException;
import com.anna.coupons.utils.DateUtils;

@Controller
public class CouponController {

	@Autowired
	private CouponDao couponDao;

	public long createCoupon(Coupon coupon) throws ApplicationException {
		validateCreateCoupon(coupon);

		return this.couponDao.createCoupon(coupon);
	}

	private void validateCreateCoupon(Coupon coupon) throws ApplicationException {
		if (this.couponDao.isCouponExistByTitle(coupon.getCouponTitle())) {
			throw new ApplicationException(ErrorType.ALREADY_EXISTS, "This coupon already exists");
		}

	}

	public Coupon getCoupon(long couponId) throws ApplicationException {
		return this.couponDao.getCoupon(couponId);
	}

	public void deleteCoupon(long couponId) throws ApplicationException {
		couponDao.deleteCoupon(couponId);
	}

	public void deleteCouponByCompany(long companyId) throws ApplicationException {
		couponDao.deleteCouponByCompany(companyId);
	}

	public void updateCoupon(Coupon coupon) throws ApplicationException {
		couponDao.updateCoupon(coupon);
	}

	public List<Coupon> getAllCoupons() throws ApplicationException {
		return this.couponDao.getAllCoupons();
	}

	public List<Coupon> getCouponByCouponType(CouponType couponType) throws ApplicationException {
		return this.couponDao.getCouponByCouponType(couponType);
	}

	public List<Coupon> getCouponsUpToPrice(int couponPrice) throws ApplicationException {
		return this.couponDao.getCouponsUpToPrice(couponPrice);
	}

	public List<Coupon> getCouponsUpToDate(String couponEndDate) throws ApplicationException {
		return this.couponDao.getCouponUpToDate(couponEndDate);
	}

	public List<Coupon> getAllExpiredCoupons() throws ApplicationException {
		return this.couponDao.getAllExpiredCoupons();
	}

	public List<Coupon> getCouponByCustomerId(long customerId) throws ApplicationException {
		return this.couponDao.getCouponByCustomerId(customerId);
	}

	public List<Coupon> getCouponByCompanyId(long companyId) throws ApplicationException {
		return this.couponDao.getCouponByCompanyId(companyId);
	}

	public void isCouponExistByTitle(String couponTitle) throws ApplicationException {
		if (this.couponDao.isCouponExistByTitle(couponTitle) == false) {
			throw new ApplicationException(ErrorType.INVALID_PARMETER, "Coupon doesn't exist by this title");
		}
		couponDao.isCouponExistByTitle(couponTitle);

	}

	public void deleteExpiredCoupons() throws ApplicationException {

		GregorianCalendar today = new GregorianCalendar();

		String todayStr = DateUtils.dateToStr(today);

		this.couponDao.deleteCouponsByEndDate(todayStr);

	}

	public void deleteCouponFromCouponCustomer(long couponId) throws ApplicationException {
		couponDao.deleteCouponFromCouponCustomer(couponId);
	}

	public void deleteCouponsByEndDate(String couponEndDate) throws ApplicationException {
		couponDao.deleteCouponsByEndDate(couponEndDate);

	}

	public void purchase(long customerId, long couponId) throws ApplicationException {
		this.couponDao.purchase(customerId, couponId);
	}

}
