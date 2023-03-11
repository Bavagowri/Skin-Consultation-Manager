package packageA;

public interface SkinConsultationManager {
    void addDoctor();      //to add doctor to the system
    void deleteDoctor();   //delete a doctor from a system
    void listOfDoctor();  //to display a list of doctor added to the system
    void saveInFile();   //to save the data to a file
    void readDocFile();     //to read the doc file when restarting the program
    void readPatientFile();  //to read the patient file when restarting the program
    void readConsultationFile(); //to read the consultation file when restarting the program
    void addConsultation();  //to add a consultation with doctor from console
    void deleteConsultation(); //to delete a consultation from console
    void viewConsultationDetails(); //to view to consultation details from console



}
