import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VideoForm extends JPanel   {

    public VideoForm(){

    }
    private JTextField[] fields;
    private int[] messageType =
            { JOptionPane.PLAIN_MESSAGE };
    private int[] buttonTypes =
            { JOptionPane.DEFAULT_OPTION, JOptionPane.YES_NO_OPTION,
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.OK_CANCEL_OPTION };

    public VideoForm(String[] labels, char[] mnemonics, int[] widths, String[] tips) {

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

    public  void videoTest() {
        String[] labels = { "VID", "Title", "Year","Genre","Producer"};
        char[] mnemonics = { 'V', 'T', 'Y','G','P'};
        int[] widths = { 20, 45, 15,5,15,15};
        String[] descs = {"VID", "Title", "Year","Genre","Producer"};
        JLabel tt = new JLabel("Video Details");


        final VideoForm form = new VideoForm(labels, mnemonics, widths, descs);

        JButton submit = new JButton("Add Video");

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(form.getText(0) + " " + form.getText(1) + ". " + form.getText(2)
                        + "");
                Video v = new Video();
                v.add(form.getText(1) ,form.getText(2), form.getText(3),form.getText(4));
                form.fields[0].setText("");
                form.fields[1].setText("");
                form.fields[2].setText("");
                form.fields[3].setText("");
                form.fields[4].setText("");

            }
        });


        JButton submit1 = new JButton("Delete Video");

        submit1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(form.getText(0) + " " + form.getText(1) + ". " + form.getText(2)
                        + "");
                Video v = new Video();
                v.delete(Integer.parseInt(form.getText(0))); //,Integer.parseInt(form.getText(1)), form.getText(2));
                form.fields[0].setText("");

            }
        });

        JButton submit2 = new JButton("Update Video");

        submit2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(form.getText(0) + " " + form.getText(1) + ". " + form.getText(2)
                        + "");
                Video v = new Video();
                v.update(Integer.parseInt(form.getText(0)) ,form.getText(1), form.getText(2),form.getText(3),form.getText(4));
                form.fields[0].setText("");
                form.fields[1].setText("");
                form.fields[2].setText("");
                form.fields[3].setText("");
                form.fields[4].setText("");
            }
        });


        JButton submit3 = new JButton("Find Video");

        submit3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(form.getText(0));
                Video v = new Video();
                try{
                    ArrayList rs =  v.retrieve(Integer.parseInt(form.getText(0)), form.getText(1)) ;

                    if(rs.isEmpty()==false){

                        form.fields[1].setText(String.valueOf(rs.get(0)));
                        form.fields[2].setText(String.valueOf(rs.get(1)));
                        form.fields[3].setText(String.valueOf(rs.get(2)));
                        form.fields[4].setText(String.valueOf(rs.get(3)));
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

        JFrame f = new JFrame("Video Form");
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
