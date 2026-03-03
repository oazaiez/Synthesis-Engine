import java.util.*;




public class ConcreteExample {

	private Vector<Variable> input = new Vector<Variable>();
	private Variable output;
	
	
	public ConcreteExample(Vector<Variable> input, Variable output)
	{
		this.input = input;
		this.output = output;
	}
	
	
	public Vector<Variable> getInput()
	{
		return input;
	}
	
	public Variable getOutput()
	{
		return output;
	}
	
	
	
	
}
