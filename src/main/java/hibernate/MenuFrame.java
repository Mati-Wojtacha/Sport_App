package hibernate;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuFrame extends JFrame implements ActionListener {

    JPanel jpan = new JPanel(new BorderLayout());
    static JLabel textArea = new JLabel();
    static JBDataModel dataModel = new JBDataModel();
    JTable table = new JTable(dataModel);
    JScrollPane scrollPane = new JScrollPane(table);
    JButton historiaButton = new JButton("Historia");
    JButton treningButton = new JButton("Dodaj trening");
    JButton miejscaButton = new JButton("Miejsca treningu");
    JButton pytamButton = new JButton("Pytania");
    JButton logout = new JButton("Logout");
    JMenuItem update = new JMenuItem("Zaaktualizuj");
    JMenuItem insert = new JMenuItem("Dodaj");
    JMenuItem delete = new JMenuItem("Usun");

    JMenuItem print = new JMenuItem("Drukuj");

    JMenuItem printpdf = new JMenuItem("Drukuj do pdf");
    JMenuItem sort = new JMenuItem("Sortuj po imieniu");
    JMenuItem sort2 = new JMenuItem("Sortuj po nazwisku");
    static String city[]= Sql.getCity();
   // MenuBar menu = new MenuBar();
  //  static MenuFrame frame = new MenuFrame();


    MenuFrame() {
        this.setMinimumSize(new Dimension(800, 500));
        this.setTitle("Menu");
        this.setVisible(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Main.sql.sqlend();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ;
            }
        });
       // table.setPreferredSize(new Dimension(400,400));
      //  table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        addComponentsToContainer();
        addActionEvent();
        //createMenuBar();
        Menu menu = new Menu();
        setJMenuBar(menu.menuBar);
        menu.sett.setEnabled(false);
        mouseRR();
        tab();
    }
    JMenuBar menu(){
      //  JMenuBar cos = menu.menu();
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Menu");
        JMenu pomoc = new JMenu("Pomoc");

        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_E);
        exitItem.addActionListener((event) -> System.exit(0));
        JMenuItem conItem = new JMenuItem("Polacz");
        conItem.setMnemonic(KeyEvent.VK_E);
        conItem.addActionListener((event) -> {
            try {
                Main.sql=new Sql();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        });

        pomoc.setMnemonic(KeyEvent.VK_F);
        JMenuItem about = new JMenuItem("O Mnie");
        about.setMnemonic(KeyEvent.VK_E);
        about.addActionListener((event)-> new About());

        if(Sql.conn==null){
            conItem.setEnabled(true);
        }
        else
            conItem.setEnabled(false);

        fileMenu.add(conItem);
        fileMenu.add(exitItem);
        pomoc.add(about);
        menuBar.add(fileMenu);
        menuBar.add(pomoc);
        return(menuBar);
    }


    private void tab() {
        treningButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                up("");
            }
        });
    }
