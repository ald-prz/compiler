%%
%class LexicalAnalyzer
%unicode
%line
%column
%standalone
%caseless
%ignorecase

%init{
	preprocessor = new Preprocessor();
%init}

%eof{
	preprocessor.outputIdentifiers();
%eof}

%{
	Preprocessor preprocessor;

	public Symbol token(LexicalUnit unit, int line, int column, String match) {
		return preprocessor.newToken(unit, line, column, match);
	}
%}

single_endline = \r|\n|\r\n
endline = {single_endline}+   
comment = {endline} ("c "|"C "|"* "|"d "|"D "|"!")(.*)
varname = ([a-zA-Z])([a-zA-Z]|[0-9])*
number = [0-9]+
space = " "+

%state PROGRAM

%%

<YYINITIAL> {
	{number} {token(LexicalUnit.NUMBER, yyline, yycolumn, yytext());}
	{endline} {token(LexicalUnit.ENDLINE, yyline, yycolumn, yytext());}
	
	"PROGRAM" {token(LexicalUnit.PROGRAM, yyline, yycolumn, yytext()); yybegin(PROGRAM);}
	"END" {token(LexicalUnit.END, yyline, yycolumn, yytext());}
	
	// Various elements
	"," {token(LexicalUnit.COMMA, yyline, yycolumn, yytext());}
	"=" {token(LexicalUnit.EQUAL, yyline, yycolumn, yytext());}
	"(" {token(LexicalUnit.LEFT_PARENTHESIS, yyline, yycolumn, yytext());}
	")" {token(LexicalUnit.RIGHT_PARENTHESIS, yyline, yycolumn, yytext());}
	
	// Operators
	"-" {token(LexicalUnit.MINUS, yyline, yycolumn, yytext());}
	"+" {token(LexicalUnit.PLUS, yyline, yycolumn, yytext());}
	"*" {token(LexicalUnit.TIMES, yyline, yycolumn, yytext());}
	"/" {token(LexicalUnit.DIVIDE, yyline, yycolumn, yytext());}
	
	// Conditions
	"IF" {token(LexicalUnit.IF, yyline, yycolumn, yytext());}
	"THEN" {token(LexicalUnit.THEN, yyline, yycolumn, yytext());}
	"ENDIF" {token(LexicalUnit.ENDIF, yyline, yycolumn, yytext());}
	"ELSE" {token(LexicalUnit.ELSE, yyline, yycolumn, yytext());}
	
	// Boolean logic
	"NOT" {token(LexicalUnit.NOT, yyline, yycolumn, yytext());}
	"AND" {token(LexicalUnit.AND, yyline, yycolumn, yytext());}
	"OR" {token(LexicalUnit.OR, yyline, yycolumn, yytext());}
	
	// Comparators
	".EQ." {token(LexicalUnit.EQUAL_COMPARE, yyline, yycolumn, yytext());}
	".GE." {token(LexicalUnit.GREATER_EQUAL, yyline, yycolumn, yytext());}
	".GT." {token(LexicalUnit.GREATER, yyline, yycolumn, yytext());}
	".LE." {token(LexicalUnit.SMALLER_EQUAL, yyline, yycolumn, yytext());}
	".LT." {token(LexicalUnit.SMALLER, yyline, yycolumn, yytext());}
	".NE." {token(LexicalUnit.DIFFERENT, yyline, yycolumn, yytext());}
	
	// Remaining Keywords
	"INTEGER" {token(LexicalUnit.INTEGER, yyline, yycolumn, yytext());}
	"DO" {token(LexicalUnit.DO, yyline, yycolumn, yytext());}
	"ENDDO" {token(LexicalUnit.ENDDO, yyline, yycolumn, yytext());}
	"PRINT" {token(LexicalUnit.PRINT, yyline, yycolumn, yytext());}
	"READ*" {token(LexicalUnit.READ, yyline, yycolumn, yytext());}
}

<PROGRAM> {
	// Add ProgName only to the symbols list
	{varname} {token(LexicalUnit.VARNAME, yyline, yycolumn, yytext()); yybegin(YYINITIAL);}
}

// Keep these ones in the bottom to ensure lower priority
// Add varname to both the symbols and varnames list
{varname} {preprocessor.newVariable(token(LexicalUnit.VARNAME, yyline, yycolumn, yytext()));} 
{comment} { } 
{space} { }
