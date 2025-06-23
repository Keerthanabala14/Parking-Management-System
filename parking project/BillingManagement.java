package com.management;

import com.model.Billing;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BillingManagement {

    public void insertBill(Billing bill) {
        String sql = "INSERT INTO Billing (billId, bookingId, totalBill, startTime, endTime, billDate) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bill.getBillId());
            stmt.setString(2, bill.getBookingId());
            stmt.setDouble(3, bill.getTotalBill());
            stmt.setTimestamp(4, Timestamp.valueOf(bill.getStartTime()));
            stmt.setTimestamp(5, Timestamp.valueOf(bill.getEndTime()));
            stmt.setTimestamp(6, Timestamp.valueOf(bill.getBillDate()));

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Billing> getAllBills() {
        List<Billing> bills = new ArrayList<>();
        String sql = "SELECT * FROM Billing";

        try (Connection conn = DBConnectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Billing bill = new Billing(
                        rs.getInt("billId"),
                        rs.getString("bookingId"),
                        rs.getDouble("totalBill"),
                        rs.getTimestamp("startTime").toLocalDateTime(),
                        rs.getTimestamp("endTime").toLocalDateTime(),
                        rs.getTimestamp("billDate").toLocalDateTime()
                );
                bills.add(bill);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bills;
    }
}
