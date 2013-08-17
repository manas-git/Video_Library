import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class CustomerForm extends JPanel  {

    private JTextField[] fields;

    private int[] messageType =
            { JOptionPane.PLAIN_MESSAGE };
    private int[] buttonTypes =
            { JOptionPane.DEFAULT_OPTION, JOptionPane.YES_NO_OPTION,
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.OK_CANCEL_OPTION };

    public CustomerForm(){

    }
    public CustomerForm(String[] labels, char[] mnemonics, int[] widths, String[] tips) {

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
            final JLabel lab2=new JLabel();
            p.add(lab2);
            if(i==3){

                fields[3].addKeyListener(new KeyAdapter() {
                    public void keyTyped(KeyEvent e) {

                        String input = fields[3].getText();
                        Pattern p = Pattern.compile("[A-Z,a-z,&%$@!()*^]");
                        Matcher m = p.matcher(input);
                        if (m.find()||input.length()>9) {
                            lab2.setText("* Enter only Numeric Value or Max 10 digits");
                            lab2.setVisible(true);
                            lab2.setForeground(Color.red);
                        }
                        else{
                            lab2.setVisible(false);
                        }
                    }
                });
            }
        }
    }

    public String getText(int i) {
        return (fields[i].getText());
    }

    public  void testMain() {
        String[] labels = { "CustomerID","Name", "Address", "Phone" };
        char[] mnemonics = { 'C','N', 'A', 'P' };
        int[] widths = { 4,20, 45, 15 };
        String[] descs = {"CustomerID", "Name", "Address", "Phone"  };
        JLabel tt = new JLabel("Customer Details");


        final CustomerForm form = new CustomerForm(labels, mnemonics, widths, descs);

        JButton submit = new JButton("Add Customer");

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(form.getText(0) + " " + form.getText(1) + ". " + form.getText(2)
                        + "");
                Customer c = new Customer();
                int cc=c.add(form.getText(1), form.getText(2),form.getText(3) );
                JOptionPane.showMessageDialog(null,"Your CustomerID is "+cc);
                System.out.println("Your CustomerID is "+cc);
                form.fields[1].setText("");
                form.fields[2].setText("");
                form.fields[3].setText("");
            }
        });

        JButton submit1 = new JButton("Delete Customer");

        submit1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(form.getText(0) + " " + form.getText(1) + ". " + form.getText(2)
                        + "");
                Customer c = new Customer();
                c.delete(form.getText(1), form.getText(2),form.getText(3) );
                form.fields[0].setText("");
                form.fields[1].setText("");
                form.fields[2].setText("");
                form.fields[3].setText("");
            }
        });

        JButton submit2 = new JButton("Update Customer");

        submit2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(form.getText(0) + " " + form.getText(1) + ". " + form.getText(2)
                        + "");
                Customer c = new Customer();
                c.update(Integer.parseInt(form.getText(0)) ,form.getText(1), form.getText(2), form.getText(3));
                form.fields[0].setText("");
                form.fields[1].setText("");
                form.fields[2].setText("");
                form.fields[3].setText("");
            }
        });


        JButton submit3 = new JButton("Find Customer");

        submit3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(form.getText(0));
                Customer c = new Customer();
                try{
                    ArrayList rs = c.retrieve(Integer.parseInt(form.getText(0))) ;

                    if(rs.isEmpty()==false){

                        form.fields[1].setText(String.valueOf(rs.get(0)));
                        form.fields[2].setText(String.valueOf(rs.get(1)));
                        form.fields[3].setText(String.valueOf(rs.get(2)));
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

        JFrame f = new JFrame("Customer Form");
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
