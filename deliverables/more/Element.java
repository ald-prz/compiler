
/**
 * Element to be put in the stack. Can be Token or variable
 */
public abstract class Element {

	public Element()
	{
	}
	
	public Element(Element element)
	{
		this.derivedFromRule = element.derivedFromRule;		
	}
	
	/**
	 * Number of the rule which has derived this element. Used to output the number of erroneous rule
	 */
	public int derivedFromRule; 
	
}
