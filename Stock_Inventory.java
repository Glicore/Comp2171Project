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

public class Stock_Inventory
{
    private static JFrame iFrame;
    private static JFrame sec;
    private static DefaultTableModel model;
    private static JScrollPane scrollPane;
    private static JTable table;
    private static ArrayList<Gas_Records> grom;
    private static ArrayList<Gas_Records> temp;
    private static ArrayList<Gas_Records> low;
    private static ArrayList<Gas_Records> temp3;
    private static ArrayList<MiniMart_Records> menn;
    private static ArrayList<MiniMart_Records> temp2;
    private static boolean status;
    private static JFrame lowStockFrame;
    private static ArrayList<String> pur;
    private static ArrayList<String> pur2;
    private static ArrayList<String> date2;
    private static ArrayList<String> date3;
    private static JFrame errorScreen;
    private static JFrame search;
    private static JTextField searchText;
    private static JFrame jm;
    private static JTextField txtMsg;
    private static JTextField txtMsg2;
    private static JTextField txtMsg3;
    private static String currName;
    private static String currPurchase;
    private static String currDate;
    private static double currPrice;
    private static String newName;
    private static int currQuantity;
    private static double newPrice;
    private static int newQuantity;
    private static JTextField nam;
    private static JTextField text;
    private static JTextField text2;
    public static final Color VERY_LIGHT_BLUE = new Color(51,204,255);

    public static class StockListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Record_Listing.setNotWindow();

            calcGasSales();

//            for (int i = 0; i < temp.size(); i++)
//            {
//                System.out.println(temp.get(i).getQuantity());
//            }

            grom = loadGasSales("AddEF.txt");

            menn = loadMMSales("AddMMStock.txt");

            String [] columnNames =  {"Name", "Price", "Quantity","Date","Profit","Loss"};//This is the column headers for the table.
            model = new DefaultTableModel(columnNames,0);//Row Count set to 0.
            table = new JTable(model);//Create a new JTable.

            showTable(grom);

            table.setPreferredScrollableViewportSize(new Dimension(630, 250));//Sets the preferred size of the viewport for this table.
            table.setFillsViewportHeight(true);//Sets whether or not this table is always made large enough to fill the height of an enclosing viewport.
            table.setBackground(VERY_LIGHT_BLUE);//Sets the background of the table to light grey.

            scrollPane = new JScrollPane(table);

            JPanel panel = new JPanel();
            panel.setBackground(VERY_LIGHT_BLUE);
            panel.add(scrollPane);

            JButton btn1 = new JButton("Search Records");
            btn1.setBounds(10,300,130,25);
            btn1.addActionListener(new SearchListener());
            btn1.setBackground(Color.YELLOW);
            btn1.setFocusable(false);

            JButton btn2 = new JButton("Refuel");
            btn2.setBounds(150,300,130,25);
            btn2.setBackground(Color.YELLOW);
            btn2.addActionListener(new EditListener());
            btn2.setFocusable(false);

            JButton btn3 = new JButton("Update");
            btn3.setBounds(290,300,130,25);
            btn3.setBackground(Color.YELLOW);
            btn3.addActionListener(new UpdateListener());
            btn3.setFocusable(false);

            JButton button1 = new JButton("Back");
            button1.setBounds(480,300,130,25);
            button1.setBackground(Color.YELLOW);
            button1.addActionListener(new BackListener());
            button1.setFocusable(false);

            iFrame = new JFrame("Stock Inventory");
            iFrame.setPreferredSize(new Dimension(650, 500));
            iFrame.setLocationRelativeTo(null);
            iFrame.add(btn1);
            iFrame.add(btn2);
            iFrame.add(btn3);
            iFrame.add(button1);
            iFrame.add(panel);
            iFrame.pack();

