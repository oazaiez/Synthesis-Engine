import java.util.*;

import com.microsoft.z3.*;
/**
 * Write a description of class Function here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Function
{
	protected int arity;
	protected String outputType;
	protected HashMap<Integer, String> inputType = new HashMap<Integer, String>();

    /**
     * Constructor for objects of class FunctionSet
     */
    public Function()
    {
       
    }
   
    public int getArity()
    {
        return arity;
    }
    
   
    
    public String getOutputType()
    {
        return outputType;
    }
    
    public HashMap<Integer, String> getInputType()
    {
        return inputType;
    }
    
   
    
    protected Vector<Vector<Object>> prepare(Vector<Expr> v, Map<Expr, Vector<Object>> set)
	{
		Vector<Vector<Object>> main = new Vector<Vector<Object>>();
		for(Expr e : v)
		{
			Vector<Object> vector = set.get(e);
			main.add(vector);			
		}
		return main;
	}

	

	public Expr makeExpression(Vector<Expr> v2, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}
	public Vector<Object> returnValue(Vector<Expr> v, Map<Expr, Vector<Object>> set, Context ctx)
	{
		return null;
	}

	
    
}
    
    
