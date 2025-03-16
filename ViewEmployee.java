package Employee_Managment_System;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class ViewEmployee extends JFrame implements ActionListener {

    JTable table;
    Choice cemployeeId;
    CustomButton search, print, update, back;

    ViewEmployee() {
        setTitle("View Employee Details");
        setSize(900, 700);
        setLocation(300, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Custom Panel with Gradient Background
        CustomPanel panel = new CustomPanel();
        panel.setLayout(null);
        setContentPane(panel);

        JLabel searchlbl = new JLabel("Search by Employee ID");
        searchlbl.setBounds(20, 20, 200, 25);
        searchlbl.setFont(new Font("Tahoma", Font.BOLD, 16));
        searchlbl.setForeground(Color.WHITE);
        panel.add(searchlbl);

        cemployeeId = new Choice();
        cemployeeId.setBounds(220, 20, 150, 25);
        panel.add(cemployeeId);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            while (rs.next()) {
                cemployeeId.add(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(20, 100, 850, 500);
        panel.add(jsp);

        // Custom Buttons
        search = new CustomButton("Search");
        search.setBounds(20, 70, 100, 30);
        search.addActionListener(this);
        panel.add(search);

        print = new CustomButton("Print");
        print.setBounds(140, 70, 100, 30);
        print.addActionListener(this);
        panel.add(print);

        update = new CustomButton("Update");
        update.setBounds(260, 70, 100, 30);
        update.addActionListener(this);
        panel.add(update);

        back = new CustomButton("Back");
        back.setBounds(380, 70, 100, 30);
        back.addActionListener(this);
        panel.add(back);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String query = "select * from employee where empId = '" + cemployeeId.getSelectedItem() + "'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            setVisible(false);
            new UpdateEmployee(cemployeeId.getSelectedItem());
        } else {
            setVisible(false);
            new Home();
        }
    }

    // Custom Curved Button Class
    class CustomButton extends JButton {
        public CustomButton(String text) {
            super(text);
            setFont(new Font("Arial", Font.BOLD, 14));
            setForeground(Color.WHITE);
            setBackground(new Color(41, 128, 185));
            setFocusPainted(false);
            setBorderPainted(false);
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            g2.dispose();
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {}
    }

    // Custom Panel with Gradient Background
    class CustomPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            super.paintComponent(g2d);
            GradientPaint gp = new GradientPaint(0, 0, new Color(52, 152, 219), getWidth(), getHeight(), new Color(41, 128, 185));
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public static void main(String[] args) {
        new ViewEmployee();
    }
}