import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Scanner;

public class Tester extends JFrame {
    private Connection connection;
    private JLabel nameLabel;
    private JLabel ageLabel;
    private JLabel genderLabel;
    private JTextField nameTextField;
    private JTextField ageTextField;
    private JTextField genderTextField;
    private JButton addButton;

    public Tester(Connection connection) {
        this.connection = connection;
        new Patient(connection, new Scanner(System.in));
        
        nameLabel = new JLabel("Name:");
        ageLabel = new JLabel("Age:");
        genderLabel = new JLabel("Gender:");
        nameTextField = new JTextField(20);
        ageTextField = new JTextField(5);
        genderTextField = new JTextField(10);
        addButton = new JButton("Add Patient");

        setLayout(new GridLayout(4, 2));
        add(nameLabel);
        add(nameTextField);
        add(ageLabel);
        add(ageTextField);
        add(genderLabel);
        add(genderTextField);
        add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPatient();
            }
        });

        setTitle("Hospital Management System");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addPatient() {
        String name = nameTextField.getText();
        int age = Integer.parseInt(ageTextField.getText());
        String gender = genderTextField.getText();

        try {
            String query = "INSERT INTO patients(name , age , gender) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(this, "Patient added successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add patient.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "H@ri1086");
            new Tester(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


