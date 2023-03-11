package packageA;

import javax.print.DocFlavor;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Patient extends Person {
    private String patientId;


    //setter for patient ID
    public void setPatientId(String id){
        this.patientId = id;
    }

    //getter for patient ID
    public String getPatientId(){

         return this.patientId;
    }

}
