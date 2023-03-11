package packageGUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PatientGUI implements ActionListener {
    static JFrame frame = new JFrame();

    private JTable doctor ; //to display the table to book
    private JButton openCSV;
    private JFileChooser fileChooser;
    private JLabel details1 ;
    private JLabel details2 ;
    private JLabel details3 ;
    private JPanel panelNorth;
    private JPanel panelSouth;

    MyTableModel dataModel;



    PatientGUI(){

        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setVisible(true);
        frame.setTitle("Westminster Skin Consultation Center!");
        frame.setLayout(new BorderLayout(10,10));
        init();
        doctor.setAutoCreateRowSorter(true); //To sort the information

    }

    private void init(){  //to add the components in the frame
        //to add panels
        panelNorth = new JPanel();
        panelSouth = new JPanel();

        //set backgroun colors
        panelNorth.setBackground(Color.LIGHT_GRAY);
        panelSouth.setBackground(Color.LIGHT_GRAY);

        //set the panel to preferred size
        panelNorth.setPreferredSize(new Dimension(100,100));
        panelSouth.setPreferredSize(new Dimension(100,100));

        //ADD them to frame
        frame.add(panelNorth, BorderLayout.NORTH);
        frame.add(panelSouth, BorderLayout.SOUTH);

        details1 = new JLabel("Please CLICK on the button to Get start Consultation");
        details2 = new JLabel("If you want to sort information click on the respective column");
        details3 = new JLabel("If you want to add consultation click on the respective doctor's row");

        //to set the label inside pannels
        details1.setFont(new Font(null,Font.PLAIN,15));
        panelNorth.add(details1);
        details2.setFont(new Font(null,Font.PLAIN,15));
        panelNorth.add(details2);
        details3.setFont(new Font(null,Font.PLAIN,15));
        panelNorth.add(details3);

        openCSV = new JButton("Load the Doc details");
        openCSV.addActionListener(this); //to do something if button clicked

        fileChooser = new JFileChooser("w1912885_cw.csv");  //to select the file

        panelSouth.add(openCSV);

        doctor = new JTable();  //create a doc table
        frame.add(doctor,BorderLayout.CENTER);  //to add the table in frame
        frame.add(new JScrollPane(doctor));
        dataModel = new MyTableModel();   //creating MyTableModel object to call the methods
        doctor.setModel(dataModel);     //to set the table
        dataModel.fireTableDataChanged();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openCSV){
            ArrayList<String[]> rows = readCSV();  //to get all the rows

            //to add header for table
            String [] cols = {"Doc Name","Surname","Date of birth","Mobile","Specialisation","License no"}; //to add header
            MyTableModel tableModel = new MyTableModel(rows,cols); //to set the header and rows
            doctor.setModel(tableModel); //to set the table

            dataModel.fireTableDataChanged();
            theader();  //to style the header
            selectRow(); //to go to consulting window if a row is selected
        }

    }

    public ArrayList<String[]> readCSV(){  //method to read through the csv file to get doc details
        BufferedReader reader;
        String  line ;
        ArrayList<String[]> data = new ArrayList<>(); //creating a array list
        try {
            reader = new BufferedReader(new FileReader("w1912885_cw.csv"));
            while((line = reader.readLine())!=null){
                String [] row = line.split(","); //storing the first line after splitting it
                data.add(row); //adding it to the created array list data
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
    public void selectRow(){ //to open a new window if a row iss selected
        ListSelectionModel model = doctor.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!model.isSelectionEmpty()){
                    //get selected row
                    int selectedRow = model.getMinSelectionIndex();

                    Object value1 = doctor.getValueAt(selectedRow, 0);
                    Object value2 = doctor.getValueAt(selectedRow, 1);
                    Object value3 = doctor.getValueAt(selectedRow, 2);
                    Object value4 = doctor.getValueAt(selectedRow, 3);
                    Object value5 = doctor.getValueAt(selectedRow, 4);
                    Object value6 = doctor.getValueAt(selectedRow, 5);

                    ConsultGui consultGUI = new ConsultGui(value1,value2,value3,value4,value5,value6);
                    frame.dispose();  //to open only one window
                    //ConsultGui consultGUI = new ConsultGui();
                }
            }
        });

    }

    private void theader(){
        JTableHeader theader = doctor.getTableHeader();
        theader.setForeground(Color.WHITE);
        theader.setBackground(Color.BLACK);
        theader.setFont(new Font("Tahome",Font.BOLD,14));
        TableColumn column = doctor.getColumnModel().getColumn(0);
        column.setPreferredWidth(100);

    }

}
