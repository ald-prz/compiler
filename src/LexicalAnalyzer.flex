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
	
	public void idle()
	{
	}
%}

comment = ("c "|"C "|"* "|"d "|"D "|"!")(.*)(\r|\n|\r\n)
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
then = "then"
endif = "endif"
else = "else"
not = ".not."
and = ".and."
or = ".or."
equal_compare = ".eq."
greater_equal = ".ge."
greater = ".gt."
smaller_equal = ".le."
smaller = ".lt."
different = ".ne."
do = "do"
enddo = "enddo"
print = "print*,"
read = "read*,"
endline = \r|\n|\r\n   // do not remove \r\n, otherwise it will consider 2 endlines per one


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
{then} {token(LexicalUnit.THEN, yyline, yycolumn, yytext());}
{endif} {token(LexicalUnit.ENDIF, yyline, yycolumn, yytext());}
{else} {token(LexicalUnit.ELSE, yyline, yycolumn, yytext());}
{not} {token(LexicalUnit.NOT, yyline, yycolumn, yytext());}
{and} {token(LexicalUnit.AND, yyline, yycolumn, yytext());}
{or} {token(LexicalUnit.OR, yyline, yycolumn, yytext());}
{equal_compare} {token(LexicalUnit.EQUAL_COMPARE, yyline, yycolumn, yytext());}
{greater_equal} {token(LexicalUnit.GREATER_EQUAL, yyline, yycolumn, yytext());}
{greater} {token(LexicalUnit.GREATER, yyline, yycolumn, yytext());}
{smaller_equal} {token(LexicalUnit.SMALLER_EQUAL, yyline, yycolumn, yytext());}
{smaller} {token(LexicalUnit.SMALLER, yyline, yycolumn, yytext());}
{different} {token(LexicalUnit.DIFFERENT, yyline, yycolumn, yytext());}
{do} {token(LexicalUnit.DO, yyline, yycolumn, yytext());}
{enddo} {token(LexicalUnit.ENDDO, yyline, yycolumn, yytext());}
{print} {token(LexicalUnit.PRINT, yyline, yycolumn, yytext());}
{read} {token(LexicalUnit.READ, yyline, yycolumn, yytext());}
{endline} {token(LexicalUnit.ENDLINE, yyline, yycolumn, yytext());}
{varname} {token(LexicalUnit.VARNAME, yyline, yycolumn, yytext());} // keep it in the bottom
{comment} {idle();} // also keep in the bottom since it is least priority check (could be confused with multiplication)
