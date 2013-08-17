import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
//import javax.swing.JIntField;


public class RentalForm extends JPanel {

    private int[] messageType =
            { JOptionPane.PLAIN_MESSAGE };
    private int[] buttonTypes =
            { JOptionPane.DEFAULT_OPTION, JOptionPane.YES_NO_OPTION,
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.OK_CANCEL_OPTION };

    public RentalForm(){
    }

    private JTextField[] fields;
    public RentalForm(String[] labels, char[] mnemonics, int[] widths, String[] tips) {

        super(new BorderLayout());

        JPanel labelPanel = new JPanel(new GridLayout(labels.length, 1));
        JPanel fieldPanel = new JPanel(new GridLayout(labels.length, 1));


        add(labelPanel, BorderLayout.WEST);
        add(fieldPanel, BorderLayout.CENTER);
        fields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i += 1) {
            fields[i] = new JTextField();
            if (i < tips.length)
                fields[i].setToolTipText(tips[i]);
            if (i < widths.length)
                fields[i].setColumns(widths[i]);

            JLabel lab = new JLabel(labels[i], JLabel.RIGHT);
            lab.setLabelFor(fields[i]);
            if (i < mnemonics.length)
                lab.setDisplayedMnemonic(mnemonics[i]);

            labelPanel.add(lab);
            JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p.add(fields[i]);
            fieldPanel.add(p);
        }
    }

    public String getText(int i) {
        return (fields[i].getText());
    }

    public void rentalTest() {
        String[] labels = { "CID", "VID", "DueDate"};
        char[] mnemonics = { 'C', 'V', 'D' };
        int[] widths = { 20, 20, 15};
        String[] descs = { "CID", "VID", "DueDate"};
        JLabel tt = new JLabel("Rental Details");


        final RentalForm form = new RentalForm(labels, mnemonics, widths, descs);

        JButton submit = new JButton("Add Rent");

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(form.getText(0) + " " + form.getText(1) + ". " + form.getText(2)
                        + "");
                Rental r = new Rental();
                r.add(Integer.parseInt(form.getText(0)) ,Integer.parseInt(form.getText(1)));
                form.fields[0].setText("");
                form.fields[1].setText("");
            }
        });

        JButton submit1 = new JButton("Delete Rent");

        submit1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(form.getText(0) + " " + form.getText(1) + ". " + form.getText(2)
                        + "");
                Rental r = new Rental();
                r.delete(Integer.parseInt(form.getText(0)) ,Integer.parseInt(form.getText(1)), form.getText(2));
                form.fields[0].setText("");
                form.fields[1].setText("");
                form.fields[2].setText("");
            }
        });

        JButton submit2 = new JButton("Update Rent");

        submit2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(form.getText(0) + " " + form.getText(1) + ". " + form.getText(2)
                        + "");
                Rental r = new Rental();
                r.update(Integer.parseInt(form.getText(0)) ,Integer.parseInt(form.getText(1)), form.getText(2));
                form.fields[0].setText("");
                form.fields[1].setText("");
                form.fields[2].setText("");
            }
        });


        JButton submit3 = new JButton("Find Rent");

        submit3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(form.getText(0));
                Rental r = new Rental();
                try{
                    ArrayList rs = r.retrieve(Integer.parseInt(form.getText(0)),Integer.parseInt(form.getText(1))) ;

                    if(rs.isEmpty()==false){


                        form.fields[2].setText(String.valueOf(rs.get(0)));

                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Sorry!no data found.Please enter valid data ");
                    }

                }catch(Exception ee)
                {
                    System.out.println(ee.getMessage());
                    ee.printStackTrace();
                }


            }
        });




        JFrame f = new JFrame("Rental Form");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(form, BorderLayout.NORTH);
        JPanel p = new JPanel();

        p.add(submit);
        p.add(submit1);
        p.add(submit2);
        p.add(submit3);
        f.getContentPane().add(p, BorderLayout.SOUTH);
        f.pack();
        f.setVisible(true);
    }

    protected static int getInt(int i) {

        return 0;
    }
}
