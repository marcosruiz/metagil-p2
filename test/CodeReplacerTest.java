import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class CodeReplacerTest {
	
	private static StringWriter strOut;
	private static String stringSolucion;
	static PrintWriter out;
	private CodeReplacer cr;
	
	@Before
	public void inicializacion() throws IOException {
		cr = new CodeReplacer();
		strOut = new StringWriter();	
		out = new PrintWriter(strOut);
	}
	@After
	public void finalizacion(){
		assertEquals(stringSolucion, strOut.toString());
	}
	
	@Test
	public void pruebaValido() throws IOException {
		stringSolucion = "XXXcXXXcambioXXX%INITIALS%XXX%NAME%XXX\nfdsfsd\nfjsdlkfjs\n";
		cr.substitute("cambio", out);
	}
	
	@Test
	public void pruebaCadenaVacia() throws IOException {
		stringSolucion = "XXXXXXXXX%INITIALS%XXX%NAME%XXX\nfdsfsd\nfjsdlkfjs\n";
		cr.substitute("", out);
	}
	
	@Test
	public void pruebaNullString() throws IOException {
		stringSolucion = "";
		cr.substitute(null, out);
	}
	
	@Test(expected=NullPointerException.class)
	public void pruebaNullOut() throws IOException {
		stringSolucion = "";
		cr.substitute("cambio", null);
	}
	
	@Test(expected=NullPointerException.class)
	public void pruebaNullNull() throws IOException {
		stringSolucion = "";
		cr.substitute(null, null);
	}
	
	
}
