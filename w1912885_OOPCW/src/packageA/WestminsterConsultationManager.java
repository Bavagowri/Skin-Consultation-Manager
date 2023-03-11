package packageA;
import packageGUI.GUI;
import packageGUI.PatientGUI;

import java.io.*;
import java.text.ParseException;
import java.time.temporal.Temporal;
import java.util.*;
import java.time.temporal.ChronoUnit;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.Duration;
public class WestminsterConsultationManager implements SkinConsultationManager {

    // to store the details of the doctors in Linked list
    LinkedList<String> docName = new LinkedList<>();
    LinkedList<String> docSurName = new LinkedList<>();
    LinkedList<String> docDOB = new LinkedList<>();
    LinkedList<String> docMobile = new LinkedList<>();
    LinkedList<String> docSpecialisation = new LinkedList<>();
    LinkedList<String> docLicenseNo = new LinkedList<>();

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
    int id ; // to assign id for new patient

    public static void main(String[] args) {
        //instance variable initialization
        String selected; //to store the option selected from the console menu option

        //To create an object of WestminsterConsultationManager class object
        WestminsterConsultationManager w1 = new WestminsterConsultationManager();

        //to load the data stored into csv file to program
        w1.readDocFile(); //to load doc details
        w1.readPatientFile(); //to load patient details
        w1.readConsultationFile(); //to load consultation details


        //to get user input
        Scanner scanner = new Scanner(System.in);

        //to run the program until user enters exit
        while (true){
            //console for Manager
            //To print the message in the console
            System.out.println("""
                                               Welcome to Westminster Skin Consultation Manager!
                            Please select from the option given below to perform an action.
                            AND/and/And        - Add a new Doctor
                            DD/dd/Dd           - Delete a doctor
                            PLOD/plod/Plod     - Prints the list of doctor
                            SIF/sif/Sif        - Save in the file
                            AC/ac/Ac           - Add consultation
                            RC/rc/Rc           - Remove consultation
                            VCD/vcd/Vcd        - View consultationDetails
                            GUI/gui/Gui        - For open user interface
                            EXIT/exit          - To quit the program
                            """);
            System.out.print("Enter the operation needs to performed : ");
            selected = scanner.nextLine().toLowerCase();

            if (selected.equals("exit")) { //to stop the program if user enters exit
                break;
            }

            //switch case is used to perform the operation selected
            switch (selected) {
                case "and":
                    w1.addDoctor();
                    break;
                case "dd":
                    w1.deleteDoctor();
                    break;
                case "plod":
                    w1.listOfDoctor();
                    break;
                case "sif":
                    w1.saveInFile();
                    break;
                case "ac":
                    w1.addConsultation();
                    break;
                case "rc":
                    w1.deleteConsultation();
                    break;
                case "vcd":
                    w1.viewConsultationDetails();
                    break;
                case "gui":
                    GUI gui = new GUI();
                    break;
                default:
                    System.out.println("selected option is not valid!\n");
            }
        }
    }
    public String getTheirVisit(){
        Scanner sc = new Scanner(System.in);
        String response = "";
        System.out.print("Are you consulting for the first time (yes/no) : ");
        response = sc.nextLine().toUpperCase();
        while(response.equals("YES") == false && response.equals("NO") == false){
            System.out.print("Are you consulting for the first time (yes/no) : ");
            response = sc.nextLine().toUpperCase();
        }
        return response;
    }
    public String validatePatientID(int lastId){
        Scanner sc = new Scanner(System.in);
        String patientId = null;
        if(lastId == 0){
            //System.out.println(patientID.get(0));
            id = id + 1; //increment by one
            patientId = String.valueOf(id); // making it as a string value after increment
        }
        else if (lastId != 0) {
            lastId +=1;
            patientId = String.valueOf(lastId); // making it as a string value after increment
        }
        return patientId;
    }
    @Override
    public void addDoctor() {
        if(docName.size()<10){
            System.out.println("-----Enter the Doctor's details you would like to add------\n");
            Doctor p = new Doctor();  //creating an object of doctor class

            String name = p.validateName();
            p.setName(name);           //to get input name of doctor from manager and set value

            String surname = p.validateSurName();
            p.setSurName(surname);        //to get input surname of doctor from manager and set value

            String dob = p.validateDOB();
            p.setDateOfBirth(dob);    //to get input Date of birth of doctor from manager and set value

            String mob = p.validateMobile();
            p.setMobile(mob);         //to get input mobile no of doctor from manager and set value

            String specialisation = p.validateSpecialisation();
            p.setSpecialisation(specialisation);  //to get input specialisation of doctor from manager and set value

            String licenseNo = p.validateLicenseNo();
            p.setLicenseNo(licenseNo);      //to get input license no of doctor from manager and set value

            //to validate license no is unique
            String temp = p.getLicenseNo();
            while(docLicenseNo.contains(temp)){
                System.out.println("""
                        Entered doctor's license no already exit
                        Check and enter a proper value.
                        """);
                licenseNo = p.validateLicenseNo();
                p.setLicenseNo(licenseNo);

                temp = p.getLicenseNo();
            }
            //to store the value in the created linked using the getters
            docName.add(p.getName());
            docSurName.add(p.getSurName());
            docDOB.add(p.getDateOfBirth());
            docMobile.add(p.getMobile());
            docLicenseNo.add(p.getLicenseNo());
            docSpecialisation.add(p.getSpecialisation());
            System.out.println("Doctor added to the system successfully!\n");//message if doctor added successfully
        }
        else if (docName.size() == 10){
            System.out.println("Cant add doctor to system\nIt has 10 doctors already!\n");
        }

    }

