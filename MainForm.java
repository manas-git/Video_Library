import java.awt.Container;
import javax.swing.Action;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicArrowButton;

public class MainForm extends JApplet {
    JButton button = new JButton("Customer");
    public void init() {
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        cp.add(button);
        JButton jb= new JButton("Video");
        cp.add(jb);
        jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                VideoForm vf =new VideoForm();
                vf.videoTest();

            }});

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Customer c = new Customer();

                CustomerForm cf= new CustomerForm();
                cf.testMain();
            }
        });

        JButton jt= new JButton("Rental");
        jt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Customer c = new Customer();

                RentalForm rf= new RentalForm();
                rf.rentalTest();
            }
        });

        cp.add(jt);

        JButton jo= new JButton("Overdue Report");
        jo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Customer c = new Customer();

                Table rf= new Table();
                rf.report();
            }
        });

        cp.add(jo);

        JButton pp= new JButton("Print Report");
        pp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                PrintUIWindow p= new PrintUIWindow();
                p.Print();
            }
        });

        cp.add(pp);

        JButton bp= new JButton(" Create Customer Backup");
        bp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Customer b= new Customer();
                b.backUp();
            }
        });

        cp.add(bp);
    }

    public static void main(String[] args) {
        run(new MainForm(), 500, 200);
    }

    public static void run(JApplet applet, int width, int height) {
        JFrame frame = new JFrame("Video Rental System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(applet);
        frame.setSize(width, height);
        applet.init();
        applet.start();
        frame.setVisible(true);
    }
} 
