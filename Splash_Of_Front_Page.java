package Employee_Managment_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Custom Modern Button with Fully Rounded Corners & Smooth Animations
class ModernButton extends JButton {
    
    private Color baseColor = new Color(52, 152, 219); // Soft Blue
    private Color hoverColor = new Color(41, 128, 185); // Deep Blue
    private Color clickColor = new Color(31, 97, 141); // Dark Blue
    private boolean hovered = false;
    private boolean pressed = false;

    public ModernButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font("Raleway", Font.BOLD, 18));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Mouse Effects for Animations
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

        // Background Color Effect
        Color currentColor = pressed ? clickColor : (hovered ? hoverColor : baseColor);
        g2.setPaint(new GradientPaint(0, 0, currentColor, getWidth(), getHeight(), currentColor.darker()));

        // Draw Fully Rounded Button
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);

        // Glow Effect on Hover
        if (hovered) {
            g2.setColor(new Color(255, 255, 255, 80));
            g2.drawRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 50, 50);
        }

        // Button Text
        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() + fm.getAscent()) / 2 - 2;
        g2.setColor(Color.WHITE);
        g2.drawString(getText(), x, y);

        g2.dispose();
        super.paintComponent(g);
    }
}

// Main Splash Page
public class Splash_Of_Front_Page extends JFrame implements ActionListener {
    
    private JLabel heading;

    public Splash_Of_Front_Page() {
        setTitle("Employee Management System");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/f1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1200, 700, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1200, 700);
        add(image);

        // Glass Effect Panel
        JPanel panel = new JPanel();
        panel.setBounds(300, 150, 600, 350);
        panel.setBackground(new Color(0, 0, 0, 100)); // Semi-transparent Black
        panel.setLayout(null);
        image.add(panel);

        // Heading with Smooth Fade Animation
        heading = new JLabel("EMPLOYEE MANAGEMENT SYSTEM", JLabel.CENTER);
        heading.setBounds(20, 30, 560, 50);
        heading.setFont(new Font("Raleway", Font.BOLD, 26));
        heading.setForeground(Color.WHITE);
        panel.add(heading);

        // Animated Heading (Smooth Fade)
        Timer timer = new Timer(700, new ActionListener() {
            private boolean fade = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                heading.setForeground(fade ? new Color(255, 255, 255, 120) : Color.WHITE);
                fade = !fade;
            }
        });
        timer.start();

        // Custom Rounded Button (Modern Look)
        ModernButton clickHere = new ModernButton("GET STARTED");
        clickHere.setBounds(170, 250, 260, 55);
        clickHere.addActionListener(this);
        panel.add(clickHere);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Login(); // Assuming Login class exists
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(Splash_Of_Front_Page::new);
    }
}
