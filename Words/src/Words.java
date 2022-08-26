
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.Popup;


public class Words extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextArea text;

	private JTabbedPane tabPane;

	public static void main(String[] args) {
		 new Words().setVisible(true);

	}

	private Words() {
		super("Words");
		super.setSize(800, 600);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);

		initialize();
	}

	private void initialize() {
		tabPane = new JTabbedPane();

		WordDocument doc = new WordDocument();
		tabPane.addTab(doc.getName(), doc);

		text = new JTextArea();
		JScrollPane scroll = new JScrollPane(text);
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem Hide = new JMenuItem("Hide");
		JMenuItem newDoc = new JMenuItem("New");
		JMenuItem open = new JMenuItem("Open");
		
		JMenuItem saveAs = new JMenuItem("Save As");
		JMenuItem exit = new JMenuItem("Exit");

		JMenuItem[] items = { Hide,newDoc, open, saveAs, exit };
		for (JMenuItem item : items) {
			item.addActionListener(this);
		}
		file.add(Hide);
		file.add(newDoc);
		file.add(open);
		
		file.add(saveAs);
		file.addSeparator();
		file.add(exit);

		bar.add(file);

		add(tabPane);
		 
		setJMenuBar(bar);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("New")) {
			WordDocument doc = new WordDocument();
			tabPane.addTab(doc.getName(), doc);

		} else if(e.getActionCommand().equals("Hide")){
			Hide();
		}
		else if (e.getActionCommand().equals("Open")) {
			open();
		} else if (e.getActionCommand().equals("Save As")) {
			saveAs();
		} else if (e.getActionCommand().equals("Exit")) {
			System.exit(0);
		}
	}
	
	private void Hide() {
		
		JFrame j = new JFrame();
		JOptionPane pane = new JOptionPane();
		JButton jb = new JButton();
		
		pane.showMessageDialog(this, "Are you sure"  ); 
		Words words = new Words();
		words.show();
		jb.setActionCommand("Yes");
		jb.setActionCommand("No");
	}
	private void open() {
		JFileChooser chooser = new JFileChooser();

		int returned = chooser.showOpenDialog(this);

		if (returned == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			WordDocument doc = new WordDocument(file.getName(), new JTextArea());

			tabPane.addTab(file.getName(), doc);

			try {
				BufferedReader br = new BufferedReader(new FileReader(file));

				String line;
				while ((line = br.readLine()) != null) {
					doc.getText().append(line + "\n");
				}

				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	

	private void saveAs() {
		JFileChooser chooser = new JFileChooser();
		int returned = chooser.showSaveDialog(this);
		if (returned == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();

			WordDocument doc = (WordDocument) tabPane.getSelectedComponent();
			doc.saveAs(file.getAbsolutePath());
		}

	}
}
