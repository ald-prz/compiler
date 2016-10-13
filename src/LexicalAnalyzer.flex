%%
%class LexicalAnalyzer
%unicode
%line
%column
%standalone
%caseless
%ignorecase

%{
	public void token(LexicalUnit unit, int line, int column, String match)
	{
		Symbol s = new Symbol(unit, line, column, match);
		System.out.printf("%s\n", s.toString());
	}
%}



varname = ([a-z]|[A-Z])([a-z]|[A-Z]|[0-9])*
integer = "integer"
number = [0-9]+
problem = "program"
end = "end"
comma = ","
equal = "="
left_parenthesis = "("
right_parenthesis = ")"
minus = "-"
plus = "+"
times = "*"
divide = "/"
if = "if"


%%

{integer} {token(LexicalUnit.INTEGER, yyline, yycolumn, yytext());}
{number} {token(LexicalUnit.NUMBER, yyline, yycolumn, yytext());}
{problem} {token(LexicalUnit.PROGRAM, yyline, yycolumn, yytext());}
{end} {token(LexicalUnit.END, yyline, yycolumn, yytext());}
{comma} {token(LexicalUnit.COMMA, yyline, yycolumn, yytext());}
{equal} {token(LexicalUnit.EQUAL, yyline, yycolumn, yytext());}
{left_parenthesis} {token(LexicalUnit.LEFT_PARENTHESIS, yyline, yycolumn, yytext());}
{right_parenthesis} {token(LexicalUnit.RIGHT_PARENTHESIS, yyline, yycolumn, yytext());}
{minus} {token(LexicalUnit.MINUS, yyline, yycolumn, yytext());}
{plus} {token(LexicalUnit.PLUS, yyline, yycolumn, yytext());}
{times} {token(LexicalUnit.TIMES, yyline, yycolumn, yytext());}
{divide} {token(LexicalUnit.DIVIDE, yyline, yycolumn, yytext());}
{if} {token(LexicalUnit.IF, yyline, yycolumn, yytext());}

{varname} {token(LexicalUnit.VARNAME, yyline, yycolumn, yytext());} // keep it in the bottom for last option check
