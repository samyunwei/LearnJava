package xpath;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.xml.namespace.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class XPathTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new XPathFrame();
				frame.setTitle("XPathText");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}


class XPathFrame extends JFrame
{
	private DocumentBuilder builder;
	private Document doc;
	private XPath path;
	private JTextField expression;
	private JTextField result;
	private JTextArea docText;
	private JComboBox<String> typeCombo;
	
	public XPathFrame()
	{
		JMenu fileMenu = new JMenu("File");
		JMenuItem openItem = new JMenuItem("Open");
		openItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				openFile();
			}
		});
		fileMenu.add(openItem);
		JMenuItem exitItem = new JMenuItem("exit");
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		fileMenu.add(exitItem);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				evalute();
			}
		};
		
		expression = new JTextField(20);
		expression.addActionListener(listener);
		JButton evaluateButton = new JButton("Evaluate");
		evaluateButton.addActionListener(listener);
		
		typeCombo = new JComboBox<String>(new String[]{
				"STRING","NODE","NODESET","NUMBER","BOOLEAN"
		});
		typeCombo.setSelectedItem("STRING");
		
		JPanel panel = new JPanel();
		panel.add(expression);
		panel.add(typeCombo);
		panel.add(evaluateButton);
		
		docText = new JTextArea(10,40);
		result = new JTextField();
		result.setBorder(new TitledBorder("Result"));
		
		add(panel,BorderLayout.NORTH);
		add(new JScrollPane(docText),BorderLayout.CENTER);
		add(result,BorderLayout.SOUTH);
		
		
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
		}catch(ParserConfigurationException e)
		{
			JOptionPane.showMessageDialog(this,e);
		}
		
		XPathFactory xpfactory = XPathFactory.newInstance();
		path = xpfactory.newXPath();
		pack();
	}
	
	
	public void openFile()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("xpath"));
		chooser.setFileFilter(new javax.swing.filechooser.FileFilter()
				{

					@Override
					public boolean accept(File f) {
						// TODO Auto-generated method stub
						return f.isDirectory() || f.getName().toLowerCase().endsWith(".xml");
					}

					@Override
					public String getDescription() {
						// TODO Auto-generated method stub
						return "XML files";
					}
				});
		int r = chooser.showOpenDialog(this);
		if(r != JFileChooser.APPROVE_OPTION)
		{
			return;
		}
		File file = chooser.getSelectedFile();
		try
		{
			docText.setText(new String(Files.readAllBytes(file.toPath())));
			doc = builder.parse(file);
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(this,e);
		}
		catch (SAXException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, e);
		}
	}
	
	
	public void evalute()
	{
		try
		{
			String typedName = (String) typeCombo.getSelectedItem();
			QName returnType = (QName) XPathConstants.class.getField(typedName).get(null);
			Object evalResult = path.evaluate(expression.getText(),doc,returnType);
			if(typedName.equals("NODESET"))
			{
				NodeList list = (NodeList) evalResult;
				StringBuilder builder = new StringBuilder();
				builder.append("{");
				for(int i = 0;i<list.getLength();i++)
				{
					if(i > 0)
					{
						builder.append(",");
						builder.append("" + list.item(i));
					}
				}
				builder.append("}");
				result.setText("" + builder);
			}else
			{
				result.setText("" + evalResult);
				
			}
		}catch(XPathException e)
		{
			result.setText("" + e);
			System.out.println("testaaaa");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}