import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Content
{
    private static JFrame rmFrame;
    private static JFrame rcFrame;
    private static JFrame asFrame;
    private static JPanel asPanel;
    private static JFrame efFrame;
    private static JTextField cText;
    private static JTextField c2Text;
    private static JTextField c3Text;
    private static JTextField c4Text;
    private static JTextField c5Text;
    private static JTextField mText;
    private static JTextField m2Text;
    private static JTextField m3Text;
    private static JPasswordField m4Text;
    private static JTextField ef1Text;
    private static JTextField ef2Text;
    private static JTextField ef3Text;
    private static JTextField ef4Text;
    private static JTextField ef5Text;
    private static JTextField mm1Text;
    private static JTextField mm2Text;
    private static JTextField mm3Text;
    private static JTextField mm4Text;
    private static JTextField mm5Text;
    private static JButton btn1;
    private static JButton btn2;
    private static JButton btn3;
    private static JButton btn4;
    public static final Color VERY_LIGHT_BLUE = new Color(51,204,255);

    public static void RegisterManager()
    {
        JPanel rmPanel = new JPanel();
        rmPanel.setBackground(VERY_LIGHT_BLUE);

        rmFrame = new JFrame("Register Manager");
        rmFrame.setPreferredSize(new Dimension(600, 300));
        rmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rmFrame.setLocationRelativeTo(null);
        rmFrame.add(rmPanel);
        rmFrame.pack();

        rmPanel.setLayout(null);

        JLabel label5 = new JLabel("Please enter information to create a Manager account");
        label5.setBounds(10,20,320,25);
        rmPanel.add(label5);

        JLabel label = new JLabel("Name");
        label.setBounds(10, 80, 80, 25);
        rmPanel.add(label);

        mText = new JTextField(30);
        mText.setBounds(120,80,165,25);
        rmPanel.add(mText);

        JLabel label2 = new JLabel("Phone Number");
        label2.setBounds(10, 120, 110, 25);
        rmPanel.add(label2);

        m2Text = new JTextField(30);
        m2Text.setBounds(120,120,165,25);
        rmPanel.add(m2Text);

        btn1 = new JButton("Create Account");
        btn1.setBounds(360,100,160,25);
        btn1.setBackground(Color.YELLOW);
        btn1.addActionListener(new CreateManagerListener());
        btn1.setFocusable(false);
        rmPanel.add(btn1);

        btn2 = new JButton("Cancel");
        btn2.setBounds(400,140,80,25);
        btn2.setBackground(Color.YELLOW);
        btn2.setFocusable(false);
        btn2.addActionListener(new CloseListener());
        rmPanel.add(btn2);

        JLabel label3 = new JLabel("Email");
        label3.setBounds(10, 160, 110, 25);
        rmPanel.add(label3);

        m3Text = new JTextField(30);
        m3Text.setBounds(120,160,165,25);
        rmPanel.add(m3Text);

        JLabel label4 = new JLabel("Password");
        label4.setBounds(10, 200, 80, 25);
        rmPanel.add(label4);

        m4Text = new JPasswordField(30);
        m4Text.setBounds(120,200,165,25);
        rmPanel.add(m4Text);

        rmFrame.setVisible(true);
    }

    public static void RegisterCashier()
    {
        JPanel rmPanel = new JPanel();
        rmPanel.setBackground(VERY_LIGHT_BLUE);

        rcFrame = new JFrame("Register Cashier");
        rcFrame.setPreferredSize(new Dimension(600, 300));
        rcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rcFrame.setLocationRelativeTo(null);
        rcFrame.add(rmPanel);
        rcFrame.pack();

        rmPanel.setLayout(null);

        JLabel label5 = new JLabel("Please enter information to create a Cashier account");
        label5.setBounds(10,20,320,25);
        rmPanel.add(label5);

        JLabel label = new JLabel("Name");
        label.setBounds(10, 80, 80, 25);
        rmPanel.add(label);

        cText = new JTextField(30);
        cText.setBounds(120,80,165,25);
        rmPanel.add(cText);

        JLabel label2 = new JLabel("Pay per hour");
        label2.setBounds(10, 120, 110, 25);
        rmPanel.add(label2);

        c2Text = new JTextField(30);
        c2Text.setBounds(120,120,165,25);
        rmPanel.add(c2Text);

        btn3 = new JButton("Create Account");
        btn3.setBounds(360,100,160,25);
        btn3.setBackground(Color.YELLOW);
        btn3.addActionListener(new CreateCashierListener());
        btn3.setFocusable(false);
        rmPanel.add(btn3);

        btn4 = new JButton("Cancel");
        btn4.setBounds(400,140,80,25);
        btn4.setBackground(Color.YELLOW);
        btn4.setFocusable(false);
        btn4.addActionListener(new Close2Listener());
        rmPanel.add(btn4);

        JLabel label4 = new JLabel("Password");
        label4.setBounds(10, 160, 110, 25);
        rmPanel.add(label4);

        c4Text = new JPasswordField(30);
        c4Text.setBounds(120,160,165,25);
        rmPanel.add(c4Text);

        rcFrame.setVisible(true);
    }

    public static void AddStock()
    {
        Log_In.setFOwner();

        asPanel = new JPanel();
        asPanel.setBackground(VERY_LIGHT_BLUE);

        asFrame = new JFrame("Add Stock");
        asFrame.setPreferredSize(new Dimension(600, 400));
        asFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        asFrame.setLocationRelativeTo(null);
        asFrame.add(asPanel);
        asFrame.pack();

        asPanel.setLayout(null);

        JLabel label5 = new JLabel("Please enter information for Mini-Mart product");
        label5.setBounds(100,20,390,25);
        asPanel.add(label5);

        JLabel label = new JLabel("Name");
        label.setBounds(10, 60, 80, 25);
        asPanel.add(label);

        mm1Text = new JTextField(30);
        mm1Text.setBounds(100,60,165,25);
        asPanel.add(mm1Text);

        JLabel label2 = new JLabel("price");
        label2.setBounds(10, 100, 120, 25);
        asPanel.add(label2);

        mm2Text = new JTextField(30);
        mm2Text.setBounds(100,100,165,25);
        asPanel.add(mm2Text);

        JLabel label3 = new JLabel("quantity");
        label3.setBounds(10,140,120,25);
        asPanel.add(label3);

        mm3Text = new JTextField(30);
        mm3Text.setBounds(100,140,165,25);
        asPanel.add(mm3Text);

        JLabel label4 = new JLabel("volume");
        label4.setBounds(10,180,120,25);
        asPanel.add(label4);

        mm4Text = new JTextField(30);
        mm4Text.setBounds(100,180,165,25);
        asPanel.add(mm4Text);

        JLabel label6 = new JLabel("Purchase Price");
        label6.setBounds(10,220,120,25);
        asPanel.add(label6);

        mm5Text = new JTextField(30);
        mm5Text.setBounds(100,220,165,25);
        asPanel.add(mm5Text);

        JButton a4button = new JButton("Add");
        a4button.setBounds(100,260,150,25);
        a4button.setBackground(Color.YELLOW);
        a4button.setFocusable(false);
        a4button.addActionListener(new MMListener());
        asPanel.add(a4button);

        JButton a3button = new JButton("Cancel");
        a3button.setBounds(280,260,150,25);
        a3button.setBackground(Color.YELLOW);
        a3button.setFocusable(false);
        a3button.addActionListener(new Close3Listener());
        asPanel.add(a3button);

        asFrame.setVisible(true);
    }

    public static void AddFluid()
    {
        Log_In.setFOwner();

        JPanel efPanel = new JPanel();
        efPanel.setBackground(VERY_LIGHT_BLUE);

        efFrame = new JFrame("Petrol entry");
        efFrame.setPreferredSize(new Dimension(600, 400));
        efFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        efFrame.setLocationRelativeTo(null);
        efFrame.add(efPanel);
        efFrame.pack();

        efPanel.setLayout(null);

        JLabel label5 = new JLabel("Please enter information for Petrol product");
        label5.setBounds(100,20,390,25);
        efPanel.add(label5);

        JLabel label = new JLabel("Name");
        label.setBounds(10, 60, 120, 25);
        efPanel.add(label);

        ef1Text = new JTextField(30);
        ef1Text.setBounds(100,60,165,25);
        efPanel.add(ef1Text);

        JLabel label2 = new JLabel("price");
        label2.setBounds(10, 100, 120, 25);
        efPanel.add(label2);

        ef2Text = new JTextField(30);
        ef2Text.setBounds(100,100,165,25);
        efPanel.add(ef2Text);

        JLabel label3 = new JLabel("quantity");
        label3.setBounds(10,140,120,25);
        efPanel.add(label3);

        ef3Text = new JTextField(30);
        ef3Text.setBounds(100,140,165,25);
        efPanel.add(ef3Text);

        JLabel label4 = new JLabel("Purchase Price");
        label4.setBounds(10,180,120,25);
        efPanel.add(label4);

        ef4Text = new JTextField(30);
        ef4Text.setBounds(100,180,165,25);
        efPanel.add(ef4Text);

        JLabel label6 = new JLabel("Week");
        label6.setBounds(10,220,120,25);
        efPanel.add(label6);

        ef5Text = new JTextField(30);
        ef5Text.setBounds(100,220,165,25);
        efPanel.add(ef5Text);

        JButton a4button = new JButton("Add");
        a4button.setBounds(100,270,150,25);
        a4button.setBackground(Color.YELLOW);
        a4button.setFocusable(false);
        a4button.addActionListener(new EFListener());
        efPanel.add(a4button);

        JButton a3button = new JButton("Cancel");
        a3button.setBounds(280,270,150,25);
        a3button.setBackground(Color.YELLOW);
        a3button.setFocusable(false);
        a3button.addActionListener(new Close4Listener());
        efPanel.add(a3button);

        efFrame.setVisible(true);
    }

    public static void RecordManagement()
    {
        Record_Listing.Inventory();
    }

    private static class CloseListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            rmFrame.setVisible(false);
        }
    }

    private static class Close2Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            rcFrame.setVisible(false);
        }
    }

    private static class Close3Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            asFrame.setVisible(false);
            Log_In.setTOwner();
        }
    }

    private static class Close4Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            efFrame.setVisible(false);
            Log_In.setTOwner();
        }
    }

    private static class CreateManagerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            rmFrame.setVisible(false);
            char a = ' ';
            String name[] = mText.getText().split(" ");
            String name0 = name[0];
            String pass = m4Text.getText();
            String email = m3Text.getText();
            String num = m2Text.getText();

            for (int i =0; i < name0.length(); i++)
            {
                a = name0.charAt(i);
                break;
            }
            String name2 = a + "." + name[1];

            try
            {
                File file = new File("LogFile.txt");

                if (!file.exists())
                {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file, true);

                fw.write("\n" + name2);
                fw.write(" ");
                fw.write(pass);
                fw.write(" ");
                fw.write("Manager");
                fw.write(" ");
                fw.write(email);
                fw.write(" ");
                fw.write(num);
                fw.close();
            }
            catch (IOException exception)
            {
                System.out.println("Error");
            }
        }
    }

    private static class CreateCashierListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            rcFrame.setVisible(false);
            char a = ' ';
            String name[] = cText.getText().split(" ");
            String name0 = name[0];
            String pass = c4Text.getText();
            String pay = c2Text.getText();

            for (int i =0; i < name0.length(); i++)
            {
                a = name0.charAt(i);
                break;
            }
            String name2 = a + "." + name[1];

            try
            {
                File file = new File("LogFile.txt");

                if (!file.exists())
                {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file, true);

                fw.write("\n" + name2);
                fw.write(" ");
                fw.write(pass);
                fw.write(" ");
                fw.write("Cashier");
                fw.write(" ");
                fw.write(pay);
                fw.close();
            }
            catch (IOException exception)
            {
                System.out.println("Error");
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
            String purchasePrice = ef4Text.getText();
            String week = ef5Text.getText();

            try
            {
                File file = new File("AddEF.txt");

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
                fw.write(purchasePrice);
                fw.write(" ");
                fw.write(week+ "\n");
                fw.close();
            }
            catch (IOException exception)
            {
                System.out.println("Error");
            }
            Log_In.setTOwner();
        }
    }

    private static class MMListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            asFrame.setVisible(false);

            String name = mm1Text.getText();
            String price = mm2Text.getText();
            String quantity = mm3Text.getText();
            String volume = mm4Text.getText();
            String purchasePrice = mm5Text.getText();

            try
            {
                File file = new File("AddMMStock.txt");

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
                fw.write(purchasePrice + "\n");
                fw.close();
            }
            catch (IOException exception)
            {
                System.out.println("Error");
            }
            Log_In.setTOwner();
        }
    }
}
