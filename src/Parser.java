import java.util.*;


public class Parser {

	protected int[][] actionTable;
	protected ArrayList<Rule> rules;
	
	public void parse(List<Symbol> symbol)
	{		
		initializeActionTable();
		initializeRules();
		
		
	}
	
	protected void initializeRules()
	{
		ArrayList<Rule> rules = new ArrayList<Rule>();
		
		Rule rule = new Rule();
		rule.left = EnumVariable.All;
		rule.addVar(EnumVariable.Program);
		rule.addTkn(null);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.All;
		rule.addTkn(LexicalUnit.PROGRAM);
		rule.addTkn(LexicalUnit.VARNAME);
		rules.add(rule);
	}
	
	protected void initializeActionTable()
	{
		actionTable = new int[EnumVariable.values().length][LexicalUnit.values().length];
		
		for (int i = 0; i < actionTable.length; i++)
			for (int j = 0; j < actionTable[0].length; j++)
				actionTable[i][j] = -1;
		
		addToActionTable(EnumVariable.All, LexicalUnit.PROGRAM, 0);
		addToActionTable(EnumVariable.Program, LexicalUnit.PROGRAM, 1);
		addToActionTable(EnumVariable.Vars, LexicalUnit.VARNAME, 3);
		addToActionTable(EnumVariable.Vars, LexicalUnit.INTEGER, 2);
		addToActionTable(EnumVariable.Vars, LexicalUnit.END, 3);
		addToActionTable(EnumVariable.Vars, LexicalUnit.IF, 3);
		addToActionTable(EnumVariable.Vars, LexicalUnit.DO, 3);
		addToActionTable(EnumVariable.Vars, LexicalUnit.PRINT, 3);
		addToActionTable(EnumVariable.Vars, LexicalUnit.READ, 3);
		addToActionTable(EnumVariable.VarList, LexicalUnit.VARNAME, 4);
		addToActionTable(EnumVariable.FactVarList, LexicalUnit.VARNAME, 5);
		addToActionTable(EnumVariable.FactVarList, LexicalUnit.ENDLINE, 6);
		addToActionTable(EnumVariable.Code, LexicalUnit.VARNAME, 7);
		addToActionTable(EnumVariable.Code, LexicalUnit.END, 8);
		addToActionTable(EnumVariable.Code, LexicalUnit.IF, 7);
		addToActionTable(EnumVariable.Code, LexicalUnit.ENDIF, 8);
		addToActionTable(EnumVariable.Code, LexicalUnit.ELSE, 8);
		addToActionTable(EnumVariable.Code, LexicalUnit.DO, 7);
		addToActionTable(EnumVariable.Code, LexicalUnit.ENDDO, 8);
		addToActionTable(EnumVariable.Code, LexicalUnit.PRINT, 7);
		addToActionTable(EnumVariable.Code, LexicalUnit.READ, 7);
		addToActionTable(EnumVariable.Instruction, LexicalUnit.VARNAME, 9);
		addToActionTable(EnumVariable.Instruction, LexicalUnit.IF, 10);
		addToActionTable(EnumVariable.Instruction, LexicalUnit.DO, 11);
		addToActionTable(EnumVariable.Instruction, LexicalUnit.PRINT, 12);
		addToActionTable(EnumVariable.Instruction, LexicalUnit.READ, 13);
		addToActionTable(EnumVariable.Assign, LexicalUnit.VARNAME, 14);
		addToActionTable(EnumVariable.Op1, LexicalUnit.MINUS, 16);
		addToActionTable(EnumVariable.Op1, LexicalUnit.PLUS, 15);
		addToActionTable(EnumVariable.Op2, LexicalUnit.TIMES, 17);
		addToActionTable(EnumVariable.Op2, LexicalUnit.DIVIDE, 18);
		addToActionTable(EnumVariable.ExprArith, LexicalUnit.VARNAME, 19);
		addToActionTable(EnumVariable.ExprArith, LexicalUnit.NUMBER, 19);
		addToActionTable(EnumVariable.ExprArith, LexicalUnit.LEFT_PARENTHESIS, 19);
		addToActionTable(EnumVariable.ExprArith, LexicalUnit.MINUS, 19);
		addToActionTable(EnumVariable.RecArithE, LexicalUnit.COMMA, 21);
		addToActionTable(EnumVariable.RecArithE, LexicalUnit.RIGHT_PARENTHESIS, 21);
		addToActionTable(EnumVariable.RecArithE, LexicalUnit.MINUS, 20);
		addToActionTable(EnumVariable.RecArithE, LexicalUnit.PLUS, 20);
		addToActionTable(EnumVariable.RecArithE, LexicalUnit.AND, 21);
		addToActionTable(EnumVariable.RecArithE, LexicalUnit.OR, 21);
		addToActionTable(EnumVariable.RecArithE, LexicalUnit.EQUAL_COMPARE, 21);
		addToActionTable(EnumVariable.RecArithE, LexicalUnit.GREATER_EQUAL, 21);
		addToActionTable(EnumVariable.RecArithE, LexicalUnit.GREATER, 21);
		addToActionTable(EnumVariable.RecArithE, LexicalUnit.SMALLER_EQUAL, 21);
		addToActionTable(EnumVariable.RecArithE, LexicalUnit.SMALLER, 21);
		addToActionTable(EnumVariable.RecArithE, LexicalUnit.DIFFERENT, 21);
		addToActionTable(EnumVariable.RecArithE, LexicalUnit.ENDLINE, 21);
		addToActionTable(EnumVariable.ArithT, LexicalUnit.VARNAME, 22);
		addToActionTable(EnumVariable.ArithT, LexicalUnit.NUMBER, 22);
		addToActionTable(EnumVariable.ArithT, LexicalUnit.LEFT_PARENTHESIS, 22);
		addToActionTable(EnumVariable.ArithT, LexicalUnit.MINUS, 22);
		addToActionTable(EnumVariable.RecArithT, LexicalUnit.COMMA, 24);
		addToActionTable(EnumVariable.RecArithT, LexicalUnit.RIGHT_PARENTHESIS, 24);
		addToActionTable(EnumVariable.RecArithT, LexicalUnit.MINUS, 24);
		addToActionTable(EnumVariable.RecArithT, LexicalUnit.PLUS, 24);
		addToActionTable(EnumVariable.RecArithT, LexicalUnit.TIMES, 23);
		addToActionTable(EnumVariable.RecArithT, LexicalUnit.DIVIDE, 23);
		addToActionTable(EnumVariable.RecArithT, LexicalUnit.AND, 24);
		addToActionTable(EnumVariable.RecArithT, LexicalUnit.OR, 24);
		addToActionTable(EnumVariable.RecArithT, LexicalUnit.EQUAL_COMPARE, 41);
		addToActionTable(EnumVariable.RecArithT, LexicalUnit.GREATER_EQUAL, 24);
		addToActionTable(EnumVariable.RecArithT, LexicalUnit.GREATER, 24);
		addToActionTable(EnumVariable.RecArithT, LexicalUnit.SMALLER_EQUAL, 24);
		addToActionTable(EnumVariable.RecArithT, LexicalUnit.SMALLER, 24);
		addToActionTable(EnumVariable.RecArithT, LexicalUnit.DIFFERENT, 24);
		addToActionTable(EnumVariable.RecArithT, LexicalUnit.ENDLINE, 24);
		addToActionTable(EnumVariable.ArithF, LexicalUnit.VARNAME, 25);
		addToActionTable(EnumVariable.ArithF, LexicalUnit.NUMBER, 26);
		addToActionTable(EnumVariable.ArithF, LexicalUnit.LEFT_PARENTHESIS, 27);
		addToActionTable(EnumVariable.ArithF, LexicalUnit.MINUS, 28);
		addToActionTable(EnumVariable.If, LexicalUnit.IF, 29);
		addToActionTable(EnumVariable.FactIf, LexicalUnit.ENDIF, 30);
		addToActionTable(EnumVariable.FactIf, LexicalUnit.ELSE, 31);
		addToActionTable(EnumVariable.CondPrefix, LexicalUnit.VARNAME, 33);
		addToActionTable(EnumVariable.CondPrefix, LexicalUnit.NUMBER, 33);
		addToActionTable(EnumVariable.CondPrefix, LexicalUnit.LEFT_PARENTHESIS, 33);
		addToActionTable(EnumVariable.CondPrefix, LexicalUnit.MINUS, 33);
		addToActionTable(EnumVariable.CondPrefix, LexicalUnit.NOT, 32);
		addToActionTable(EnumVariable.Cond, LexicalUnit.VARNAME, 34);
		addToActionTable(EnumVariable.Cond, LexicalUnit.NUMBER, 34);
		addToActionTable(EnumVariable.Cond, LexicalUnit.LEFT_PARENTHESIS, 34);
		addToActionTable(EnumVariable.Cond, LexicalUnit.MINUS, 34);
		addToActionTable(EnumVariable.Cond, LexicalUnit.NOT, 34);
		addToActionTable(EnumVariable.CondRecE, LexicalUnit.RIGHT_PARENTHESIS, 36);
		addToActionTable(EnumVariable.CondRecE, LexicalUnit.OR, 35);
		addToActionTable(EnumVariable.CondT, LexicalUnit.VARNAME, 37);
		addToActionTable(EnumVariable.CondT, LexicalUnit.NUMBER, 37);
		addToActionTable(EnumVariable.CondT, LexicalUnit.LEFT_PARENTHESIS, 37);
		addToActionTable(EnumVariable.CondT, LexicalUnit.MINUS, 37);
		addToActionTable(EnumVariable.CondT, LexicalUnit.NOT, 37);
		addToActionTable(EnumVariable.CondRecT, LexicalUnit.RIGHT_PARENTHESIS, 39);
		addToActionTable(EnumVariable.CondRecT, LexicalUnit.AND, 38);
		addToActionTable(EnumVariable.CondRecT, LexicalUnit.OR, 39);
		addToActionTable(EnumVariable.CondF, LexicalUnit.VARNAME, 40);
		addToActionTable(EnumVariable.CondF, LexicalUnit.NUMBER, 40);
		addToActionTable(EnumVariable.CondF, LexicalUnit.LEFT_PARENTHESIS, 40);
		addToActionTable(EnumVariable.CondF, LexicalUnit.MINUS, 40);
		addToActionTable(EnumVariable.Comp, LexicalUnit.EQUAL_COMPARE, 41);
		addToActionTable(EnumVariable.CondF, LexicalUnit.GREATER_EQUAL, 42);
		addToActionTable(EnumVariable.CondF, LexicalUnit.GREATER, 43);
		addToActionTable(EnumVariable.CondF, LexicalUnit.SMALLER_EQUAL, 44);
		addToActionTable(EnumVariable.CondF, LexicalUnit.SMALLER, 45);
		addToActionTable(EnumVariable.CondF, LexicalUnit.DIFFERENT, 46);
		addToActionTable(EnumVariable.Do, LexicalUnit.DO, 47);
		addToActionTable(EnumVariable.Print, LexicalUnit.PRINT, 48);
		addToActionTable(EnumVariable.Read, LexicalUnit.READ, 49);
		addToActionTable(EnumVariable.ExpList, LexicalUnit.VARNAME, 50);
		addToActionTable(EnumVariable.ExpList, LexicalUnit.NUMBER, 50);
		addToActionTable(EnumVariable.ExpList, LexicalUnit.LEFT_PARENTHESIS, 50);
		addToActionTable(EnumVariable.ExpList, LexicalUnit.MINUS, 50);
		addToActionTable(EnumVariable.FactExprArith, LexicalUnit.COMMA, 51);
		addToActionTable(EnumVariable.FactExprArith, LexicalUnit.ENDLINE, 52);
	}
	
	protected void addToActionTable(EnumVariable variable, LexicalUnit unit, int rule)
	{
		actionTable[variable.ordinal()][unit.ordinal()] = rule;
	}
}
