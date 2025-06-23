package com.model;

import java.time.LocalDateTime;

public class Billing {

    private int billId;
    private String bookingId;
    private double totalBill;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime billDate;

    public Billing() {}

    public Billing(int billId, String bookingId, double totalBill,
                   LocalDateTime startTime, LocalDateTime endTime, LocalDateTime billDate) {
        this.billId = billId;
        this.bookingId = bookingId;
        this.totalBill = totalBill;
        this.startTime = startTime;
        this.endTime = endTime;
        this.billDate = billDate;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDateTime billDate) {
        this.billDate = billDate;
    }
}
