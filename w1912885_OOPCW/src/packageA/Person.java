package packageA;

import java.util.Scanner;

public class Person {

    //Creating instance variables
    private String name;
    private String surname;
    private String dateOfBirth;
    private String mobile;

    String checkName = "^[A-Za-z]+$"; //created to validate the name and surname

    /*
    {4} - means 4 digit s for year
    /- to format with /
    month can start with either 0 therefore 0 and [1-9] can have 1 to 9 months
    and for 10 or 11 or 12 |1[0-2]
    same way for date
    */
    String checkDOB = "^[0-9]{4}/(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])";// created to validate the Date of birth


    //to get user input
    Scanner scanner = new Scanner(System.in);

    //to get user input for variables and validate them
    public String validateName(){
        System.out.print("Enter Name : ");
        String name = scanner.nextLine();
        //to make sure the entered value is a string and has more than 2 character
        while(!name.matches(this.checkName) || name.length()<3){
            System.out.println("Entered name is not Valid!\n"); // to output if the value is not valid
            System.out.print("Enter your Name : ");
            name = scanner.nextLine();
        }
        return name;
    }

    public String validateSurName(){
        System.out.print("Enter Surname : ");
        String surname = scanner.nextLine();
        while(!surname.matches(this.checkName) || surname.length()<3){
            System.out.println("Entered surname is not Valid!\n"); // to output if the value is not valid
            System.out.print("Enter  surName : ");
            surname = scanner.nextLine();
        }
        return surname;
    }

    public  String validateDOB(){
        System.out.print("Enter Date Of Birth (YYYY/MM/DD): ");
        String dob = scanner.nextLine();

        // to validate the date and get input until valid date is entered
        while(!dob.matches(this.checkDOB)){
            System.out.print("Entered date is not valid enter again\n");
            System.out.print("Enter Date Of Birth (YYYY/MM/DD): ");
            dob = scanner.nextLine();
        }
        return dob;
    }

    public  String validateMobile(){
        System.out.print("Enter Mobile No : ");
        String mobNo = scanner.nextLine();

        String regex = "\\d{10}";  //to make sure to enter 10 numbers for mobile no
        while (!mobNo.matches(regex)){
            System.out.println("Entered mobile number is not valid enter 10 digits no");
            System.out.print("Enter Mobile No : ");
            mobNo = scanner.nextLine();
        }
        return mobNo;
    }

    //setter for name
    public void setName(String name){
        this.name = name;
    }

    //setter for surname
    public void setSurName(String surname){
        this.surname = surname;
    }

    //setter for DOB
    public void setDateOfBirth(String dob){

        this.dateOfBirth = dob;
    }

    //setter for mobile
    public void setMobile(String mobNo){
        this.mobile = mobNo;
    }

    //getter for name
    public String getName(){

        return this.name;
    }

    //getter for surname
    public String getSurName(){

        return this.surname;
    }

    //getter for DOB
    public String getDateOfBirth(){

        return this.dateOfBirth;
    }

    //getter for mobile
    public String getMobile(){

        return this.mobile;
    }

}
