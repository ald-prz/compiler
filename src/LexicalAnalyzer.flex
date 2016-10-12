%%
%class LexicalAnalyzer
%unicode
%line
%column
%standalone

%{
	public void token(LexicalUnit unit)
	{
		switch (unit)
		{
			case PROGRAM: System.out.println("[We have a PROGRAM token]"); break;
		}
	}
%}

token_problem = "PROGRAM"

%%
{token_problem} {token(LexicalUnit.PROGRAM);}