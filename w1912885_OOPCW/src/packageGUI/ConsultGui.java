package packageGUI;

import packageA.Consultation;
import packageA.DateTime;
import packageA.Patient;
import packageA.WestminsterConsultationManager;

import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class ConsultGui implements ActionListener {
    static JFrame frame = new JFrame();
    JLabel doc ;
    JLabel patient ;
    JTextField tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8,tf10;
    private JButton confirm; //to add consultation
    private JButton tf9,view;
    // to store the details of the doctors in Linked list
    LinkedList<String> docName = new LinkedList<>();
    LinkedList<String> docSpecialisation = new LinkedList<>();

    // to store patient details
    LinkedList<String> patientName = new LinkedList<>();
    LinkedList<String> patientSurName = new LinkedList<>();
    LinkedList<String> patientDOB = new LinkedList<>();
    LinkedList<String> patientMobile = new LinkedList<>();
    LinkedList<String> patientID = new LinkedList<>();

    // to store the consultation details
    LinkedList<String> bookedDoc = new LinkedList<>();
    LinkedList<String> consultationDate = new LinkedList<>();
    LinkedList<String> startTime = new LinkedList<>();
    LinkedList<String> endTime = new LinkedList<>();
    LinkedList<String> consultationNotes = new LinkedList<>();
    LinkedList<Float> consultationCost = new LinkedList<>();

    int consultLength = 0;
    LinkedList<String> docDetails = new LinkedList<>();


    ConsultGui(Object value1, Object value2, Object value3, Object value4, Object value5, Object value6){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.setLayout(new GridLayout(1,2,10,10));
        frame.setTitle("Booking Consultation!");
        frame.setVisible(true);

        docDetails.add(String.valueOf(value1));
        docDetails.add(String.valueOf(value2));
        docDetails.add(String.valueOf(value3));
        docDetails.add(String.valueOf(value4));
        docDetails.add(String.valueOf(value5));
        docDetails.add(String.valueOf(value6));

        //System.out.println(docName.getFirst());
        init();
        readData();
    }

    public void init() {
        JPanel p1 =new JPanel();
        p1.setBackground(Color.LIGHT_GRAY);
        JPanel p2 = new JPanel();
        p2.setBackground(Color.white);

        Container c  = new Container();
        BoxLayout b = new BoxLayout(c,BoxLayout.Y_AXIS);
        c.setLayout(b);

        Container c1  = new Container();
        BoxLayout b1 = new BoxLayout(c1,BoxLayout.Y_AXIS);
        c1.setLayout(b1);

        frame.add(p1);
        frame.add(p2);
        p1.add(c);
        p2.add(c1);

        //label to display selected doc details
        doc = new JLabel("Selected doc details to add consultation");
        doc.setFont(new Font("Tahoe", Font.BOLD,18));
        doc.setForeground(Color.BLUE);

        JLabel label1 = new JLabel("DocName : "+docDetails.get(0));
        label1.setFont(new Font("Tahoe",Font.BOLD,15));

        JLabel label2 = new JLabel("Doc Surname : "+docDetails.get(1));
        label2.setFont(new Font("Tahoe",Font.BOLD,15));

        JLabel label3 = new JLabel("DOB : "+docDetails.get(3));
        label3.setFont(new Font("Tahoe",Font.BOLD,15));

        JLabel label4 = new JLabel("Mobile : "+docDetails.get(4));
        label4.setFont(new Font("Tahoe",Font.BOLD,15));

        JLabel label5 = new JLabel("Specialisation :"+docDetails.get(5));
        label5.setFont(new Font("Tahoe",Font.BOLD,15));

        JLabel label6 = new JLabel("License no : "+docDetails.get(5));
        label6.setFont(new Font("Tahoe",Font.BOLD,15));

        //to get patient info
        patient = new JLabel("Please Enter the fields given below to add consultation");
        patient.setFont(new Font("Tahoe",Font.CENTER_BASELINE,18));

        JLabel pName = new JLabel("Name : ");
        pName.setFont(new Font("Tahoe",Font.BOLD,15));
        tf1 = new JTextField();
        toSetFontOfTextField(tf1);

        JLabel pSName = new JLabel("Surname : ");
        pSName.setFont(new Font("Tahoe",Font.BOLD,15));
        tf2 = new JTextField();
        toSetFontOfTextField(tf2);

        JLabel pDOB = new JLabel("DOB : ");
        pDOB.setFont(new Font("Tahoe",Font.BOLD,15));
        tf3 = new JTextField();
        toSetFontOfTextField(tf3);

        JLabel pMobile = new JLabel("Mobile : ");
        pMobile.setFont(new Font("Tahoe",Font.BOLD,15));
        tf4 = new JTextField();
        toSetFontOfTextField(tf4);

        JLabel date = new JLabel("Consultation Date : ");
        date.setFont(new Font("Tahoe",Font.BOLD,15));
        tf5 = new JTextField();
        toSetFontOfTextField(tf5);

        JLabel startTime = new JLabel("Start Time : ");
        startTime.setFont(new Font("Tahoe",Font.BOLD,15));
        tf6 = new JTextField();
        toSetFontOfTextField(tf6);

        JLabel endTime = new JLabel("End Time : ");
        endTime.setFont(new Font("Tahoe",Font.BOLD,15));
        tf7 = new JTextField();
        toSetFontOfTextField(tf7);

        JLabel notes = new JLabel("Notes : ");
        notes.setFont(new Font("Tahoe",Font.BOLD,15));
        tf8 = new JTextField();
        toSetFontOfTextField(tf8);

        JLabel image = new JLabel("Image : ");
        image.setFont(new Font("Tahoe",Font.BOLD,15));
        tf9 = new JButton("Select an image");
        tf9.addActionListener(this); //to do something if button clicked

        JLabel visit = new JLabel("Is this you 1st time visIt yes/no : ");
        visit.setFont(new Font("Tahoe",Font.BOLD,15));
        tf10 = new JTextField();
        toSetFontOfTextField(tf10);

        //buttons
        confirm = new JButton("Add consultation");
        confirm.addActionListener(this); //to do something if button clicked
        confirm.setSize(100,200);

        view = new JButton("view consultation");
        view.addActionListener(this); //to do something if button clicked
        view.setSize(100,200);

        //TO SET doc details
        c.add(doc);
        c.add(label1);
        c.add(label2);
        c.add(label3);
        c.add(label4);
        c.add(label5);
        c.add(label6);

        //to get patient info to add consultation
        c1.add(patient);
        c1.add(pName);
        c1.add(tf1);
        c1.add(pSName);
        c1.add(tf2);
        c1.add(pDOB);
        c1.add(tf3);
        c1.add(pMobile);
        c1.add(tf4);
        c1.add(date);
        c1.add(tf5);
        c1.add(startTime);
        c1.add(tf6);
        c1.add(endTime);
        c1.add(tf7);
        c1.add(notes);
        c1.add(tf8);
        c1.add(image);
        c1.add(tf9);

        c1.add(visit);
        c1.add(tf10);

        c1.add(confirm);
        c1.add(view);

    }
    public void toSetFontOfTextField(JTextField text){
        text.setFont(new Font("Tahoe",Font.PLAIN,15));
    }

    public boolean isValidName(String name) { //to validate name
        // The regular expression to match names. It allows letters and spaces,
        // but disallows consecutive spaces.
        String regex = "^[A-Za-z]+(?:[\\s-][A-Za-z]+)*$";
        return Pattern.matches(regex, name);
    }
    public boolean isValidDOB(String dob) { //to validate dob
        // The regular expression to match names. It allows letters and spaces,
        // but disallows consecutive spaces.
        String regex = "^[0-9]{4}/(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])";
        return Pattern.matches(regex, dob);
    }

    public boolean isValidMobile(String mobile) { //to validate mobile
        // The regular expression to match names. It allows letters and spaces,
        // but disallows consecutive spaces.
        String regex = "\\d{10}";
        return Pattern.matches(regex, mobile);
    }

    public void showOptionPane(String message, String title){
        // Display an error message if the text field is invalid
        JOptionPane.showMessageDialog(frame, message,title, JOptionPane.ERROR_MESSAGE);
    }
    public boolean validateEndTime(String t1, String t2){
        boolean val =false ;
        try {
            //time format
            SimpleDateFormat sdf = new SimpleDateFormat("hh.mm a");
            sdf.setLenient(false);  //to make sure that system doesn't assign a value if it is a wrong one

            Date end = sdf.parse(t1);
            Date start = sdf.parse(t2);

            boolean isInRange = end.after(start);
            if(isInRange==true){
                val = true;
            }
            else if(isInRange == false){
                val = false;
            }

        } catch (ParseException e) {
            throw new RuntimeException();
        }
        return val;
    }

    @Override
    public  void actionPerformed(ActionEvent e) {
        if(e.getSource()==tf9){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("")); // start in current directory
            fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg")); // only show text files
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            }
        }
        if (e.getSource() == confirm) {
            if(validateFields()==true){
                addConsultation();
                clearTextFields();
            }
        }
        if(e.getSource() == view){
            String sn = docDetails.get(0);
            String name= patientName.getLast();
            String i = patientID.getLast();
            float c = consultationCost.getLast();
            String date = consultationDate.getLast();
            ViewConsult v = new ViewConsult(sn,name,i,c,date);
            frame.dispose();  //to close the windows after viewing the consultation
        }

    }
    //to set all fields empty
    public void clearTextFields(){
        tf1.setText(null);
        tf2.setText(null);
        tf3.setText(null);
        tf4.setText(null);
        tf5.setText(null);
        tf6.setText(null);
        tf7.setText(null);
        tf8.setText(null);
        tf9.setText(null);
        tf10.setText(null);
    }
    public boolean validateFields(){
        boolean val = true;
        if (!isValidName(tf1.getText())) {
            showOptionPane("Invalid name!", "Error");
            val = false;
        }
        if (!isValidName(tf2.getText())) {
            showOptionPane("Invalid surname!", "Error");
            val = false;
        }
        if (!isValidDOB(tf3.getText())) {
            showOptionPane("Invalid Date of Birth! Date is not valid!", "Error");
            val = false;
        }
        if (!isValidMobile(tf4.getText())) {
            showOptionPane("Invalid mobile no!", "Error");
            val = false;
        }

        String regex = "^2023/(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])";
        if(!Pattern.matches(regex,tf5.getText())){
            showOptionPane("Invalid consult date!", "Error");
            val = false;
        }
        String time = tf6.getText();
        DateTime dt = new DateTime();
        if(!dt.timeValidate(time)){
            showOptionPane("Invalid start time!open hour at 8.00 am-12.00 pm", "Error");
            val = false;
        }
        String time2 = tf7.getText();
        if(!dt.timeValidate(time2)){
            showOptionPane("Invalid start time!open hour at 8.00 am-12.00 pm", "Error");
            val = false;
        }
        if(!validateEndTime(time2, time)){
            showOptionPane("Invalid end time!end is before start!", "Error");
            val = false;
        }
        return val;
    }

    public void addConsultation() {
        Patient p = new Patient();
        Consultation consult = new Consultation();
        boolean val = false;
        String temp = docDetails.get(0);
        String s = tf6.getText();
        String e = tf7.getText();
        p.setName(tf1.getText());
        p.setSurName(tf2.getText());
        p.setDateOfBirth(tf3.getText());
        p.setMobile(tf4.getText());
        consult.setConsultationDate(tf5.getText());
        consult.setGUIStime(tf6.getText());
        consult.setGUIEtime(tf7.getText());
        consult.setNotes(tf8.getText());


        //to check whether the time slot is taken
        for (int i = 0; i < bookedDoc.size(); i++) {
            //to check the entered doctor is booked on the same date and time
            if (bookedDoc.contains(temp) && consultationDate.contains(consult.getConsultationDate())) {
                int index = bookedDoc.indexOf(temp);
                //to get the start time of the same doctor
                String existStart = startTime.get(index); //to get the start time exist in the list


                //to get the end time of the same doctor
                String existEnd = endTime.get(index);
                //to check if time slot of that particular doctor is taken or not
                SimpleDateFormat sdf = new SimpleDateFormat("hh.mm a");
                sdf.setLenient(false);  //to make sure that system doesn't assign a value if it is a wrong one

                try {
                    Date start = sdf.parse(s);
                    Date end = sdf.parse(e);
                    Date exitSTime = sdf.parse(existStart);
                    Date existETime = sdf.parse(existEnd);
                    boolean istaken = e.equals(existEnd)||s.equals(existStart); //cant select same time exist
                    boolean b1 = (start.after(exitSTime) && (end.before(existETime)||end.after(existETime)));
                    boolean b2 = (start.before(existETime) && (end.equals(existETime)||end.after(existETime)));


                    if ((b1||b2||istaken)==true){
                        val = true;
                    }
                    else {
                        val = false;
                    }
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        if (val == false){
            //to set values in list only if slot id free
            bookedDoc.add(docDetails.get(0));
            patientName.add(p.getName());
            patientSurName.add(p.getSurName());
            patientDOB.add(p.getDateOfBirth());
            patientMobile.add(p.getMobile());
            consultationDate.add(consult.getConsultationDate());
            startTime.add(consult.getConsultationStartTime());
            endTime.add(consult.getConsultationEndTime());
            consultationNotes.add(consult.getNotes());

            //to calculate the duration of consultation
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("hh.mm a");
                sdf.setLenient(false);
                Date start = sdf.parse(s);
                Date end = sdf.parse(e);
                long difference = end.getTime() - start.getTime();
                long hours = difference / (1000 * 60 * 60);  //to get in hours
                long min = difference/(1000*60);  //to get min
                String visit = tf10.getText();  //to get to know if it is their 1st visit
                consult.setTotalCost((float) ((min/60.0)), visit.toUpperCase());
                consultationCost.add(consult.getTotalCost());
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }

            WestminsterConsultationManager w = new WestminsterConsultationManager();
            int lastId = Integer.parseInt(patientID.getLast());
            String id = w.validatePatientID(lastId); // to get and validate patient id
            p.setPatientId(id);
            patientID.add(p.getPatientId());
            saveConsultation();
            savePatient();
            consultLength += 1; //to increment inorder to add the consultation in 2d array
            String name= patientName.getLast();
            String i = patientID.getLast();
            float c = consultationCost.getLast();
            String sn = patientSurName.getLast();
            showOptionPane("Consultation success!\n name: "+name+" is added consultation & your id is :"+i+" cost:"+c, "Error");
            //frame.dispose();
        }
        else if(val == true){ //to display if the doctor is booked on that day
            showOptionPane("Booking Consultation is failed! Time slot is already taken","Error");
            //to close the window if consultation is not added successfully
            frame.dispose();
        }
    }
    public void savePatient(){
        try {
            FileWriter csvFile2 = new FileWriter("w1912885_patientGUI.csv",false); //to store patient details
            //to store patient details
            /*to print the details without leaving a new line in the end if system has one doctor details
            otherwise it will print new line in the end, and it causes error while reading data in to system
            when running again */
            if(patientName.size() == 1){
                csvFile2.write(String.valueOf(patientName.get(0)));
                csvFile2.write(",");
                csvFile2.write(String.valueOf(patientSurName.get(0)));
                csvFile2.write(",");
                csvFile2.write(String.valueOf(patientDOB.get(0)));
                csvFile2.write(",");
                csvFile2.write(String.valueOf(patientMobile.get(0)));
                csvFile2.write(",");
                csvFile2.write(String.valueOf(patientID.get(0)));
            }
            else{
                /*to print the details leaving a new line in the end if system has more than one doctor's details
            otherwise it will print all in one line it cause error while reading data in to system
            when running again */
                //to store the data into csv file
                for (int i = 0; i<patientName.size(); i++){
                    csvFile2.write(String.valueOf(patientName.get(i)));
                    csvFile2.write(",");
                    csvFile2.write(String.valueOf(patientSurName.get(i)));
                    csvFile2.write(",");
                    csvFile2.write(String.valueOf(patientDOB.get(i)));
                    csvFile2.write(",");
                    csvFile2.write(String.valueOf(patientMobile.get(i)));
                    csvFile2.write(",");
                    csvFile2.write("%s\n".formatted(String.valueOf(patientID.get(i))));
                }
            }
            csvFile2.close();

        }catch (FileNotFoundException e) {
            System.out.println("The file is not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void saveConsultation(){
        try{
            FileWriter csvFile3 = new FileWriter("w1912885_ConsultationGUI.csv",false);
            //to store consulting details
            /*to print the details without leaving a new line in the end if system has one patient details
            otherwise it will print new line in the end, and it causes error while reading data in to system
            when running again */
            if(bookedDoc.size() == 1){
                csvFile3.write(String.valueOf(bookedDoc.get(0)));
                csvFile3.write(",");
                csvFile3.write(String.valueOf(consultationDate.get(0)));
                csvFile3.write(",");
                csvFile3.write(String.valueOf(startTime.get(0)));
                csvFile3.write(",");
                csvFile3.write(String.valueOf(endTime.get(0)));
                csvFile3.write(",");
                csvFile3.write(String.valueOf(consultationNotes.get(0)));
                csvFile3.write(",");
                csvFile3.write(String.valueOf(consultationCost.get(0)));
            }
            else{
                /*to print the details leaving a new line in the end if system has more than one patient's details
            otherwise it will print all in one line it cause error while reading data in to system
            when running again */
                //to store the data into csv file
                for (int i = 0; i<bookedDoc.size(); i++){
                    csvFile3.write(String.valueOf(bookedDoc.get(i)));
                    csvFile3.write(",");
                    csvFile3.write(String.valueOf(consultationDate.get(i)));
                    csvFile3.write(",");
                    csvFile3.write(String.valueOf(startTime.get(i)));
                    csvFile3.write(",");
                    csvFile3.write(String.valueOf(endTime.get(i)));
                    csvFile3.write(",");
                    csvFile3.write(String.valueOf(consultationNotes.get(i)));
                    csvFile3.write(",");
                    csvFile3.write("%s\n".formatted(String.valueOf(consultationCost.get(i))));
                }
            }
            csvFile3.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("The file is not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void readData(){
        BufferedReader reader;
        String  line ;
        try {
            reader = new BufferedReader(new FileReader("w1912885_cw.csv"));
            while((line = reader.readLine())!=null){
                String [] row = line.split(",");
                //to assign the split values to respect linked list
                docName.add(row[0]);
                docSpecialisation.add(row[4]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            reader = new BufferedReader(new FileReader("w1912885_patientGUI.csv"));
            while((line = reader.readLine())!=null){
                String [] row = line.split(",");
                //to assign the split values to respect linked list
                patientName.add(row[0]);
                patientSurName.add(row[1]);
                patientDOB.add(row[2]);
                patientMobile.add(row[3]);
                patientID.add(row[4]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            reader = new BufferedReader(new FileReader("w1912885_ConsultationGUI.csv"));
            while((line = reader.readLine())!=null){
                String [] row = line.split(",");
                //to assign the split values to respect linked list
                bookedDoc.add(row[0]);
                consultationDate.add(row[1]);
                startTime.add(row[2]);
                endTime.add(row[3]);
                consultationNotes.add(row[4]);
                consultationCost.add(Float.valueOf(row[5]));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

