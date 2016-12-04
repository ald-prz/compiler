import java.util.*;

public class Rule {
	
	/**
	 * Left part of the rule
	 */
	public EnumVariable left;
	
	/**
	 * Right part of the rule
	 */
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
	
	
    @Override
    public String toString(){
        String s;
        
        s = "<" + left.toString() + "> --> ";
        
        for (int i = 0; i < right.size(); i++)
        {
        	if (right.get(i) instanceof ElementToken)
        		s += ((ElementToken) right.get(i)).toString();
        	else
        		s += "<" + ((ElementVariable) right.get(i)).toString() + ">";
        	
        	s += " ";
        }
        	
        
        return s;
    }
}
