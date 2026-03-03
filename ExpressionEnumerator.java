import java.util.*;
import com.microsoft.z3.*;
import com.microsoft.z3.FuncDecl.Parameter;

/**
 * Write a description of class ExpressionEnumerator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExpressionEnumerator
{
	private Vector<ConcreteExample> concreteExamples = new Vector<ConcreteExample>();
    private Vector<Function> functionSet = new Vector<Function>();
    private Vector<Variable> variableSet = new Vector<Variable>();
    private Map<Pair, Vector<Expr>> expSet = new HashMap<Pair, Vector<Expr>>();  
    private HashMap<Expr, Vector<Object>> sigSet = new HashMap<Expr, Vector<Object>>();
    private Vector<Object> goal = new Vector<Object>();
    private Context ctx;
    
    

    /**
     * Constructor for objects of class ExpressionEnumerator
     */
    public ExpressionEnumerator(Vector<ConcreteExample> concreteExamples, Vector<Function> functionSet, Vector<Variable> variableSet, Context ctx)
    {
    	this.concreteExamples = concreteExamples;
    	this.functionSet = functionSet;
    	this.variableSet = variableSet;
    	this.ctx = ctx;
    	goal = setUp(concreteExamples);
    }
    
    public void enumerateSizeOne()
    {
    	Pair p = new Pair(1 , "BitVector");
    	Vector<Expr> v = new Vector<Expr>();
    	for(Variable s : variableSet)
    	{
    		Expr e = ctx.mkBVConst(s.getName(), 8);
    		v.add(e);
    	}
    	expSet.put(p, v);
    	Vector<Vector<Object>> vec = rearrange(concreteExamples, v.size());
    	for(int i=0 ; i<v.size(); i++)
    	{
    		sigSet.put(v.get(i), vec.get(i));
    	}
    
    
    	
    }
    
    public Expr enumerate()
    {
    	enumerateSizeOne();
    	
        for(int i=2; i<300; i++)
        {
            for(Function f : functionSet)
            {
                Pair p = new Pair(i,f.getOutputType());
                Vector<Vector<Pair>> pairs = partition2(i-1, f.getInputType());
                for(Vector<Pair> m : pairs)
                {
                   Vector<Vector<Expr>> v = method2(m);                    
                   for(Vector<Expr> v2 : v)
                    {    
                	   
                        Expr newExpression = f.makeExpression(v2, ctx);
                        Vector<Object> value = f.returnValue(v2, sigSet, ctx);
                      
                       
                        if(value.equals(goal))
                        {
                        	return newExpression;
                        }
                        if(!(sigSet.containsValue(value)))
                        {
                             sigSet.put(newExpression, value);
                             System.out.println(newExpression+ "  " + value);
                        
                        	 Vector<Expr> v3 = new Vector<Expr>();
                             if(expSet.containsKey(p))
                             {
                                 v3 = expSet.get(p);                                 
                             }
                             v3.add(newExpression);
                             expSet.put(p,v3);
                        }
                    }
                }
            }
        }
        return null;
    }
                        
  
        
   
    public void partition(int size, int arity, Vector<Integer> v, Vector<Vector<Integer>> mainVector)
    {
            if(arity>1)
            {
                for(int i=1; i<size; i++)
                {
                    Vector<Integer> v1 = new Vector<Integer>(v);
                    v1.add(i);
                    partition((size-i), (arity-1), v1, mainVector);                   
                 }
            }
            else if(arity==1)
            {           
                v.add(size);
                mainVector.add(v);
            }
    }
    
        
    public Vector<Vector<Pair>> partition2(int size, HashMap<Integer,String> inputType)
    {
        Vector<Integer> v = new Vector<Integer>();
        Vector<Vector<Integer>> mainVector = new Vector<Vector<Integer>>();
        Vector<Vector<Pair>> finalVector = new Vector<Vector<Pair>>();
        
        partition(size,inputType.size(),v,mainVector);
        
        for(Vector<Integer> v9 : mainVector)
        {
            Vector<Pair> pairVector = new Vector<Pair>();
            for(int i = 0; i<inputType.size(); i++)
            {
                Pair p = new Pair(v9.get(i), inputType.get(i));
                pairVector.add(p);                              
            }
            
            finalVector.add(pairVector);            
        }
        return finalVector;
    }
    
    
    public void method(Vector<Pair> v, Vector<Expr> vector1, Vector<Vector<Expr>> mainVector)
    {
        if(v.size()>1 && expSet.containsKey(v.get(0)))
        {
            for(Expr e : expSet.get(v.get(0)))
            {                
                Vector<Expr> v9 = new Vector<Expr>(vector1);                
                v9.add(e);
                Vector<Pair> ve = new Vector<Pair>(v);
                ve.remove(0);
                method(ve, v9, mainVector); 
            }
        }
        else if (v.size()==1 && expSet.containsKey(v.get(0)))
        {
            for(Expr e : expSet.get(v.get(0)))
            {               
                Vector<Expr> v9 = new Vector<Expr>(vector1);
                v9.add(e);
                mainVector.add(v9);
            }
        }
    }
   
    
    public Vector<Vector<Expr>> method2(Vector<Pair> v)
    {
        Vector<Vector<Expr>> mainVector = new Vector<Vector<Expr>>();
        Vector<Expr> vector1 = new Vector<Expr>();
        method(v,vector1,mainVector);
        return mainVector;
    }
    
    private Vector<Object> setUp(Vector<ConcreteExample> examples)
{
    	Vector<Object> output = new Vector<Object>();
    	for(ConcreteExample c : examples)
    	{
    		output.add(c.getOutput().getOutput());
    	}
    	return output;
}
    
    private Vector<Vector<Object>> rearrange(Vector<ConcreteExample> ex, int size)
    {
    	Vector<Vector<Object>> output = new Vector<Vector<Object>>();
    	for(int i=0 ; i<size ; i++)
    	{
    		Vector<Object> vec = new Vector<Object>();
    		for(ConcreteExample concrete : ex)
    		{
    			Vector<Variable> one = concrete.getInput();    			
    			vec.add(one.get(i).getOutput());
    		}
    		output.add(vec);
    	}
    	return output;
    }
   
    
    

         
}

