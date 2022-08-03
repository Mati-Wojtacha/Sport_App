package hibernate;
import java.io.IOException;
import java.sql.SQLException;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Main {

   // static LoginFrame frame =
    static Sql sql;

    static {
        try {
            sql = new Sql();
        } catch (IOException | SQLException e ) {
            e.printStackTrace();
            sql=null;
        }
    }

    private static final Logger logger = LogManager.getLogger(Main.class);


    public static void main(final String... args) {
       // new LoginFrame();
        logger.trace("Entering application.");
        new SplashScreen();
      //  Useradd u1 = new Useradd();
      //  int x = 9;

        /*
        Scanner wprowadz = new Scanner(System.in);
        while (x != 0) {
            System.out.println("co chcesz zrobic 1 select, 2 update, 3 insert, 4 dell, 0 koniec");
            x = wprowadz.nextInt();
            switch (x) {
                case 1:
                    System.out.println("Select");
                    u1.select();
                    break;
                case 2:
                    System.out.println("Uodate");
                    u1.update();
                    break;
                case 3:
                    System.out.println("Insert");
                    u1.insert();
                    break;
                case 4:
                    System.out.println("Delete");
                    u1.delete();
            }

        }
        sql.sqlend();
         */
    }
}
