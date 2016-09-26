package tansform;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.xml.transform.*;
import javax.xml.transform.sax.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class TransformTest {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Path path = Paths.get("./employee.dat");
//		if(args.length > 0)
//		{
//			path = Paths.get("./employee.dat");
//		}else
//		{
//			path = Paths.get("transform","makehtml.xsl");
//		}
		try(InputStream styleIn = Files.newInputStream(path))
		{
			StreamSource styleSource = new StreamSource(styleIn);
			//Transformer t = TransformerFactory.newInstance().newTransformer(styleSource);
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.INDENT,"yes");
			t.setOutputProperty(OutputKeys.METHOD,"xml");
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");
			
			try(InputStream docIn = Files.newInputStream(Paths.get("employee.dat")))
			{
				t.transform(new SAXSource(new EmployeeReader(),new InputSource(docIn)),new StreamResult(System.out));
			}
		}
	}

}



class EmployeeReader implements XMLReader
{
	private ContentHandler handler;

	@Override
	public ContentHandler getContentHandler() {
		// TODO Auto-generated method stub
		return handler;
	}

	@Override
	public DTDHandler getDTDHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityResolver getEntityResolver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorHandler getErrorHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getFeature(String arg0) throws SAXNotRecognizedException, SAXNotSupportedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getProperty(String arg0) throws SAXNotRecognizedException, SAXNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void parse(InputSource source) throws IOException, SAXException {
		// TODO Auto-generated method stub
		InputStream stream = source.getByteStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(stream));
		String rootElement = "staff";
		AttributesImpl atts = new AttributesImpl();
		
		if(handler == null)
		{
			throw new SAXException("No content handler");
			
		}
		
		handler.startDocument();
		handler.startElement("",rootElement,rootElement,atts);
		String line;
		while((line = in.readLine())!=null)
		{
			handler.startElement("","employee","employee",atts);
			StringTokenizer t = new StringTokenizer(line,"|");
			
			
			handler.startElement("","name","name",atts);
			String s= t.nextToken();
			handler.characters(s.toCharArray(),0,s.length());
			handler.endElement("","name","name");
			
			handler.startElement("","salary","salary",atts);
			s = t.nextToken();
			handler.characters(s.toCharArray(),0,s.length());
			handler.endElement("","salary","salary");
			
			atts.addAttribute("","year","year","CDATA",t.nextToken());
			atts.addAttribute("","month","month","month",t.nextToken());
			atts.addAttribute("","day","day","day",t.nextToken());
			handler.startElement("","hireday","hireday",atts);
			handler.endElement("","hireday","hireday");
			atts.clear();
			
			handler.endElement("","employee","employee");
		}
		
		handler.endElement("",rootElement,rootElement);
		handler.endDocument();
		
	}

	@Override
	public void parse(String arg0) throws IOException, SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContentHandler(ContentHandler newValue) {
		// TODO Auto-generated method stub
		handler = newValue;
	}

	@Override
	public void setDTDHandler(DTDHandler arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEntityResolver(EntityResolver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setErrorHandler(ErrorHandler arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFeature(String arg0, boolean arg1) throws SAXNotRecognizedException, SAXNotSupportedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProperty(String arg0, Object arg1) throws SAXNotRecognizedException, SAXNotSupportedException {
		// TODO Auto-generated method stub
		
	}
	
}