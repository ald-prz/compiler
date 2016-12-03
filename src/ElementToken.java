

// a terminal symbol element of stack (Symbol wrapper)
public class ElementToken extends Element {

	protected Symbol symbol;
	protected Boolean isEndingToken;
	
	public Boolean getIsEndingToken() {
		return isEndingToken;
	}

	public Symbol getSymbol() {
		return symbol;
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
}
