package view;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.sql.rowset.*;
import javax.swing.*;

import org.apache.derby.impl.sql.compile.TableName;

public class ViewDB {
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new ViewDBFrame();
				frame.setTitle("ViewDB");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}


class ViewDBFrame extends JFrame
{
	private JButton previousButton;
	private JButton nextButton;
	private JButton deleteButton;
	private JButton saveButton;
	private DataPanel dataPanel;
	private Component scrollPane;
	private JComboBox<String> tableNames;
	private Properties props;
	private CachedRowSet crs;
	
	public ViewDBFrame()
	{
		tableNames = new JComboBox<String>();
		tableNames.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				showTable((String) tableNames.getSelectedItem());
			}
		});
		
		add(tableNames,BorderLayout.NORTH);
		
		try
		{
			readDatabaseProperties();
			try(Connection conn = getConnection())
			{
				DatabaseMetaData meta = conn.getMetaData();
				ResultSet mrs = meta.getTables(null, null, null, new String[]{"TABLE"});
				while(mrs.next())
				{
					tableNames.addItem(mrs.getString(3));
				}
			}
		}catch(SQLException e)
		{
			JOptionPane.showMessageDialog(this, e);
		}
		catch (IOException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, e);
		}
		
		
		JPanel buttonPanel = new JPanel();
		add(buttonPanel,BorderLayout.SOUTH);
		
		previousButton = new JButton("Previous");
		previousButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				showPreviousRow();
			}
		});
		
		buttonPanel.add(previousButton);
		nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				showNextRow();
			}
		});
		
		buttonPanel.add(nextButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				deleteRow();
			}
		});
		
		buttonPanel.add(deleteButton);
		
		saveButton= new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				saveChanges();
			}
		});
		buttonPanel.add(saveButton);
		
		pack();
	}
	
	
	public void showTable(String tableName)
	{
		try
		{
			try(Connection conn = getConnection())
			{
				Statement stat = conn.createStatement();
				ResultSet result = stat.executeQuery("SELECT * FROM " + tableName);
				RowSetFactory factory = RowSetProvider.newFactory();
				crs = factory.createCachedRowSet();
				crs.setTableName(tableName);
				crs.populate(result);
			}
			
			if(scrollPane != null)
			{
				remove(scrollPane);
			}
			dataPanel = new DataPanel(crs);
			scrollPane = new JScrollPane(dataPanel);
			add(scrollPane,BorderLayout.CENTER);
			validate();
			showNextRow();
		}catch(SQLException e)
		{
			JOptionPane.showMessageDialog(this,e);
		}
	}
	
	public void showPreviousRow()
	{
		try
		{
			if(crs == null || crs.isFirst())
			{
				return;
			}
			crs.previous();
			dataPanel.showRow(crs);
		}catch(SQLException e)
		{
			for(Throwable t : e)
			{
				t.printStackTrace();
			}
		}
	}
	
	public void showNextRow()
	{
		try
		{
			if(crs == null || crs.isLast())
			{
				return;
			}
			crs.next();
			dataPanel.showRow(crs);
		}catch(SQLException e)
		{
			JOptionPane.showMessageDialog(this,e);
		}
	}
	
	public void deleteRow()
	{
		try
		{
			try(Connection conn  = getConnection())
			{
				crs.deleteRow();
				crs.acceptChanges(conn);
				if(crs.isAfterLast())
				{
					if(!crs.last())
					{
						crs = null;
					}
					dataPanel.showRow(crs);
				}
			}
		}catch(SQLException e)
		{
			JOptionPane.showMessageDialog(this, e);
		}
	}
	
	public void saveChanges()
	{
		try
		{
			try(Connection conn = getConnection())
			{
				dataPanel.setRow(crs);
				crs.acceptChanges(conn);
			}
		}catch(SQLException e)
		{
			JOptionPane.showMessageDialog(this, e);
		}
	}
	
	
	private void readDatabaseProperties() throws IOException
	{
		props = new Properties();
		try(InputStream in = Files.newInputStream(Paths.get("database.properties")))
		{
			props.load(in);
		}
	}
	
	private Connection getConnection() throws SQLException
	{
		String url = props.getProperty("jdbc.url");
		return DriverManager.getConnection(url);
	}
}

class DataPanel extends JPanel
{
	private java.util.List<JTextField> fields;
	
	public DataPanel(RowSet rs) throws SQLException
	{
		fields = new ArrayList<>();
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		
		ResultSetMetaData rsmd = rs.getMetaData();
		for(int i = 1;i <= rsmd.getColumnCount();i++)
		{
			gbc.gridy = i -1;
			String columnName = rsmd.getColumnLabel(i);
			gbc.gridx = 0;
			gbc.anchor = GridBagConstraints.EAST;
			add(new JLabel(columnName),gbc);
			
			int columnWidth = rsmd.getColumnDisplaySize(i);
			JTextField tb = new JTextField(columnWidth);
			if(!rsmd.getColumnClassName(i).equals("Java.lang.String"))
			{
				tb.setEditable(false);
			}
			fields.add(tb);
			gbc.gridx =1 ;
			gbc.anchor = GridBagConstraints.WEST;
			add(tb,gbc);
		}
	}
	
	public void showRow(ResultSet rs) throws SQLException
	{
		for(int i = 1;i <= fields.size();i++)
		{
			String field = rs == null ? "":rs.getString(i);
			JTextField tb = fields.get(i - 1);
			tb.setText(field);
		}
	}
	
	public void setRow(RowSet rs) throws SQLException
	{
		for(int i = 1;i<fields.size();i++)
		{
			String field = rs.getString(i);
			JTextField tb = fields.get(i - 1);
			if(!field.equals(tb.getText()))
			{
				rs.updateString(i,tb.getText());
			}
			rs.updateRow();
		}
	}
}
