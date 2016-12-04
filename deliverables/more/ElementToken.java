

/**
 * Token element to be put in the stack
 */
public class ElementToken extends Element {

	protected Symbol symbol;
	protected Boolean isEndingToken;
	
	public ElementToken(LexicalUnit unit)
	{
		if (unit != null)
		{
			this.symbol = new Symbol(unit, 0, 0, null);
			this.isEndingToken = false;
		}
		else
			this.isEndingToken = true;
	}
	
	public ElementToken(Symbol symbol)
	{
		if (symbol.getType() != null)
		{
			this.symbol = symbol;
			this.isEndingToken = false;
		}
		else
			this.isEndingToken = true;
	}
	
	public ElementToken(ElementToken token)
	{
		super(token);
		this.symbol = token.symbol;
		this.isEndingToken = token.isEndingToken;
	}
	
	public Boolean getIsEndingToken() {
		return isEndingToken;
	}

	public Symbol getSymbol() {
		return symbol;
	}
	
	@Override
    public String toString(){
        if (this.isEndingToken == true)
        	return "$";
        else
        	return symbol.getType().toString();
 
    }
}
