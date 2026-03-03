import com.microsoft.z3.*;
/**
 * Write a description of class Variable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Variable
{
    // instance variables - replace the example below with your own
    
    private Symbol name;
    private Object output;

    /**
     * Constructor for objects of class Variables
     */
    public Variable (Symbol symbol, Object output)
    {
        
        this.output = output;
        this.name = symbol;
    }
    

    public Symbol getName()
    {
        return name;
    }
    
   
    
    public Object getOutput()
    {
        return output;
    }
    
}
