import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class Record_Listing
{
    private static JFrame iFrame;
    private static ArrayList<Gas_Records> grom;
    private static ArrayList<MiniMart_Records> menn;
    private static ArrayList<String> dt;
    private static ArrayList<String> dt2;
    private static ArrayList<Integer> idConfirm;
    private static ArrayList<Integer> idConfirm2;
    private static DefaultTableModel model;
    private static JScrollPane scrollPane;
    private static JTable table;
    private static boolean status;
    private static JFrame efFrame;
    private static JFrame sec;
    private static JFrame sec2;
    private static JFrame mmFrame;
    private static JTextField ef1Text;
    private static JTextField ef2Text;
    private static JTextField ef3Text;
    private static JTextField ef4Text;
    private static JTextField mm1Text;
    private static JTextField mm2Text;
    private static JTextField mm3Text;
    private static JTextField mm4Text;
    private static JTextField nam;
    private static JTextField text;
    private static JTextField text2;
    private static JFrame del;
    private static JTextField nam2;
    private static JTextField text22;
    private static JTextField text222;
    private static JTextField text2222;
    private static JTextField dTextMsg;
    private static JFrame errorScreen;
    private static JFrame success;
    private static JFrame del2;
    private static JTextField dTextMsg2;
    private static JFrame search;
    private static JTextField searchText;
    private static JFrame jm;
    private static JFrame jm2;
    private static JTextField txtMsg;
    private static JTextField txtMsg2;
    private static JFrame search2;
    private static JTextField searchText2;
    private static JFrame success2;
    private static String currName;
    private static int currID;
    private static int currID2;
    private static String currDte;
    private static String currDate;
    private static double currPrice;
    private static String newName;
    private static int currQuantity;
    private static double newPrice;
    private static int newQuantity;
    private static String currName2;
    private static String currDate2;
    private static double currPrice2;
    private static String newName2;
    private static int currQuantity2;
    private static double newPrice2;
    private static int newQuantity2;
    private static double newVolume;
    private static double currVolume;
    private static int count = 0;
    private static int num1;
    private static int num2;
    public static final Color VERY_LIGHT_BLUE = new Color(51,204,255);

    public static void Inventory()
    {
        Log_In.setFOwner();

        grom = loadGasSales("EFSales.txt");
        dt = loadDates("Date.txt");

        num1 = idCalc();

        menn = loadMMSales("MMSales.txt");
        dt2 = loadDates2("Date2.txt");

        num2 = idCalc2();

        String [] columnNames =  {"Name", "Price", "Quantity","Date Added", "Time Added", "ID Number"};//This is the column headers for the table.
        model = new DefaultTableModel(columnNames,0);//Row Count set to 0.
        table = new JTable(model);//Create a new JTable.

        showTable(grom,dt);

        table.setPreferredScrollableViewportSize(new Dimension(630, 250));//Sets the preferred size of the viewport for this table.
        table.setFillsViewportHeight(true);//Sets whether or not this table is always made large enough to fill the height of an enclosing viewport.
        table.setBackground(VERY_LIGHT_BLUE);//Sets the background of the table to light grey.

        scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel();
        panel.setBackground(VERY_LIGHT_BLUE);
        panel.add(scrollPane);

        JButton btn1 = new JButton("New Purchase");
        btn1.setBounds(10,300,130,25);
        btn1.addActionListener(new NewListener());
        btn1.setBackground(Color.YELLOW);
        btn1.setFocusable(false);

        JButton btn2 = new JButton("Delete Purchase");
        btn2.setBounds(150,300,130,25);
        btn2.addActionListener(new DeleteListener());
        btn2.setBackground(Color.YELLOW);
        btn2.setFocusable(false);

        JButton btn3 = new JButton("Edit Purchase");
        btn3.setBounds(290,300,130,25);
        btn3.addActionListener(new EditListener());
        btn3.setBackground(Color.YELLOW);
        btn3.setFocusable(false);

        JButton button1 = new JButton("Stock Inventory");
        button1.setBounds(480,300,130,25);
        button1.addActionListener(new Stock_Inventory.StockListener());
        button1.setBackground(Color.YELLOW);
        button1.setFocusable(false);

        JButton button2 = new JButton("Back");
        button2.setBounds(480,340,130,25);
        button2.addActionListener(new CloseListener());
        button2.setBackground(Color.YELLOW);
        button2.setFocusable(false);

        JButton btn4 = new JButton("Gas Sales");
        btn4.setBounds(10,340,130,25);
        btn4.addActionListener(new StatusListener());
        btn4.setBackground(Color.YELLOW);
        btn4.setFocusable(false);

        JButton btn5 = new JButton("Sort by Name");
        btn5.setBounds(150,340,130,25);
        btn5.addActionListener(new SortNameListener());
        btn5.setBackground(Color.YELLOW);
        btn5.setFocusable(false);

        JButton btn6 = new JButton("M.M. Sales");
        btn6.setBounds(290,340,130,25);
        btn6.addActionListener(new Status2Listener());
        btn6.setBackground(Color.YELLOW);
        btn6.setFocusable(false);

        JButton btn8 = new JButton("Search Records");
        btn8.setBounds(150,375,130,25);
        btn8.setFocusable(false);
        btn8.setBackground(Color.YELLOW);
        btn8.addActionListener(new SearchListener());

        iFrame = new JFrame("Record Management");
        iFrame.setPreferredSize(new Dimension(650, 500));
        iFrame.setLocationRelativeTo(null);
        iFrame.add(btn1);
        iFrame.add(btn2);
        iFrame.add(btn3);
        iFrame.add(btn4);
        iFrame.add(btn5);
        iFrame.add(btn6);
        iFrame.add(btn8);
        iFrame.add(button1);
        iFrame.add(button2);
        iFrame.add(panel);
        iFrame.pack();

        iFrame.setVisible(true);
    }

    public static int idCalc()
    {
        if (idConfirm.size() != 0)
        {
            int temp;

            for (int i = 0; i < idConfirm.size(); i++)
            {
                for (int j = 0; j < idConfirm.size(); j++)
                {
                    if (idConfirm.get(i) > idConfirm.get(j))
                    {
                        temp = idConfirm.get(i);

                        idConfirm.set(i,idConfirm.get(j));

                        idConfirm.set(j,temp);
                    }
                }
            }
            return idConfirm.get(0);
        }
        else
        {
            return 0;
        }
    }

    public static int idCalc2()
    {
        if (idConfirm2.size() != 0)
        {
            int temp;

            for (int i = 0; i < idConfirm2.size(); i++)
            {
                for (int j = 0; j < idConfirm2.size(); j++)
                {
                    if (idConfirm2.get(i) > idConfirm2.get(j))
                    {
                        temp = idConfirm2.get(i);

                        idConfirm2.set(i,idConfirm2.get(j));

                        idConfirm2.set(j,temp);
                    }
                }
            }
            return idConfirm2.get(0);
        }
        else
        {
            return 0;
        }
    }

    private static ArrayList<Gas_Records> loadGasSales(String vfile)
    {
        ArrayList<Gas_Records> plist = new ArrayList<Gas_Records>();
        idConfirm = new ArrayList<>();

        if (vfile.length() != 0)
        {
            Scanner vscan = null;

            try
            {
                vscan = new Scanner(new File(vfile));

                while (vscan.hasNext())
                {
                    String[] nextLine = vscan.nextLine().split(" ");
                    String name = nextLine[0];
                    double price = Double.parseDouble(nextLine[1]);
                    int quantity = Integer.parseInt(nextLine[2]);
                    int id = Integer.parseInt(nextLine[4]);

                    Gas_Records p = new Gas_Records(name, price, quantity);
                    p.setId(id);

                    plist.add(p);
                    idConfirm.add(id);
                }
            }
            catch (IOException e)
            {
                System.out.println("ERROR1");
            }
        }
        return plist;
    }

    private static ArrayList<MiniMart_Records> loadMMSales(String vfile)
    {
        ArrayList<MiniMart_Records> plist = new ArrayList<MiniMart_Records>();
        idConfirm2 = new ArrayList<>();

        if (vfile.length() != 0)
        {
            Scanner vscan = null;

            try
            {
                vscan = new Scanner(new File(vfile));

                while (vscan.hasNext())
                {
                    String[] nextLine = vscan.nextLine().split(" ");
                    String name = nextLine[0];
                    double price = Double.parseDouble(nextLine[1]);
                    int quantity = Integer.parseInt(nextLine[2]);
                    double volume = Double.parseDouble(nextLine[3]);
                    int id = Integer.parseInt(nextLine[4]);

                    MiniMart_Records p = new MiniMart_Records(name, price, quantity,volume);
                    p.setId(id);

                    plist.add(p);
                    System.out.println(id);
                    idConfirm2.add(id);
                }
            }
            catch (IOException e)
            {
                System.out.println("ERROR2");
            }
        }
        return plist;
    }

    private static ArrayList<String> loadDates(String dFile)
    {
        ArrayList<String> dList = new ArrayList<String>();
        int num1 = 0;

        if (dFile.length() != 0)
        {
            Scanner dScan = null;

            try {
                dScan = new Scanner(new File(dFile));

                while (dScan.hasNext()) {
                    String[] nextLine = dScan.nextLine().split(" ");
                    String date = nextLine[1];
                    String time = nextLine[2];
                    String status = nextLine[3];
                    String id = nextLine[4];

                    dList.add(grom.get(num1).getName() + " " + date + " " + time + " " + status + " " + id);

                    num1 += 1;
                }
            } catch (Exception e) {
                System.out.println("ERROR3");
            }
        }
        return dList;
    }

    private static ArrayList<String> loadDates2(String dFile)
    {
        ArrayList<String> dList = new ArrayList<String>();
        int num1 = 0;

        if (dFile.length() != 0)
        {
            Scanner dScan = null;

            try {
                dScan = new Scanner(new File(dFile));

                while (dScan.hasNext()) {
                    String[] nextLine = dScan.nextLine().split(" ");
                    String date = nextLine[1];
                    String time = nextLine[2];
                    String status = nextLine[3];
                    String id = nextLine[4];

                    dList.add(menn.get(num1).getName() + " " + date + " " + time + " " + status + " " + id);

                    num1 += 1;
                }
            } catch (Exception e) {
                System.out.println("ERROR4");
            }
        }
        return dList;
    }

    private static void showTable(ArrayList<Gas_Records> glist, ArrayList<String> dList)
    {
        for (int i = 0; i < glist.size(); i++)
        {
            String [] tm = dList.get(i).split(" ");
            String date = tm[1];
            String time = tm[2];
            String status = tm[3];
            //String id = Integer.toString(idConfirm.get(i));

            addToTable(glist.get(i), Integer.toString(glist.get(i).getId()), date, time + " " + status);
        }
    }

    private static void showTable2(ArrayList<MiniMart_Records> glist, ArrayList<String> dList)
    {
        for (int i = 0; i < glist.size(); i++)
        {
            String [] tm = dList.get(i).split(" ");
            String date = tm[1];
            String time = tm[2];
            String status = tm[3];

            addToTable2(glist.get(i), Integer.toString(glist.get(i).getId()), date, time + " " + status);
        }
    }

    public static void addGasRecord(Gas_Records g)
    {
        String cDate;
        String cTime;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatter_2 = new SimpleDateFormat("hh:mm a");

        Date date = new Date();
        Date time = new Date();

        cDate = formatter.format(date);
        cTime = formatter_2.format(time);

        String id = Integer.toString(g.getId());

        try
        {
            File file = new File("Date.txt");

            if (!file.exists())
            {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, true);

            fw.write(g.getName() + " ");
            fw.write(cDate + " ");
            fw.write(cTime + " ");
            fw.write(id + "\n");
            fw.close();
        }
        catch (Exception e)
        {
            System.out.println("Error5");
        }

        addToTable(g, id, cDate, cTime);
    }

    public static void addMMRecords(MiniMart_Records m)
    {
        String cDate;
        String cTime;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatter_2 = new SimpleDateFormat("hh:mm a");

        Date date = new Date();
        Date time = new Date();

        cDate = formatter.format(date);
        cTime = formatter_2.format(time);

        String id = Integer.toString(m.getId());

        try
        {
            File file = new File("Date2.txt");

            if (!file.exists())
            {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, true);

            fw.write(m.getName() + " ");
            fw.write(cDate + " ");
            fw.write(cTime + " ");
            fw.write(id + "\n");
            fw.close();
        }
        catch (Exception e)
        {
            System.out.println("Error6");
        }

        addToTable2(m, id, cDate, cTime);
    }

    private static void addToTable(Gas_Records g, String id, String date, String time)
    {
        String name = g.getName();
        String[] item = {name, "" + g.getPrice(), "" + g.getQuantity(), date, time, "" + id};

        model.addRow(item);
    }

    private static void addToTable2(MiniMart_Records m, String id, String date, String time)
    {
        String name = m.getName();
        String[] item = {name, "" + m.getPrice(), "" + m.getQuantity(), date, time, "" + id};

        model.addRow(item);
    }

    private static class CloseListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            iFrame.setVisible(false);
            Log_In.setTOwner();
        }
    }

    private static class StatusListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            iFrame.setTitle("Gas Sales Inventory");
            status = true;

            model.setRowCount(0);
            grom = loadGasSales("EFSales.txt");
            dt = loadDates("Date.txt");
            showTable(grom,dt);
        }
    }

    private static class Status2Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            iFrame.setTitle("Mini-Mart Sales Inventory");
            status = false;

            model.setRowCount(0);
            menn = loadMMSales("MMSales.txt");
            dt2 = loadDates2("Date2.txt");
            showTable2(menn,dt2);
        }
    }

    private static class NewListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (status)
            {
                JPanel efPanel = new JPanel();
                efPanel.setBackground(VERY_LIGHT_BLUE);

                efFrame = new JFrame("Gas Sales entry");
                efFrame.setPreferredSize(new Dimension(600, 400));
                efFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                efFrame.setLocationRelativeTo(null);
                efFrame.add(efPanel);
                efFrame.pack();

                efPanel.setLayout(null);

                JLabel label5 = new JLabel("Please enter information for Engine Fluid product");
                label5.setBounds(100,20,390,25);
                efPanel.add(label5);

                JLabel label = new JLabel("Name");
                label.setBounds(10, 60, 80, 25);
                efPanel.add(label);

                ef1Text = new JTextField(30);
                ef1Text.setBounds(100,60,165,25);
                efPanel.add(ef1Text);

                JLabel label2 = new JLabel("price");
                label2.setBounds(10, 100, 80, 25);
                efPanel.add(label2);

                ef2Text = new JTextField(30);
                ef2Text.setBounds(100,100,165,25);
                efPanel.add(ef2Text);

                JLabel label3 = new JLabel("quantity");
                label3.setBounds(10,140,80,25);
                efPanel.add(label3);

                ef3Text = new JTextField(30);
                ef3Text.setBounds(100,140,165,25);
                efPanel.add(ef3Text);

                JLabel label4 = new JLabel("Week");
                label4.setBounds(10,180,80,25);
                efPanel.add(label4);

                ef4Text = new JTextField(30);
                ef4Text.setBounds(100,180,165,25);
                efPanel.add(ef4Text);

                JButton a4button = new JButton("Add");
                a4button.setBounds(100,230,150,25);
                a4button.setBackground(Color.YELLOW);
                a4button.setFocusable(false);
                a4button.addActionListener(new EFListener());
                efPanel.add(a4button);

                JButton a3button = new JButton("Cancel");
                a3button.setBounds(280,230,150,25);
                a3button.setBackground(Color.YELLOW);
                a3button.setFocusable(false);
                a3button.addActionListener(new Close5Listener());
                efPanel.add(a3button);

                efFrame.setVisible(true);
            }
            else if (!status)
            {
                JPanel efPanel = new JPanel();
                efPanel.setBackground(VERY_LIGHT_BLUE);

                mmFrame = new JFrame("MiniMart Sales entry");
                mmFrame.setPreferredSize(new Dimension(600, 400));
                mmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mmFrame.setLocationRelativeTo(null);
                mmFrame.add(efPanel);
                mmFrame.pack();

                efPanel.setLayout(null);

                JLabel label5 = new JLabel("Please enter information for Mini-Mart product");
                label5.setBounds(100,20,390,25);
                efPanel.add(label5);

                JLabel label = new JLabel("Name");
                label.setBounds(10, 60, 80, 25);
                efPanel.add(label);

                mm1Text = new JTextField(30);
                mm1Text.setBounds(100,60,165,25);
                efPanel.add(mm1Text);

                JLabel label2 = new JLabel("price");
                label2.setBounds(10, 100, 80, 25);
                efPanel.add(label2);

                mm2Text = new JTextField(30);
                mm2Text.setBounds(100,100,165,25);
                efPanel.add(mm2Text);

                JLabel label3 = new JLabel("quantity");
                label3.setBounds(10,140,80,25);
                efPanel.add(label3);

                mm3Text = new JTextField(30);
                mm3Text.setBounds(100,140,165,25);
                efPanel.add(mm3Text);

                JLabel label4 = new JLabel("Volume");
                label4.setBounds(10,180,80,25);
                efPanel.add(label4);

                mm4Text = new JTextField(30);
                mm4Text.setBounds(100,180,165,25);
                efPanel.add(mm4Text);

                JButton a4button = new JButton("Add");
                a4button.setBounds(100,230,150,25);
                a4button.setBackground(Color.YELLOW);
                a4button.setFocusable(false);
                a4button.addActionListener(new MMListener());
                efPanel.add(a4button);

                JButton a3button = new JButton("Cancel");
                a3button.setBounds(280,230,150,25);
                a3button.setBackground(Color.YELLOW);
                a3button.setFocusable(false);
                a3button.addActionListener(new Close6Listener());
                efPanel.add(a3button);

                mmFrame.setVisible(true);
            }
        }
    }

    private static class EFListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            efFrame.setVisible(false);

            String name = ef1Text.getText();
            String price = ef2Text.getText();
            String quantity = ef3Text.getText();
            String week = ef4Text.getText();

            double price2 = Double.parseDouble(price);
            int quantity2 = Integer.parseInt(quantity);

            Gas_Records g = new Gas_Records(name,price2,quantity2);

            loadGasSales("EFSales.txt");

            int id = idCalc() + 1;
            g.setId(id);

            addGasRecord(g);

            try
            {
                File file = new File("EFSales.txt");

                if (!file.exists())
                {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file, true);

                fw.write(name);
                fw.write(" ");
                fw.write(price);
                fw.write(" ");
                fw.write(quantity);
                fw.write(" ");
                fw.write(week);
                fw.write(" ");
                fw.write(id + "\n");
                fw.close();
            }
            catch (IOException exception)
            {
                System.out.println("Error7");
            }

            iFrame.setVisible(true);
        }
    }

    private static class MMListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            mmFrame.setVisible(false);

            String name = mm1Text.getText();
            String price = mm2Text.getText();
            String quantity = mm3Text.getText();
            String volume = mm4Text.getText();

            double price2 = Double.parseDouble(price);
            int quantity2 = Integer.parseInt(quantity);
            double volume2 = Double.parseDouble(volume);

            MiniMart_Records min = new MiniMart_Records(name,price2,quantity2,volume2);

            loadMMSales("MMSales.txt");

            int id = idCalc2() + 1;
            min.setId(id);

            addMMRecords(min);

            try
            {
                File file = new File("MMSales.txt");

                if (!file.exists())
                {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file, true);

                fw.write(name);
                fw.write(" ");
                fw.write(price);
                fw.write(" ");
                fw.write(quantity);
                fw.write(" ");
                fw.write(volume);
                fw.write(" ");
                fw.write(id + "\n");
                fw.close();
            }
            catch (IOException exception)
            {
                System.out.println("Error8");
            }

            iFrame.setVisible(true);
        }
    }

    private static class Close5Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            efFrame.setVisible(false);
            iFrame.setVisible(true);
        }
    }

    private static class Close6Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            mmFrame.setVisible(false);
            iFrame.setVisible(true);
        }
    }

    public static void setWindow()
    {
        iFrame.setVisible(true);
    }

    public static void setNotWindow()
    {
        iFrame.setVisible(false);
    }

    private static class DeleteListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (status)
            {
                del = new JFrame("Delete Gas Sales Record");
                del.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                del.setLocationRelativeTo(null);

                JPanel displayInfo = new JPanel();
                displayInfo.setPreferredSize (new Dimension(600, 150));
                displayInfo.setBackground(VERY_LIGHT_BLUE);

                JPanel top = new JPanel();
                top.setPreferredSize(new Dimension(600,75));
                top.setBackground(VERY_LIGHT_BLUE);

                JPanel bottom = new JPanel();
                bottom.setPreferredSize(new Dimension(600,75));
                bottom.setBackground(VERY_LIGHT_BLUE);

                JLabel message = new JLabel("Please enter the ID of the Product you would like to delete");
                message.setFont(new Font("Verdana",Font.PLAIN,12));

                dTextMsg = new JTextField(20);

                JButton delete = new JButton("Delete");
                delete.setBackground(Color.YELLOW);
                delete.setFocusable(false);

                JButton cancel = new JButton("Cancel");
                cancel.setBackground(Color.YELLOW);
                cancel.setFocusable(false);

                delete.addActionListener(new DeleteButton_2Listener());
                cancel.addActionListener(new CancelButton_3Listener());

                top.add(message);
                bottom.add(dTextMsg);
                bottom.add(delete);
                bottom.add(cancel);

                displayInfo.add(top);
                displayInfo.add(bottom);

                del.add(displayInfo, BorderLayout.CENTER);

                del.pack();
                del.setVisible(true);
            }
            else if (!status)
            {
                del2 = new JFrame("Delete Mini-Mart Sales Record");
                del2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                del2.setLocationRelativeTo(null);

                JPanel displayInfo = new JPanel();
                displayInfo.setPreferredSize (new Dimension(600, 150));
                displayInfo.setBackground(VERY_LIGHT_BLUE);

                JPanel top = new JPanel();
                top.setPreferredSize(new Dimension(600,75));
                top.setBackground(VERY_LIGHT_BLUE);

                JPanel bottom = new JPanel();
                bottom.setPreferredSize(new Dimension(600,75));
                bottom.setBackground(VERY_LIGHT_BLUE);

                JLabel message = new JLabel("Please enter the id of the Product you would like to delete");
                message.setFont(new Font("Verdana",Font.PLAIN,12));

                dTextMsg2 = new JTextField(20);

                JButton delete = new JButton("Delete");
                delete.setBackground(Color.YELLOW);
                delete.setFocusable(false);

                JButton cancel = new JButton("Cancel");
                cancel.setBackground(Color.YELLOW);
                cancel.setFocusable(false);

                delete.addActionListener(new DeleteButton2_2Listener());
                cancel.addActionListener(new CancelButton2_3Listener());

                top.add(message);
                bottom.add(dTextMsg2);
                bottom.add(delete);
                bottom.add(cancel);

                displayInfo.add(top);
                displayInfo.add(bottom);

                del2.add(displayInfo, BorderLayout.CENTER);

                del2.pack();
                del2.setVisible(true);
            }

        }
    }

    private static class DeleteButton_2Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String name = dTextMsg.getText();
            boolean stat = false;

            for (int i = 0; i < grom.size(); i++)
            {
                String id = Integer.toString(grom.get(i).getId());
                //System.out.println(grom.get(i).getId() + " " + grom.get(i).getName());
                if (dTextMsg.getText().equals(id))
                {
                    stat = true;
                }
            }

            if (stat == false)
            {
                errorScreen = new JFrame("ERROR!!!");
                errorScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel pane = new JPanel();
                pane.setBackground(Color.RED);
                pane.setPreferredSize(new Dimension(600, 150));
                pane.setLayout(new GridLayout(2, 0));

                JLabel txt = new JLabel("ERROR!!!");
                txt.setForeground(Color.WHITE);
                txt.setHorizontalAlignment(SwingConstants.CENTER);
                txt.setFont(new Font("Verdana", Font.BOLD, 35));

                JLabel txt2 = new JLabel("The name you entered was not found!!");
                txt2.setForeground(Color.WHITE);
                txt2.setHorizontalAlignment(SwingConstants.CENTER);
                txt2.setFont(new Font("Verdana", Font.PLAIN, 20));

                pane.add(txt);
                pane.add(txt2);

                JPanel response = new JPanel();
                response.setBackground(Color.RED);
                response.setPreferredSize(new Dimension(600, 150));

                JButton ok = new JButton("OK");
                ok.setPreferredSize(new Dimension(80, 45));

                ok.setFocusable(false);

                ok.addActionListener(new OkButtonListener());

                response.add(ok);

                errorScreen.add(response, BorderLayout.SOUTH);
                errorScreen.add(pane, BorderLayout.CENTER);
                errorScreen.pack();
                errorScreen.setVisible(true);
            }
            else
            {
                File file = new File("EFSales.txt");
                File dFile = new File("Date.txt");

                ArrayList<Gas_Records> eProm = new ArrayList<>();
                ArrayList<String> dDate = new ArrayList<>();
                ArrayList<Integer> idList = new ArrayList<>();
                ArrayList<String> dList = new ArrayList<>();

                try {
                    Scanner scan = new Scanner(file);

                    while (scan.hasNext())
                    {
                        String[] nameInFile = scan.nextLine().split(" ");
                        String name1 = nameInFile[0];
                        double price = Double.parseDouble(nameInFile[1]);
                        int quantity = Integer.parseInt(nameInFile[2]);
                        String dt = nameInFile[3];
                        String id = nameInFile[4];
                        int id2 = Integer.parseInt(id);

                        if (name.equals(id))
                        {
                            continue;
                        }

                        Gas_Records p = new Gas_Records(name1, price, quantity);
                        p.setId(id2);
                        eProm.add(p);
                        dList.add(dt);
                    }

                    new FileWriter(file, false).close();

                    if (!file.exists())
                    {
                        file.createNewFile();
                    }

                    FileWriter fw = new FileWriter(file, true);

                    for (int i = 0; i < eProm.size(); i++)
                    {
                        String newName = eProm.get(i).getName();
                        String newPrice = Double.toString(eProm.get(i).getPrice());
                        String quantity = Integer.toString(eProm.get(i).getQuantity());
                        String date = dList.get(i);
                        String id = Integer.toString(eProm.get(i).getId());
                        fw.write(newName + " ");
                        fw.write(newPrice + " ");
                        fw.write(quantity + " ");
                        fw.write(date + " ");
                        fw.write(id + "\n");
                    }
                    fw.close();

                    Scanner scan_1 = new Scanner(dFile);

                    while (scan_1.hasNext())
                    {
                        String date = scan_1.nextLine();
                        String[] delName = date.split(" ");
                        String nName = delName[0];
                        String id = delName[4];

                        if (name.equals(id))
                        {
                            continue;
                        }

                        dDate.add(nName + " " + delName[1] + " " + delName[2] + " " + delName[3] + " " + delName[4]);
                        dList.add(id);
                    }

                    new FileWriter(dFile, false).close();

                    if (!dFile.exists())
                    {
                        dFile.createNewFile();
                    }

                    FileWriter fil = new FileWriter(dFile, true);

                    for (int j = 0; j < dDate.size(); j++)
                    {
                        String[] tempDate = dDate.get(j).split(" ");
                        fil.write(tempDate[0] + " " + tempDate[1] + " " + tempDate[2] + " " + tempDate[3] + " " + tempDate[4] + "\n");
                    }
                    fil.close();
                }
                catch (IOException exception)
                {
                    exception.printStackTrace();
                }

                success = new JFrame("Gas Sales Record Deleted");
                success.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel pane = new JPanel();
                pane.setBackground(Color.GREEN);
                pane.setPreferredSize(new Dimension(600, 150));
                pane.setLayout(new GridLayout(2, 0));

                JLabel txt = new JLabel("Gas Sales Record Deleted");
                txt.setForeground(Color.WHITE);
                txt.setHorizontalAlignment(SwingConstants.CENTER);
                txt.setFont(new Font("Verdana", Font.BOLD, 35));

                JLabel txt2 = new JLabel("The Gas Sales Record was Deleted Successfully!!");
                txt2.setForeground(Color.WHITE);
                txt2.setHorizontalAlignment(SwingConstants.CENTER);
                txt2.setFont(new Font("Verdana", Font.PLAIN, 20));

                pane.add(txt);
                pane.add(txt2);

                JPanel response = new JPanel();
                response.setBackground(Color.GREEN);
                response.setPreferredSize(new Dimension(600, 150));

                JButton ok = new JButton("OK");
                ok.setPreferredSize(new Dimension(80, 45));

                ok.setFocusable(false);

                ok.addActionListener(new OkButton_2Listener());

                response.add(ok);

                success.add(response, BorderLayout.SOUTH);
                success.add(pane, BorderLayout.CENTER);
                success.pack();
                success.setVisible(true);
            }
        }
    }

    private static class DeleteButton2_2Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String name = dTextMsg2.getText();
            boolean stat = false;

            for (int i = 0; i < menn.size(); i++)
            {
                String id = Integer.toString(menn.get(i).getId());
                if (dTextMsg2.getText().equals(id))
                {
                    stat = true;
                }
            }

            if (stat == false)
            {
                errorScreen = new JFrame("ERROR!!!");
                errorScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel pane = new JPanel();
                pane.setBackground(Color.RED);
                pane.setPreferredSize(new Dimension(600, 150));
                pane.setLayout(new GridLayout(2, 0));

                JLabel txt = new JLabel("ERROR!!!");
                txt.setForeground(Color.WHITE);
                txt.setHorizontalAlignment(SwingConstants.CENTER);
                txt.setFont(new Font("Verdana", Font.BOLD, 35));

                JLabel txt2 = new JLabel("The id you entered was not found!!");
                txt2.setForeground(Color.WHITE);
                txt2.setHorizontalAlignment(SwingConstants.CENTER);
                txt2.setFont(new Font("Verdana", Font.PLAIN, 20));

                pane.add(txt);
                pane.add(txt2);

                JPanel response = new JPanel();
                response.setBackground(Color.RED);
                response.setPreferredSize(new Dimension(600, 150));

                JButton ok = new JButton("OK");
                ok.setPreferredSize(new Dimension(80, 45));

                ok.setFocusable(false);

                ok.addActionListener(new OkButtonListener());

                response.add(ok);

                errorScreen.add(response, BorderLayout.SOUTH);
                errorScreen.add(pane, BorderLayout.CENTER);
                errorScreen.pack();
                errorScreen.setVisible(true);
            }
            else
            {
                File file = new File("MMSales.txt");
                File dFile = new File("Date2.txt");

                ArrayList<MiniMart_Records> eProm = new ArrayList<>();
                ArrayList<String> dDate = new ArrayList<>();
                ArrayList<Integer> idList = new ArrayList<>();
                ArrayList<String> dList = new ArrayList<>();

                try {
                    Scanner scan = new Scanner(file);

                    while (scan.hasNext())
                    {
                        String[] nameInFile = scan.nextLine().split(" ");
                        String name1 = nameInFile[0];
                        double price = Double.parseDouble(nameInFile[1]);
                        int quantity = Integer.parseInt(nameInFile[2]);
                        double volume = Double.parseDouble(nameInFile[3]);
                        int id = Integer.parseInt(nameInFile[4]);

                        if (name.equals(nameInFile[4]))
                        {
                            continue;
                        }

                        MiniMart_Records p = new MiniMart_Records(name1, price, quantity, volume);
                        p.setId(id);
                        eProm.add(p);
                    }

                    new FileWriter(file, false).close();

                    if (!file.exists())
                    {
                        file.createNewFile();
                    }

                    FileWriter fw = new FileWriter(file, true);

                    for (int i = 0; i < eProm.size(); i++)
                    {
                        String newName = eProm.get(i).getName();
                        String newPrice = Double.toString(eProm.get(i).getPrice());
                        String quantity = Integer.toString(eProm.get(i).getQuantity());
                        String volume = Double.toString(eProm.get(i).getVolume());
                        String id = Integer.toString(eProm.get(i).getId());
                        fw.write(newName + " ");
                        fw.write(newPrice + " ");
                        fw.write(quantity + " ");
                        fw.write(volume + " ");
                        fw.write( id + "\n");
                    }
                    fw.close();

                    Scanner scan_1 = new Scanner(dFile);

                    while (scan_1.hasNext())
                    {
                        String date = scan_1.nextLine();
                        String[] delName = date.split(" ");
                        String nName = delName[0];
                        String id = delName[4];

                        if (name.equals(id))
                        {
                            continue;
                        }

                        dDate.add(nName + " " + delName[1] + " " + delName[2] + " " + delName[3] + " " + delName[4]);
                    }

                    new FileWriter(dFile, false).close();

                    if (!dFile.exists())
                    {
                        dFile.createNewFile();
                    }

                    FileWriter fil = new FileWriter(dFile, true);

                    for (int j = 0; j < dDate.size(); j++)
                    {
                        String[] tempDate = dDate.get(j).split(" ");
                        fil.write(tempDate[0] + " " + tempDate[1] + " " + tempDate[2] + " " + tempDate[3] + " " + tempDate[4] + "\n");
                    }
                    fil.close();
                }
                catch (IOException exception)
                {
                    exception.printStackTrace();
                }

                success2 = new JFrame("Mini-Mart Record Deleted");
                success2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel pane = new JPanel();
                pane.setBackground(Color.GREEN);
                pane.setPreferredSize(new Dimension(600, 150));
                pane.setLayout(new GridLayout(2, 0));

                JLabel txt = new JLabel("Mini-Mart Record Deleted");
                txt.setForeground(Color.WHITE);
                txt.setHorizontalAlignment(SwingConstants.CENTER);
                txt.setFont(new Font("Verdana", Font.BOLD, 35));

                JLabel txt2 = new JLabel("The Mini-Mart Record was Deleted Successfully!!");
                txt2.setForeground(Color.WHITE);
                txt2.setHorizontalAlignment(SwingConstants.CENTER);
                txt2.setFont(new Font("Verdana", Font.PLAIN, 20));

                pane.add(txt);
                pane.add(txt2);

                JPanel response = new JPanel();
                response.setBackground(Color.GREEN);
                response.setPreferredSize(new Dimension(600, 150));

                JButton ok = new JButton("OK");
                ok.setPreferredSize(new Dimension(80, 45));

                ok.setFocusable(false);

                ok.addActionListener(new OkButton2_2Listener());

                response.add(ok);

                success2.add(response, BorderLayout.SOUTH);
                success2.add(pane, BorderLayout.CENTER);
                success2.pack();
                success2.setVisible(true);
            }
        }
    }

    private static class CancelButton_3Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            del.setVisible(false);
        }
    }

    private static class CancelButton2_3Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            del2.setVisible(false);
        }
    }

    private static class OkButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            errorScreen.setVisible(false);
        }
    }

    private static class OkButton_2Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            model.setRowCount(0);
            for (int i = 0; i < grom.size(); i++)
            {
                if (dTextMsg.getText().equals(Integer.toString(grom.get(i).getId())))
                {
                    grom.remove(i);
                }
            }

            for (int j = 0; j < dt.size(); j++)
            {
                String [] sName = dt.get(j).split(" ");
                String name = sName[0];
                String id = sName[4];

                if (dTextMsg.getText().equals(id))
                {
                    dt.remove(j);
                }
            }
            showTable(grom,dt);
            del.setVisible(false);
            success.setVisible(false);
        }
    }

    private static class OkButton2_2Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            model.setRowCount(0);
            for (int i = 0; i < menn.size(); i++)
            {
                if (dTextMsg2.getText().equals(Integer.toString(menn.get(i).getId())))
                {
                    menn.remove(i);
                }
            }

            for (int j = 0; j < dt2.size(); j++)
            {
                String [] sName = dt2.get(j).split(" ");
                String name = sName[0];
                String id = sName[4];

                if (dTextMsg2.getText().equals(id))
                {
                    dt2.remove(j);
                }
            }
            showTable2(menn,dt2);
            del2.setVisible(false);
            success2.setVisible(false);
        }
    }

    private static class EditListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (status)
            {
                jm = new JFrame("Edit Gas Sales");
                jm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jm.setLocationRelativeTo(null);

                JPanel displayInfo = new JPanel();
                displayInfo.setPreferredSize (new Dimension(600, 150));
                displayInfo.setBackground(VERY_LIGHT_BLUE);

                JPanel top = new JPanel();
                top.setPreferredSize(new Dimension(600,75));
                top.setBackground(VERY_LIGHT_BLUE);

                JPanel bottom = new JPanel();
                bottom.setPreferredSize(new Dimension(600,75));
                bottom.setBackground(VERY_LIGHT_BLUE);

                JLabel message = new JLabel("Please enter the Id of the Gas Sales Record you would like to edit");
                message.setFont(new Font("Verdana",Font.PLAIN,12));

                txtMsg = new JTextField(20);

                JButton edit = new JButton("Edit");
                edit.setBackground(Color.YELLOW);
                edit.setFocusable(false);

                JButton cancel = new JButton("Cancel");
                cancel.setBackground(Color.YELLOW);
                cancel.setFocusable(false);

                edit.addActionListener(new EditButtonListener_2());
                cancel.addActionListener(new CancelButton_2Listener());

                top.add(message);
                bottom.add(txtMsg);
                bottom.add(edit);
                bottom.add(cancel);

                displayInfo.add(top);
                displayInfo.add(bottom);

                jm.add(displayInfo, BorderLayout.CENTER);

                jm.pack();
                jm.setVisible(true);
            }
            if (!status)
            {
                jm2 = new JFrame("Edit Mini-Mart Sales");
                jm2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jm2.setLocationRelativeTo(null);

                JPanel displayInfo = new JPanel();
                displayInfo.setPreferredSize (new Dimension(600, 150));
                displayInfo.setBackground(VERY_LIGHT_BLUE);

                JPanel top = new JPanel();
                top.setPreferredSize(new Dimension(600,75));
                top.setBackground(VERY_LIGHT_BLUE);

                JPanel bottom = new JPanel();
                bottom.setPreferredSize(new Dimension(600,75));
                bottom.setBackground(VERY_LIGHT_BLUE);

                JLabel message = new JLabel("Please enter the ID of the Mini-Mart Sales Record you would like to edit");
                message.setFont(new Font("Verdana",Font.PLAIN,12));

                txtMsg2 = new JTextField(20);

                JButton edit = new JButton("Edit");
                edit.setBackground(Color.YELLOW);
                edit.setFocusable(false);

                JButton cancel = new JButton("Cancel");
                cancel.setBackground(Color.YELLOW);
                cancel.setFocusable(false);

                edit.addActionListener(new EditButtonListener_22());
                cancel.addActionListener(new CancelButton_2Listener2());

                top.add(message);
                bottom.add(txtMsg2);
                bottom.add(edit);
                bottom.add(cancel);

                displayInfo.add(top);
                displayInfo.add(bottom);

                jm2.add(displayInfo, BorderLayout.CENTER);

                jm2.pack();
                jm2.setVisible(true);
            }
        }
    }

    private static class EditButtonListener_22 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String name = txtMsg2.getText();
            boolean stat_ = false;

            for (int i = 0; i < menn.size(); i++)
            {
                String id = Integer.toString(menn.get(i).getId());
                if (name.equals(id))
                {
                    stat_ = true;
                }
            }

            if (stat_ == false)
            {
                errorScreen = new JFrame("ERROR!!!");
                errorScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel pane = new JPanel();
                pane.setBackground(Color.RED);
                pane.setPreferredSize(new Dimension(600, 150));
                pane.setLayout(new GridLayout(2, 0));

                JLabel txt = new JLabel("ERROR!!!");
                txt.setForeground(Color.WHITE);
                txt.setHorizontalAlignment(SwingConstants.CENTER);
                txt.setFont(new Font("Verdana", Font.BOLD, 35));

                JLabel txt2 = new JLabel("The name you entered was not found!!");
                txt2.setForeground(Color.WHITE);
                txt2.setHorizontalAlignment(SwingConstants.CENTER);
                txt2.setFont(new Font("Verdana", Font.PLAIN, 20));

                pane.add(txt);
                pane.add(txt2);

                JPanel response = new JPanel();
                response.setBackground(Color.RED);
                response.setPreferredSize(new Dimension(600, 150));

                JButton ok = new JButton("OK");
                ok.setPreferredSize(new Dimension(80, 45));

                ok.setFocusable(false);

                ok.addActionListener(new OkButtonListener());

                response.add(ok);

                errorScreen.add(response, BorderLayout.SOUTH);
                errorScreen.add(pane, BorderLayout.CENTER);
                errorScreen.pack();
                errorScreen.setVisible(true);
            }
            else
            {
                File file = new File("MMSales.txt");
                ArrayList<MiniMart_Records> eProm = new ArrayList<>();

                try
                {
                    Scanner scan = new Scanner(file);

                    while (scan.hasNext())
                    {
                        String[] nameInFile = scan.nextLine().split(" ");
                        String nameSplit = nameInFile[0];
                        double price = Double.parseDouble(nameInFile[1]);
                        int quantity = Integer.parseInt(nameInFile[2]);
                        double volume = Double.parseDouble(nameInFile[3]);
                        int id = Integer.parseInt(nameInFile[4]);

                        if (name.equals(Integer.toString(id)))
                        {
                            currName2 = nameSplit;
                            currPrice2 = price;
                            currQuantity2 = quantity;
                            currVolume = volume;
                            currID2 = id;

                            continue;
                        }

                        MiniMart_Records p = new MiniMart_Records(nameSplit, price, quantity, volume);
                        p.setId(id);
                        eProm.add(p);
                    }

                    new FileWriter(file, false).close();

                    if (!file.exists())
                    {
                        file.createNewFile();
                    }

                    FileWriter fw = new FileWriter(file, true);

                    for (int i = 0; i < eProm.size(); i++)
                    {
                        String newName = eProm.get(i).getName();
                        String newPrice = Double.toString(eProm.get(i).getPrice());
                        String newQuantity = Integer.toString(eProm.get(i).getQuantity());
                        String newVolume = Double.toString(eProm.get(i).getVolume());
                        String id = Integer.toString(eProm.get(i).getId());
                        fw.write(newName + " ");
                        fw.write(newPrice + " ");
                        fw.write(newQuantity + " ");
                        fw.write(newVolume + " ");
                        fw.write(id + "\n");
                    }
                    fw.close();

                    File fil = new File("Date2.txt");
                    ArrayList<String> eDate = new ArrayList<>();

                    Scanner eScan = new Scanner(fil);

                    while (eScan.hasNext())
                    {
                        String date = eScan.nextLine();
                        String[] eName = date.split(" ");
                        String nName = eName[0];
                        String id = eName[4];

                        if (name.equals(id))
                        {
                            currDate2 = eName[0] + " " + eName[1] + " " + eName[2] + " " + eName[3] + " " + eName[4];
                            continue;
                        }

                        eDate.add(nName + " " + eName[1] + " " + eName[2] + " " + eName[3] + " " + eName[4]);
                    }

                    new FileWriter(fil, false).close();

                    if (!fil.exists())
                    {
                        fil.createNewFile();
                    }

                    FileWriter filW = new FileWriter(fil, true);

                    for (int s = 0; s < eDate.size(); s++) {
                        String[] tempDate = eDate.get(s).split(" ");
                        filW.write(tempDate[0] + " " + tempDate[1] + " " + tempDate[2] + " " + tempDate[3] + " " + tempDate[4] + "\n");
                    }

                    filW.close();

                    for (int i = 0; i < menn.size(); i++)
                    {
                        if (currName2.equals(Integer.toString(menn.get(i).getId())))
                        {
                            menn.remove(i);
                        }
                    }

                    for (int j = 0; j < dt2.size(); j++)
                    {
                        String[] splitName = dt2.get(j).split(" ");
                        String eName = splitName[0];
                        String id = splitName[4];

                        if (currName2.equals(id))
                        {
                            dt2.remove(j);
                        }
                    }
                }
                catch (IOException exception)
                {
                    exception.printStackTrace();
                }

                sec2 = new JFrame("Editing Mini-Mart Sales...");
                sec2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                sec2.setLocationRelativeTo(null);

                JPanel dud = new JPanel();
                dud.setPreferredSize(new Dimension(600, 300));
                dud.setBackground(VERY_LIGHT_BLUE);

                JPanel top = new JPanel();
                top.setPreferredSize(new Dimension(600, 100));
                top.setBackground(VERY_LIGHT_BLUE);

                JPanel bottom = new JPanel();
                bottom.setPreferredSize(new Dimension(600, 100));
                bottom.setBackground(VERY_LIGHT_BLUE);
                GridBagLayout layout = new GridBagLayout();
                bottom.setLayout(layout);
                GridBagConstraints gbc = new GridBagConstraints();

                JLabel mes = new JLabel("(Leave fields blank if you want it to remain the same)");
                mes.setFont(new Font("Verdana", Font.BOLD, 18));

                JLabel newName = new JLabel("New name: ");
                newName.setFont(new Font("Verdana", Font.BOLD, 15));
                nam2 = new JTextField(20);

                JLabel price = new JLabel("New price: ");
                price.setFont(new Font("Verdana", Font.BOLD, 15));
                text22 = new JTextField(20);

                JLabel quantity = new JLabel("New Quantity: ");
                quantity.setFont(new Font("Verdana", Font.BOLD, 15));
                text222 = new JTextField(20);

                JLabel volume = new JLabel("New Volume: ");
                volume.setFont(new Font("Verdana", Font.BOLD, 15));
                text2222 = new JTextField(20);

                JButton confirm = new JButton("Confirm");
                confirm.setBackground(Color.YELLOW);
                JButton cancel = new JButton("Cancel");
                cancel.setBackground(Color.YELLOW);

                confirm.addActionListener(new ConfirmButtonListener2());
                cancel.addActionListener(new CancelButtonEditListener2());

                top.add(mes);

                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.gridx = 0;
                gbc.gridy = 0;
                bottom.add(newName, gbc);

                gbc.gridx = 1;
                gbc.gridy = 0;
                bottom.add(nam2, gbc);

                gbc.gridx = 0;
                gbc.gridy = 2;
                bottom.add(price, gbc);

                gbc.gridx = 1;
                gbc.gridy = 2;
                bottom.add(text22, gbc);

                gbc.gridx = 0;
                gbc.gridy = 4;
                bottom.add(quantity, gbc);

                gbc.gridx = 1;
                gbc.gridy = 4;
                bottom.add(text222, gbc);

                gbc.gridx = 0;
                gbc.gridy = 6;
                bottom.add(volume, gbc);

                gbc.gridx = 1;
                gbc.gridy = 6;
                bottom.add(text2222, gbc);

                JPanel choice = new JPanel();
                choice.setPreferredSize(new Dimension(600, 100));
                choice.setBackground(VERY_LIGHT_BLUE);

                choice.add(confirm);
                choice.add(cancel);

                dud.add(top);
                dud.add(bottom);
                dud.add(choice);

                sec2.add(dud);

                sec2.pack();
                sec2.setVisible(true);
            }
        }
    }

    private static class EditButtonListener_2 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String name = txtMsg.getText();
            boolean stat_ = false;

            for (int i = 0; i < grom.size(); i++)
            {
                String id = Integer.toString(grom.get(i).getId());
                if (name.equals(id))
                {
                    stat_ = true;
                }
            }

            if (stat_ == false)
            {
                errorScreen = new JFrame("ERROR!!!");
                errorScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel pane = new JPanel();
                pane.setBackground(Color.RED);
                pane.setPreferredSize(new Dimension(600, 150));
                pane.setLayout(new GridLayout(2, 0));

                JLabel txt = new JLabel("ERROR!!!");
                txt.setForeground(Color.WHITE);
                txt.setHorizontalAlignment(SwingConstants.CENTER);
                txt.setFont(new Font("Verdana", Font.BOLD, 35));

                JLabel txt2 = new JLabel("The id you entered was not found!!");
                txt2.setForeground(Color.WHITE);
                txt2.setHorizontalAlignment(SwingConstants.CENTER);
                txt2.setFont(new Font("Verdana", Font.PLAIN, 20));

                pane.add(txt);
                pane.add(txt2);

                JPanel response = new JPanel();
                response.setBackground(Color.RED);
                response.setPreferredSize(new Dimension(600, 150));

                JButton ok = new JButton("OK");
                ok.setPreferredSize(new Dimension(80, 45));

                ok.setFocusable(false);

                ok.addActionListener(new OkButtonListener());

                response.add(ok);

                errorScreen.add(response, BorderLayout.SOUTH);
                errorScreen.add(pane, BorderLayout.CENTER);
                errorScreen.pack();
                errorScreen.setVisible(true);
            }
            else
            {
                File file = new File("EFSales.txt");
                ArrayList<Gas_Records> eProm = new ArrayList<>();
                ArrayList<String> dte = new ArrayList<>();

                try
                {
                    Scanner scan = new Scanner(file);

                    while (scan.hasNext())
                    {
                        String[] nameInFile = scan.nextLine().split(" ");
                        String nameSplit = nameInFile[0];
                        double price = Double.parseDouble(nameInFile[1]);
                        int quantity = Integer.parseInt(nameInFile[2]);
                        String date = nameInFile[3];
                        int id = Integer.parseInt(nameInFile[4]);

                        if (name.equals(Integer.toString(id)))
                        {
                            currName = nameSplit;
                            currPrice = price;
                            currQuantity = quantity;
                            currDte = date;
                            currID = id;

                            continue;
                        }

                        Gas_Records p = new Gas_Records(nameSplit, price, quantity);
                        p.setId(id);
                        eProm.add(p);
                        dte.add(date);
                    }

                    new FileWriter(file, false).close();

                    if (!file.exists())
                    {
                        file.createNewFile();
                    }

                    FileWriter fw = new FileWriter(file, true);

                    for (int i = 0; i < eProm.size(); i++)
                    {
                        String newName = eProm.get(i).getName();
                        String newPrice = Double.toString(eProm.get(i).getPrice());
                        String newQuantity = Integer.toString(eProm.get(i).getQuantity());
                        String dte2 = dte.get(i);
                        String id = Integer.toString(eProm.get(i).getId());
                        fw.write(newName + " ");
                        fw.write(newPrice + " ");
                        fw.write(newQuantity + " ");
                        fw.write(dte2 + " ");
                        fw.write(id + "\n");
                    }
                    fw.close();

                    File fil = new File("Date.txt");
                    ArrayList<String> eDate = new ArrayList<>();

                    Scanner eScan = new Scanner(fil);

                    while (eScan.hasNext())
                    {
                        String date = eScan.nextLine();
                        String[] eName = date.split(" ");
                        String nName = eName[0];
                        String id = eName[4];

                        if (name.equals(id))
                        {
                            currDate = eName[0] + " " + eName[1] + " " + eName[2] + " " + eName[3] + " " + eName[4];
                            continue;
                        }

                        eDate.add(nName + " " + eName[1] + " " + eName[2] + " " + eName[3] + " " + eName[4]);
                    }

                    new FileWriter(fil, false).close();

                    if (!fil.exists())
                    {
                        fil.createNewFile();
                    }

                    FileWriter filW = new FileWriter(fil, true);

                    for (int s = 0; s < eDate.size(); s++) {
                        String[] tempDate = eDate.get(s).split(" ");
                        filW.write(tempDate[0] + " " + tempDate[1] + " " + tempDate[2] + " " + tempDate[3] + " " + tempDate[4] + "\n");
                    }

                    filW.close();

                    for (int i = 0; i < grom.size(); i++)
                    {
                        if (currName.equals(Integer.toString(grom.get(i).getId())))
                        {
                            grom.remove(i);
                        }
                    }

                    for (int j = 0; j < dt.size(); j++)
                    {
                        String[] splitName = dt.get(j).split(" ");
                        String eName = splitName[0];
                        String id = splitName[4];

                        if (currName.equals(id))
                        {
                            dt.remove(j);
                        }
                    }
                }
                catch (IOException exception)
                {
                    exception.printStackTrace();
                }

                sec = new JFrame("Editing Gas Sales...");
                sec.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                sec.setLocationRelativeTo(null);

                JPanel dud = new JPanel();
                dud.setPreferredSize(new Dimension(600, 300));
                dud.setBackground(VERY_LIGHT_BLUE);

                JPanel top = new JPanel();
                top.setPreferredSize(new Dimension(600, 100));
                top.setBackground(VERY_LIGHT_BLUE);

                JPanel bottom = new JPanel();
                bottom.setPreferredSize(new Dimension(600, 100));
                bottom.setBackground(VERY_LIGHT_BLUE);
                GridBagLayout layout = new GridBagLayout();
                bottom.setLayout(layout);
                GridBagConstraints gbc = new GridBagConstraints();

                JLabel mes = new JLabel("(Leave fields blank if you want it to remain the same)");
                mes.setFont(new Font("Verdana", Font.BOLD, 18));

                JLabel newName = new JLabel("New name: ");
                newName.setFont(new Font("Verdana", Font.BOLD, 15));
                nam = new JTextField(20);

                JLabel price = new JLabel("New price: ");
                price.setFont(new Font("Verdana", Font.BOLD, 15));
                text = new JTextField(20);

                JLabel quantity = new JLabel("New Quantity: ");
                quantity.setFont(new Font("Verdana", Font.BOLD, 15));
                text2 = new JTextField(20);

                JButton confirm = new JButton("Confirm");
                confirm.setBackground(Color.YELLOW);
                JButton cancel = new JButton("Cancel");
                cancel.setBackground(Color.YELLOW);

                confirm.addActionListener(new ConfirmButtonListener());
                cancel.addActionListener(new CancelButtonEditListener());

                top.add(mes);

                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.gridx = 0;
                gbc.gridy = 0;
                bottom.add(newName, gbc);

                gbc.gridx = 1;
                gbc.gridy = 0;
                bottom.add(nam, gbc);

                gbc.gridx = 0;
                gbc.gridy = 2;
                bottom.add(price, gbc);

                gbc.gridx = 1;
                gbc.gridy = 2;
                bottom.add(text, gbc);

                gbc.gridx = 0;
                gbc.gridy = 4;
                bottom.add(quantity, gbc);

                gbc.gridx = 1;
                gbc.gridy = 4;
                bottom.add(text2, gbc);

                JPanel choice = new JPanel();
                choice.setPreferredSize(new Dimension(600, 100));
                choice.setBackground(VERY_LIGHT_BLUE);

                choice.add(confirm);
                choice.add(cancel);

                dud.add(top);
                dud.add(bottom);
                dud.add(choice);

                sec.add(dud);

                sec.pack();
                sec.setVisible(true);
            }
        }
    }

    private static class ConfirmButtonListener2 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (nam2.getText().equals(""))
            {
                newName2 = currName2;
            }
            else
            {
                newName2 = nam2.getText();
            }

            if (text22.getText().equals(""))
            {
                newPrice2 = currPrice2;
            }
            else
            {
                newPrice2 = Double.parseDouble(text22.getText());
            }

            if (text222.getText().equals(""))
            {
                newQuantity2 = currQuantity2;
            }
            else
            {
                newQuantity2 = Integer.parseInt(text222.getText());
            }

            if (text2222.getText().equals(""))
            {
                newVolume = currVolume;
            }
            else
            {
                newVolume = Double.parseDouble(text2222.getText());
            }

            MiniMart_Records pr = new MiniMart_Records(newName2, newPrice2, newQuantity2, newVolume);
            pr.setId(currID2);

            menn.add(pr);

            String [] newDate = currDate2.split(" ");
            dt2.add(newDate[0] + " " + newDate[1] + " " + newDate[2] + " " + newDate[3] + " " + newDate[4]);

            //addToTable2(pr, newDate[1], newDate[2] + " " + newDate[3]);

            try
            {
                File file = new File("MMSales.txt");

                if (!file.exists())
                {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file, true);

                fw.write(newName2 + " ");
                fw.write(newPrice2 + " ");
                fw.write(newQuantity2 + " ");
                fw.write(newVolume + " ");
                fw.write( currID2 + "\n");
                fw.close();

                File fil = new File("Date2.txt");

                if (!fil.exists())
                {
                    fil.createNewFile();
                }

                FileWriter filWr = new FileWriter(fil, true);

                filWr.write(currDate2 + "\n");
                filWr.close();
            }
            catch (IOException exception)
            {

            }

            model.setRowCount(0);
            menn = loadMMSales("MMSales.txt");
            showTable2(menn,dt2);
            sec2.setVisible(false);
            jm2.setVisible(false);
        }
    }

    private static class ConfirmButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (nam.getText().equals(""))
            {
                newName = currName;
            }
            else
            {
                newName = nam.getText();
            }

            if (text.getText().equals(""))
            {
                newPrice = currPrice;
            }
            else
            {
                newPrice = Double.parseDouble(text.getText());
            }

            if (text2.getText().equals(""))
            {
                newQuantity = currQuantity;
            }
            else
            {
                newQuantity = Integer.parseInt(text2.getText());
            }

            Gas_Records pr = new Gas_Records(newName, newPrice, newQuantity);
            pr.setId(currID);

            grom.add(pr);

            String [] newDate = currDate.split(" ");
            dt.add(newDate[0] + " " + newDate[1] + " " + newDate[2] + " " + newDate[3] + " " + newDate[4]);

            //addToTable(pr, newDate[1], newDate[2] + " " + newDate[3]);

            try
            {
                File file = new File("EFSales.txt");

                if (!file.exists())
                {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file, true);

                fw.write(newName + " ");
                fw.write(newPrice + " ");
                fw.write(newQuantity + " ");
                fw.write(currDte + " ");
                fw.write(currID + "\n");
                fw.close();

                File fil = new File("Date.txt");

                if (!fil.exists())
                {
                    fil.createNewFile();
                }

                FileWriter filWr = new FileWriter(fil, true);

                filWr.write(currDate + "\n");
                filWr.close();
            }
            catch (IOException exception)
            {

            }

            model.setRowCount(0);
            grom = loadGasSales("EFSales.txt");
            showTable(grom,dt);
            sec.setVisible(false);
            jm.setVisible(false);
        }
    }

    private static class CancelButtonEditListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            sec.setVisible(false);
        }
    }

    private static class CancelButtonEditListener2 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            sec2.setVisible(false);
        }
    }

    private static class CancelButton_2Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            jm.setVisible(false);
        }
    }

    private static class CancelButton_2Listener2 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            jm2.setVisible(false);
        }
    }

    private static class SortNameListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (status)
            {
                model.setRowCount(0);

                Collections.sort(grom);
                ArrayList<String> temp = new ArrayList<>();
                for (int i = 0; i < grom.size(); i++)
                {
                    for (int j = 0; j < dt.size(); j++)
                    {
                        String [] date = dt.get(j).split(" ");
                        String name = date[0];

                        if (grom.get(i).getName().equals(name))
                        {
                            temp.add(dt.get(j));

                            break;
                        }
                    }
                }

                dt = temp;
                showTable(grom,dt);
            }
            else if (!status)
            {
                model.setRowCount(0);

                Collections.sort(menn,new SortByName());
                ArrayList<String> temp = new ArrayList<>();

                for (int i = 0; i < menn.size(); i++)
                {
                    for (int j = 0; j < dt2.size(); j++)
                    {
                        String [] date = dt2.get(j).split(" ");
                        String name = date[0];

                        if (menn.get(i).getName().equals(name))
                        {
                            temp.add(dt2.get(j));

                            break;
                        }
                    }
                }

                dt2 = temp;
                showTable2(menn,dt2);
            }
        }
    }

    private static class SearchListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (status)
            {
                search = new JFrame("Search for a Gas Sales Record");
                search.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                search.setLocationRelativeTo(null);

                JPanel displayInfo = new JPanel();
                displayInfo.setPreferredSize (new Dimension(600, 150));
                displayInfo.setBackground(VERY_LIGHT_BLUE);

                JPanel top = new JPanel();
                top.setPreferredSize(new Dimension(600,75));
                top.setBackground(VERY_LIGHT_BLUE);

                JPanel bottom = new JPanel();
                bottom.setPreferredSize(new Dimension(600,75));
                bottom.setBackground(VERY_LIGHT_BLUE);

                JLabel message = new JLabel("Please enter the name of the Product you would like to search for.");
                message.setFont(new Font("Verdana",Font.PLAIN,12));

                searchText = new JTextField(20);

                JButton delete = new JButton("Search");
                delete.setBackground(Color.YELLOW);
                delete.setFocusable(false);

                JButton cancel = new JButton("Cancel");
                cancel.setBackground(Color.YELLOW);
                cancel.setFocusable(false);

                delete.addActionListener(new Search2Listener());
                cancel.addActionListener(new CancelButton4Listener());

                top.add(message);
                bottom.add(searchText);
                bottom.add(delete);
                bottom.add(cancel);

                displayInfo.add(top);
                displayInfo.add(bottom);

                search.add(displayInfo, BorderLayout.CENTER);

                search.pack();
                search.setVisible(true);
            }
            else if (!status)
            {
                search2 = new JFrame("Search for a Mini-Mart Sales Record");
                search2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                search2.setLocationRelativeTo(null);

                JPanel displayInfo = new JPanel();
                displayInfo.setPreferredSize (new Dimension(600, 150));
                displayInfo.setBackground(VERY_LIGHT_BLUE);

                JPanel top = new JPanel();
                top.setPreferredSize(new Dimension(600,75));
                top.setBackground(VERY_LIGHT_BLUE);

                JPanel bottom = new JPanel();
                bottom.setPreferredSize(new Dimension(600,75));
                bottom.setBackground(VERY_LIGHT_BLUE);

                JLabel message = new JLabel("Please enter the name of the Product you would like to search for.");
                message.setFont(new Font("Verdana",Font.PLAIN,12));

                searchText2 = new JTextField(20);

                JButton delete = new JButton("Search");
                delete.setBackground(Color.YELLOW);
                delete.setFocusable(false);

                JButton cancel = new JButton("Cancel");
                cancel.setBackground(Color.YELLOW);
                cancel.setFocusable(false);

                delete.addActionListener(new Search3Listener());
                cancel.addActionListener(new CancelButton5Listener());

                top.add(message);
                bottom.add(searchText2);
                bottom.add(delete);
                bottom.add(cancel);

                displayInfo.add(top);
                displayInfo.add(bottom);

                search2.add(displayInfo, BorderLayout.CENTER);

                search2.pack();
                search2.setVisible(true);
            }
        }
    }

    private static class Search2Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (status)
            {
                model.setRowCount(0);
                ArrayList<Gas_Records> temp = new ArrayList<>();
                ArrayList<String> temp2 = new ArrayList<>();

                for (int i = 0; i < grom.size(); i++)
                {
                    if (searchText.getText().equals(grom.get(i).getName()))
                    {
                        temp.add(grom.get(i));
                    }
                }

                for (int j = 0; j < dt.size(); j++)
                {
                    String [] sName = dt.get(j).split(" ");
                    String name = sName[0];

                    if (searchText.getText().equals(name))
                    {
                        temp2.add(dt.get(j));
                    }
                }

                showTable(temp,temp2);
                search.setVisible(false);
            }
        }
    }

    private static class Search3Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            model.setRowCount(0);
            ArrayList<MiniMart_Records> temp = new ArrayList<>();
            ArrayList<String> temp2 = new ArrayList<>();

            for (int i = 0; i < menn.size(); i++)
            {
                if (searchText2.getText().equals(menn.get(i).getName()))
                {
                    temp.add(menn.get(i));
                }
            }

            for (int j = 0; j < dt2.size(); j++)
            {
                String [] sName = dt2.get(j).split(" ");
                String name = sName[0];

                if (searchText2.getText().equals(name))
                {
                    temp2.add(dt2.get(j));
                }
            }

            showTable2(temp,temp2);
            search2.setVisible(false);
        }
    }

    private static class CancelButton4Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            search.setVisible(false);
        }
    }

    private static class CancelButton5Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            search2.setVisible(false);
        }
    }
}
