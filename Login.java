package Employee_Managment_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    
    private JTextField tfusername;
    private JPasswordField tfpassword;
    private CustomButton loginButton;

    public Login() {
        // Frame properties
        setTitle("Login - Employee Management System");
        setSize(500, 350);
        setLocationRelativeTo(null); // Centers window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(30, 30, 30)); // Modern Dark Background

        // Heading Label
        JLabel heading = new JLabel("User Login:");
        heading.setFont(new Font("Railway", Font.BOLD, 28));
        heading.setForeground(new Color(50, 150, 250));
        heading.setBounds(180, 20, 200, 30);
        add(heading);

        // Username Label & Field
        JLabel lblusername = new JLabel("Username:");
        lblusername.setFont(new Font("Railway", Font.PLAIN, 16));
        lblusername.setForeground(Color.WHITE);
        lblusername.setBounds(50, 80, 100, 25);
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(180, 80, 200, 30);
        tfusername.setFont(new Font("Railway", Font.PLAIN, 14));
        add(tfusername);

        // Password Label & Field
        JLabel lblpassword = new JLabel("Password:");
        lblpassword.setFont(new Font("Railway", Font.PLAIN, 16));
        lblpassword.setForeground(Color.WHITE);
        lblpassword.setBounds(50, 130, 100, 25);
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(180, 130, 200, 30);
        tfpassword.setFont(new Font("Arial", Font.PLAIN, 14));
        add(tfpassword);

        // Custom Login Button
        loginButton = new CustomButton("LOGIN");
        loginButton.setBounds(180, 190, 200, 40);
        loginButton.addActionListener(this);
        add(loginButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String username = tfusername.getText();
            String password = new String(tfpassword.getPassword());

            Conn c = new Conn();
            String query = "SELECT * FROM login WHERE username = '" + username + "' AND password = '" + password + "'";

            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                setVisible(false);
                new Home(); // Assuming Home class exists
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
                tfpassword.setText(""); // Clear password field
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Custom Button with Stylish Look
    class CustomButton extends JButton {
        private Color baseColor = new Color(50, 150, 250);
        private Color hoverColor = new Color(30, 130, 230);
        private Color pressColor = new Color(20, 110, 200);
        private boolean hovered = false;
        private boolean pressed = false;

        public CustomButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setForeground(Color.WHITE);
            setFont(new Font("Arial", Font.BOLD, 16));

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    hovered = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hovered = false;
                    repaint();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    pressed = true;
                    repaint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    pressed = false;
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Color currentColor = pressed ? pressColor : (hovered ? hoverColor : baseColor);
            g2.setPaint(new GradientPaint(0, 0, currentColor, getWidth(), getHeight(), currentColor.darker()));

            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() + fm.getAscent()) / 2 - 2;
            g2.setColor(Color.WHITE);
            g2.drawString(getText(), x, y);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
