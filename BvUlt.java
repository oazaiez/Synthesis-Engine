import java.util.*;
import com.microsoft.z3.*;


public class BvUlt extends Function {
	
	public BvUlt()
	{
		super.arity = 2 ;
		super.outputType = "BoolExpr";
		super.inputType.put(0, "BitVector");
		super.inputType.put(1, "BitVector");
	}
	
		
    public Vector<Object> returnValue(Vector<Expr> v, Map<Expr, Vector<Object>> set, Context ctx)
    {
    	Vector<Object> output = new Vector<Object>();
    	System.out.println("ooo");
    	Vector<Vector<Object>> vector = super.prepare(v, set); 	
    	System.out.println("aaaa " +vector);
    	for(int i=0; i<vector.get(0).size(); i++)
    	{
    		
    		
    		BoolExpr e = ctx.mkBVULT((BitVecExpr) vector.get(0).get(i), (BitVecExpr) vector.get(1).get(i));
    		
    	
    		
    		output.add((BoolExpr) e.simplify());
    		
    	}    	
    	
    	
    	return output;
    }
    	
    	
        
    public Expr makeExpression(Vector<Expr> v, Context ctx)
    {
    	if(v.size()==2)
    	{
    	Expr e = ctx.mkBVULT((BitVecExpr) v.get(0), (BitVecExpr) v.get(1));
    	return e;
    	}
    	else return null;
    }

}