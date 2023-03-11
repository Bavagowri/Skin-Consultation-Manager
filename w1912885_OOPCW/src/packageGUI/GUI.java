package packageGUI;


// Packages to import
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    static JFrame frame = new JFrame();
    JLabel welcome = new JLabel("Welcome to Westminster Skin Consultation Center!");
    JLabel details = new JLabel("Please CLICK on the button to Get start Consultation");
    private JButton start;

    public GUI(){
        welcome.setBounds(200,100,800,150);
        welcome.setFont(new Font(null,Font.PLAIN,15));

        details.setBounds(200,150,800,150);
        details.setFont(new Font(null,Font.PLAIN,15));

        frame.add(welcome);
        frame.add(details);

        start = new JButton("Click to get Started");
        start.setBounds(300,400,200,100);
        start.addActionListener(this); //to do something if button clicked
        frame.add(start);

       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setLayout(null);
        frame.setTitle("Westminster Skin Consultation Center!");
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == start){
            PatientGUI p =new PatientGUI(); //to open new window with the table
            frame.dispose();  //to open only one window
        }

    }
}
