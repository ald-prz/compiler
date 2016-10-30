import java.util.*;

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
	 * Creates a new Symbol from the recognized token and adds it to the list of symbols and variable names if needed.
	 * 
	 * @param unit The recognized unit.
	 * @param line Line where the token was found.
	 * @param column Column where the token was found.
	 * @param match String that was matched.
	 */
	public void newToken(LexicalUnit unit, int line, int column, String match) {
		if (unit == LexicalUnit.ENDLINE)
			match = "                  ";

		Symbol s = new Symbol(unit, line, column, match);

		if (s.getType() == LexicalUnit.VARNAME) {

			// Check if the variable name is already in the list

			boolean alreadyHave = false;

			for (int i = 0; i < varnames.size(); i++) {
				if (varnames.get(i).getValue().toString().equals(match)) {
					alreadyHave = true;
					break;
				}
			}

			if (!alreadyHave)
				varnames.add(s);
		}

		symbols.add(s);

		System.out.printf("%s\n", s.toString());
	}

	/**
	 * Outputs the list of identifiers and the line where they were found.
	 */
	public void outputIdentifiers() {

		// Sort varnames alphabetically

		for (int i = varnames.size() - 2; i >= 0; i--)
			for (int j = 0; j <= i; j++)
				if (varnames.get(j).getValue().toString().compareTo(varnames.get(j + 1).getValue().toString()) > 0) {
					Symbol change = varnames.get(j);
					varnames.set(j, varnames.get(j + 1));
					varnames.set(j + 1, change);
				}

		System.out.printf("Identifiers\n");

		for (int i = 0; i < varnames.size(); i++)
			System.out.printf("%s\t%d\n", varnames.get(i).getValue().toString(), varnames.get(i).getLine());
	}
}