    @Override
    public void deleteDoctor() {
        if(docLicenseNo.size() == 0){
            System.out.println("The system is not assigned with any doctors.\nThe doctors list is empty!\n");
        }
        else {
            System.out.println("Enter the Doctor's details you would like to delete\n");
            Doctor p = new Doctor();  //creating an object of doctor class
            //to let user know they are going to delete a doctor by giving a license no
            System.out.println("Please enter the doctor's license no to delete him/her from system!\n");

            String licenseNo = p.validateLicenseNo();
            p.setLicenseNo(licenseNo);

            String license = p.getLicenseNo();
            if(docLicenseNo.contains(license)){
                //to get the index value license from the list
                int index = docLicenseNo.indexOf(license);
                // to print the deleted doc details
                System.out.println("Name : " +docName.get(index) +
                        "\nSurname : " + docSurName.get(index) +
                        "\nDate of birth : " + docDOB.get(index) +
                        "\nMobile No : " + docMobile.get(index) +
                        "\nSpecialisation : " + docSpecialisation.get(index) +
                        "\nLicense No : " + docLicenseNo.get(index));

                //using that index to delete the information of that particular doctor
                docName.remove(index);              //remove the name of that doctor
                docSurName.remove(index);           //remove the surname of that doctor
                docDOB.remove(index);                //remove the dob of that doctor
                docMobile.remove(index);               //remove the mobile no of that doctor
                docSpecialisation.remove(index);    //remove the specialisation of that doctor
                docLicenseNo.remove(index);        //remove the license no of that doctor
                System.out.println("Doctor is deleted from the system successfully!\n");
                System.out.println("No of doctors exist in the clinic is "+docName.size()+"\n\n");
            }
            else{
                System.out.println("Doctor doesn't exist int he system!\n");
            }
        }
    }

