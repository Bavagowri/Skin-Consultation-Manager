package packageA;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Consultation {
    //initializing the instance variables
    private String bookedDoctors; //instantiated additionally to get the doctors name
    private String consultationDate;  //to store consulting date
    private String consultationStartTime; //to store consulting time
    private String notes;
    private final int costForFirstTime = 15; //charge per hour for 1st time visit
    private final int normalCost = 25;  //charge per hour for normal visit

    String consultationEndTime; //to get the end time of consultation
    float totalCost;  //to calculate the total cost for a consultation
    String checkName = "^[a-zA-Z ]+$"; //created to validate the name and surname
    //to get user input
    Scanner scanner = new Scanner(System.in);

    // to create an object of DateTime class and use it
    DateTime dt = new DateTime();

    //validate and take user inputs for console menu
    public String validateBookedDoctors(){
        System.out.print("\nEnter a doctor's name that you would like to consult : ");
        String bookedDoc = scanner.nextLine();
        //to validate doctor's name
        while(!bookedDoc.matches(this.checkName)) {
            System.out.println("Entered name is not Valid!\n"); // to output if the value is not valid
            System.out.print("Enter a doctor's name that you would like to consult : ");
            bookedDoc = scanner.nextLine();
        }
        return bookedDoc;
    }
    public String validateConsultationDate(){
        System.out.print("Enter the date you would like to book your consultation ");
        //to get the date from user
        dt.setYear();
        dt.setMonth();
        dt.setDay();
        String d = dt.setFormattedDate();
        return d;
    }

    //validate and get notes from user
    public String validateNotes(){
        System.out.print("Enter any notes for the doctor if you have :");
        String note = scanner.nextLine();
        while((!note.matches(this.checkName)) || (note.length()<5)) {
            System.out.println("Entered note is not Valid!/n"); // to output if the value is not valid
            System.out.print("Enter any notes for the doctor if you have :");
            note = scanner.nextLine();
        }
        return note;
    }

    //setter for booked doctor
    public void setBookedDoctors(String bookedDoc){
        //assigning the validated value
        this.bookedDoctors = bookedDoc;
    }

    //setter for consultation date
    public void setConsultationDate(String d){
        this.consultationDate = d;
    }

    //setter for consultation time
    public void setConsultationStartTime(){
        System.out.print("Enter the time you would like to book your consultation\nEnter Start Time\n");
        //to get the date from user
        dt.setTime();
        String time = dt.getTime();
        this.consultationStartTime = time;

    }

    //setter for consultation ending time
    public void setConsultationEndTime(){
        System.out.print("Enter the End time\n");
        //to get the date from user
        DateTime d = new DateTime();
        d.setTime();
        String time = d.getTime();
        this.consultationEndTime = time;

    }

    //setter for notes
    public void setNotes(String note){
        this.notes = note;
    }


    //setter for total cost
    public void setTotalCost(float hour, String visit){  //to find the consulting hour and set cost
        String r = visit;
        boolean error = true;

        //to perform the task according to the selection of input
        switch(r) {
            case "YES":
                this.totalCost = hour * this.costForFirstTime; // to calculate the cost for 1st time visit
                break;

            case "NO":
                this.totalCost = hour * this.normalCost; // to calculate the cost for after 1st visit
                break;

            default:
                System.out.println("Entered answer is Invalid! try again"); // to output if the entered value is not valid
        }
    }
    //setter for gui times
    public void setGUIStime(String s){

        this.consultationStartTime = s;
    }
    public void setGUIEtime(String e){

        this.consultationEndTime = e;
    }

    //getter for booked doctor
    public String getBookedDoctors(){

        return this.bookedDoctors;
    }

    //getter for consultation date
    public String getConsultationDate(){

        return this.consultationDate;
    }

    //getter for consultation time
    public  String  getConsultationStartTime(){

        return this.consultationStartTime;
    }

    //getter for notes
    public  String getNotes(){

        return this.notes;
    }

    public String getConsultationEndTime(){
        return this.consultationEndTime;
    }

    //getter for total cost
    public float getTotalCost(){

        return this.totalCost;
    }
}
