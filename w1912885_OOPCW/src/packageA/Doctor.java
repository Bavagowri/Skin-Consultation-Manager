package packageA;

import java.util.ArrayList;
import java.util.Scanner;

public class Doctor extends Person {
    //Creating instance variables
    private String licenseNo;
    private String specialisation;

    //to get user input
    Scanner scanner = new Scanner(System.in);

    public String validateLicenseNo(){
        System.out.print("Enter the doctor's License No : ");
        String licenseNo = scanner.nextLine();

        String regex = "\\d{5}";  //to make sure to enter 5 numbers for license no
        while (!licenseNo.matches(regex)){
            System.out.println("Entered license number is not valid enter 5 digits no");
            System.out.print("Enter License No : ");
            licenseNo = scanner.nextLine();
        }
        return licenseNo;
    }
    public String validateSpecialisation(){
        //Assume that only the given specialisation can be entered in the system
        System.out.println("""
                Specialisation categories:
                Dermapathology
                Cosmetic Dermatology
                Medical Dermatology
                Pediatric Dermatology
                """);
        System.out.print("Enter the doctor's specialisation from above : ");
        String specialisation = scanner.nextLine().toUpperCase();

        //to validate the specialisation given
        ArrayList <String> specialist = new ArrayList<>();
        specialist.add("DERMAPATHOLOGY");
        specialist.add("COSMETIC DERMATOLOGY");
        specialist.add("MEDICAL DERMATOLOGY");
        specialist.add("PEDIATRIC DERMATOLOGY");//to check with the given value
        while(!specialist.contains(specialisation)){
            System.out.println("Entered specialisation is wrong try again!");// error message to display if the specialisation doesn't match value in specialist array
            //to get the input
            System.out.println("""
                    Specialisation categories:
                    Dermapathology
                    Cosmetic Dermatology
                    Medical Dermatology
                    Pediatric Dermatology
                    """);
            System.out.print("Enter the doctor's specialisation from above : ");
            specialisation = scanner.nextLine().toUpperCase();
        }
        return specialisation;
    }

    //setter for licenseNo
    public void setLicenseNo(String licenseNo){

        this.licenseNo = licenseNo;
    }

    //setter for specialisation
    public void setSpecialisation(String specialisation){

        this.specialisation = specialisation;
    }

    //getter for license no
    public String getLicenseNo(){

        return this.licenseNo;
    }

    //getter for specialisation
    public String getSpecialisation(){

        return  this.specialisation;
    }

}
