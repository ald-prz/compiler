
public class ElementVariable extends Element {

	protected EnumVariable variable;
	
	public EnumVariable getVariable() {
		return variable;
	}

	public ElementVariable(EnumVariable variable)
	{
		this.variable = variable;
	}
	
	@Override
    public String toString(){
        return variable.toString();
    }
}
