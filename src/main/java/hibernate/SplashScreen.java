package hibernate;
import javax.swing.*;

public class SplashScreen extends JFrame {

    public SplashScreen() {
        JWindow window = new JWindow();
        window.setBounds(10,10,300,200);
       // Icon imgIcon = new ImageIcon(this.getClass().getResource("C:\\Users\\Mati-HP\\IdeaProjects\\Sport_app\\src\\main\\java\\load.gif"));
        //JLabel label = new JLabel(imgIcon);
        //window.add(label);
        window.setVisible(true);
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        window.dispose();
        LoginFrame.loginFrame.setVisible(true);
    }

}