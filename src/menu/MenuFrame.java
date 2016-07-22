package menu;

import java.awt.event.*;
import javax.swing.*;

public class MenuFrame extends JFrame {
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	private Action saveAction;
	private Action saveAsAction;
	private JCheckBoxMenuItem readonlyItem;
	private JPopupMenu popup;

	class TestAction extends AbstractAction {
		public TestAction(String name) {
			super(name);
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			System.out.println(getValue(Action.NAME) + "selected.");
		}
	}

	public MenuFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_WIDTH);

		JMenu fileMenu = new JMenu("File");
		fileMenu.add(new TestAction("New"));

		JMenuItem openItem = fileMenu.add(new TestAction("Open"));
		openItem.setAccelerator(KeyStroke.getKeyStroke("ctrl 0"));

		fileMenu.addSeparator();
		saveAction = new TestAction("Save");
		JMenuItem saveItem = fileMenu.add(saveAction);
		saveItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));

		saveAsAction = new TestAction("Save As");
		fileMenu.add(saveAsAction);
		fileMenu.addSeparator();

		fileMenu.add(new AbstractAction("Exit") {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

		readonlyItem = new JCheckBoxMenuItem("Read-only");
		readonlyItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				boolean saveOK = !readonlyItem.isSelected();
				saveAction.setEnabled(saveOK);
				saveAsAction.setEnabled(saveOK);
			}
		});

		ButtonGroup group = new ButtonGroup();

		JRadioButtonMenuItem insertItem = new JRadioButtonMenuItem("Insert");
		insertItem.setSelected(true);
		JRadioButtonMenuItem overtypeItem = new JRadioButtonMenuItem("Overtype");

		group.add(insertItem);
		group.add(overtypeItem);

		Action cutAction = new TestAction("Cut");
		cutAction.putValue(Action.SMALL_ICON, new ImageIcon("test.gif"));
		Action copyAction = new TestAction("Copy");
		copyAction.putValue(Action.SMALL_ICON, new ImageIcon("test.gif"));
		Action pasteAction = new TestAction("Paste");
		pasteAction.putValue(Action.SMALL_ICON, new ImageIcon("test.gif"));

		JMenu editMenu = new JMenu("Edit");
		editMenu.add(cutAction);
		editMenu.add(copyAction);
		editMenu.add(pasteAction);

		JMenu optionMenu = new JMenu("Options");

		optionMenu.add(readonlyItem);
		optionMenu.addSeparator();
		optionMenu.add(insertItem);
		optionMenu.add(overtypeItem);

		editMenu.addSeparator();
		editMenu.add(optionMenu);

		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');

		JMenuItem indexItem = new JMenuItem("Index");
		indexItem.setMnemonic('I');
		helpMenu.add(indexItem);

		Action aboutAction = new TestAction("About");
		aboutAction.putValue(Action.MNEMONIC_KEY, new Integer('A'));
		helpMenu.add(aboutAction);

		JMenuBar MenuBar = new JMenuBar();
		setJMenuBar(MenuBar);

		MenuBar.add(fileMenu);
		MenuBar.add(editMenu);
		MenuBar.add(helpMenu);

		popup = new JPopupMenu();
		popup.add(cutAction);
		popup.add(copyAction);
		popup.add(pasteAction);

		JPanel panel = new JPanel();
		panel.setComponentPopupMenu(popup);
		add(panel);

		panel.addMouseListener(new MouseAdapter() {
		});

	}
}
