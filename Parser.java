import java.util.*;
import com.microsoft.z3.*;

public class Parser {
	
	private Expr formula;
	private Vector<Expr> inputs = new Vector<Expr>();
	private Expr output;
	String s;
	Context ctx;
	Vector<String> inputVar = new Vector<String>();
	
	public Parser(Context ctx)
	{
		this.ctx = ctx;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Variables used ");
		
		while(sc.hasNextLine())
		{
			if(sc.nextLine().equals("exit"))
			{
				break;
			}
			else
			{
			inputVar.add(sc.nextLine()) ;
			}
			
			
		}
		System.out.println("Enter formula");
		
		s = sc.nextLine();
		
		setUp();
	}
	
	private void setUp()
	{
		String s2 = "(benchmark tst :";
				
		String s3 = "extrafuns ((o(_ BitVec 8))";
		for(String ss : inputVar)
		{
			s3 = s3 + "(" + ss + "(_ BitVec 8))";
			BitVecExpr b = ctx.mkBVConst(ss, 8);
			inputs.add(b);
		}
		s3 = s3 + ")";
		
		String s4 = s2 + s3 + ":formula " + s + ")";
		
		
		ctx.parseSMTLIBString( s4 , null, null, null, null);
		formula = ctx.getSMTLIBFormulas()[0];
		Symbol sym = ctx.mkSymbol("o");
		output = ctx.mkBVConst(sym, 8);
		
	}
	
	public Expr getFormula()
	{
		return formula;
	}
	
	public Vector<Expr> getInputs()
	{
		return inputs;
	}
	
	public Expr getOutput()
	{
		return output;
	}

}
