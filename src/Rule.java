import java.util.*;

public class Rule {
	
	public EnumVariable left;  // sorry for not using get and set, no time
	public ArrayList<Element> right;

	/*
	 * adds a token to the right side of the rule
	 */
	public void addTkn(LexicalUnit unit)
	{
		if (this.right == null)
			this.right = new ArrayList();
		
		this.right.add(new ElementToken(new Symbol(unit, 0, 0, null)));
	}
	
	/*
	 * adds a variable to the right side of the rule
	 */
	public void addVar(EnumVariable variable)
	{
		if (this.right == null)
			this.right = new ArrayList();
		
		this.right.add(new ElementVariable(variable));
	}
}
