import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm extends JFrame {
    // Components for Registration Form
    private JTextField nameField, mobileField;
    private JRadioButton maleButton, femaleButton;
    private JComboBox<String> dayBox, monthBox, yearBox;
    private JTextArea addressArea;
    private JCheckBox termsCheckBox;
    private JButton submitButton, resetButton;

    public RegistrationForm() {
        // Frame settings
        setTitle("Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(null);

        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 100, 20);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(150, 20, 200, 25);
        add(nameField);

        // Mobile
        JLabel mobileLabel = new JLabel("Mobile:");
        mobileLabel.setBounds(20, 60, 100, 20);
        add(mobileLabel);
        mobileField = new JTextField();
        mobileField.setBounds(150, 60, 200, 25);
        add(mobileField);

        // Gender
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(20, 100, 100, 20);
        add(genderLabel);
        maleButton = new JRadioButton("Male");
        maleButton.setBounds(150, 100, 70, 20);
        femaleButton = new JRadioButton("Female");
        femaleButton.setBounds(230, 100, 80, 20);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        add(maleButton);
        add(femaleButton);

        // Date of Birth
        JLabel dobLabel = new JLabel("DOB:");
        dobLabel.setBounds(20, 140, 100, 20);
        add(dobLabel);
        dayBox = new JComboBox<>(generateNumbers(1, 31));
        dayBox.setBounds(150, 140, 50, 25);
        monthBox = new JComboBox<>(new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});
        monthBox.setBounds(210, 140, 70, 25);
        yearBox = new JComboBox<>(generateNumbers(1900, 2024));
        yearBox.setBounds(290, 140, 70, 25);
        add(dayBox);
        add(monthBox);
        add(yearBox);

        // Address
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(20, 180, 100, 20);
        add(addressLabel);
        addressArea = new JTextArea();
        addressArea.setBounds(150, 180, 200, 60);
        add(addressArea);

        // Terms and Conditions
        termsCheckBox = new JCheckBox("Accept Terms and Conditions.");
        termsCheckBox.setBounds(150, 250, 250, 20);
        add(termsCheckBox);

        // Buttons
        submitButton = new JButton("Submit");
        submitButton.setBounds(150, 290, 100, 30);
        add(submitButton);

        resetButton = new JButton("Reset");
        resetButton.setBounds(260, 290, 100, 30);
        add(resetButton);

        // Button Action Listeners
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleReset();
            }
        });

        // Set the frame to be visible
        setVisible(true);
    }

    // Generate number array for JComboBox
    private String[] generateNumbers(int start, int end) {
        String[] numbers = new String[end - start + 1];
        for (int i = start; i <= end; i++) {
            numbers[i - start] = String.valueOf(i);
        }
        return numbers;
    }

    // Handle Submit Button Click
    private void handleSubmit() {
        // Validate inputs
        if (nameField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name is required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (mobileField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mobile number is required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!maleButton.isSelected() && !femaleButton.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please select a gender!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (addressArea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Address is required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!termsCheckBox.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please accept the terms and conditions!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = nameField.getText();
        String mobile = mobileField.getText();
        String gender = maleButton.isSelected() ? "Male" : "Female";
        String dob = dayBox.getSelectedItem() + " " + monthBox.getSelectedItem() + " " + yearBox.getSelectedItem();
        String address = addressArea.getText();

        // Open the Welcome Screen
        new WelcomeScreen(name);
        dispose(); // Close the current frame
    }

    // Handle Reset Button Click
    private void handleReset() {
        nameField.setText("");
        mobileField.setText("");
        maleButton.setSelected(false);
        femaleButton.setSelected(false);
        dayBox.setSelectedIndex(0);
        monthBox.setSelectedIndex(0);
        yearBox.setSelectedIndex(0);
        addressArea.setText("");
        termsCheckBox.setSelected(false);
    }

    // Welcome Screen Class
    // Welcome Screen Class
    public static class WelcomeScreen extends JFrame {
        public WelcomeScreen(String name) {
            // Frame settings
            setTitle("Welcome User");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400, 300);
            setLayout(null); // Use null layout for custom positioning

            // Welcome Label
            JLabel welcomeLabel = new JLabel("Welcome " + name, SwingConstants.CENTER);
            welcomeLabel.setBounds(50, 30, 300, 50); // Position and size
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Larger font size
            add(welcomeLabel);

            // Logout Button
            JButton logoutButton = new JButton("Logout");
            logoutButton.setBounds(150, 150, 100, 40); // Positioned and resized
            add(logoutButton);

            logoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose(); // Close the welcome screen
                    new RegistrationForm(); // Reopen the registration form
                }
            });

            // Set the frame to be visible
            setVisible(true);
        }
    }


    // Main Method
    public static void main(String[] args) {
        new RegistrationForm();
    }
}
