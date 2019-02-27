package com.anna.coupons.beans;



import com.anna.coupons.enums.CouponType;

public class Coupon {

	private long couponId;
	private String couponTitle;
	private String couponStartDate;
	private String couponEndDate;
	private int couponAmount;
	private CouponType couponType;
	private String couponMessage;
	private int couponPrice;
	private String couponImage;
	private int companyId;

	public Coupon() {
	}

	public Coupon(String couponTitle, String couponStartDate, String couponEndDate, int couponAmount,
			CouponType couponType, String couponMessage, int couponPrice, String couponImage, int companyId) {
		this.couponTitle = couponTitle;
		this.couponStartDate = couponStartDate;
		this.couponEndDate = couponEndDate;
		this.couponAmount = couponAmount;
		this.couponType = couponType;
		this.couponMessage = couponMessage;
		this.couponPrice = couponPrice;
		this.couponImage = couponImage;
		this.companyId = companyId;
	}

	public Coupon(long couponId, String couponTitle, String couponStartDate, String couponEndDate, int couponAmount,
			CouponType couponType, String couponMessage, int couponPrice, String couponImage, int companyId) {
		this.couponId = couponId;
		this.couponTitle = couponTitle;
		this.couponStartDate = couponStartDate;
		this.couponEndDate = couponEndDate;
		this.couponAmount = couponAmount;
		this.couponType = couponType;
		this.couponMessage = couponMessage;
		this.couponPrice = couponPrice;
		this.couponImage = couponImage;
		this.companyId = companyId;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public String getCouponTitle() {
		return couponTitle;
	}

	public void setCouponTitle(String couponTitle) {
		this.couponTitle = couponTitle;
	}

	public String getCouponStartDate() {
		return couponStartDate;
	}

	public void setCouponStartDate(String couponStartDate) {
		this.couponStartDate = couponStartDate;
	}

	public String getCouponEndDate() {
		return couponEndDate;
	}

	public void setCouponEndDate(String couponEndDate) {
		this.couponEndDate = couponEndDate;
	}

	public int getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(int couponAmount) {
		this.couponAmount = couponAmount;
	}

	public String getCouponMessage() {
		return couponMessage;
	}

	public void setCouponMessage(String couponMessage) {
		this.couponMessage = couponMessage;
	}

	public int getCouponPrice() {
		return couponPrice;
	}

	public void setCouponPrice(int couponPrice) {
		this.couponPrice = couponPrice;
	}

	public String getCouponImage() {
		return couponImage;
	}

	public void setCouponImage(String couponImage) {
		this.couponImage = couponImage;
	}

//	public String getCouponType() {
//		return couponType.toString();
//	}

//	public void setCouponType(String couponType) {
//		this.couponType = CouponType.valueOf(couponType.toUpperCase());
//	}

	public int getCompanyId() {
		return companyId;
	}

	public CouponType getCouponType() {
		return couponType;
	}

	public void setCouponType(CouponType couponType) {
		this.couponType = couponType;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	

	@Override
	public String toString() {
		return "\n Coupon[couponId=" + couponId + ", couponTitle=" + couponTitle + ", couponStartDate="
				+ couponStartDate + ", couponEndDate=" + couponEndDate + ", couponAmount=" + couponAmount
				+ ", couponType=" + couponType + ", couponMessage=" + couponMessage + ", couponPrice=" + couponPrice
				+ ", couponImage=" + couponImage + ", companyId=" + companyId + "]";
	}
}
