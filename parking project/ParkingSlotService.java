package com.service;

import com.management.ParkingManagement;
import com.model.ParkingSlot;
import com.util.ApplicationUtil;

import java.util.ArrayList;
import java.util.List;

public class ParkingSlotService {

    ParkingManagement parkingManagement = new ParkingManagement();

    // Returns all parking slots
    public List<ParkingSlot> buildReservationList() {
        try {
            return parkingManagement.retrieveReservationList();
        } catch (Exception e) {
            System.err.println("Error retrieving reservation list: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void addParkingSlot(String location) {
        String slotId = ApplicationUtil.generateSlotId();
        ParkingSlot slot = new ParkingSlot(slotId, location, true);  // ✅ no price input
        List<ParkingSlot> slots = new ArrayList<>();
        slots.add(slot);
        parkingManagement.insertReservationList(slots);
        System.out.println("✅ Slot added with ID: " + slotId );
    }

    // Returns available slots only
    public List<ParkingSlot> retrieveAvailableParkingSlots() {
        List<ParkingSlot> availableSlots = new ArrayList<>();
        try {
            List<ParkingSlot> allSlots = parkingManagement.retrieveReservationList();
            for (ParkingSlot slot : allSlots) {
                if (slot.isAvailable()) {
                    availableSlots.add(slot);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return availableSlots;
    }

    // Deletes a slot by ID
    public boolean deleteParkingSpotRecord(String slotId) {
        try {
            return parkingManagement.deleteReservation(slotId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
