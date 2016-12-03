import java.util.*;

public class Rule {
	
	public EnumVariable left;  // sorry for not using get and set, no time
	public ArrayList<Element> right;

	/*
	 * adds a token to the right side of the rule
	 */
	public void addTkn(LexicalUnit unit)
	{
		initRightSide();		
		this.right.add(new ElementToken(unit));
	}
	
	/*
	 * adds a variable to the right side of the rule
	 */
	public void addVar(EnumVariable variable)
	{
		initRightSide();		
		this.right.add(new ElementVariable(variable));
	}
	
	
	public void initRightSide()
	{
		if (this.right == null)
			this.right = new ArrayList();
	}
}
