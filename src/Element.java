
public abstract class Element {

	public Element()
	{
	}
	
	public Element(Element element)
	{
		this.derivedFromRule = element.derivedFromRule;		
	}
	
	public int derivedFromRule; // to return error rule number
	
}
