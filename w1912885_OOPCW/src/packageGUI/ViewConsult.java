package packageGUI;

import javax.swing.*;
import java.awt.*;

public class ViewConsult {
    static JFrame frame = new JFrame();
    JLabel welcome = new JLabel("Welcome to Westminster Skin Consultation Center!");
    public ViewConsult(String doc, String patient, String id, float cost,String date){
        welcome.setBounds(200,100,800,100);
        welcome.setFont(new Font(null,Font.BOLD,15));
        frame.add(welcome);
        frame.setSize(800,800);
        frame.setLayout(null);
        frame.setTitle("Westminster Skin Consultation Center!");
        frame.setVisible(true);

        JLabel l1,l2,l3,l4,l5;
        l1=new JLabel("Patient name : "+patient);
        l1.setBounds(200,200,800,100);
        l1.setFont(new Font(null,Font.PLAIN,15));

        l2=new JLabel("Doc name : "+doc);
        l2.setBounds(200,300,800,100);
        l2.setFont(new Font(null,Font.PLAIN,15));

        l3=new JLabel("Patient id: "+id);
        l3.setBounds(200,400,800,100);
        l3.setFont(new Font(null,Font.PLAIN,15));

        l5=new JLabel("Date : "+date);
        l5.setBounds(200,500,800,100);
        l5.setFont(new Font(null,Font.PLAIN,15));

        l4=new JLabel("consultation fee : "+cost);
        l4.setBounds(200,600,800,100);
        l4.setFont(new Font(null,Font.PLAIN,15));
        frame.add(l1);
        frame.add(l2);
        frame.add(l3);
        frame.add(l5);
        frame.add(l4);
    }
}
