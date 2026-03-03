
/**
 * Write a description of class Pair here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pair
{
    private int size;
    private String type;

    /**
     * Constructor for objects of class Pair
     */
    public Pair(int size, String type)
    {
        this.size = size;
        this.type = type;
    }
    
    public int getSize()
    {
        return size;
    }
    
    public String getType()
    {
        return type;
    }
    
    public String toString()
    {
        return size + type;
    }
    public int sig()
    {
        int a =0;
        if(type.equals("Integer"))
        {
            a=111;
        }
        else
        {
            a=999999;
        }
        return size+a;
    }
    
    public int hashCode()
    {
        
        int hash =  sig();
        return hash;
    }
    
    public boolean equals(Object o) {
        if ((o instanceof Pair) && (((Pair) o).sig() == sig())) {
            return true;
        } else {
            return false;
        }
    }
 
    
}