/*
    private void createMenuBar() {

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Menu");
        JMenu pomoc = new JMenu("Pomoc");

        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_E);
        exitItem.addActionListener((event) -> System.exit(0));
        JMenuItem conItem = new JMenuItem("Polacz");
        conItem.setMnemonic(KeyEvent.VK_E);
        conItem.addActionListener((event) -> Main.sql=new Sql() );

        pomoc.setMnemonic(KeyEvent.VK_F);
        JMenuItem about = new JMenuItem("O Mnie");
        about.setMnemonic(KeyEvent.VK_E);
        about.addActionListener((event)-> new About());

        if(Sql.conn==null){
            conItem.setEnabled(true);
        }
        else
           conItem.setEnabled(false);

        fileMenu.add(conItem);
        fileMenu.add(exitItem);
        pomoc.add(about);
        menuBar.add(fileMenu);
        menuBar.add(pomoc);
        setJMenuBar(menuBar);
    }


 */
    void mouseRR() {
        table.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent me) {
                showPopup(me); // showPopup() is our own user-defined method
            }
        });
    }

    void showPopup(MouseEvent me) {
        JPopupMenu popup = new JPopupMenu();
        popup.add(update);
        popup.add(delete);
        popup.add(insert);
        popup.addSeparator();
        //popup.add(sort);
        int index = table.getSelectedColumn();
        System.out.println();
        if (index == 0) {
            popup.add(sort);
        } else{
            popup.add(sort2);
         }
        popup.addSeparator();
        popup.add(print);

        popup.add(printpdf);
        if (me.isPopupTrigger()){
            popup.show(me.getComponent(), me.getX(), me.getY());

    }}

    public void addComponentsToContainer() {
        Dimension przycisk = new Dimension(150, 30);
        JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel west = new JPanel();
        west.setPreferredSize(new Dimension(300, 300));
        this.add(north, BorderLayout.NORTH);
       // north.setBackground(Color.white);
        textArea.setFont(textArea.getFont().deriveFont(17f));
        this.add(jpan, BorderLayout.PAGE_END);
        jpan.setBackground(Color.white);
        jpan.add(textArea);
        this.add(west, BorderLayout.WEST);
        pytamButton.setPreferredSize(przycisk);
        historiaButton.setPreferredSize(przycisk);
        miejscaButton.setPreferredSize(przycisk);
        treningButton.setPreferredSize(przycisk);

        west.add(pytamButton);
        west.add(historiaButton);
        west.add(miejscaButton);
        west.add(treningButton);
        this.add(scrollPane, BorderLayout.EAST);
    }

    public void addActionEvent() {
        miejscaButton.addActionListener(this);
        pytamButton.addActionListener(this);
        treningButton.addActionListener(this);
        historiaButton.addActionListener(this);
        logout.addActionListener(this);
        update.addActionListener(this);
        delete.addActionListener(this);
        insert.addActionListener(this);
        print.addActionListener(this);

        printpdf.addActionListener(this);
        sort.addActionListener(this);
        sort2.addActionListener(this);
    }

    static void up(String sql){
        deps = Sql.getUser(sql);
        dataModel.fireTableDataChanged();
    }
    int i= 0;
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logout) {
            this.dispose();
            LoginFrame.loginFrame.setVisible(true);
        }
        if (e.getSource() == miejscaButton) {
            try {
                new JTableExample();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        if (e.getSource() == sort) {

            int index = table.getSelectedColumn();
            System.out.println();
                if (i % 2 == 0) {
                    up(" ORDER BY `user`.`Name` ASC ");
                }
                if (i % 2 == 1) {
                    up(" ORDER BY `user`.`Name` DESC ");
                }
                i++;
        }
            if (e.getSource() == sort2) {
                    if (i % 2 == 0) {
                        up(" ORDER BY `user`.`Lastname` ASC ");
                    }
                    if (i % 2 == 1) {

                        up(" ORDER BY `user`.`Lastname` DESC ");

                    }
                    i++;
          //  else
            //    System.out.println("blad");
            //System.out.println(i);
        }

        if (e.getSource() == update) {
           int index =deps.get(table.getSelectedRow()).number;
            String name = deps.get(table.getSelectedRow()).name;
            String lastname = deps.get(table.getSelectedRow()).nawisko;
            System.out.println("Row: " + index);
            new Useraup(index, name, lastname);
        }
        if (e.getSource() == insert) {
           new Useradd();
        }
        if (e.getSource() == print) {
            try {
                new Printing();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        if (e.getSource() == printpdf) {
            new PDFprinter();
        }
        if (e.getSource() == delete) {
            int index =deps.get(table.getSelectedRow()).number;
            String name = deps.get(table.getSelectedRow()).name;
            String lastname = deps.get(table.getSelectedRow()).nawisko;
                new Userdell(index, name, lastname);
        }

    }

    static ArrayList<UserToString> deps = new ArrayList<UserToString>();
    static class JBDataModel extends AbstractTableModel {

        String column[] = {"Imie", "Nazwisko"};

        @Override
        public String getColumnName(int columnIndex) {
            return column[columnIndex];
        }

        @Override
        public int getColumnCount() {
            return column.length;
        }

        @Override
        public int getRowCount() {
            return deps.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            //System.out.println("getValueAt " + rowIndex + " " + columnIndex);
            switch (columnIndex) {
                // case 0:
                //   return deps.get(rowIndex).number;

                case 0:
                    return deps.get(rowIndex).name;
                case 1:
                    return deps.get(rowIndex).nawisko;
            }
            return "";
        }

        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 1) {
                return false;
            } else {
                return true;
            }
        }
    }
}





