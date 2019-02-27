package com.anna.coupons.api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anna.coupons.beans.Coupon;
import com.anna.coupons.controller.CouponController;
import com.anna.coupons.enums.CouponType;
import com.anna.coupons.exceptions.ApplicationException;
import com.anna.coupons.utils.CookieUtils;

@RestController
@RequestMapping("/coupons")
public class CouponApi {

	@Autowired
	private CouponController couponController;
	
	@GetMapping
	public ArrayList<Coupon> getAllCoupons() throws ApplicationException {
		return (ArrayList<Coupon>) couponController.getAllCoupons();
	}

	@GetMapping
	@RequestMapping("/{couponId}")
	public Coupon getCoupon(@PathVariable("couponId") long couponId) throws ApplicationException {
		return couponController.getCoupon(couponId);
	}

	@DeleteMapping
	@RequestMapping("/byCouponId/{couponId}")
	public void deleteCoupon(@PathVariable("couponId") long couponId) throws ApplicationException {
		couponController.deleteCoupon(couponId);
	}

	@DeleteMapping
	@RequestMapping("/byCompany/{companyId}")
	public void deleteCouponByCompany(@PathVariable("companyId") long companyId) throws ApplicationException {
		couponController.deleteCouponByCompany(companyId);
	}
	
	@DeleteMapping
	//@RequestMapping("/byExpiration")
	public void deleteExpiredCoupons() throws ApplicationException {
		couponController.deleteExpiredCoupons();
	}

	@DeleteMapping
	@RequestMapping("/byCouponID/{couponId}")
	public void deleteCouponFromCouponCustomer(@PathVariable("couponId") long couponId) throws ApplicationException {
		couponController.deleteCouponFromCouponCustomer(couponId);
	}

	@DeleteMapping
	@RequestMapping("/byEndDate/{couponEndDate}")
	public void deleteCouponsByEndDate(@PathVariable("couponEndDate") String couponEndDate) throws ApplicationException {
		couponController.deleteCouponsByEndDate(couponEndDate);
	}

	@PostMapping
	public void createCoupon(@RequestBody Coupon coupon) throws ApplicationException, SQLException {
		couponController.createCoupon(coupon);
	}

	@PutMapping
	public void updateCoupon(@RequestBody Coupon coupon) throws ApplicationException {
		couponController.updateCoupon(coupon);
	}

	@GetMapping
	@RequestMapping("/byCouponType/{couponType}")
	public ArrayList<Coupon> getCouponByCouponType(@PathVariable("couponType") CouponType couponType)
			throws ApplicationException {
		return (ArrayList<Coupon>) couponController.getCouponByCouponType(couponType);
	}

	@GetMapping
	@RequestMapping("/upToPrice/{couponPrice}")
	public List<Coupon> getCouponsUpToPrice(@PathVariable("couponPrice") int couponPrice) throws ApplicationException {
		return couponController.getCouponsUpToPrice(couponPrice);
	}

	@GetMapping
	@RequestMapping("/upToDate/{couponEndDate}")
	public List<Coupon> getCouponsUpToDate(@PathVariable("couponEndDate") String couponEndDate)
			throws ApplicationException {
		return couponController.getCouponsUpToDate(couponEndDate);
	}

	@GetMapping
	@RequestMapping("/byExpiration")
	public List<Coupon> getAllExpiredCoupons() throws ApplicationException {
		return couponController.getAllExpiredCoupons();
	}

	@GetMapping
	@RequestMapping("/byCustomerId/{customerId}/")
	public List<Coupon> getCouponByCustomerId(@PathVariable("customerId") long customerId) throws ApplicationException {
		return couponController.getCouponByCustomerId(customerId);
	}

	@GetMapping
	@RequestMapping("/byCompanyId/{companyId}")
	public List<Coupon> getCouponByCompanyId(@PathVariable("companyId") long companyId) throws ApplicationException {
		return couponController.getCouponByCompanyId(companyId);
	}

	@PostMapping
	@RequestMapping("/purchase/{couponId}")
	public void purchase(@PathVariable long couponId, HttpServletRequest request)
			throws ApplicationException {
		long customerId = 0;
		String StrCustomerId = CookieUtils.getCookieValue(request, "login");
		customerId = Long.parseLong(StrCustomerId);
		couponController.purchase(customerId, couponId);
	}

}
