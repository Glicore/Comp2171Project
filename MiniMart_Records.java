public class MiniMart_Records
{
    private int id;
    private String name;
    private double price;
    private int quantity;
    private double volume;
    private static int ID;

    public MiniMart_Records()
    {}

    public MiniMart_Records(String name, double price, int quantity, double volume)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.volume = volume;
    }

    public String getName()
    {
        return name;
    }

    public double getPrice()
    {
        return price;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public double getVolume()
    {
        return volume;
    }

    public int getId()
    {
        return id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public void setVolume(double volume)
    {
        this.volume = volume;
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
}
