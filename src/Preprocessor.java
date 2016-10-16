import java.util.*;


public class Preprocessor {
	
	protected List<Symbol> symbols;
	protected ArrayList<Symbol> varnames;
	
	public Preprocessor() {
		symbols = new ArrayList<Symbol>();
		varnames = new ArrayList<Symbol>();
	}
	
	public void NewToken(LexicalUnit unit, int line, int column, String match) {
		if (unit == LexicalUnit.ENDLINE)
			match = "                  ";
		
		Symbol s = new Symbol(unit, line, column, match);		
		
		if (s.getType() == LexicalUnit.VARNAME)
		{		
			// since we cannot use lambdas we have to manually check uniqueness
			
			int alreadyHave = 0;
			
			for (int i = 0; i < varnames.size(); i++)
			{
				if (varnames.get(i).getValue().toString().equals(match))
				{
					alreadyHave = 1;
					break;
				}
			}
			
			if (alreadyHave == 0)
				varnames.add(s);
		}
		
		symbols.add(s);
		
		System.out.printf("%s\n", s.toString());
	}
	
	public void OutputIdentifiers()	{
		
		// once again since we cannot use lambdas we cannot create comparators therefore we have to do sorting manually
		
		for (int i = varnames.size() - 2; i >= 0; i--)
			for (int j = 0; j <= i; j++)
			if (varnames.get(j).getValue().toString().compareTo(varnames.get(j + 1).getValue().toString()) > 0)
			{
				Symbol change = varnames.get(j);
				varnames.set(j, varnames.get(j + 1));
				varnames.set(j + 1, change);
			}
		
		System.out.printf("Identifiers\n");
		
		
		for (int i = 0; i < varnames.size(); i++)
			System.out.printf("%s\t%d\n", varnames.get(i).getValue().toString(), varnames.get(i).getLine());
	}
}
