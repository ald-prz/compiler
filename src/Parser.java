import java.util.*;


public class Parser {

	protected int[][] actionTable;
	protected ArrayList<Rule> rules;
	protected int lastRuleNumber = 0;
	
	public void parse(ArrayList<Symbol> symbol)
	{		
		initializeActionTable();
		initializeRules();
		
		Stack stack = new Stack();
		stack.push(new ElementVariable(EnumVariable.All));		
		
		while (true)
		{
			Element element = (Element) stack.pop();
			
			if (element instanceof ElementVariable)
			{
				ElementVariable variable = (ElementVariable) element;
				
				int rule = actionTable[variable.getVariable().ordinal()][symbol.get(0).getType().ordinal()];
				
				if (rule != -1)
				{
					writeRule(rule);
					
					for (int i = rules.get(rule).right.size() - 1; i >= 0; i--)
						stack.push(rules.get(rule).right.get(i));
				}
				else
				{
					raiseError();
					return;
				}
				
				lastRuleNumber = rule;
			}
			else
			{
				ElementToken token = (ElementToken) element;
				
				if ((token.getIsEndingToken() == true) && (symbol.size() == 0))
					return; // success;
				
				if (symbol.size() != 0)
				{	
					if (token.getSymbol().getType() == symbol.get(0).getType())
						symbol.remove(0);
					else
					{
						raiseError();
						return;
					}
				}
				else
				{
					raiseError();
					return;
				}
			}
		}
	}
	
	protected void writeRule(int rule)
	{
		System.out.print("[" + String.valueOf(rule) + "]");
		System.out.println(rules.get(rule).toString());
	}
	
	protected void raiseError()
	{
		System.out.println("A syntax error occurs at rule [" + lastRuleNumber + "]");
	}

