package com.model;

import java.time.LocalDateTime;

public class ParkingSlotBooking {

    private String bookingId;
    private ParkingSlot slot;
    private Vehicle vehicle;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    
    public ParkingSlotBooking(String bookingId, ParkingSlot slot, Vehicle vehicle,
                              LocalDateTime startTime, LocalDateTime endTime, String status) {
        this.bookingId = bookingId;
        this.slot = slot;
        this.vehicle = vehicle;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }
    public ParkingSlotBooking() {}

    
    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public void setSlot(ParkingSlot slot) {
        this.slot = slot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId +
               "\nSlot ID: " + (slot != null ? slot.getSlotId() : "null") +
               "\nVehicle ID: " + (vehicle != null ? vehicle.getVehicleId() : "null") +
               "\nStart Time: " + startTime +
               "\nEnd Time: " + endTime +
               "\nStatus: " + status;
    }
}