            iFrame.setVisible(true);
        }
    }

    private static ArrayList<Gas_Records> loadGasSales(String vfile)
    {
        ArrayList<Gas_Records> plist = new ArrayList<Gas_Records>();

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
                    String week = nextLine[4];

                    Gas_Records p = new Gas_Records(name, price, quantity);
                    p.setDate(week);

                    plist.add(p);
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

                    MiniMart_Records p = new MiniMart_Records(name, price, quantity,volume);

                    plist.add(p);
                }
            }
            catch (IOException e)
            {
                System.out.println("ERROR2");
            }
        }
        return plist;
    }

    private static void showTable(ArrayList<Gas_Records> glist)
    {
        for (int i = 0; i < glist.size(); i++)
        {
            addToTable(glist.get(i), date2.get(i) , pur.get(i));
        }
    }

    private static void showTable2(ArrayList<MiniMart_Records> glist)
    {
        for (int i = 0; i < glist.size(); i++)
        {
            addToTable2(glist.get(i));
        }
    }

    public static void addGasRecord(Gas_Records g)
    {
        //addToTable(g);
    }

    public static void addMMRecords(MiniMart_Records m)
    {
        addToTable2(m);
    }

    private static void addToTable(Gas_Records g, String d, String p)
    {
        String name = g.getName();
        double p_1 = Double.parseDouble(p);
        if (p_1 <= 0)
        {
            String[] item = {name, "" + g.getPrice(), "" + g.getQuantity(), "" + d, "" + Math.abs(p_1), "" + "        -"};
            model.addRow(item);
        }
        else
        {
            String[] item = {name, "" + g.getPrice(), "" + g.getQuantity(), "" + d, "" + "        -", "" + p_1};
            model.addRow(item);
        }
    }

    private static void addToTable2(MiniMart_Records m)
    {
        String name = m.getName();
        String[] item = {name, "" + m.getPrice(), "" + m.getQuantity()};

        model.addRow(item);
    }

    private static class BackListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            iFrame.setVisible(false);
            Record_Listing.setWindow();
        }
    }

    private static class StatusListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            iFrame.setTitle("Gas Sales Inventory");
            status = true;

            model.setRowCount(0);
            grom = loadGasSales("AddEF.txt");
            showTable(grom);
        }
    }

    private static class Status2Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            iFrame.setTitle("Mini-Mart Sales Inventory");
            status = false;

            model.setRowCount(0);
            menn = loadMMSales("AddMMStock.txt");
            showTable2(menn);
        }
    }

    public static void calcGasSales()
    {
        String vfile = "AddEF.txt";
        String vfile2 = "EFSales.txt";
        double total;
        int amount;
        int remainder;
        temp = new ArrayList<>();
        temp3 = new ArrayList<>();
        pur = new ArrayList<>();
        pur2 = new ArrayList<>();
        date2 = new ArrayList<>();
        date3 = new ArrayList<>();
        int count = 0;

        if (vfile.length() != 0)
        {
            Scanner vscan = null;
            Scanner vscan2 = null;

            try
            {
                vscan = new Scanner(new File(vfile));
                vscan2 = new Scanner(new File(vfile2));

                while (vscan.hasNext())
                {
                    String[] nextLine = vscan.nextLine().split(" ");
                    String name = nextLine[0];
                    double price = Double.parseDouble(nextLine[1]);
                    int quantity = Integer.parseInt(nextLine[2]);
                    String purchase = nextLine[3];
                    String date = nextLine[4];
                    //System.out.println(name + " " + price);

                    Gas_Records g = new Gas_Records(name, price, quantity);
                    g.setDate(date);

                    //System.out.println(g.getName() + " " + g.getPrice());

                    temp.add(g);
                    count++;
                    pur.add(purchase);
                    //Collections.copy(pur2,pur);
                    date2.add(date);
                }

                //System.out.println(count);
//                for (int i = 0; i < temp.size(); i++)
//                {
//                    System.out.println(temp.get(i).getName() + " " + temp.get(i).getPrice());
//                }

                while (vscan2.hasNext())
                {
                    String[] nextLine2 = vscan2.nextLine().split(" ");
                    String name2 = nextLine2[0];
                    double price2 = Double.parseDouble(nextLine2[1]);
                    int quantity2 = Integer.parseInt(nextLine2[2]);
                    String date = nextLine2[3];

                    Gas_Records g1 = new Gas_Records(name2, price2, quantity2);
                    //System.out.println(g1.getName() + " " + g1.getPrice());

                    temp3.add(g1);
                    date3.add(date);
                }

//                for (int i = 0; i < temp3.size(); i++)
//                {
//                    System.out.println(temp3.get(i).getName() + " " + temp3.get(i).getPrice());
//                }

                for (int i = 0; i < temp.size(); i++)
                {
                    for (int j = 0; j < temp3.size(); j++)
                    {
                        if (temp.get(i).getName().equals(temp3.get(j).getName()) && date2.get(i).equals(date3.get(j)))
                        {
                            //System.out.println(temp.get(i).getName() + " " + temp.get(i).getPrice() + " " + temp.get(i).getQuantity());
                            int quantity = temp.get(i).getQuantity() - temp3.get(j).getQuantity();
                            temp.get(i).setQuantity(quantity);
                        }
                    }
                }

                for (int i = 0; i < temp.size(); i++)
                {
                    for (int j = 0; j < temp3.size(); j++)
                    {
                        if (temp.get(i).getName().equals(temp3.get(j).getName()) && date2.get(i).equals(date3.get(j)))
                        {
                            double value = (temp3.get(j).getPrice() * temp3.get(j).getQuantity());

                            double purchase = Double.parseDouble(pur.get(i));
                            double val1 = (purchase - value);
                            String num1 = Double.toString(val1);
                            //System.out.println(num1);

                            pur.set(i,num1);
                        }
                    }
                }
            }
            catch (IOException e)
            {
                System.out.println("ERROR2");
            }
        }
    }

    private static class UpdateListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            model.setRowCount(0);
            showTable(temp);

            low = new ArrayList<>();

            for (int i = 0; i < temp.size(); i++)
            {
                if (temp.get(i).getQuantity() <= 1000)
                {
                    low.add(temp.get(i));
                }
            }

            if (low.size() != 0)
            {
                for (int i = 0; i < low.size(); i++)
                {
                    //System.out.println(low.get(i).getQuantity());
                    String name = low.get(i).getName();
                    String quantity = Integer.toString(low.get(i).getQuantity());

                    errorScreen = new JFrame("Warning!!! Stock is low");

                    JPanel pane = new JPanel();
                    pane.setBackground(Color.RED);
                    pane.setPreferredSize(new Dimension(600, 150));
                    pane.setLayout(new GridLayout(2, 0));

                    JLabel txt = new JLabel("Warning!!! Stock is low");
                    txt.setForeground(Color.WHITE);
                    txt.setHorizontalAlignment(SwingConstants.CENTER);
                    txt.setFont(new Font("Verdana", Font.BOLD, 35));

                    JLabel txt2 = new JLabel(name + " " + "Is below One Thousand Litres!!!" + " " + "Currently at: " + quantity);
                    txt2.setForeground(Color.WHITE);
                    txt2.setHorizontalAlignment(SwingConstants.CENTER);
                    txt2.setFont(new Font("Verdana", Font.PLAIN, 20));

                    pane.add(txt);
                    pane.add(txt2);

                    JPanel response = new JPanel();
                    response.setBackground(Color.RED);
                    response.setPreferredSize(new Dimension(600, 150));

                    errorScreen.add(response, BorderLayout.SOUTH);
                    errorScreen.add(pane, BorderLayout.CENTER);
                    errorScreen.pack();
                    errorScreen.setVisible(true);
                }
            }
        }
    }

    private static class SearchListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
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
    }

    private static class Search2Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            model.setRowCount(0);
            ArrayList<Gas_Records> temp1 = new ArrayList<>();

            for (int i = 0; i < temp.size(); i++)
            {
                if (searchText.getText().equals(temp.get(i).getName()))
                {
                    temp1.add(temp.get(i));
                }
            }

            showTable(temp1);
            search.setVisible(false);
        }
    }

    private static class CancelButton4Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            search.setVisible(false);
        }
    }

    private static class EditListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            jm = new JFrame("Edit Gas");
            jm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jm.setLocationRelativeTo(null);

            JPanel displayInfo = new JPanel();
            displayInfo.setPreferredSize (new Dimension(600, 300));
            displayInfo.setBackground(VERY_LIGHT_BLUE);

            JPanel top = new JPanel();
            top.setPreferredSize(new Dimension(600,100));
            top.setBackground(VERY_LIGHT_BLUE);

            JPanel bottom = new JPanel();
            bottom.setPreferredSize(new Dimension(600,200));
            bottom.setBackground(VERY_LIGHT_BLUE);

            JLabel message = new JLabel("Please enter the name and week of the gas record.");
            message.setFont(new Font("Verdana",Font.PLAIN,12));

            JLabel newName = new JLabel("Name: ");
            newName.setFont(new Font("Verdana", Font.BOLD, 15));
            bottom.add(newName);
            nam = new JTextField(20);
            bottom.add(nam);

            JLabel price = new JLabel("Week: ");
            price.setFont(new Font("Verdana", Font.BOLD, 15));
            bottom.add(price);
            text = new JTextField(20);
            bottom.add(text);

            JButton edit = new JButton("Edit");
            edit.setBackground(Color.YELLOW);
            edit.setFocusable(false);

            JButton cancel = new JButton("Cancel");
            cancel.setBackground(Color.YELLOW);
            cancel.setFocusable(false);

            edit.addActionListener(new EditButtonListener_2());
            cancel.addActionListener(new CancelButton_2Listener());

            top.add(message);
            bottom.add(edit);
            bottom.add(cancel);

            displayInfo.add(top);
            displayInfo.add(bottom);

            jm.add(displayInfo, BorderLayout.CENTER);

            jm.pack();
            jm.setVisible(true);
        }
    }

    private static class EditButtonListener_2 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String name = nam.getText();
            String week = text.getText();
            boolean stat_ = false;

            for (int i = 0; i < temp.size(); i++)
            {
                if (name.equals(temp.get(i).getName()) && week.equals(temp.get(i).getDate()))
                {
                    stat_ = true;
                }
            }

            if (stat_ == false)
            {
                errorScreen = new JFrame("ERROR!!!");

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

                errorScreen.add(response, BorderLayout.SOUTH);
                errorScreen.add(pane, BorderLayout.CENTER);
                errorScreen.pack();
                errorScreen.setVisible(true);
            }
            else
            {
                File file = new File("AddEF.txt");
                ArrayList<Gas_Records> eProm = new ArrayList<>();
                ArrayList<String> purch = new ArrayList<>();
                ArrayList<String> dte = new ArrayList<>();
                String date;

                try
                {
                    Scanner scan = new Scanner(file);

                    while (scan.hasNext())
                    {
                        String[] nameInFile = scan.nextLine().split(" ");
                        String nameSplit = nameInFile[0];
                        double price = Double.parseDouble(nameInFile[1]);
                        int quantity = Integer.parseInt(nameInFile[2]);
                        String pur = nameInFile[3];
                        date = nameInFile[4];

                        if (name.equals(nameSplit) && week.equals(date))
                        {
                            currName = nameSplit;
                            currPrice = price;
                            currQuantity = quantity;
                            currPurchase = pur;
                            currDate = date;

                            continue;
                        }

                        Gas_Records p = new Gas_Records(nameSplit, price, quantity);
                        p.setDate(date);
                        eProm.add(p);
                        purch.add(pur);
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
                        String newPurchase = purch.get(i);
                        String newWeek = dte.get(i);
                        fw.write(newName + " ");
                        fw.write(newPrice + " ");
                        fw.write(newQuantity + " ");
                        fw.write(newPurchase + " ");
                        fw.write(newWeek + " ");
                        fw.write("" + "\n");
                    }
                    fw.close();

                    for (int i = 0; i < temp.size(); i++)
                    {
                        if (currName.equals(temp.get(i).getName()) && currDate.equals(temp.get(i).getDate()))
                        {
                            temp.remove(i);
                        }
                    }
                }
                catch (IOException exception)
                {
                    exception.printStackTrace();
                }

                sec = new JFrame("Editing petroleum...");
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

                JLabel mes = new JLabel("Please enter the amount you are adding.");
                mes.setFont(new Font("Verdana", Font.BOLD, 18));

                JLabel newQuan = new JLabel("New Quantity: ");
                newQuan.setFont(new Font("Verdana", Font.BOLD, 15));
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
                bottom.add(newQuan, gbc);

                gbc.gridx = 1;
                gbc.gridy = 0;
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

    private static class ConfirmButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

            if (text2.getText().equals(""))
            {
                newQuantity = currQuantity;
            }
            else
            {
                newQuantity = Integer.parseInt(text2.getText());
            }

            Gas_Records pr = new Gas_Records(currName, currPrice, newQuantity);

            temp.add(pr);


            addToTable(pr, currDate, currPurchase);

            try
            {
                File file = new File("AddEF.txt");

                if (!file.exists())
                {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file, true);

                fw.write(currName + " ");
                fw.write(currPrice + " ");
                fw.write(newQuantity + " ");
                fw.write( currPurchase + " ");
                fw.write(currDate + "\n");
                fw.close();
            }
            catch (IOException exception)
            {

            }

            model.setRowCount(0);
            showTable(temp);
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

    private static class CancelButton_2Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            jm.setVisible(false);
        }
    }
}
