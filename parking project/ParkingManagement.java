package com.management;

import com.model.ParkingSlot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParkingManagement {

    // Insert parking slots into DB
    public void insertReservationList(List<ParkingSlot> slots) {
        String query = "INSERT INTO ParkingSlot (slotId, location, isAvailable, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (ParkingSlot slot : slots) {
                stmt.setString(1, slot.getSlotId());
                stmt.setString(2, slot.getLocation());
                stmt.setBoolean(3, slot.isAvailable());
                stmt.setDouble(4, slot.getPrice());  // ✅ fixed price from model
                stmt.addBatch();
            }

            stmt.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Retrieve all slots
    public List<ParkingSlot> retrieveReservationList() {
        List<ParkingSlot> slots = new ArrayList<>();
        String query = "SELECT * FROM ParkingSlot";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ParkingSlot slot = new ParkingSlot(
                        rs.getString("slotId"),
                        rs.getString("location"),
                        rs.getBoolean("isAvailable")
                );
                slots.add(slot);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return slots;
    }

    // Delete slot by ID
    public boolean deleteReservation(String slotId) {
        String query = "DELETE FROM ParkingSlot WHERE slotId = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, slotId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Find slot by ID
    public ParkingSlot findSlotById(String slotId) {
        String query = "SELECT * FROM ParkingSlot WHERE slotId = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, slotId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ParkingSlot(
                        rs.getString("slotId"),
                        rs.getString("location"),
                        rs.getBoolean("isAvailable")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update availability
    public boolean updateSlotAvailability(String slotId, boolean isAvailable) {
        String query = "UPDATE ParkingSlot SET isAvailable = ? WHERE slotId = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setBoolean(1, isAvailable);
            stmt.setString(2, slotId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
