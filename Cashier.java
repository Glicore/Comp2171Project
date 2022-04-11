public class Cashier extends Worker
{
    private double pay;
    private String dateStarted;
    private int hours;

    public Cashier(String name, int age, String DOB, String gender, int phoneNum,double pay, String dateStarted, int hours)
    {
        super(name, age, DOB, gender, phoneNum);
        this.pay = pay;
        this.dateStarted = dateStarted;
        this.hours = hours;
    }

    public String getDate()
    {
        return this.dateStarted;
    }

    public int getHours()
    {
        return this.hours;
    }

    public double getPay()
    {
        return this.pay;
    }

    public void setDate(String dateStarted)
    {
        this.dateStarted = dateStarted;
    }

    public void setPay(double pay)
    {
        this.pay = pay;
    }

    public void setHours(int hours)
    {
        this.hours = hours;
    }
}
