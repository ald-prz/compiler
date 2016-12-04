public class Main {

	/*
	To compile & run:
	1) Run build_windows.bat or equivalent linux script.
	2) In Eclipse, press F5 or Refresh on the project in the Package Explorer.
	3) Switch to Main.java scope.
	4) Press Run.
	*/
	
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please supply the path to the file you wish to analyse.");
			return;
		}
		
		LexicalAnalyzer.main(args);
	}
}
