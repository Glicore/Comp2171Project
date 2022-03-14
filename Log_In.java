import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Log_In
{
    private static JTextField uText;
    private static String status;
    private static JPasswordField pText;
    private static JButton btn;
    private static JButton btn2;
    private static JFrame frame;
    private static JFrame ownerFrame;
    private static JFrame managerFrame;
    private static JFrame cashierFrame;
    private static String role = "Owner";
    public static final Color VERY_LIGHT_BLUE = new Color(51,204,255);

    public static void main(String[] args)
    {
        JPanel panel = new JPanel();
        panel.setBackground(VERY_LIGHT_BLUE);

        frame = new JFrame();
        frame.setSize(350,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);

        panel.setLayout(null);

        JLabel label = new JLabel("User");
        label.setBounds(10, 20, 80, 25);
        panel.add(label);

        uText = new JTextField(20);
        uText.setBounds(100,20,165,25);
        panel.add(uText);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(10,50,80,25);
        panel.add(passLabel);

        pText = new JPasswordField();
        pText.setBounds(100,50,165,25);
        panel.add(pText);

        btn = new JButton("Log In");
        btn.setBounds(50,80,80,25);
        btn.setFocusable(false);
        btn.setBackground(Color.YELLOW);
        btn.setForeground(Color.BLACK);
        btn.addActionListener(new LogInListener());
        panel.add(btn);

        btn2 = new JButton("Close");
        btn2.setBounds(140,80,80,25);
        btn2.setFocusable(false);
        btn2.setBackground(Color.YELLOW);
        btn2.setForeground(Color.BLACK);
        btn2.addActionListener(new CloseListener());
        panel.add(btn2);

        frame.setVisible(true);
    }

    private static class CloseListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            frame.setVisible(false);
            System.exit(0);
        }
    }

    private static class LogInListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //managerState();
            //ownerState();
            //cashierState();
            String logFile = "LogFile.txt";

            if (logFile.length() != 0)
            {
                Scanner lscan = null;

                try
                {
                    lscan = new Scanner(new File(logFile));

                    while (lscan.hasNext())
                    {
                        String[] words = lscan.nextLine().split(" ");
                        String name = words[0];
                        String pass = words[1];
                        role = words[2];
                        if (name.equals(uText.getText()))
                        {
                            if (pass.equals(pText.getText()))
                            {
                                if (role.equals("Owner"))
                                {
                                    status = "Owner";
                                    ownerState();
                                }
                                else if (role.equals("Manager"))
                                {
                                    status = "Manager";
                                    managerState();
                                }
                                else if (role.equals("Cashier"))
                                {
                                    status = "Cashier";
                                    cashierState();
                                }
                            }
                        }
                    }
                }
                catch (IOException ex)
                {
                    System.out.println("Error");
                }
            }
        }
    }

    static void managerState()
    {
        frame.setVisible(false);

        JPanel mPanel = new JPanel();
        mPanel.setPreferredSize (new Dimension(600, 150));
        mPanel.setBackground(VERY_LIGHT_BLUE);
        JPanel m2Panel = new JPanel();
        m2Panel.setPreferredSize (new Dimension(600, 150));
        m2Panel.setBackground(VERY_LIGHT_BLUE);
        m2Panel.setLayout(new GridLayout(2,0));

        managerFrame = new JFrame("Options Screen");
        managerFrame.setSize(600,300);
        managerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        managerFrame.setLocationRelativeTo(null);
        managerFrame.add(m2Panel,BorderLayout.NORTH);
        managerFrame.add(mPanel,BorderLayout.SOUTH);
        managerFrame.pack();

        JLabel heading = new JLabel("Welcome!");
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setFont(new Font("Verdana", Font.BOLD,30));
        m2Panel.add(heading);

        JLabel message = new JLabel("to the New Hope Management System");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setFont(new Font("Verdana",Font.PLAIN,12));
        m2Panel.add(message);

        JButton button2 = new JButton("Register Cashier");
        button2.setBounds(30,10,170,30);
        button2.addActionListener(new RegisterCashierListener());
        button2.setBackground(Color.YELLOW);
        button2.setFocusable(false);
        mPanel.add(button2);

        JButton button3 = new JButton("Record Management");
        button3.setBounds(210,10,170,30);
        button3.addActionListener(new RecordListener());
        button3.setBackground(Color.YELLOW);
        button3.setFocusable(false);
        mPanel.add(button3);

        JButton button4 = new JButton("Pay Calculation");
        button4.setBounds(390,10,170,30);
        button4.setBackground(Color.YELLOW);
        button4.setFocusable(false);
        mPanel.add(button4);

        JButton button5 = new JButton("Add stock");
        button5.setBounds(130,50,170,30);
        button5.addActionListener(new AddFluidListener());
        button5.setBackground(Color.YELLOW);
        button5.setFocusable(false);
        mPanel.add(button5);

        JButton button6 = new JButton("Log Out");
        button6.setBounds(310,50,170,30);
        button6.setFocusable(false);
        button6.setBackground(Color.YELLOW);
        button6.addActionListener(new ExitListener());
        mPanel.add(button6);

        managerFrame.setVisible(true);
    }

    static void ownerState()
    {
        frame.setVisible(false);

        JPanel oPanel = new JPanel();
        oPanel.setPreferredSize (new Dimension(600, 150));
        oPanel.setBackground(VERY_LIGHT_BLUE);
        JPanel o2Panel = new JPanel();
        o2Panel.setPreferredSize (new Dimension(600, 150));
        o2Panel.setBackground(VERY_LIGHT_BLUE);
        o2Panel.setLayout(new GridLayout(2,0));

        ownerFrame = new JFrame("Options Screen");
        ownerFrame.setSize(600,300);
        ownerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ownerFrame.setLocationRelativeTo(null);
        ownerFrame.add(o2Panel,BorderLayout.NORTH);
        ownerFrame.add(oPanel,BorderLayout.SOUTH);
        ownerFrame.pack();

        oPanel.setLayout(null);

        JLabel heading = new JLabel("Welcome!");
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setFont(new Font("Verdana", Font.BOLD,30));
        o2Panel.add(heading);

        JLabel message = new JLabel("to the New Hope Management System");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setFont(new Font("Verdana",Font.PLAIN,12));
        o2Panel.add(message);

        JButton button1 = new JButton("Register Manager");
        button1.setBounds(30,10,170,30);
        button1.setFocusable(false);
        button1.addActionListener(new RegisterManagerListener());
        button1.setBackground(Color.YELLOW);
        oPanel.add(button1);

        JButton button2 = new JButton("Register Cashier");
        button2.setBounds(210,10,170,30);
        button2.addActionListener(new RegisterCashierListener());
        button2.setFocusable(false);
        button2.setBackground(Color.YELLOW);
        oPanel.add(button2);

        JButton button3 = new JButton("Record Management");
        button3.setBounds(390,10,170,30);
        button3.setFocusable(false);
        button3.addActionListener(new RecordListener());
        button3.setBackground(Color.YELLOW);
        oPanel.add(button3);

        JButton button4 = new JButton("Add Engine Fluid");
        button4.setBounds(30,50,170,30);
        button4.setFocusable(false);
        button4.addActionListener(new AddFluidListener());
        button4.setBackground(Color.YELLOW);
        oPanel.add(button4);

        JButton button5 = new JButton("Add M.M. Products");
        button5.setBounds(210,50,170,30);
        button5.setFocusable(false);
        button5.addActionListener(new AddStockListener());
        button5.setBackground(Color.YELLOW);
        oPanel.add(button5);

        JButton button6 = new JButton("Log Out");
        button6.setBounds(390,50,170,30);
        button6.setFocusable(false);
        button6.addActionListener(new Exit2Listener());
        button6.setBackground(Color.YELLOW);
        oPanel.add(button6);

        ownerFrame.setVisible(true);
    }

    static void setFOwner()
    {
        if (status.equals("Owner"))
        {
            ownerFrame.setVisible(false);
        }
        else if (status.equals("Manager"))
        {
            managerFrame.setVisible(false);
        }
        else if (status.equals("Cashier"))
        {
            cashierFrame.setVisible(false);
        }
    }
    static void setTOwner()
    {
        if (status.equals("Owner"))
        {
            ownerFrame.setVisible(true);
        }
        else if (status.equals("Manager"))
        {
            managerFrame.setVisible(true);
        }
        else if (status.equals("Cashier"))
        {
            cashierFrame.setVisible(true);
        }
    }

    static void cashierState()
    {
        frame.setVisible(false);

        JPanel cPanel = new JPanel();
        cPanel.setPreferredSize (new Dimension(600, 150));
        cPanel.setBackground(VERY_LIGHT_BLUE);
        JPanel c2Panel = new JPanel();
        c2Panel.setPreferredSize (new Dimension(600, 150));
        c2Panel.setBackground(VERY_LIGHT_BLUE);
        c2Panel.setLayout(new GridLayout(2,0));

        cashierFrame = new JFrame("Options Screen");
        cashierFrame.setSize(600,300);
        cashierFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cashierFrame.setLocationRelativeTo(null);
        cashierFrame.add(c2Panel,BorderLayout.NORTH);
        cashierFrame.add(cPanel,BorderLayout.SOUTH);
        cashierFrame.pack();

        cPanel.setLayout(null);

        JLabel heading = new JLabel("Welcome!");
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setFont(new Font("Verdana", Font.BOLD,30));
        c2Panel.add(heading);

        JLabel message = new JLabel("to the New Hope Management System");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setFont(new Font("Verdana",Font.PLAIN,12));
        c2Panel.add(message);

        JButton button3 = new JButton("Record Management");
        button3.setBounds(30,10,170,30);
        button3.addActionListener(new RecordListener());
        button3.setBackground(Color.YELLOW);
        button3.setFocusable(false);
        cPanel.add(button3);

        JButton button5 = new JButton("Add stock");
        button5.setBounds(210,10,170,30);
        button5.addActionListener(new AddFluidListener());
        button5.setBackground(Color.YELLOW);
        button5.setFocusable(false);
        cPanel.add(button5);

        JButton button6 = new JButton("Log Out");
        button6.setBounds(390,10,170,30);
        button6.setFocusable(false);
        button6.addActionListener(new Exit3Listener());
        button6.setBackground(Color.YELLOW);
        cPanel.add(button6);

        cashierFrame.setVisible(true);
    }

    private static class ExitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            managerFrame.setVisible(false);

            uText.setText("");
            pText.setText("");

            frame.setVisible(true);
        }
    }

    private static class Exit2Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            ownerFrame.setVisible(false);

            uText.setText("");
            pText.setText("");

            frame.setVisible(true);
        }
    }

    private static class Exit3Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            cashierFrame.setVisible(false);

            uText.setText("");
            pText.setText("");

            frame.setVisible(true);
        }
    }

    private static class RegisterManagerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Content.RegisterManager();
        }
    }

    private static class RegisterCashierListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Content.RegisterCashier();
        }
    }

    private static class AddStockListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Content.AddStock();
        }
    }

    private static class AddFluidListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Content.AddFluid();
        }
    }

    private static class RecordListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Content.RecordManagement();
        }
    }
}

