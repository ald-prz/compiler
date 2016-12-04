
public class ElementVariable extends Element {

	protected EnumVariable variable;
	
	public EnumVariable getVariable() {
		return variable;
	}

	public ElementVariable(EnumVariable variable)
	{
		this.variable = variable;
	}
	
	public ElementVariable(ElementVariable variable)
	{
		super(variable);
		this.variable = variable.variable;
	}
	
	@Override
    public String toString(){
        return variable.toString();
    }
}
