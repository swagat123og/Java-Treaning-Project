package Employee_Managment_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {

    private CustomButton view, add, update, remove, totalEmployees;

    public Home() {
        // Frame properties
        setTitle("Home - Employee Management System");
        setSize(1120, 630);
        setLocation(250, 100);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1120, 630);
        add(image);

        // Semi-Transparent Panel
        JPanel panel = new JPanel();
        panel.setBounds(600, 50, 450, 350);
        panel.setBackground(new Color(0, 0, 0, 120)); // Transparent Black
        panel.setLayout(null);
        image.add(panel);

        // Heading
        JLabel heading = new JLabel("Employee Management System");
        heading.setBounds(30, 20, 400, 40);
        heading.setFont(new Font("Serif", Font.BOLD, 22));
        heading.setForeground(Color.WHITE);
        panel.add(heading);

        // Buttons
        add = new CustomButton("Add Employee");
        add.setBounds(50, 80, 170, 40);
        add.addActionListener(this);
        panel.add(add);

        view = new CustomButton("View Employees");
        view.setBounds(230, 80, 170, 40);
        view.addActionListener(this);
        panel.add(view);

        update = new CustomButton("Update Employee");
        update.setBounds(50, 140, 170, 40);
        update.addActionListener(this);
        panel.add(update);

        remove = new CustomButton("Remove Employee");
        remove.setBounds(230, 140, 170, 40);
        remove.addActionListener(this);
        panel.add(remove);

        totalEmployees = new CustomButton("Total Employees");
        totalEmployees.setBounds(140, 200, 170, 40);
        totalEmployees.addActionListener(this);
        panel.add(totalEmployees);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            setVisible(false);
            new AddEmployee();
        } else if (ae.getSource() == view) {
            setVisible(false);
            new ViewEmployee();
        } else if (ae.getSource() == update) {
            setVisible(false);
            new ViewEmployee();
        } else if (ae.getSource() == remove) {
            setVisible(false);
            new RemoveEmployee();
        } else if (ae.getSource() == totalEmployees) {
            new TotalEmployees(); // ✅ Open the TotalEmployees page
        }
    }

    // Custom Button for Better UI
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
            setFont(new Font("Arial", Font.BOLD, 14));

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
        new Home();
    }
}
