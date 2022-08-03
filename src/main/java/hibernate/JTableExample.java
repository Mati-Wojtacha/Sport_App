package hibernate;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JTableExample {
    JTableExample() throws SQLException {
        JTableExample();
    }
   public void JTableExample() throws SQLException {
        JFrame f = new JFrame();
        JTable j = new JTable();
        f.setTitle("JTable Example");

        String[] tableColumnsName = {"id","name","lastname", "cos", "cos2", "cos3"};
        DefaultTableModel aModel = (DefaultTableModel) j.getModel();
        aModel.setColumnIdentifiers(tableColumnsName);

// the query
        ResultSet rs = Main.sql.select("SELECT * FROM `user`");

// Loop through the ResultSet and transfer in the Model
        java.sql.ResultSetMetaData rsmd = rs.getMetaData();
        int colNo = rsmd.getColumnCount();
        Object[] objects = new Object[colNo];
        while(rs.next()){
            for(int i=0;i<colNo;i++){
                objects[i]=rs.getObject(i+1);
            }
            aModel.addRow(objects);
        }
        j.setModel(aModel);
        j.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane 
       JScrollPane sp = new JScrollPane(j);
        f.add(sp);
       // Frame Size
        f.setSize(500, 200);
        f.setVisible(true);
    }

}