package packageA;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class DateTime {
    //creating instance variables
    private int month;   // to assign the value of month 1 to 12
    private int day;     // to assign the value for day in date
    private int year;     // to assign value of year in date
    private String time;  // to store  the time to consult

    //to get user input
    Scanner scanner = new Scanner(System.in);
    //FOR CONSOLE MENU
    //setter for year
    public void setYear(){
        int year = 0;
        boolean error =false;
        do{
            try {
                System.out.print("\nEnter the year : ");
                year = scanner.nextInt();
                while (year < 2023) { //to only take booking for the present and future
                    System.out.print("Entered year is not valid! Try again\n");
                    System.out.print("Enter the year : ");
                    year = scanner.nextInt();

                }
                this.year = year;
                break;
            } catch (InputMismatchException e) {
                System.out.println("Enter full number!" + year);
                error = true;
                scanner.nextLine();
            }
        }while(error);
    }

    //setter for month
    public void setMonth(){
        boolean error =false;
        do {
            try {
                System.out.print("Enter the month (1 to 12): ");
                int month = scanner.nextInt();
                //to check whether month is validated properly
                boolean validation = false;
                // validating whether month is between 1 and 12
                while (!validation) {
                    if ((month > 0) && (month <= 12)) {
                        this.month = month;
                        validation = true;
                        break;
                    } else {
                        System.out.print("Entered month is invalid!\n"); //displaying an error message if the month is incorrect
                        System.out.print("Enter the month (1 to 12): ");
                        month = scanner.nextInt();
                        validation = false;
                    }
                }
            } catch (RuntimeException e) {
                System.out.println("Enter number!\n");
                error = true;
                scanner.nextLine();
            }
        }while(error);
    }

    //setter for day
    public void setDay(){
        boolean error = false;
         do{
            try {
                System.out.print("Enter the day : ");
                int day = scanner.nextInt();
                //to check whether day is validated properly
                boolean validation = false;

                //to check the day in each moth is entered correctly using the given array
                int[] noOfDay = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

                while (!validation) {
                    // validating whether day and month is correct
                    if ((day >= 1) && (day <= noOfDay[this.month])) {
                        this.day = day; //assigning the value to day
                        validation = true;  // to stop running the loop if the entered value is correct
                        break;
                    }
                    //for leap year day calculation
                    else if (((this.month == 2) && (day == 29 && this.year % 400 == 0)) || ((this.year % 4 == 0) && (this.year % 100 != 0))) {
                        this.day = day;//assigning the value to day
                        validation = true;  // to stop running the loop if the entered value is correct
                        break;
                    } else {
                        System.out.println("Entered day is invalid!" + day); //displaying an error message if the month is incorrect
                        System.out.print("Enter the day : ");
                        day = scanner.nextInt();
                        validation = false; // to rerun and check whether given date is correct
                    }
                }
            } catch (RuntimeException e) {
                System.out.println("Enter number!\n");
                error = true;
                scanner.nextLine();
            }
        }while (error);
    }

    //setter for time
    public void setTime() {
        //to run the loop until this.time is given a valid input
        while (this.time == null) {
            try {
                //to get input
                //must leave a space between am/pm and time
                System.out.print("Enter the time (hh.mm am/pm) : ");
                String t = scanner.nextLine();  //to store the input value before validating

                //time format
                SimpleDateFormat sdf = new SimpleDateFormat("hh.mm a");
                sdf.setLenient(false);  //to make sure that system doesn't assign a value if it is a wrong one

                Date d = sdf.parse(t);
                this.time = sdf.format(d);
                Date start = sdf.parse("07.59 AM");
                Date end = sdf.parse("09.59 PM");

                boolean isInRange = d.after(start) && d.before(end);
                while(isInRange == false){
                    //to display a message if the date is not in given range
                    System.out.print("Enter time is not a working hour!\nClinic is open from 8.00 am to 10.00 pm\n\n");

                    //to get input
                    //must leave a space between am/pm and time
                    System.out.print("Enter the time (hh.mm am/pm) : ");
                    t = scanner.nextLine();  //to store the input value before validating
                    d = sdf.parse(t);  //assume that the clinic is opened for 24 hours
                    isInRange = d.after(start) && d.before(end);
                }
                this.time = sdf.format(d);
            } catch (ParseException e) {
                System.out.println("Entered time is incorrect, Please try again!");
                this.time =null;

            }
        }
    }
    //to validate the time entered in gui
    public  boolean timeValidate(String t){
        boolean val=false;
        //if (this.time == null) {
            try {
                //time format
                SimpleDateFormat sdf = new SimpleDateFormat("hh.mm a");
                sdf.setLenient(false);  //to make sure that system doesn't assign a value if it is a wrong one

                Date d = sdf.parse(t);
                this.time = sdf.format(d);
                Date start = sdf.parse("07.59 AM");
                Date end = sdf.parse("09.59 PM");

                boolean isInRange = d.after(start) && d.before(end);
                if(isInRange == false){
                    val = false;
                }
                else{
                    val = true;
                }
            } catch (ParseException e) {
                System.out.println("Entered time is incorrect, Please try again!");
                val = false;

            //}
        }
        return val;
    }

    //getter for year
    public int getYear(){

        return this.year;
    }

    //getter for month
    public int getMonth(){

        return this.month;
    }

    //getter for day
    public int getDay() {

        return this.day;
    }

    // getter for formatted date
    public String setFormattedDate(){
        return this.day + "/" + this.month + "/" + this.year;
    }

    //getter for time
    public String getTime(){

        return this.time;
    }

}
