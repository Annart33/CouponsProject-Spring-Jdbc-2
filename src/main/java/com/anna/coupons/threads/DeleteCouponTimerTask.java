
package com.anna.coupons.threads;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import com.anna.coupons.controller.CouponController;
import com.anna.coupons.exceptions.ApplicationException;

public class DeleteCouponTimerTask extends TimerTask {

	public void run() {

		try {
			CouponController couponController = new CouponController();
			couponController.deleteExpiredCoupons();
			System.out.println("Coupons deleted successfully");

		} catch (ApplicationException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("\n Task finished at: " + new java.util.Date() + "\n");
		}

	}

	public static void startDeletingCoupons() {
		GregorianCalendar gc = new GregorianCalendar();

		gc.set(Calendar.HOUR, 00);
		gc.set(Calendar.MINUTE, 00);
		gc.set(Calendar.SECOND, 00);

		gc.add(Calendar.DAY_OF_MONTH, 1);

		Timer timer = new Timer();

		timer.schedule(new DeleteCouponTimerTask(), gc.getTime(), 1000 * 60 * 60 * 24);

	}
}