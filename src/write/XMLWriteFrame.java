package write;
import java.awt.event.*;
import java.beans.*;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;
import javax.xml.stream.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;


public class XMLWriteFrame extends JFrame
{
	private RectangleComponent comp;
	private JFileChooser chooser;
	
	public XMLWriteFrame()
	{
		chooser = new JFileChooser();
		
		comp = new RectangleComponent();
		add(comp);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("File");
		
		menuBar.add(menu);
		
		JMenuItem newItem = new JMenuItem("New");
		menu.add(newItem);
		newItem.addActionListener(EventHandler.create(ActionListener.class,comp,"newDrawing"));
		
		JMenuItem saveItem = new JMenuItem("Save with DOM/XSLT");
		menu.add(saveItem);
		saveItem.addActionListener(EventHandler.create(ActionListener.class,this,"saveDocument"));
		
		JMenuItem saveStAXItem = new JMenuItem("Save with StAX");
		menu.add(saveStAXItem );
		saveStAXItem.addActionListener(EventHandler.create(ActionListener.class,this,"saveStAX"));
		
		JMenuItem exitItem = new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
				}
		});
		pack();
	}
	
	
	public void saveDocument() throws TransformerException,IOException
	{
		if(chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION)
		{
			return;
		}
		File file = chooser.getSelectedFile();
		Document doc = comp.buildDocument();
		Transformer t = TransformerFactory.newInstance().newTransformer();
		t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"http://www.w3.org/TR/2000/CR-SVG-20000802/DTD/svg-20000802.dtd");
		t.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,"-//W3C//DTD SVG 20000802//EN");
		t.setOutputProperty(OutputKeys.INDENT,"yes");
		t.setOutputProperty(OutputKeys.METHOD, "xml");
		t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");
		t.transform(new DOMSource(doc),new StreamResult(Files.newOutputStream(file.toPath())));
	}
	
	public void saveStAX() throws IOException,XMLStreamException
	{
		if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION)
		{
			return;
		}
		File file = chooser.getSelectedFile();
		XMLOutputFactory factory = XMLOutputFactory.newFactory();
		XMLStreamWriter writer = factory.createXMLStreamWriter(Files.newOutputStream(file.toPath()));
		try
		{
			comp.writeDocunment(writer);
		}
		finally
		{
			writer.close();
		}
	}
}
