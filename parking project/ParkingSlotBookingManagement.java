package com.management;

import com.model.ParkingSlot;
import com.model.ParkingSlotBooking;
import com.model.Vehicle;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingSlotBookingManagement {

    // Insert booking into DB
    public boolean insertBooking(ParkingSlotBooking booking) {
        String sql = "INSERT INTO ParkingSlotBooking (bookingId, vehicleId, slotId, startTime, endTime, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, booking.getBookingId());
            stmt.setString(2, booking.getVehicle().getVehicleId());
            stmt.setString(3, booking.getSlot().getSlotId());
            stmt.setTimestamp(4, Timestamp.valueOf(booking.getStartTime()));
            stmt.setTimestamp(5, Timestamp.valueOf(booking.getEndTime()));
            stmt.setString(6, booking.getStatus());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retrieve all bookings
    public List<ParkingSlotBooking> getAllBookings() {
        List<ParkingSlotBooking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM ParkingSlotBooking";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ParkingSlotBooking booking = new ParkingSlotBooking();

                booking.setBookingId(rs.getString("bookingId"));

                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleId(rs.getString("vehicleId"));
                booking.setVehicle(vehicle);

                ParkingSlot slot = new ParkingSlot();
                slot.setSlotId(rs.getString("slotId"));
                booking.setSlot(slot);

                booking.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());
                booking.setEndTime(rs.getTimestamp("endTime").toLocalDateTime());
                booking.setStatus(rs.getString("status"));

                bookings.add(booking);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookings;
    }

    // Cancel a booking (update status instead of delete)
    public boolean cancelBooking(String bookingId) {
        String sql = "UPDATE ParkingSlotBooking SET status = 'CANCELLED' WHERE bookingId = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bookingId);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get booking by ID (used for billing or updating slot availability)
    public ParkingSlotBooking findBookingById(String bookingId) {
        String sql = "SELECT * FROM ParkingSlotBooking WHERE bookingId = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bookingId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ParkingSlotBooking booking = new ParkingSlotBooking();

                booking.setBookingId(rs.getString("bookingId"));

                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleId(rs.getString("vehicleId"));
                booking.setVehicle(vehicle);

                ParkingSlot slot = new ParkingSlot();
                slot.setSlotId(rs.getString("slotId"));
                booking.setSlot(slot);

                booking.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());
                booking.setEndTime(rs.getTimestamp("endTime").toLocalDateTime());
                booking.setStatus(rs.getString("status"));

                return booking;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
