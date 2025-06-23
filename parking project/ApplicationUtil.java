package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

public class ApplicationUtil {

    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm";

    // Validate date format
    public static boolean isValidDateFormat(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            sdf.setLenient(false);
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // Calculate booking duration in hours
    public static long getDurationInHours(String startTime, String endTime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            Date start = sdf.parse(startTime);
            Date end = sdf.parse(endTime);
            long diff = end.getTime() - start.getTime();
            return diff / (1000 * 60 * 60);
        } catch (ParseException e) {
            return -1;
        }
    }

    // Generate Vehicle ID (e.g., VEH-XYZ123)
    public static String generateVehicleId() {
        return "VEH-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    // Generate Slot ID (e.g., SLOT-XYZ123)
    public static String generateSlotId() {
        return "SLOT-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    // Generate Booking ID (e.g., BOOK-XYZ123)
    public static String generateBookingId() {
        return "BOOK-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }
     
    public static int generateBillId() {
    	String uuid = UUID.randomUUID().toString().replaceAll("[^0-9]", ""); // keep digits only
        String sixDigits = uuid.length() >= 6 ? uuid.substring(0, 6) : String.format("%06d", 0);
        return Integer.parseInt(sixDigits);
    }

		 public static LocalDateTime parseToLocalDateTime(String endStr) {
			    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
			    return LocalDateTime.parse(endStr, formatter);
	}
}