	protected void initializeRules()
	{
		rules = new ArrayList<Rule>();
		
		Rule rule = new Rule();
		rule.left = EnumVariable.All;
		rule.addVar(EnumVariable.Program);
		rule.addTkn(null);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Program;
		rule.addTkn(LexicalUnit.PROGRAM);
		rule.addTkn(LexicalUnit.VARNAME);
		rule.addTkn(LexicalUnit.ENDLINE);
		rule.addVar(EnumVariable.Vars);
		rule.addVar(EnumVariable.Code);
		rule.addTkn(LexicalUnit.END);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Vars;
		rule.addTkn(LexicalUnit.INTEGER);
		rule.addVar(EnumVariable.VarList);
		rule.addTkn(LexicalUnit.ENDLINE);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Vars;
		rule.initRightSide();
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.VarList;
		rule.addTkn(LexicalUnit.VARNAME);
		rule.addVar(EnumVariable.FactVarList);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.FactVarList;
		rule.addTkn(LexicalUnit.COMMA);
		rule.addVar(EnumVariable.VarList);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.FactVarList;
		rule.initRightSide();
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Code;
		rule.addVar(EnumVariable.Instruction);
		rule.addTkn(LexicalUnit.ENDLINE);
		rule.addVar(EnumVariable.Code);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Code;
		rule.initRightSide();
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Instruction;
		rule.addVar(EnumVariable.Assign);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Instruction;
		rule.addVar(EnumVariable.If);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Instruction;
		rule.addVar(EnumVariable.Do);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Instruction;
		rule.addVar(EnumVariable.Print);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Instruction;
		rule.addVar(EnumVariable.Read);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Assign;
		rule.addTkn(LexicalUnit.VARNAME);
		rule.addTkn(LexicalUnit.EQUAL);
		rule.addVar(EnumVariable.ExprArith);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Op1;
		rule.addTkn(LexicalUnit.PLUS);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Op1;
		rule.addTkn(LexicalUnit.MINUS);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Op2;
		rule.addTkn(LexicalUnit.TIMES);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Op2;
		rule.addTkn(LexicalUnit.DIVIDE);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.ExprArith;
		rule.addVar(EnumVariable.ArithT);
		rule.addVar(EnumVariable.RecArithE);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.RecArithE;
		rule.addVar(EnumVariable.Op1);
		rule.addVar(EnumVariable.ArithT);
		rule.addVar(EnumVariable.RecArithE);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.RecArithE;
		rule.initRightSide();
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.ArithT;
		rule.addVar(EnumVariable.ArithF);
		rule.addVar(EnumVariable.RecArithT);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.RecArithT;
		rule.addVar(EnumVariable.Op2);
		rule.addVar(EnumVariable.ArithF);
		rule.addVar(EnumVariable.RecArithT);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.RecArithT;
		rule.initRightSide();
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.ArithF;
		rule.addTkn(LexicalUnit.VARNAME);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.ArithF;
		rule.addTkn(LexicalUnit.NUMBER);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.ArithF;
		rule.addTkn(LexicalUnit.LEFT_PARENTHESIS);
		rule.addTkn(LexicalUnit.VARNAME);
		rule.addTkn(LexicalUnit.RIGHT_PARENTHESIS);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.ArithF;
		rule.addTkn(LexicalUnit.MINUS);
		rule.addVar(EnumVariable.ArithF);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.If;
		rule.addTkn(LexicalUnit.IF);
		rule.addTkn(LexicalUnit.LEFT_PARENTHESIS);
		rule.addVar(EnumVariable.Cond);
		rule.addTkn(LexicalUnit.RIGHT_PARENTHESIS);
		rule.addTkn(LexicalUnit.THEN);
		rule.addTkn(LexicalUnit.ENDLINE);
		rule.addVar(EnumVariable.Code);
		rule.addVar(EnumVariable.FactIf);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.FactIf;
		rule.addTkn(LexicalUnit.ENDIF);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.FactIf;
		rule.addTkn(LexicalUnit.ELSE);
		rule.addTkn(LexicalUnit.ENDLINE);
		rule.addVar(EnumVariable.Code);
		rule.addTkn(LexicalUnit.ENDIF);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.CondPrefix;
		rule.addTkn(LexicalUnit.NOT);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.CondPrefix;
		rule.initRightSide();
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Cond;
		rule.addVar(EnumVariable.CondT);
		rule.addVar(EnumVariable.CondRecE);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.CondRecE;
		rule.addTkn(LexicalUnit.OR);
		rule.addVar(EnumVariable.CondT);
		rule.addVar(EnumVariable.CondRecE);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.CondRecE;
		rule.initRightSide();
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.CondT;
		rule.addVar(EnumVariable.CondPrefix);
		rule.addVar(EnumVariable.CondF);
		rule.addVar(EnumVariable.CondRecT);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.CondRecT;
		rule.addTkn(LexicalUnit.AND);
		rule.addVar(EnumVariable.CondPrefix);
		rule.addVar(EnumVariable.CondF);
		rule.addVar(EnumVariable.CondRecT);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.CondRecT;
		rule.initRightSide();
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.CondF;
		rule.addVar(EnumVariable.ExprArith);
		rule.addVar(EnumVariable.Comp);
		rule.addVar(EnumVariable.ExprArith);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Comp;
		rule.addTkn(LexicalUnit.EQUAL_COMPARE);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Comp;
		rule.addTkn(LexicalUnit.GREATER_EQUAL);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Comp;
		rule.addTkn(LexicalUnit.GREATER);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Comp;
		rule.addTkn(LexicalUnit.SMALLER_EQUAL);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Comp;
		rule.addTkn(LexicalUnit.SMALLER);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Comp;
		rule.addTkn(LexicalUnit.DIFFERENT);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Do;
		rule.addTkn(LexicalUnit.DO);
		rule.addTkn(LexicalUnit.VARNAME);
		rule.addTkn(LexicalUnit.EQUAL);
		rule.addTkn(LexicalUnit.NUMBER);
		rule.addTkn(LexicalUnit.COMMA);
		rule.addTkn(LexicalUnit.NUMBER);
		rule.addTkn(LexicalUnit.ENDLINE);
		rule.addVar(EnumVariable.Code);
		rule.addTkn(LexicalUnit.ENDDO);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Print;
		rule.addTkn(LexicalUnit.PRINT);
		rule.addTkn(LexicalUnit.COMMA);
		rule.addVar(EnumVariable.ExpList);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.Read;
		rule.addTkn(LexicalUnit.READ);
		rule.addTkn(LexicalUnit.COMMA);
		rule.addVar(EnumVariable.VarList);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.ExpList;
		rule.addVar(EnumVariable.ExprArith);
		rule.addVar(EnumVariable.FactExprArith);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.FactExprArith;
		rule.addTkn(LexicalUnit.COMMA);
		rule.addVar(EnumVariable.ExpList);
		rules.add(rule);
		
		rule = new Rule();
		rule.left = EnumVariable.FactExprArith;
		rule.initRightSide();
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
		addToActionTable(EnumVariable.FactVarList, LexicalUnit.COMMA, 5);
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
		addToActionTable(EnumVariable.Comp, LexicalUnit.GREATER_EQUAL, 42);
		addToActionTable(EnumVariable.Comp, LexicalUnit.GREATER, 43);
		addToActionTable(EnumVariable.Comp, LexicalUnit.SMALLER_EQUAL, 44);
		addToActionTable(EnumVariable.Comp, LexicalUnit.SMALLER, 45);
		addToActionTable(EnumVariable.Comp, LexicalUnit.DIFFERENT, 46);
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
