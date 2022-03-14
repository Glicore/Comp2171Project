public class Manager extends Worker
{
    private String email;

    public Manager(String name, int age, String DOB, String gender, int phoneNum, String email)
    {
        super(name, age, DOB, gender, phoneNum);
        this.email = email;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
