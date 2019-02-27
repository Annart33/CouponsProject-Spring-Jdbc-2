package com.anna.coupons.enums;

public enum CouponType {

	ELECTRICITY("ELECTRICITY"), FOOD("FOOD"), VACATION("VACATION");

	private final String couponType;

	private CouponType(String couponType) {
		this.couponType = couponType;
	}

	public boolean equalsName(String otherCouponType) {
		return couponType.equals(otherCouponType);
	}

	public String toString() {
		return this.couponType;
	}

	public String getCouponType() {
		return couponType;
	}
	
	

}