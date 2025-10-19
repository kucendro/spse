package sprava_uzivatelu;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import org.mindrot.jbcrypt.BCrypt;
import java.awt.*;

public class GUI extends JFrame {

    private String title = "";

    public GUI() {
        initGUI();
    }

    private void initGUI() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // on close save
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                UsersTable usersTable = UsersTable.getInstance();
                usersTable.save();
                PersonsTable personsTable = PersonsTable.getInstance();
                personsTable.save();

                // PersonsTable personsTable = PersonsTable.getInstance();
                // personsTable.save();
            }
        });
        setLayout(new BorderLayout());

        loginPanel(true);

        setSize(1000, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ! ----------------------------------------------------- LOGIN PANEL
    private void loginPanel(boolean visible) {

        title = "- Login";

        // just div for centering
        JPanel div = new JPanel(new GridBagLayout());
        add(div, BorderLayout.CENTER);
        //

        JPanel loginPanel = new JPanel(new BorderLayout());
        loginPanel.setBorder(BorderFactory.createTitledBorder("Please log in"));

        loginPanel.setPreferredSize(new Dimension(300, 140));
        loginPanel.setMaximumSize(new Dimension(300, 140));

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        loginPanel.add(inputPanel, BorderLayout.CENTER);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(10);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(10);

        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        JButton loginButton = new JButton("Log in");
        JButton signUpButton = new JButton("Sign up");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);

        loginPanel.add(buttonPanel, BorderLayout.SOUTH);

        // clicks actions
        usernameField.addActionListener(e -> loginButton.doClick());
        passwordField.addActionListener(e -> loginButton.doClick());

        loginButton.addActionListener(e -> {
            User user = (User) VerifyCredentials.verify(usernameField.getText(), passwordField.getPassword());
            if (user != null) {
                div.setVisible(false);
                userPanel(true, user);
            } else {
                JOptionPane.showMessageDialog(this, "Access denied.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        signUpButton.addActionListener(e -> {
            SignUp(true);
            div.setVisible(false);
        });

        div.add(loginPanel);

        setVisible(visible);
        setTitle("User management " + title);
    }

    // ! ----------------------------------------------------- SIGNUP PANEL
    private void SignUp(boolean visible) {
        title = "- Sign Up";

        // just div for centering
        JPanel div = new JPanel(new GridBagLayout());
        add(div, BorderLayout.CENTER);
        //

        JPanel loginPanel = new JPanel(new BorderLayout());
        loginPanel.setBorder(BorderFactory.createTitledBorder("Please sign up"));

        loginPanel.setPreferredSize(new Dimension(300, 180));
        loginPanel.setMaximumSize(new Dimension(300, 180));

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        loginPanel.add(inputPanel, BorderLayout.CENTER);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(10);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(10);
        JLabel passwordConfirmLabel = new JLabel("Confirm password:");
        JPasswordField passwordConfirmField = new JPasswordField(10);

        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        inputPanel.add(passwordConfirmLabel);
        inputPanel.add(passwordConfirmField);

        JButton loginButton = new JButton("Log in");
        JButton signUpButton = new JButton("Sign up");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);

        loginPanel.add(buttonPanel, BorderLayout.SOUTH);

        usernameField.addActionListener(e -> signUpButton.doClick());
        passwordField.addActionListener(e -> signUpButton.doClick());
        passwordConfirmField.addActionListener(e -> signUpButton.doClick());

        loginButton.addActionListener(e -> {
            loginPanel(true);
            div.setVisible(false);
        });

        signUpButton.addActionListener(e -> {
            if (String.valueOf(passwordField.getPassword())
                    .equals(String.valueOf(passwordConfirmField.getPassword()))) {

                StoreCredentials.hash(usernameField.getText(), passwordField.getPassword());

                div.setVisible(false);

                loginPanel(true);

            } else {
                JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        div.add(loginPanel);

        setVisible(visible);
        setTitle("User management " + title);
    }

    // ! ----------------------------------------------------- USER PANEL
    private void userPanel(boolean visible, User user) {
        title = "- Person panel";

        if (user.isAdmin()) {
            adminPanel(user);
        } else if (user.isRemoved()) {
            JOptionPane.showMessageDialog(this,
                    "Your person was removed by admin.", "Warning",
                    JOptionPane.ERROR_MESSAGE);
            loginPanel(true);
        } else if (user.person_id == null || user.person_id.isEmpty() || user.person_id.equals("null")) {
            registerPersonPannel(user);
        } else {
            personPanel(user, user.id);
        }

        setVisible(visible);
        setTitle("User management " + title);
    }

    // ! ----------------------------------------------------- REGISTER PERSON PANEL
    private void registerPersonPannel(User user) {

        PersonsTable personsTable = PersonsTable.getInstance();

        // just div for centering
        JPanel div = new JPanel(new GridBagLayout());
        add(div, BorderLayout.CENTER);
        //

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Register to the evidence"));

        panel.setPreferredSize(new Dimension(400, 400));
        panel.setMaximumSize(new Dimension(400, 400));

        JPanel inputPanel = new JPanel(new GridLayout(15, 2));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(inputPanel, BorderLayout.CENTER);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        nameField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void validate() {
                String text = nameField.getText();
                boolean valid = text.matches("^[A-Za-záčďéěíňóřšťúůýžÁČĎÉĚÍŇÓŘŠŤÚŮÝŽ-]+$");
                nameField.setForeground(valid ? Color.BLACK : Color.RED);
            }

            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                validate();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                validate();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                validate();
            }
        });
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JLabel surnameLabel = new JLabel("Surname:");
        JTextField surnameField = new JTextField(20);
        surnameField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void validate() {
                String text = surnameField.getText();
                boolean valid = text.matches("^[A-Za-záčďéěíňóřšťúůýžÁČĎÉĚÍŇÓŘŠŤÚŮÝŽ-]+$");
                surnameField.setForeground(valid ? Color.BLACK : Color.RED);
            }

            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                validate();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                validate();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                validate();
            }
        });
        inputPanel.add(surnameLabel);
        inputPanel.add(surnameField);

        JLabel titleBeforeLabel = new JLabel("Title Before:");
        JTextField titleBeforeField = new JTextField(20);
        inputPanel.add(titleBeforeLabel);
        inputPanel.add(titleBeforeField);

        JLabel titleAfterLabel = new JLabel("Title After:");
        JTextField titleAfterField = new JTextField(20);
        inputPanel.add(titleAfterLabel);
        inputPanel.add(titleAfterField);

        JLabel dobLabel = new JLabel("Date of Birth:");
        JSpinner dobSpinner = new JSpinner(new SpinnerDateModel());
        dobSpinner.setEditor(new JSpinner.DateEditor(dobSpinner, "dd-MM-yyyy"));
        inputPanel.add(dobLabel);
        inputPanel.add(dobSpinner);

        JLabel birthNumberLabel = new JLabel("Birth Number:");
        JTextField birthNumberField = new JTextField(20);
        birthNumberField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void validate() {
                String text = birthNumberField.getText();
                boolean valid = text.matches("\\d{6}/\\d{4}");
                birthNumberField.setForeground(valid ? Color.BLACK : Color.RED);
            }

            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                validate();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                validate();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                validate();
            }
        });
        inputPanel.add(birthNumberLabel);
        inputPanel.add(birthNumberField);

        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField(20);
        inputPanel.add(addressLabel);
        inputPanel.add(addressField);

        JLabel countryCodeLabel = new JLabel("Country Code:");
        JComboBox<String> countryCodeBox = new JComboBox<>(
                new String[] { "SK", "CZ", "HU", "PL", "DE", "AT", "CH", "UK", "FR", "IT" });
        inputPanel.add(countryCodeLabel);
        inputPanel.add(countryCodeBox);

        JLabel cityLabel = new JLabel("City:");
        JTextField cityField = new JTextField(20);
        cityField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void validate() {
                String text = cityField.getText();
                boolean valid = text.matches("^[A-Za-záčďéěíňóřšťúůýžÁČĎÉĚÍŇÓŘŠŤÚŮÝŽ]+$") && !text.contains(" ");
                cityField.setForeground(valid ? Color.BLACK : Color.RED);
            }

            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                validate();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                validate();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                validate();
            }
        });
        inputPanel.add(cityLabel);
        inputPanel.add(cityField);

        JLabel postalCodeLabel = new JLabel("Postal Code:");
        JTextField postalCodeField = new JTextField(20);
        inputPanel.add(postalCodeLabel);
        inputPanel.add(postalCodeField);

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JTextField phoneNumberField = new JTextField(20);
        phoneNumberField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void validate() {
                String text = phoneNumberField.getText();
                boolean valid = text.matches("\\+\\d{12,15}");
                phoneNumberField.setForeground(valid ? Color.BLACK : Color.RED);
            }

            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                validate();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                validate();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                validate();
            }
        });
        inputPanel.add(phoneNumberLabel);
        inputPanel.add(phoneNumberField);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        inputPanel.add(emailLabel);
        inputPanel.add(emailField);

        JLabel ztpLabel = new JLabel("ZTP:");
        JCheckBox ztpCheckBox = new JCheckBox();
        inputPanel.add(ztpLabel);
        inputPanel.add(ztpCheckBox);

        JLabel studentLabel = new JLabel("Student:");
        JCheckBox studentCheckBox = new JCheckBox();
        inputPanel.add(studentLabel);
        inputPanel.add(studentCheckBox);

        JLabel retiredLabel = new JLabel("Retired:");
        JCheckBox retiredCheckBox = new JCheckBox();
        inputPanel.add(retiredLabel);
        inputPanel.add(retiredCheckBox);

        JButton registerButton = new JButton("Register");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(registerButton);

        registerButton.addActionListener(e -> {
            String dobString = new java.text.SimpleDateFormat("dd-MM-yyyy").format(dobSpinner.getValue());
            String countryCode = (String) countryCodeBox.getSelectedItem();
            personsTable.addPerson(new Person(user.id, nameField.getText(), surnameField.getText(),
                    titleBeforeField.getText(), titleAfterField.getText(),
                    dobString, birthNumberField.getText(), addressField.getText(), cityField.getText(),
                    postalCodeField.getText(), countryCode, phoneNumberField.getText(),
                    emailField.getText(), ztpCheckBox.isSelected(), studentCheckBox.isSelected(),
                    retiredCheckBox.isSelected()));
            UsersTable users = UsersTable.getInstance();
            users.assignPersonToUser(user.id, user.id);

            div.setVisible(false);
            personPanel(user, user.id);
        });

        panel.add(buttonPanel, BorderLayout.SOUTH);

        div.add(panel);
    }

    // ! ----------------------------------------------------- PERSON PANEL
    private void personPanel(User user, String person_id) {

        PersonsTable personsTable = PersonsTable.getInstance();
        Person person = personsTable.getPerson(person_id);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Person details"));

        // north panel
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(new JLabel(person.getTitleBefore() + " " + person.getName() + " " + person.getSurname() + " "
                + person.getTitleAfter()));
        namePanel.add(Box.createHorizontalStrut(30));
        namePanel.add(new JLabel("ID: " + person.getId()));

        // font size
        for (Component comp : namePanel.getComponents()) {
            if (comp instanceof JLabel) {
                comp.setFont(comp.getFont().deriveFont(Font.BOLD, 25f));
            }
        }

        panel.add(namePanel, BorderLayout.NORTH);

        JPanel detailsPanel = new JPanel(new GridLayout(0, 2));
        detailsPanel.add(new JLabel("Date of Birth:"));
        detailsPanel.add(new JLabel(person.getDateOfBirth()));
        detailsPanel.add(new JLabel("Birth Number:"));
        detailsPanel.add(new JLabel(person.getBirthNumber()));
        detailsPanel.add(new JLabel("Address:"));
        detailsPanel.add(new JLabel(person.getAddress()));
        detailsPanel.add(new JLabel("City:"));
        detailsPanel.add(new JLabel(person.getCity()));
        detailsPanel.add(new JLabel("Postal Code:"));
        detailsPanel.add(new JLabel(person.getPostalCode()));
        detailsPanel.add(new JLabel("Country Code:"));
        detailsPanel.add(new JLabel(person.getCountryCode()));
        detailsPanel.add(new JLabel("Phone Number:"));
        detailsPanel.add(new JLabel(person.getPhoneNumber()));
        detailsPanel.add(new JLabel("Email:"));
        detailsPanel.add(new JLabel(person.getEmail()));
        detailsPanel.add(new JLabel("ZTP:"));

        JLabel ztpLabelValue = new JLabel(person.isZTP() ? "✓" : "✕");
        ztpLabelValue.setForeground(person.isZTP() ? Color.GREEN : Color.RED);
        detailsPanel.add(ztpLabelValue);

        detailsPanel.add(new JLabel("Student:"));
        JLabel studentLabelValue = new JLabel(person.isStudent() ? "✓" : "✕");
        studentLabelValue.setForeground(person.isStudent() ? Color.GREEN : Color.RED);
        detailsPanel.add(studentLabelValue);

        detailsPanel.add(new JLabel("Retired:"));
        JLabel retiredLabelValue = new JLabel(person.isRetired() ? "✓" : "✕");
        retiredLabelValue.setForeground(person.isRetired() ? Color.GREEN : Color.RED);
        detailsPanel.add(retiredLabelValue);

        panel.add(detailsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton logoutButton = new JButton("Log out");
        buttonPanel.add(logoutButton);
        JButton editButton = new JButton("Edit");
        buttonPanel.add(editButton);
        JButton removeMyPersonButton = new JButton("Remove my person");
        buttonPanel.add(removeMyPersonButton);
        JButton deleteAccountButton = new JButton("Delete account");
        buttonPanel.add(deleteAccountButton);

        logoutButton.addActionListener(e -> {
            panel.setVisible(false);
            loginPanel(true);
        });

        editButton.addActionListener(e -> {
            editPersonPanel(user, person_id, () -> {
                // Callback pro refresh person panelu
                SwingUtilities.invokeLater(() -> {
                    panel.setVisible(false);
                    personPanel(user, person_id);
                });
            }, false);
        });

        removeMyPersonButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to remove you from evidence? ", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                personsTable.removePerson(person_id);
                user.setPerson_Id(null);
                panel.setVisible(false);
                registerPersonPannel(user);
            }
        });

        deleteAccountButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete your account and person? ", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                UsersTable usersTable = UsersTable.getInstance();
                personsTable.removePerson(person_id);
                usersTable.removeUser(user.id);
                panel.setVisible(false);
                loginPanel(true);
            }
        });

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);

        setTitle("User management " + person.getName() + " " + person.getSurname());
    }

    // ! ----------------------------------------------------- EDIT PERSON PANEL
    private void editPersonPanel(User user, String person_id, Runnable onSaveCallback, boolean isNewPerson) {

        PersonsTable personsTable = PersonsTable.getInstance();
        Person person = personsTable.getPerson(person_id);

        // new window
        JFrame editFrame = new JFrame(
                isNewPerson ? "Add Person" : "Edit " + person.getName() + " " + person.getSurname());
        editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editFrame.setSize(400, 600);
        editFrame.setLocationRelativeTo(null);
        editFrame.setLayout(new BorderLayout());
        editFrame.setVisible(true);
        editFrame.setResizable(false);
        editFrame.setAlwaysOnTop(true);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Edit Person details"));
        editFrame.add(panel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(15, 2));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(inputPanel, BorderLayout.CENTER);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(isNewPerson ? "" : person.getName(), 20);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        JLabel surnameLabel = new JLabel("Surname:");
        JTextField surnameField = new JTextField(isNewPerson ? "" : person.getSurname(), 20);
        inputPanel.add(surnameLabel);
        inputPanel.add(surnameField);
        JLabel titleBeforeLabel = new JLabel("Title Before:");
        JTextField titleBeforeField = new JTextField(isNewPerson ? "" : person.getTitleBefore(), 20);
        inputPanel.add(titleBeforeLabel);
        inputPanel.add(titleBeforeField);
        JLabel titleAfterLabel = new JLabel("Title After:");
        JTextField titleAfterField = new JTextField(isNewPerson ? "" : person.getTitleAfter(), 20);
        inputPanel.add(titleAfterLabel);
        inputPanel.add(titleAfterField);
        JLabel dobLabel = new JLabel("Date of Birth:");
        JSpinner dobSpinner = new JSpinner(new SpinnerDateModel());
        try {
            java.util.Date date = new java.text.SimpleDateFormat("dd-MM-yyyy")
                    .parse(person.getDateOfBirth());
            dobSpinner.setValue(person != null ? date : new java.util.Date());
        } catch (Exception e) {
            dobSpinner.setValue(new java.util.Date());
        }
        dobSpinner.setEditor(new JSpinner.DateEditor(dobSpinner, "dd-MM-yyyy"));
        inputPanel.add(dobLabel);
        inputPanel.add(dobSpinner);
        JLabel birthNumberLabel = new JLabel("Birth Number:");
        JTextField birthNumberField = new JTextField(isNewPerson ? "" : person.getBirthNumber(), 20);
        inputPanel.add(birthNumberLabel);
        inputPanel.add(birthNumberField);
        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField(isNewPerson ? "" : person.getAddress(), 20);
        inputPanel.add(addressLabel);
        inputPanel.add(addressField);
        JLabel cityLabel = new JLabel("City:");
        JTextField cityField = new JTextField(isNewPerson ? "" : person.getCity(), 20);
        inputPanel.add(cityLabel);
        inputPanel.add(cityField);
        JLabel postalCodeLabel = new JLabel("Postal Code:");
        JTextField postalCodeField = new JTextField(isNewPerson ? "" : person.getPostalCode(), 20);
        inputPanel.add(postalCodeLabel);
        inputPanel.add(postalCodeField);
        JLabel countryCodeLabel = new JLabel("Country Code:");
        JComboBox<String> countryCodeBox = new JComboBox<>(
                new String[] { "SK", "CZ", "HU", "PL", "DE", "AT", "CH", "UK", "FR", "IT" });
        inputPanel.add(countryCodeLabel);
        inputPanel.add(countryCodeBox);
        try {
            countryCodeBox.setSelectedItem(person != null ? person.getCountryCode() : "SK");
        } catch (Exception e) {
            countryCodeBox.setSelectedItem("SK");
        }
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JTextField phoneNumberField = new JTextField(isNewPerson ? "" : person.getPhoneNumber(), 20);
        inputPanel.add(phoneNumberLabel);
        inputPanel.add(phoneNumberField);
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(isNewPerson ? "" : person.getEmail(), 20);
        inputPanel.add(emailLabel);
        inputPanel.add(emailField);
        JLabel ztpLabel = new JLabel("ZTP:");
        JCheckBox ztpCheckBox = new JCheckBox();
        if (!isNewPerson) {
            ztpCheckBox.setSelected(person.isZTP());
        }
        inputPanel.add(ztpLabel);
        inputPanel.add(ztpCheckBox);
        JLabel studentLabel = new JLabel("Student:");
        JCheckBox studentCheckBox = new JCheckBox();
        if (!isNewPerson) {
            studentCheckBox.setSelected(person.isStudent());
        }
        inputPanel.add(studentLabel);
        inputPanel.add(studentCheckBox);
        JLabel retiredLabel = new JLabel("Retired:");
        JCheckBox retiredCheckBox = new JCheckBox();
        if (!isNewPerson) {
            retiredCheckBox.setSelected(person.isRetired());
        }
        inputPanel.add(retiredLabel);
        inputPanel.add(retiredCheckBox);

        JButton saveButton = new JButton("Save changes");
        JButton removePersonButton = new JButton("Remove person");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(saveButton);

        if (user.isAdmin() && !isNewPerson) {
            buttonPanel.add(removePersonButton);
        }

        saveButton.addActionListener(e -> {

            if (!isNewPerson) {
                personsTable.removePerson(person_id);
            }

            String dobString = new java.text.SimpleDateFormat("dd-MM-yyyy").format(dobSpinner.getValue());
            String countryCode = (String) countryCodeBox.getSelectedItem();
            personsTable.addPerson(new Person(person_id, nameField.getText(), surnameField.getText(),
                    titleBeforeField.getText(), titleAfterField.getText(),
                    dobString, birthNumberField.getText(), addressField.getText(), cityField.getText(),
                    postalCodeField.getText(), countryCode, phoneNumberField.getText(),
                    emailField.getText(), ztpCheckBox.isSelected(), studentCheckBox.isSelected(),
                    retiredCheckBox.isSelected()));

            if (isNewPerson) {

                JFrame newUserPanel = new JFrame("New Person & User Created");
                newUserPanel.setLayout(new GridLayout(0, 1));
                newUserPanel.setSize(300, 200);
                newUserPanel.setLocationRelativeTo(null);
                newUserPanel.setVisible(true);
                newUserPanel.setResizable(false);
                newUserPanel.setAlwaysOnTop(true);

                // just div for centering
                JPanel div = new JPanel(new GridLayout(3, 1));
                newUserPanel.add(div);

                div.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

                String generatedUsername = java.util.UUID.randomUUID().toString().substring(0, 8);
                String generatedPassword = java.util.UUID.randomUUID().toString().substring(0, 8);
                div.add(new JLabel("Your person has been created."));
                div.add(new JLabel("Username: " + generatedUsername), BorderLayout.CENTER);
                div.add(new JLabel("Password: " + generatedPassword), BorderLayout.CENTER);

                newUserPanel.add(div, BorderLayout.CENTER);

                User newUser = new User(person_id, generatedUsername, false,
                        BCrypt.hashpw(generatedPassword, BCrypt.gensalt(12)), person_id, false);
                UsersTable usersTable = UsersTable.getInstance();
                usersTable.addUser(newUser);
            }

            editFrame.dispose();

            // for callback .... invoke later Panel
            if (onSaveCallback != null) {
                onSaveCallback.run();

            }
        });

        removePersonButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(editFrame,
                    "Are you sure you want to remove this person? ", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                UsersTable usersTable = UsersTable.getInstance();
                usersTable.setRemoved(person_id, true);
                usersTable.assignPersonToUser(person_id, null);
                personsTable.removePerson(person_id);
                editFrame.dispose();
                if (onSaveCallback != null) {
                    onSaveCallback.run();
                }
            }
        });

        panel.add(buttonPanel, BorderLayout.SOUTH);

    }

    // ! ----------------------------------------------------- ADMIN PANEL
    private void adminPanel(User user) {
        title = "- Admin panel";

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Admin panel"));

        PersonsTable personsTable = PersonsTable.getInstance();
        String[] columns = { "ID", "Name", "DOB", "Birth Number", "Address", "City", "Postal Code", "Country", "Phone",
                "Email", "ZTP", "Student", "Retired" };

        Object[][] data = new Object[personsTable.getPersonCount()][columns.length];
        int i = 0;
        for (Person person : personsTable.getAllPersons().values()) {
            data[i][0] = person.getId();
            data[i][1] = person.getTitleBefore() + " " + person.getName() + " " + person.getSurname() + " "
                    + person.getTitleAfter();
            data[i][2] = person.getDateOfBirth();
            data[i][3] = person.getBirthNumber();
            data[i][4] = person.getAddress();
            data[i][5] = person.getCity();
            data[i][6] = person.getPostalCode();
            data[i][7] = person.getCountryCode();
            data[i][8] = person.getPhoneNumber();
            data[i][9] = person.getEmail();
            data[i][10] = person.isZTP() ? "YES" : "NO";
            data[i][11] = person.isStudent() ? "YES" : "NO";
            data[i][12] = person.isRetired() ? "YES" : "NO";
            i++;
        }

        JTable table = new JTable(data, columns) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(true);

        // what the hell the text was black on black background
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.BLACK);
        header.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // ? ------------------------------------------------------- FILTER PANEL

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.setBorder(BorderFactory.createTitledBorder("Filtering"));
        filterPanel.setPreferredSize(new Dimension(MAXIMIZED_HORIZ, 100));

        // ID FILTER
        Filtering.searchfilter("ID", 0, table, filterPanel);

        // NAME FILTER
        Filtering.searchfilter("Name", 1, table, filterPanel);

        // BIRTH NUMBER FILTER
        Filtering.searchfilter("Birth Number", 3, table, filterPanel);

        // ADDRESS FILTER
        Filtering.searchfilter("Address", 4, table, filterPanel);

        // CITY FILTER
        Filtering.searchfilter("City", 5, table, filterPanel);

        // POSTAL CODE FILTER
        Filtering.searchfilter("Postal Code", 6, table, filterPanel);

        // COUNTRY CODE FILTER
        Filtering.countryfilter(table, filterPanel, personsTable);

        // PHONE FILTER
        Filtering.searchfilter("Phone", 8, table, filterPanel);

        // EMAIL FILTER
        Filtering.searchfilter("Email", 9, table, filterPanel);

        // ZTP FILTER
        Filtering.booleanfilter("ZTP", 10, table, filterPanel);

        // STUDENT FILTER
        Filtering.booleanfilter("Student", 11, table, filterPanel);

        // RETIRED FILTER
        Filtering.booleanfilter("Retired", 12, table, filterPanel);

        panel.add(filterPanel, BorderLayout.NORTH);

        // ? ------------------------------------------------------- BUTTON PANEL
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton logoutButton = new JButton("Log out");
        buttonPanel.add(logoutButton);
        JButton addPersonButton = new JButton("Add person");
        buttonPanel.add(addPersonButton);

        logoutButton.addActionListener(e -> {
            panel.setVisible(false);
            loginPanel(true);
        });

        addPersonButton.addActionListener(e -> {
            editPersonPanel(user, System.currentTimeMillis() + "", () -> {
                SwingUtilities.invokeLater(() -> {
                    panel.setVisible(false);
                    adminPanel(user);
                });
            }, true);
        });

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    String person_id = (String) target.getValueAt(row, 0);
                    editPersonPanel(user, person_id, () -> {
                        SwingUtilities.invokeLater(() -> {
                            panel.setVisible(false);
                            adminPanel(user);
                        });
                    }, false);
                }
            }
        });

        setTitle("User management " + title);
    }
}