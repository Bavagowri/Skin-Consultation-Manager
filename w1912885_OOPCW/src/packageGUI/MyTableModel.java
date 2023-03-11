package packageGUI;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class MyTableModel extends DefaultTableModel {
    private ArrayList<String[]> value;

    private String[] columns;

    public MyTableModel(ArrayList<String[]> value, String [] columns) {
        this.value = value;
        this.columns = columns;
    }
    public MyTableModel() { //0 parameterized constructor
    }


    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public void setValue(ArrayList<String[]> value) {
        this.value = value;
    }


    @Override
    public Object getValueAt(int row, int column) {
        return value.get(row)[column];  //to take the one row and access one element in it
    }

    @Override
    public String getColumnName(int column) {
        return columns[column]; //to get the column index of a row when retrieving
    }

    @Override
    public int getColumnCount() {
        if(columns == null){
            return 0;
        }
        return columns.length;  //to get the no of column
    }

    @Override
    public int getRowCount() {
        if(value == null){
            return 0;
        }
        return value.size();
    }

}
