public class Gas_Records implements Comparable<Gas_Records>
{
    private int id;
    private String name;
    private String date;
    private double price;
    private int quantity;
    private double pricePerGallon;
    private static int ID;
    private int count = 0;

    public Gas_Records()
    {}

    public Gas_Records(String name, double price, int quantity)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName()
    {
        return name;
    }

    public String getDate()
    {
        return date;
    }

    public double getPrice()
    {
        return price;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public int getId()
    {
        return id;
    }

    public double getPricePerGallon()
    {
        return pricePerGallon;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public void setPricePerGallon(double pricePerGallon)
    {
        this.pricePerGallon = pricePerGallon;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public boolean isAvailable()
    {
        if (quantity == 0)
        {
            return false;
        }
        else
        {
            return false;
        }
    }

    public double calcLitre(double price,double pricePerGallon)
    {
        return price/pricePerGallon;
    }

    public int compareTo(Gas_Records other)
    {
        return this.getName().compareTo(other.getName());
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int num)
    {
        count = num;
    }
}
