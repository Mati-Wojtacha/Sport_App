package hibernate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class Settings extends JFrame implements ActionListener {
    Container container = getContentPane();
    JLabel name = new JLabel("Uzytkownik");
    JLabel base = new JLabel("Nazwa bazy");
    JLabel port = new JLabel("Port");
    JLabel ip = new JLabel("IP");
    JTextField ipTextField = new JTextField();
    JTextField nameTextField = new JTextField();
    JTextField baseTextField = new JTextField();
    JTextField portTextField = new JTextField();
    JButton zatwierdz = new JButton("Zatwierdz");
    JButton resetButton = new JButton("RESET");

    Settings(){
        this.setVisible(true);
        this.setBounds(10, 10, 370, 400);
        this.setTitle("Wprowadz");
        this.setResizable(false);
        new TextPrompt("root", nameTextField);
        new TextPrompt("sport", baseTextField);
        new TextPrompt("3306", portTextField);
        new TextPrompt("127.0.0.1", ipTextField);
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        setLayoutManager();
    }
    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {

        ip.setBounds(50, 50, 100, 30);
        ipTextField.setBounds(150, 50, 150, 30);
        name.setBounds(50, 100, 100, 30);
        nameTextField.setBounds(150, 100, 150, 30);
        base.setBounds(50, 150, 100, 30);
        baseTextField.setBounds(150, 150, 150, 30);
        port.setBounds(50, 200, 100, 30);
        portTextField.setBounds(150, 200, 150, 30);
        zatwierdz.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);
    }

    public void addComponentsToContainer() {
        container.add(name);
        container.add(port);
        container.add(base);
        container.add(ip);

        container.add(ipTextField);
        container.add(portTextField);
        container.add(baseTextField);
        container.add(nameTextField);
        container.add(zatwierdz);
        container.add(resetButton);
    }

    public void addActionEvent() {
        zatwierdz.addActionListener(this);
        resetButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == zatwierdz) {
            String name;
            String db;
            int port;
            String ip = ipTextField.getText();
            name = nameTextField.getText();
            db = baseTextField.getText();
            port = Integer.parseInt(portTextField.getText());

            try {
                new XMLWriter(ip,port,db,name);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                Main.sql=new Sql();
            } catch (IOException | SQLException ioException) {
                ioException.printStackTrace();
            }
            this.dispose();
        }
        if (e.getSource() == resetButton) {
            this.dispose();
        }
    }

}
