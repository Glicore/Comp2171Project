public class Worker
{
    private String name;
    private int age;
    private String DOB;
    private String gender;
    private int phoneNum;

    public Worker()
    {}

    public Worker(String name, int age, String DOB, String gender, int phoneNum)
    {
        this.name = name;
        this.age = age;
        this.DOB = DOB;
        this.gender = gender;
        this.phoneNum = phoneNum;
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public String getDOB()
    {
        return DOB;
    }

    public String getGender()
    {
        return gender;
    }

    public int getPhoneNum()
    {
        return phoneNum;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public void setDOB(String DOB)
    {
        this.DOB = DOB;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public void setPhoneNum(int phoneNum)
    {
        this.phoneNum = phoneNum;
    }
}
