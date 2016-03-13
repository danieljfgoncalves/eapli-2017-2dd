/**
 *
 */
package eapli.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Random;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * @author Paulo Gandra Sousa
 *
 */
public final class Strings {

    public static String prettyFormat(final String input) {
        return prettyFormat(input, 2);
    }

    /**
     * returns an XML formated output.
     * <p>
     * based in code from
     * <a href="http://stackoverflow.com/questions/139076/how-to-pretty-print-xml-from-java">stack
     * overflow</a>
     *
     * @param input
     * @param indent
     * @return
     */
    public static String prettyFormat(final String input, final int indent) {
        try {
            Source xmlInput = new StreamSource(new StringReader(input));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            transformerFactory.setAttribute("indent-number", indent);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e); // TODO simple exception handling, please
            // review it
        } catch (TransformerException e) {
            throw new RuntimeException(e); // TODO simple exception handling, please
            // review it
        }
    }

	private static final String CHARSET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static String randomString(int len)
	{
		return randomString(len, CHARSET);
	}

	public static String randomString(int len, String charSet)
	{
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++)
		{
			int c = rnd.nextInt(charSet.length());
			sb.append(charSet.charAt(c));
		}
		return sb.toString();
	}
	
	public static String Truncate(String org, int len)
	{
		if (len < org.length())
			return org.substring(0, len);
		else
			return org;
	}

	public static String Left(String org, int len)
	{
		return org.substring(0, len);
	}

	public static String Right(String org, int len)
	{
		return org.substring(org.length()-len);
	}

    private Strings() {
        // to make sure this is an utility class
    }
}
