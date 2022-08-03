package hibernate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class Userdell extends JFrame implements ActionListener {
    Container container = getContentPane();
    JLabel name = new JLabel("Czy na pewno chcesz usunac");
    JLabel user = new JLabel();
    JButton zatwierdz = new JButton("Zatwierdz");
    JButton resetButton = new JButton("Rezygnuj");
    int idup;

    Userdell(int idup, String name, String lastname) {
        user.setText(name+" "+lastname);
        setLayoutManager();
        addComponentsToContainer();
        addActionEvent();
        setLocationAndSize();
        this.idup=idup;
        this.setVisible(true);
        this.setBounds(10, 10, 300, 250);
    }
    /*
    Userdell(int idup, String name, String lastname){
        this.idup = idup;
        System.out.println(name + lastname);
        nameTextField.setText(name);
        lastnameTextField.setText(lastname);
        this.setVisible(true);
        this.setBounds(10, 10, 350, 250);
        this.setTitle("Dell");
        this.setResizable(false);
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        setLayoutManager();
        JOptionPane.showMessageDialog(this, "Czy napewno chcesz usunac");
    }

     */
    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        user.setBounds(50, 70, 200, 30);
        name.setBounds(50, 50, 200, 30);
         zatwierdz.setBounds(20, 150, 100, 30);
        resetButton.setBounds(150, 150, 100, 30);
    }

    public void addComponentsToContainer() {
        container.add(user);
        container.add(name);
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

            try {
                delete(idup);
                this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                MenuFrame.up("");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        if (e.getSource() == resetButton) {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }
    public static void delete(int dell) throws SQLException {
        String query = String.format(
                "DELETE FROM `answers` WHERE `answers`.`User` = '%d'",
                dell);
        String query1 = String.format(
                "DELETE FROM `history` WHERE `sport`.`history`.`User` = '%d'",
                dell);
        String query2 = String.format(
              "DELETE FROM `user` WHERE `user`.`idUser` = '%d'",
             dell);
        Main.sql.delete(query);
        Main.sql.delete(query1);
        Main.sql.delete(query2);
    }
}
