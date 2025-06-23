package com.service;

import com.management.BillingManagement;
import com.management.ParkingSlotBookingManagement;
import com.model.Billing;
import com.model.ParkingSlotBooking;
import com.util.ApplicationUtil;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class BillingService {

    private final BillingManagement billingManagement = new BillingManagement();
    private final ParkingSlotBookingManagement bookingManagement = new ParkingSlotBookingManagement();

    public void generateBill(String bookingId) {
        ParkingSlotBooking booking = bookingManagement.findBookingById(bookingId);
        if (booking == null) {
            System.out.println("❌ Booking ID not found.");
            return;
        }

        LocalDateTime startTime = booking.getStartTime();
        LocalDateTime endTime = booking.getEndTime();
        double slotPrice = booking.getSlot().getPrice(); // Price per hour
        long hours = Duration.between(startTime, endTime).toHours();
        if (hours <= 0) {
            System.out.println("❌ Invalid duration (must be at least 1 hour).");
            return;
        }

        double total = slotPrice * hours;
        LocalDateTime billDate = LocalDateTime.now();
        int billId = ApplicationUtil.generateBillId();

        Billing bill = new Billing(billId, bookingId, total, startTime, endTime, billDate);
        billingManagement.insertBill(bill);

        System.out.println("✅ Bill Generated");
        System.out.println("Bill ID: " + billId);
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Slot Price/hr: ₹" + slotPrice);
        System.out.println("Hours: " + hours);
        System.out.println("Total: ₹" + total);
    }

    public void viewAllBills() {
        List<Billing> bills = billingManagement.getAllBills();
        if (bills.isEmpty()) {
            System.out.println("No billing records found.");
        } else {
            for (Billing bill : bills) {
                System.out.println("Bill ID: " + bill.getBillId()
                        + ", Booking ID: " + bill.getBookingId()
                        + ", Total: ₹" + bill.getTotalBill()
                        + ", From: " + bill.getStartTime()
                        + ", To: " + bill.getEndTime()
                        + ", Billed On: " + bill.getBillDate());
            }
        }
    }
}
