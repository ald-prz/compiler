import java.util.ArrayList;
import java.util.List;

public class Preprocessor {
	protected List<Symbol> symbols;
	protected List<Symbol> varnames;

	/**
	 * Constructs a new Preprocessor and initializes all lists to empty ones.
	 */
	public Preprocessor() {
		symbols = new ArrayList<Symbol>();
		varnames = new ArrayList<Symbol>();
	}

	/**
	 * Creates a new Symbol from the recognized token and adds it to the list of
	 * symbols.
	 * 
	 * @param unit
	 *            The recognized unit.
	 * @param line
	 *            Line where the token was found.
	 * @param column
	 *            Column where the token was found.
	 * @param match
	 *            String that was matched.
	 */
	public Symbol newToken(LexicalUnit unit, int line, int column, String match) {
		if (unit == LexicalUnit.ENDLINE)
			match = "                  ";

		Symbol s = new Symbol(unit, line, column, match);

		symbols.add(s);

		System.out.println(s.toString());

		return s;
	}

	/**
	 * Adds the symbol to the varnames list if not already present.
	 * 
	 * @param s
	 *            The Symbol to add.
	 */
	public void newVariable(Symbol s) {
		// Check if the variable name is already in the list

		boolean alreadyHave = false;

		for (int i = 0; i < varnames.size(); i++) {
			if (varnames.get(i).getValue().toString().equals(s.getValue())) {
				alreadyHave = true;
				break;
			}
		}

		if (!alreadyHave)
			varnames.add(s);
	}

	/**
	 * Outputs the list of identifiers and the line where they were found.
	 */
	public void parse() {
		outputTokens();
		
		Parser parser = new Parser();
		
		parser.parse(symbols);
		
	}
	
	protected void outputTokens()
	{
		// Sort varnames alphabetically

				for (int i = varnames.size() - 2; i >= 0; i--)
					for (int j = 0; j <= i; j++)
						if (varnames.get(j).getValue().toString().compareTo(varnames.get(j + 1).getValue().toString()) > 0) {
							Symbol change = varnames.get(j);
							varnames.set(j, varnames.get(j + 1));
							varnames.set(j + 1, change);
						}

				System.out.println("Identifiers");

				for (int i = 0; i < varnames.size(); i++)
					System.out.println(varnames.get(i).getValue().toString() + "\t" + varnames.get(i).getLine());
	}
}
