package hibernate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFrame extends JFrame implements ActionListener, KeyListener {
    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");
    Menu menu = new Menu();
    static LoginFrame loginFrame = new LoginFrame();
    static MenuFrame menuFrame = new MenuFrame();

    LoginFrame() {
        menu.logout.setEnabled(false);
        this.setVisible(false);
        this.setBounds(10, 10, 370, 600);
        this.setTitle("Login");
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();


        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                try {
                    Main.sql.sqlend();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        menu.menuBar.setBounds(0,0,370,20);
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);


    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        container.add(menu.menuBar);
    }


    public void addActionEvent() {
        userTextField.addKeyListener(this);
        this.addKeyListener(this);
        passwordField.addKeyListener(this);
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_A && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)){
            new About();
        }
        if(e.getKeyCode()==KeyEvent.VK_U && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)){
            new Settings();
        }
        if (e.getKeyCode()==KeyEvent.VK_ENTER) {
            login();
        }
        if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    static int id = 0;

    private static final Logger logger = LogManager.getLogger(LoginFrame.class);
    private void login(){
        String userText;
        String pwdText;
        userText = userTextField.getText();
        pwdText = passwordField.getText();
        String query = String.format("Select * from user where email='%s' and pass='%s';",
                userText, pwdText);
        ResultSet email = Main.sql.select(query);

        String name = null;
        String pass = null;
        String imie = null;
        String nazwisko = null;

        try {
            while (email.next()) {
                name = email.getString("email");
                pass = email.getString("pass");
                imie = email.getString("Name");
                nazwisko = email.getString("Lastname");
                id = email.getInt("idUser");
            }
            if (userText.equalsIgnoreCase(name) && pwdText.equalsIgnoreCase(pass)) {
                String mes = String.format("Zalogowany na %S  %S, %d",imie,nazwisko,id);
                JOptionPane.showMessageDialog(this, mes);
                this.setVisible(false);

                mes = String.format("Witaj %s  %s, %d",imie,nazwisko,id);
                MenuFrame.textArea.setText(mes);
                userTextField.setText("");
                passwordField.setText("");
                menuFrame.setVisible(true);
            } else {
                logger.error("Blad logowania");
                JOptionPane.showMessageDialog(this, "Blad logowania");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton ) {
            login();
        }

        if (e.getSource() == resetButton) {
            userTextField.setText("");
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

}