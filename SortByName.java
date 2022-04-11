import java.util.Comparator;

public class SortByName implements Comparator<MiniMart_Records>
{
    public int compare(MiniMart_Records p1, MiniMart_Records p2)
    {
        return CharSequence.compare(p1.getName(), p2.getName());
    }
}
