%%
%class main
%unicode
%line
%column
%standalone

%{
	public void idle()
	{
	}
%}

rule = [0-9]+

%%
{rule} {idle();}