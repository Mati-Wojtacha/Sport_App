package hibernate;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;


public class Menu {
    Menu() {

        menu();
    }

    JMenuItem sett = new JMenuItem("Ustawienia (U)");
    JMenuBar menuBar = new JMenuBar();
    JMenuItem logout = new JMenuItem("Wyloguj (L)");
    JMenuItem about = new JMenuItem("O Mnie (E)");


    void menu() {

        JMenu fileMenu = new JMenu("Menu");
        JMenu pomoc = new JMenu("Pomoc");

        JMenuItem exitItem = new JMenuItem("Exit (E)");
        exitItem.setMnemonic(KeyEvent.VK_E);
        exitItem.addActionListener((event) -> System.exit(0));
        JMenuItem conItem = new JMenuItem("Polacz (P)");
        conItem.setMnemonic(KeyEvent.VK_P);

        conItem.addActionListener((event) -> {
            try {
                Main.sql = new Sql();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        });
        sett.addActionListener((event) -> new Settings());
        sett.setMnemonic(KeyEvent.VK_S);
        ///JMenuItem about = new JMenuItem("O Mnie (E)");
        about.setMnemonic(KeyEvent.VK_E);
        about.addActionListener((event) -> new About());
        logout.addActionListener((event) -> setLogout());
        logout.setMnemonic(KeyEvent.VK_L);

        fileMenu.add(sett);
        fileMenu.add(logout);
        fileMenu.add(conItem);
        fileMenu.add(exitItem);
        pomoc.add(about);
        menuBar.add(fileMenu);
        menuBar.add(pomoc);
    }

    void setLogout() {
        LoginFrame.menuFrame.dispose();
        LoginFrame.loginFrame.setVisible(true);
    }
}
