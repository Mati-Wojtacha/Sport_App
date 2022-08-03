package hibernate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class Useradd extends JFrame implements ActionListener {
    Container container = getContentPane();
    JLabel name = new JLabel("Imie");
    JLabel lastname = new JLabel("Nazwisko");
    JLabel id_city = new JLabel("ID Miasta");
    JComboBox idcomboBox = new JComboBox(MenuFrame.city);
    JLabel email = new JLabel("Email");
    JLabel passwordLabel = new JLabel("Pass");
    JTextField nameTextField = new JTextField();
    JTextField lastnameTextField = new JTextField();
   // JTextField idcityTextField = new JTextField();
    JTextField emailTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton zatwierdz = new JButton("Zatwierdz");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show");

    Useradd(){
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
        id_city.setBounds(50, 200, 100, 30);
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
        container.add(idcomboBox);
        container.add(email);
        container.add(emailTextField);
        container.add(lastnameTextField);
        container.add(id_city);
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
                    insert(name, last, id, email, pwdText);
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


    public void insert(String name, String
            lastname, int id_city, String email, String pass) throws SQLException {

        String query = String.format(
            "INSERT INTO `user` (`idUser`, `Name`, `Lastname`, `City`, `Birth`, `Weight`, `Height`, `Gender`, `email`, `pass`, `admin`) " +
                        "VALUES (NULL, '%s', '%s', '%d', '1999-10-10', NULL, NULL, 'male','%s','%s',NULL);" ,
                name, lastname, id_city, email,pass);
        Main.sql.insert(query);
    }
}


