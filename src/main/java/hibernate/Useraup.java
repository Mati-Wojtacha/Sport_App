package hibernate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class Useraup extends JFrame implements ActionListener {
    Container container = getContentPane();
    JLabel name = new JLabel("Imie");
    JLabel lastname = new JLabel("Nazwisko");
    JLabel id = new JLabel("Miasto");
    JLabel email = new JLabel("Email");
    JLabel passwordLabel = new JLabel("Pass");
    JTextField nameTextField = new JTextField();
    JTextField lastnameTextField = new JTextField();
    JComboBox idcomboBox = new JComboBox(MenuFrame.city);
    JTextField emailTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton zatwierdz = new JButton("Zatwierdz");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show");
    int idup;

    Useraup(int idup, String name, String lastname){
        this.idup = idup;
        nameTextField.setText(name);
        lastnameTextField.setText(lastname);
        this.setVisible(true);
        this.setBounds(10, 10, 370, 600);
        this.setTitle("Wprowadz");
        this.setResizable(false);
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        setLayoutManager();
        new TextPrompt("example@website", emailTextField);
    }
    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        name.setBounds(50, 100, 100, 30);
        nameTextField.setBounds(150, 100, 150, 30);
        lastname.setBounds(50, 150, 100, 30);
        lastnameTextField.setBounds(150, 150, 150, 30);
        id.setBounds(50, 200, 100, 30);
        idcomboBox.setBounds(150, 200, 150, 30);
        email.setBounds(50, 250, 100, 30);
        emailTextField.setBounds(150, 250, 150, 30);
        passwordLabel.setBounds(50,300,100, 30);
        passwordField.setBounds(150, 300, 150, 30);
        showPassword.setBounds(300, 300, 150, 30);
        zatwierdz.setBounds(50, 500, 100, 30);
        resetButton.setBounds(200, 500, 100, 30);
    }

    public void addComponentsToContainer() {
        container.add(name);
        container.add(passwordLabel);
        container.add(lastname);
        container.add(id);
        container.add(email);
        container.add(emailTextField);
        container.add(lastnameTextField);
        container.add(idcomboBox);
        container.add(nameTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(zatwierdz);
        container.add(resetButton);
    }

    public void addActionEvent() {
        zatwierdz.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == zatwierdz) {
            String name;
            String last;
            int id;
            String email;
            String pwdText;
            name = nameTextField.getText();
            last = lastnameTextField.getText();
            id= idcomboBox.getSelectedIndex()+1;
            email = emailTextField.getText();
            pwdText = passwordField.getText();
            EmailValidator emailValidator = new EmailValidator();
            if (!emailValidator.validate(emailTextField.getText().trim())) {
                JOptionPane.showMessageDialog(this, "bladny adres email");
            } else {
                try {
                    update(idup, name, last);
                    this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                    MenuFrame.up("");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }
        if (e.getSource() == resetButton) {
            nameTextField.setText("");
            passwordField.setText("");
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }


        }
    }

    public static void update(int up, String name, String lastname) throws SQLException {

        String query = String.format(
                "UPDATE `user` SET `Name` = '%s', `Lastname` = '%s' WHERE `user`.`idUser` = '%d'",
                name, lastname, up);
        Main.sql.update(query);
    }

}
