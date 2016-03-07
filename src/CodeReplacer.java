import java.io.*;
import java.util.Scanner;

/** Replace %NAME% with requested id, and %INITIALS% w/"initials" version id.*/
public class CodeReplacer {
	private static String TEMPLATE_DIR = "user.dir";
	static String sourceTemplate;
	static String code;
	static String initcode;

	public CodeReplacer(){

	}

	public CodeReplacer(String reqId, String in, PrintWriter out){
		String templateDir = System.getProperty(TEMPLATE_DIR, "");
		try {
			Scanner scanner = new Scanner(new File("./template.html"));
			String line;
			line = scanner.nextLine();
			// Until template is finished
			while((line!="") && (line!=null)){
				sourceTemplate += line + "\n";
				line = scanner.nextLine();
			}
			scanner.close();
		} catch (Exception e) { }
	}
	/**
	* @param reqId java.lang.String
	* @param out java.io.PrintWriter
	* @exception IOException The exception description.
	*/
	public void substitute(String reqId, PrintWriter out) throws IOException	{
		try {
			String template = new String(sourceTemplate);
			// Substitute for %CODE%
			int templateSplitBegin = template.indexOf("%NAME%");
			int templateSplitEnd = templateSplitBegin + 6;
			String templatePartOne = new String(template.substring(0, templateSplitBegin));
			String templatePartTwo = new String(template.substring(templateSplitEnd, template.length()));
			code = new String(reqId);
			template = new String(templatePartOne+code+templatePartTwo);
			// Substitute for %ALTCODE%
			templateSplitBegin = template.indexOf("%INITIALS%");
			templateSplitEnd = templateSplitBegin + 10;
			templatePartOne = new String(template.substring(0, templateSplitBegin));
			templatePartTwo = new String(template.substring(templateSplitEnd, template.length()));
			String[] str = code.split(" ");
			initcode = "";
			for (String s: str) 
				if (s.length() > 0)
					initcode += s.charAt(0);
			out.print(templatePartOne+initcode+templatePartTwo);
		} catch (Exception e) {
			System.out.println("Error in substitute()");
		}
		out.flush();
		out.close();
	}
}