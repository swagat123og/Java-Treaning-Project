package Employee_Managment_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Custom Button Class
class CustomButton extends JButton {
    
    private Color baseColor = new Color(50, 150, 250); // Default color
    private Color hoverColor = new Color(30, 130, 230); // Hover color
    private Color pressColor = new Color(20, 110, 200); // Pressed color
    private boolean hovered = false;
    private boolean pressed = false;

    public CustomButton(String text) {
        super(text);
        setContentAreaFilled(false); // Remove default background
        setFocusPainted(false); // Remove focus border
        setBorderPainted(false); // Remove border
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 16));

        // Mouse Listener for Hover and Press Effects
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

        // Enable Anti-aliasing for smooth edges
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Determine background color
        Color currentColor = pressed ? pressColor : (hovered ? hoverColor : baseColor);
        g2.setPaint(new GradientPaint(0, 0, currentColor, getWidth(), getHeight(), currentColor.darker()));

        // Draw Rounded Button
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);

        // Draw Text
        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() + fm.getAscent()) / 2 - 2;
        g2.setColor(Color.WHITE);
        g2.drawString(getText(), x, y);

        g2.dispose();
        super.paintComponent(g);
    }
}

// Main Frame with Centered Button
public class CenteredButtonTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Centered Custom Button");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 400);
            frame.setLayout(new GridBagLayout()); // Center content

            CustomButton button = new CustomButton("Click Me!");
            button.setPreferredSize(new Dimension(200, 60));

            frame.add(button);
            frame.setVisible(true);
        });
    }
}
