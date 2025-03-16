package Employee_Managment_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class UpdateEmployee extends JFrame implements ActionListener {

    JTextField tfname, tfeducation, tffname, tfaddress, tfphone, tfemail, tfsalary, tfdesignation;
    JLabel lblempId, photoLabel;
    JButton add, back, uploadPhoto;
    String empId, photoPath = "";

    UpdateEmployee(String empId) {
        this.empId = empId;

        // Create a panel for custom background
        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(236, 240, 241), 0, getHeight(), new Color(189, 195, 199));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        background.setLayout(null);
        setContentPane(background);

        // Header Panel with Gradient
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(52, 152, 219), getWidth(), getHeight(), new Color(41, 128, 185));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setBounds(0, 0, 900, 80);
        headerPanel.setLayout(null);
        background.add(headerPanel);

        JLabel heading = new JLabel("UPDATE EMPLOYEE DETAILS", JLabel.CENTER);
        heading.setBounds(0, 20, 900, 40);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 28));
        heading.setForeground(Color.WHITE);
        headerPanel.add(heading);

        // Employee Fields
        addLabelAndField("Name", 50, 150, background);
        tfname = addTextField(200, 150, background);

        addLabelAndField("Father's Name", 400, 150, background);
        tffname = addTextField(600, 150, background);

        addLabelAndField("Salary", 50, 200, background);
        tfsalary = addTextField(200, 200, background);

        addLabelAndField("Address", 400, 200, background);
        tfaddress = addTextField(600, 200, background);

        addLabelAndField("Phone", 50, 250, background);
        tfphone = addTextField(200, 250, background);

        addLabelAndField("Email", 400, 250, background);
        tfemail = addTextField(600, 250, background);

        addLabelAndField("Highest Education", 50, 300, background);
        tfeducation = addTextField(200, 300, background);

        addLabelAndField("Designation", 400, 300, background);
        tfdesignation = addTextField(600, 300, background);

        addLabelAndField("Employee ID", 50, 350, background);
        lblempId = new JLabel(empId);
        lblempId.setBounds(200, 350, 150, 30);
        lblempId.setFont(new Font("serif", Font.PLAIN, 20));
        background.add(lblempId);

        // Upload Photo Section
        addLabelAndField("Photo", 50, 400, background);
        uploadPhoto = createButton("Upload Photo", 200, 400, background);
        uploadPhoto.addActionListener(this);
        background.add(uploadPhoto);

        // Image Preview
        photoLabel = new JLabel();
        photoLabel.setBounds(400, 380, 100, 100);
        photoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        background.add(photoLabel);

        // Buttons
        add = createButton("Update Details", 250, 550, background);
        back = createButton("Back", 450, 550, background);

        setSize(900, 700);
        setLocation(300, 50);
        setVisible(true);
    }

    private void addLabelAndField(String text, int x, int y, JPanel panel) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 150, 30);
        label.setFont(new Font("serif", Font.PLAIN, 20));
        panel.add(label);
    }

    private JTextField addTextField(int x, int y, JPanel panel) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 150, 30);
        panel.add(textField);
        return textField;
    }

    private JButton createButton(String text, int x, int y, JPanel panel) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 180, 50);
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Sans-serif", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.addActionListener(this);
        panel.add(button);
        return button;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == uploadPhoto) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select Employee Photo");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                photoPath = selectedFile.getAbsolutePath();

                // Show image preview
                ImageIcon icon = new ImageIcon(photoPath);
                Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                photoLabel.setIcon(new ImageIcon(img));
            }
        } else if (ae.getSource() == add) {
            try {
                Conn conn = new Conn();
                String query = "UPDATE employee SET name = '" + tfname.getText() + 
                               "', fname = '" + tffname.getText() + 
                               "', salary = '" + tfsalary.getText() + 
                               "', address = '" + tfaddress.getText() + 
                               "', phone = '" + tfphone.getText() + 
                               "', email = '" + tfemail.getText() + 
                               "', education = '" + tfeducation.getText() + 
                               "', designation = '" + tfdesignation.getText() + 
                               "', photo = '" + photoPath + 
                               "' WHERE empId = '" + empId + "'";

                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Employee details updated successfully!");
                setVisible(false);
                new Home();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new UpdateEmployee("EMP123"); // Example Employee ID
    }
}
