import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Table {

    public Table(){

    }
    public  void report() {
        JFrame f = new JFrame("JTable Sample");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

            JTable table = new JTable(rows, columns);

            JScrollPane scrollPane = new JScrollPane(table);
            content.add(scrollPane, BorderLayout.CENTER);
        }
        f.setSize(300, 200);
        f.setVisible(true);
    }
}