    @Override
    public void listOfDoctor() {
        if (docName.size() >0){
            //for sorting
            LinkedList<String> sorted = new LinkedList<>(List.copyOf(docSurName));
            Collections.sort(sorted);

            System.out.print("--------------The list of Doctors-----------\n\n");

            for(int i=0; i < docName.size(); i++) {
                int x = docSurName.indexOf(sorted.get(0));
                //to print the list of doctor's with their details
                System.out.println("Name : " +docName.get(x) +
                                   "  Surname : " + docSurName.get(x) +
                                   "  Date of birth : " + docDOB.get(x) +
                                   "  Mobile No : " + docMobile.get(x) +
                                   "  Specialisation : " + docSpecialisation.get(x) +
                                   "  License No : " + docLicenseNo.get(x));
                sorted.remove(0);
            }
            System.out.println("\n\n");
        }
        else{
            System.out.println("No Doctors haven't been assigned yet!\n");
        }
    }

    @Override
    public void saveInFile() {
        try {
            //to give a file path
            String filePath;

            //to create file
            FileWriter csvFile = new FileWriter("w1912885_cw.csv",false); //to store doctor details
            FileWriter csvFile2 = new FileWriter("w1912885_patient.csv",false); //to store patient details
            FileWriter csvFile3 = new FileWriter("w1912885_Consultation.csv",false); //to store consultation details
            /*to print the details without leaving a new line in the end if system has one doctor details
            otherwise it will print new line in the end, and it causes error while reading data in to system
            when running again */
            if(docName.size() == 1){
                csvFile.write(String.valueOf(docName.get(0)));
                csvFile.write(",");
                csvFile.write(String.valueOf(docSurName.get(0)));
                csvFile.write(",");
                csvFile.write(String.valueOf(docDOB.get(0)));
                csvFile.write(",");
                csvFile.write(String.valueOf(docMobile.get(0)));
                csvFile.write(",");
                csvFile.write(String.valueOf(docSpecialisation.get(0)));
                csvFile.write(",");
                csvFile.write(String.valueOf(docLicenseNo.get(0)));
            }
            else{
                /*to print the details leaving a new line in the end if system has more than one doctor's details
            otherwise it will print all in one line it cause error while reading data in to system
            when running again */
                //to store the data into csv file
                for (int i = 0; i<docName.size(); i++){
                    csvFile.write(String.valueOf(docName.get(i)));
                    csvFile.write(",");
                    csvFile.write(String.valueOf(docSurName.get(i)));
                    csvFile.write(",");
                    csvFile.write(String.valueOf(docDOB.get(i)));
                    csvFile.write(",");
                    csvFile.write(String.valueOf(docMobile.get(i)));
                    csvFile.write(",");
                    csvFile.write(String.valueOf(docSpecialisation.get(i)));
                    csvFile.write(",");
                    csvFile.write("%s\n".formatted(String.valueOf(docLicenseNo.get(i))));

                }
            }
            csvFile.close();
            System.out.println("Doctor's Data saved successfully!");

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
            System.out.println("Patient's Data saved successfully!");

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
            System.out.println("Consulting details saved to file!");
        } catch (FileNotFoundException e) {
            System.out.println("The file is not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void readDocFile() {
        BufferedReader reader;
        String  line ;
        try {
            reader = new BufferedReader(new FileReader("w1912885_cw.csv"));
            while((line = reader.readLine())!=null){
                String [] row = line.split(",");
                //to assign the split values to respect linked list
                docName.add(row[0]);
                docSurName.add(row[1]);
                docDOB.add(row[2]);
                docMobile.add(row[3]);
                docSpecialisation.add(row[4]);
                docLicenseNo.add(row[5]);
                }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void readPatientFile() {
        BufferedReader reader;
        String  line ;
        try {
            reader = new BufferedReader(new FileReader("w1912885_patient.csv"));
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
    }

    @Override
    public void readConsultationFile() {
        BufferedReader reader;
        String  line ;
        try {
            reader = new BufferedReader(new FileReader("w1912885_Consultation.csv"));
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

    @Override
    public void addConsultation() {
        //to create a patient class object
        Patient p = new Patient();
        WestminsterConsultationManager w = new WestminsterConsultationManager();
        boolean val = false;
        //to create a consultation class object
        Consultation c = new Consultation();
        Consultation c1 = new Consultation();
        //to display the doctor list  to select from that
        if (docName.size() > 0) {
            System.out.print("--------------The list of Doctors-----------\n");
            for (int i = 0; i < docName.size(); i++) {
                //to print the list of doctor's with their details
                System.out.println("Name : " + docName.get(i) +
                        "  Surname : " + docSurName.get(i) +
                        "  Date of birth : " + docDOB.get(i) +
                        "  Mobile No : " + docMobile.get(i) +
                        "  Specialisation : " + docSpecialisation.get(i) +
                        "  License No : " + docLicenseNo.get(i));
            }

            //to get the details of doctor they like to consult
            String dName = c.validateBookedDoctors();
            c.setBookedDoctors(dName); // to get the name of doctor
            //to check if doctor exit
            String temp = c.getBookedDoctors();

            while (!docName.contains(temp)) {
                System.out.println("Entered doctor name is not in the list!\nTry Again!\n");
                //to get the details of doctor they like to consult
                dName = c.validateBookedDoctors();
                c.setBookedDoctors(dName); // to get the name of doctor
                temp = c.getBookedDoctors();
            }
            //getting consulting date, time and duration if name is found in doctor's linked list
            String date = c.validateConsultationDate();
            c.setConsultationDate(date);

            //getting start time
            c1.setConsultationStartTime();
            String s = c1.getConsultationStartTime();

            //getting end time
            c.setConsultationEndTime();
            String e = c.getConsultationEndTime();
            //validating end time

                try {
                    //time format
                    SimpleDateFormat sdf = new SimpleDateFormat("hh.mm a");
                    sdf.setLenient(false);  //to make sure that system doesn't assign a value if it is a wrong one

                    Date d = sdf.parse(e);
                    Date start = sdf.parse(s);

                    boolean isInRange = d.after(start);
                    if(isInRange==true){
                    }
                    while(isInRange == false ){
                        System.out.println("Entered end time is before start time!");
                        c.setConsultationEndTime();
                        e = c.getConsultationEndTime();
                        d = sdf.parse(e);
                        start = sdf.parse(s);
                        isInRange = d.after(start);

                    }

                } catch (ParseException ex) {
                    System.out.println("Entered end time is incorrect, Please try again!");

                }


            //to check whether the time slot is taken
            for (int i = 0; i < bookedDoc.size(); i++) {
                //to check the entered doctor is booked on the same date and time
                if (bookedDoc.contains(temp) && consultationDate.contains(c.getConsultationDate())) {
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


                        if ((b1||b2||istaken)==true){ //if time slot taken
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
                //to get notes and set cost if time slot is valid one
                String note = c.validateNotes();
                c.setNotes(note);

                //to calculate the duration of consultation
                SimpleDateFormat sdf = new SimpleDateFormat("hh.mm a");
                sdf.setLenient(false);  //to make sure that system doesn't assign a value if it is a wrong one
                try {
                    Date start = sdf.parse(s);
                    Date end = sdf.parse(e);
                    long difference = end.getTime() - start.getTime();
                    long hours = difference / (1000 * 60 * 60);  //to get in hours
                    long min = difference/(1000*60);  //to get min

                    String visit = getTheirVisit();  //to get to know if its their 1st visit
                    c.setTotalCost((float) ((min/60.0)), visit);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

                //to store the consultation details if time slot is valid one
                bookedDoc.add(c.getBookedDoctors());
                consultationDate.add(c.getConsultationDate());
                startTime.add(c1.getConsultationStartTime());
                endTime.add(c.getConsultationEndTime());
                consultationCost.add(c.getTotalCost());
                consultationNotes.add(c.getNotes());

                //to get the patient's information
                String name = p.validateName();
                p.setName(name);           //to get input name of patient from manager and set value

                String surname = p.validateSurName();
                p.setSurName(surname);        //to get input surname of patient from manager and set value

                String dob = p.validateDOB();
                p.setDateOfBirth(dob);    //to get input Date of birth of patient from manager and set value

                String mob = p.validateMobile();
                p.setMobile(mob);

                if(patientID.size()!=0){
                    int lastId = Integer.parseInt(patientID.getLast());
                    String id = w.validatePatientID(lastId); // to get and validate patient id
                    p.setPatientId(id);
                }
                else{
                    int lastId =0;
                    String id = w.validatePatientID(lastId); // to get and validate patient id
                    p.setPatientId(id);
                }

                System.out.println("Your Patient ID is " + p.getPatientId());

                System.out.println("Your booking cost is "+ c.getTotalCost());

                //to add patient info if time slot is valid one
                patientName.add(p.getName());
                patientSurName.add(p.getSurName());
                patientDOB.add(p.getDateOfBirth());
                patientMobile.add(p.getMobile());
                patientID.add(p.getPatientId());

                consultLength += 1; //to increment inorder to add the consultation in 2d array
                System.out.println("Consultation is booked successfully!\n");
            }
            else if(val == true){ //to display if the doctor is booked on that day
                System.out.println("Time slot is taken!\nCan't add consultation!");
                System.out.println("Booking Consultation is failed!");
            }
        }
        else {
            System.out.println("No Doctors haven't been assigned yet!\nTherefore you can not add a consultation.");
        }
    }

    @Override
    public void deleteConsultation() {
        System.out.println("Enter the patient id to delete consultation!");
        Patient p = new Patient();

        if (patientID.size()==0) {
            System.out.println("No consultation made yet!");
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your patient ID :");
        String id = scanner.nextLine();
        if (patientID.contains(id)){
            //if id is there then delete consultation
            //assuming that patient id is unique and a patient can make only one consultation at a time
            int index = patientID.indexOf(id);
            System.out.println("DocName : "+bookedDoc.get(index));
            System.out.println("Date : "+consultationDate.get(index)+"\n"+
                    "Start Time : "+startTime.get(index)+"\n"+
                    "End time : "+endTime.get(index)+"\n"+
                    "Note : "+consultationNotes.get(index)+"\n"+
                    "cost : "+consultationCost.get(index)+"\n"+
                    "Patient name : "+patientName.get(index)+"\n"+
                    "Patient id : "+patientID.get(index)+"\n"
            );
            System.out.println("consultation deleted successfully!");

            //to delete patient info
            patientName.remove(index);
            patientSurName.remove(index);
            patientDOB.remove(index);
            patientMobile.remove(index);
            patientID.remove(index);

            // to delete consultation info
            bookedDoc.remove(index);
            consultationDate.remove(index);
            startTime.remove(index);
            endTime.remove(index);
            consultationNotes.remove(index);
            consultationCost.remove(index);
            consultLength -= 1;
        }
        else if(!patientID.contains(id) && patientID.size()!=0){
            System.out.println("Such patient doesn't made any consultation!");
        }

    }

    @Override
    public void viewConsultationDetails() {
        System.out.println("---------------Booked Consultation Details------------------");
        System.out.println("Doc Name "+" Date "+" Start Time "+" End Time "+" Notes "+"Total cost "+" Patient Name "+" PatientID ");
        if(bookedDoc.size()>0){
            for (int i=0; i<bookedDoc.size(); i++){
                int j = i+1;
                System.out.println("Consultation no: "+j+"\n");
                System.out.println("DocName : "+bookedDoc.get(i));
                System.out.println("Date : "+consultationDate.get(i)+"\n"+
                        "Start Time : "+startTime.get(i)+"\n"+
                        "End time : "+endTime.get(i)+"\n"+
                        "Note : "+consultationNotes.get(i)+"\n"+
                        "cost : "+consultationCost.get(i)+"\n"+
                        "Patient name : "+patientName.get(i)+"\n"+
                        "Patient id : "+patientID.get(i)+"\n"
                );
            }
        }
        else{
            System.out.println("------There aren't any consultations made yet!-------\n");
        }
    }

}


