public class Main {

	/*
	To compile:
	1) Run build_windows.bat or equivalent linux script.
	2) In Eclipse go to LexicalAnalyzer.java so IDE could refresh its link.
	3) Switch to Main.java scope.
	4) Press Run.
	
	
	TO-ASK
	1) When toString() endline Symbol in output it is represented by 2 lines. Should we remove it?
	2) In pdf example identifiers are not sorted.
	3) Identifiers tabulation output???
	4) Do we consider ProgName a variable name?
	5) Do we remove redundant endlines?
	*/
	
	public static void main(String[] args) {
		String[] s = new String[] {"deliverables/test/factorielle.txt"};
		
		LexicalAnalyzer.main(s);
	}
}
