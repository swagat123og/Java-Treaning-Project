package Employee_Managment_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage extends JFrame implements ActionListener {
    
    public WelcomePage() {
        setTitle("Welcome - Employee Management System");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background Image
        ImageIcon bgIcon = new ImageIcon(ClassLoader.getSystemResource("icons/WELCOME.jpg"));
        Image bgImg = bgIcon.getImage().getScaledInstance(1200, 700, Image.SCALE_SMOOTH);
        JLabel bgLabel = new JLabel(new ImageIcon(bgImg));
        bgLabel.setBounds(0, 0, 1200, 700);
        add(bgLabel);

        // Glass Effect Panel
        JPanel panel = new JPanel();
        panel.setBounds(300, 180, 600, 300);
        panel.setBackground(new Color(0, 0, 0, 100));
        panel.setLayout(null);
        bgLabel.add(panel);

        // Heading
        JLabel welcomeText = new JLabel("WELCOME", JLabel.CENTER);
        welcomeText.setFont(new Font("Raleway", Font.BOLD, 30));
        welcomeText.setForeground(Color.WHITE);
        welcomeText.setBounds(200, 30, 200, 40);
        panel.add(welcomeText);

        JLabel message = new JLabel("Experience the Future of Employee Management", JLabel.CENTER);
        message.setFont(new Font("Raleway", Font.PLAIN, 18));
        message.setForeground(Color.WHITE);
        message.setBounds(50, 90, 500, 30);
        panel.add(message);

        // Continue Button
        ModernButton continueButton = new ModernButton("CONTINUE");
        continueButton.setBounds(170, 200, 260, 55);
        continueButton.addActionListener(this);
        panel.add(continueButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Splash_Of_Front_Page(); // Connects to the splash page
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WelcomePage::new);
    }
}
