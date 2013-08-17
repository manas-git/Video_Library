import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class PrintUIWindow implements Printable, ActionListener {
    public PrintUIWindow(){

    }

    JFrame frameToPrint;

    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

        if (page > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        frameToPrint.printAll(g);


        return PAGE_EXISTS;
    }

    public void actionPerformed(ActionEvent e) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {

            }
        }
    }

    public PrintUIWindow(JFrame f) {
        frameToPrint = f;
    }

    public  void Print() {
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        JFrame f = new JFrame("Print UI Example");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        Container content = f.getContentPane();
        Rental tt = new Rental();
        ArrayList al=  tt.retrieveOverdue();

        if(al.isEmpty()==false){
            Iterator kk  = al.iterator();
            Object rows[][] = new Object[100][100];
            if(kk.hasNext()){
                int cc = 0;
                while(kk.hasNext()){
                    rows[cc][0]=kk.next();
                    rows[cc][1]=kk.next();
                    rows[cc][2]=kk.next();


                    cc++;
                }
            }


            Object columns[] = { "Customer", "VIDEO Title", "DUE DATE" };

            JTable table = new JTable(rows,columns);

            JScrollPane pane = new JScrollPane(table);
            pane.setPreferredSize(new Dimension(250, 200));
            f.add("Center", pane);
        }
        JButton printButton = new JButton("Print This Window");
        printButton.addActionListener(new PrintUIWindow(f));
        f.add("South", printButton);
        f.pack();
        f.setVisible(true);
    }
}
