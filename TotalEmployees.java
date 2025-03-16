package Employee_Managment_System;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class TotalEmployees extends JFrame {
    
    public TotalEmployees() {
        // Frame properties
        setTitle("Total Employees - Employee Management System");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10)); // Vertical layout
        panel.setBackground(new Color(200, 220, 240));

        // Fetch employee data
        int total = getTotalEmployees();
        Map<String, Integer> designationCount = getEmployeesByDesignation();
        
        JLabel totalLabel = new JLabel("Total Employees: " + total, JLabel.CENTER);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(totalLabel);

        // Display designation-wise count
        for (Map.Entry<String, Integer> entry : designationCount.entrySet()) {
            JLabel designationLabel = new JLabel(entry.getKey() + ": " + entry.getValue(), JLabel.CENTER);
            designationLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            panel.add(designationLabel);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private int getTotalEmployees() {
        int count = 0;
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Employee_Managment_System", "root", "swagat123A@");
            Statement s = c.createStatement();

            String query = "SELECT COUNT(*) FROM employee";
            ResultSet rs = s.executeQuery(query);

            if (rs.next()) {
                count = rs.getInt(1);
            }

            rs.close();
            s.close();
            c.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return count;
    }

    private Map<String, Integer> getEmployeesByDesignation() {
        Map<String, Integer> designationMap = new HashMap<>();
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Employee_Managment_System", "root", "swagat123A@");
            Statement s = c.createStatement();

            String query = "SELECT designation, COUNT(*) FROM employee GROUP BY designation";
            ResultSet rs = s.executeQuery(query);

            while (rs.next()) {
                designationMap.put(rs.getString(1), rs.getInt(2));
            }

            rs.close();
            s.close();
            c.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return designationMap;
    }

    public static void main(String[] args) {
        new TotalEmployees();
    }
}