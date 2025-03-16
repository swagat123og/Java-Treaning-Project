package Employee_Managment_System;

import java.awt.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;
import java.awt.event.*;
import java.io.File;

public class AddEmployee extends JFrame implements ActionListener {

    Random ran = new Random();
    int number = ran.nextInt(999999);

    JTextField tfname, tffname, tfaddress, tfphone, tfaadhar, tfemail, tfsalary, tfdesignation;
    JDateChooser dcdob;
    JComboBox<String> cbeducation;
    JLabel lblempId, photoLabel;
    JButton add, back, uploadPhoto;
    String photoPath = "";

    AddEmployee() {
        setTitle("Add Employee");
        setSize(900, 850);
        setLocation(300, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(new Color(236, 240, 241));

        // Modern Heading Panel
        JPanel header = new JPanel();
        header.setBackground(new Color(52, 152, 219));
        header.setBounds(0, 0, 900, 80);
        header.setLayout(null);
        add(header);

        JLabel heading = new JLabel("Add Employee Details", SwingConstants.CENTER);
        heading.setBounds(250, 20, 400, 40);
        heading.setFont(new Font("Sans-serif", Font.BOLD, 26));
        heading.setForeground(Color.WHITE);
        header.add(heading);

        // Labels & Fields Placement
        int x1 = 50, x2 = 450, y = 100, width = 150, height = 30, gap = 50;

        // Employee Fields
        addLabelAndField("Name", x1, y, width, height);
        tfname = addTextField(200, y, 200, 30);

        addLabelAndField("Father's Name", x2, y, width, height);
        tffname = addTextField(600, y, 200, 30);
        
        y += gap;

        addLabelAndField("Date of Birth", x1, y, width, height);
        dcdob = new JDateChooser();
        dcdob.setBounds(200, y, 200, 30);
        add(dcdob);

        addLabelAndField("Salary", x2, y, width, height);
        tfsalary = addTextField(600, y, 200, 30);

        y += gap;

        addLabelAndField("Address", x1, y, width, height);
        tfaddress = addTextField(200, y, 200, 30);

        addLabelAndField("Phone", x2, y, width, height);
        tfphone = addTextField(600, y, 200, 30);

        y += gap;

        addLabelAndField("Email", x1, y, width, height);
        tfemail = addTextField(200, y, 200, 30);

        addLabelAndField("Highest Education", x2, y, width, height);
        String courses[] = { "BBA", "BCA", "BA", "BSC", "B.COM", "BTech", "MBA", "MCA", "MA", "MTech", "MSC", "PHD" };
        cbeducation = new JComboBox<>(courses);
        cbeducation.setBounds(600, y, 200, 30);
        add(cbeducation);

        y += gap;

        addLabelAndField("Designation", x1, y, width, height);
        tfdesignation = addTextField(200, y, 200, 30);

        addLabelAndField("Aadhar Number", x2, y, width, height);
        tfaadhar = addTextField(600, y, 200, 30);

        y += gap;

        addLabelAndField("Employee ID", x1, y, width, height);
        lblempId = new JLabel("" + number);
        lblempId.setBounds(200, y, 200, 30);
        lblempId.setFont(new Font("Sans-serif", Font.BOLD, 18));
        lblempId.setForeground(new Color(52, 152, 219));
        add(lblempId);

        // Upload Photo Section
        y += gap;
        JLabel lblPhoto = new JLabel("Photo:");
        lblPhoto.setBounds(x1, y, width, height);
        lblPhoto.setFont(new Font("Sans-serif", Font.PLAIN, 18));
        add(lblPhoto);

        uploadPhoto = createButton("Upload Photo", 200, y);
        uploadPhoto.setBounds(200, y, 150, 30);
        uploadPhoto.addActionListener(this);
        add(uploadPhoto);

        // Image preview
        photoLabel = new JLabel();
        photoLabel.setBounds(400, y - 30, 100, 100);
        photoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(photoLabel);

        y += gap + 50;

        // Buttons
        add = createButton("Add Details", 250, y);
        back = createButton("Back", 450, y);

        setVisible(true);
    }

    private void addLabelAndField(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Sans-serif", Font.PLAIN, 18));
        add(label);
    }

    private JTextField addTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        add(textField);
        return textField;
    }

    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 150, 40);
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Sans-serif", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.addActionListener(this);
        add(button);
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
                String query = "INSERT INTO employee (name, fname, dob, salary, address, phone, email, education, designation, aadhar, empId, photo) " +
                               "VALUES ('" + tfname.getText() + "', '" + tffname.getText() + "', '" +
                               ((JTextField) dcdob.getDateEditor().getUiComponent()).getText() + "', '" +
                               tfsalary.getText() + "', '" + tfaddress.getText() + "', '" + tfphone.getText() + "', '" +
                               tfemail.getText() + "', '" + cbeducation.getSelectedItem() + "', '" +
                               tfdesignation.getText() + "', '" + tfaadhar.getText() + "', '" +
                               lblempId.getText() + "', '" + photoPath + "')";
                
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details added successfully!");
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
        new AddEmployee();
    }
}
