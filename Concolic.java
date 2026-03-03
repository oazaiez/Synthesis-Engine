import java.util.*;
import com.microsoft.z3.*;


public class Concolic {
	
	private Vector<Expr> concolicSet = new Vector<Expr>(); 
		
	private Context ctx ;
	private Vector<ConcreteExample> concreteSet = new Vector<ConcreteExample>();
    private Vector<Function> functionSet = new Vector<Function>();
    private Vector<Variable> variableSet = new Vector<Variable>();
    

	
	
	public Concolic(Context ctx)
	{
		this.ctx = ctx;
		functionSet.add(new Bvadd());
		functionSet.add(new BvAnd());
		functionSet.add(new BVNAnd());
		functionSet.add(new BvNOr());
		functionSet.add(new BvOr());
		functionSet.add(new BvSub());
		functionSet.add(new BvUlt());
		
		
	}
	
	
	public Expr solveConcolic(BoolExpr formula, Expr output, Vector<Expr> input)
	{
		Solver sv = ctx.mkSolver();
		sv.add(formula);
		Status q9 = sv.check();
		Model mm = sv.getModel();
		Vector<Variable> v9 = new Vector<Variable>();
		Vector<Expr> newV9 = new Vector<Expr>();
		for(Expr e1 : input)
		{
			Expr exp = mm.getConstInterp(e1);
			Variable var = new Variable(e1.getFuncDecl().getName(), exp);
		v9.add(var);
		variableSet.add(var);
		newV9.add(exp);
		}
		Variable one = new Variable(mm.getConstInterp(output).getFuncDecl().getName(), mm.getConstInterp(output));
		ConcreteExample newConcrete9 = new ConcreteExample(v9, one);
		concreteSet.add(newConcrete9);
		
		while(true)
		{
			ExpressionEnumerator enumerator = new ExpressionEnumerator(concreteSet, functionSet, variableSet, ctx);
			Expr e = enumerator.enumerate();
			System.out.println(e);
			
			BoolExpr e2 = (BoolExpr) formula.substitute(output, e);
			Solver s = ctx.mkSolver();			
			BoolExpr e3 = ctx.mkNot(e2);
			s.add(e3);
			Status q = s.check();
			if(q.toInt()== -1)
			{
				System.out.println("Expression is " + e);
				return e;
				
			}
			else
			{
				Vector<Variable> v = new Vector<Variable>();
				Vector<Expr> newV = new Vector<Expr>();
				Model m = s.getModel();
				System.out.println(m);
				for(Expr e1 : input)
				{
					Expr exp = m.getConstInterp(e1);
				
				Variable var = new Variable(e1.getFuncDecl().getName(), exp);
				v.add(var);
				newV.add(exp);
				}
				BoolExpr f1 = (BoolExpr) formula;
				for(int i= 0 ; i< input.size(); i++)
				{
					f1 = (BoolExpr) f1.substitute(input.get(i), newV.get(i));
				}
				Solver s5 = ctx.mkSolver();
				s5.add(f1);
				
				Status q5 = s5.check();
				Model m5 = s5.getModel();
				Expr exp5 = m5.getConstInterp(output);
				
				Variable newVar = new Variable(exp5.getFuncDecl().getName(), exp5);
				ConcreteExample newConcrete = new ConcreteExample(v, newVar);
				concreteSet.add(newConcrete);
				
			}
			
		}
	}
	
}
