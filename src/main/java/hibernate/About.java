package hibernate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class About extends JFrame  {
    Container container = getContentPane();
    JTextArea TextArea = new JTextArea();


    About(){
        this.setVisible(true);
        this.setBounds(10, 10, 350, 250);
        this.setTitle("About");
        this.setResizable(false);
        TextArea.setBounds(10, 10, 300, 200);
        TextArea.setText("\n   Prosta aplikacja sportowa \n   Wykonał Mateusz wojtasiński");
        container.add(TextArea);
    }
}
