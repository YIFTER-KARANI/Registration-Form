import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

public class RegistrationForm extends JFrame {
    private JTextField idField, nameField, addressField, contactField;
    private JRadioButton maleRadio, femaleRadio;
    private JButton registerButton, exitButton;
    private JTable dataTable;
    private DefaultTableModel tableModel;

    public RegistrationForm() {
        setTitle("Registration Form");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Form components
        addFormComponents(gbc);

        // Table for displaying data
        addDataTable(gbc);

        // Buttons
        addButtons(gbc);

        setVisible(true);
    }

    private void addFormComponents(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        add(new JLabel("ID:"), gbc);
        gbc.gridy++;
        add(new JLabel("Name:"), gbc);
        gbc.gridy++;
        add(new JLabel("Gender:"), gbc);
        gbc.gridy++;
        add(new JLabel("Address:"), gbc);
        gbc.gridy++;
        add(new JLabel("Contact:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        idField = new JTextField(20);
        add(idField, gbc);
        gbc.gridy++;

        nameField = new JTextField(20);
        add(nameField, gbc);
        gbc.gridy++;

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        add(genderPanel, gbc);
        gbc.gridy++;

        addressField = new JTextField(20);
        add(addressField, gbc);
        gbc.gridy++;

        contactField = new JTextField(20);
        add(contactField, gbc);
    }

    private void addDataTable(GridBagConstraints gbc) {
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 6;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        String[] columnNames = {"ID", "Name", "Gender", "Address", "Contact"};
        tableModel = new DefaultTableModel(columnNames, 0);
        dataTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(dataTable);
        add(scrollPane, gbc);
    }

    private void addButtons(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        registerButton = new JButton("Register");
        exitButton = new JButton("Exit");

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(registerButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, gbc);
    }

    private void registerUser() {
        String id = idField.getText();
        String name = nameField.getText();
        String gender = maleRadio.isSelected() ? "Male" : "Female";
        String address = addressField.getText();
        String contact = contactField.getText();

        // Add data to table
        tableModel.addRow(new Object[]{id, name, gender, address, contact});

        // Clear form fields
        idField.setText("");
        nameField.setText("");
        maleRadio.setSelected(true);
        addressField.setText("");
        contactField.setText("");

        // In a real application, you would also save this data to a database here
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegistrationForm::new);
    }
}