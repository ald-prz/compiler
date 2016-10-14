public class Main {

	/*
	To compile:
	1) Run build_windows.bat or equivalent linux script.
	2) In Eclipse go to LexicalAnalyzer.java so IDE could refresh its link.
	3) Switch to Main.java scope.
	4) Press Run.
	
	
	TO-ASK
	1) Is the compiler case-sensitive only to names or to key words too?
	2) Can we submit the project before the deadline for premature examination?
	3) When toString() endline Symbol in output it is represented by 2 lines. Should we remove it?
	4) Should we remove unprocessed spaces from output?
	5) After comment symbols [c,C,*,d,D, or !] do we always have space?
	6) How do we distinguish * as comment and as a multiplication symbol if we can not foresee future tokens?
	*/
	
	public static void main(String[] args) {
		String[] s = new String[] {"deliverables\\test\\factorielle.txt"};
		
		LexicalAnalyzer.main(s);
	}
}
