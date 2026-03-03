import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		  Context ctx = new Context();
          
          Concolic c = new Concolic(ctx);
          Parser p1 = new Parser(ctx);
          
          c.solveConcolic((BoolExpr) p1.getFormula(), p1.getOutput(), p1.getInputs());
	}

}
