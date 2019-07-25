import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.TextArea;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class JMenuTest extends JFrame implements ActionListener {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JMenuTest frame = new JMenuTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	JTextArea textArea;
	public JMenuTest() {
		//pane properties
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		//create and add scroll pane
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		//create and add textArea to pane
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		//array to hold Menus
		JMenuItem[] menus = new JMenuItem[8]; 
		//1. set the top bar to add menu
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		//2. add the file menu on top
		JMenu fileMenu = new JMenu("File");
		//3. add sub-menu inside of File
		JMenuItem newItem = new JMenuItem("New");
		JMenuItem openItem = new JMenuItem("Open");
		JMenuItem saveSubItem = new JMenuItem("Save");
		JMenuItem saveAsSubItem = new JMenuItem("Save As");
		JMenuItem exitSubItem = new JMenuItem("Exit");
		JMenu extra = new JMenu("Extra");
		JMenuItem projectItem = new JMenuItem("Project");
		JMenuItem classItem = new JMenuItem("Class");
		//4. add the JMenu to the bar
		bar.add(fileMenu);
		//7. add everything to bar
		//6. add sub-sub-menu to newSubItem
		extra.add(projectItem);
		extra.add(classItem);
		//5. add sub-menu to File
		fileMenu.add(newItem);
		fileMenu.add(openItem);
		fileMenu.add(saveSubItem);
		fileMenu.add(saveAsSubItem);
		fileMenu.add(extra);
		//add separator (line)
		fileMenu.addSeparator();
		fileMenu.add(exitSubItem);
		//add menu items to array
		menus[0] = newItem;
		menus[1] = saveSubItem;
		menus[2] = exitSubItem;
		menus[3] = openItem;
		menus[4] = projectItem;
		menus[5] = classItem;
		menus[6] = extra;
		menus[7] = saveAsSubItem;
		//8. add action to exitSubItem
		for (int i = 0; i < menus.length; i++) {
			menus[i].addActionListener(this);
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = e.getActionCommand();
		//9. exit implement
		if (name.equals("New")) {
			newWindow();
		}
		else if (name.equals("Open")) {
			open();
		}
		else if(name.equals("Save")) {
			save();
		}
		else if(name.equals("Save As")) {
			saveAs();
		}
		else if(name.equals("Exit")) { //name.equalsIgnoreCase(anotherString)("Exit")
			System.out.println("Closed");
			System.exit(0); //exit the window
		}
		
	}
	//method to open File
	public void open() {
		JFileChooser fc = new JFileChooser("./");
		BufferedReader br = null;
		FileReader fr = null;
		fc.showOpenDialog(null);
		try {
			fr = new FileReader(fc.getSelectedFile().getAbsolutePath());
			br = new BufferedReader(fr);
			//make sure textArea is empty
			textArea.setText("");
			//loop to read all lines in file
			while(br.readLine() != null) {
				textArea.append(br.readLine() + "\n");
			}
		}
		catch (IOException ie) {
			ie.printStackTrace();
		}
		finally {
			try {
				if (br != null) {
					br.close();
					fr.close();
				}
			}
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	//method to save file
	public void save() {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			File file = new File("fileSave.txt");
			if(!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			//string to hold text to write on new file
			String text = textArea.getText();
			//write file
			if (text != null) {
				bw.write(text);
			}
		}
		catch (IOException ie) {
			ie.printStackTrace();
		}
		finally {
			try {
				if (bw != null) {
					bw.close();
					fw.close();
				}
			}
			catch (IOException ie2) {
				ie2.printStackTrace();
			}
		}
	}
	//method to save file as
	public void saveAs() {
		JFileChooser fc = new JFileChooser("./");
		BufferedWriter bw = null;
		FileWriter fw = null;
		int returnValue = fc.showSaveDialog(contentPane);
		
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			try {
				String path = fc.getCurrentDirectory().getAbsolutePath();
				String name = fc.getSelectedFile().getName();
				if(!name.endsWith(".txt")) {
					name += ".txt";
				}
				File file = new File(path,name);
				
				fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
				//string to hold each line to write on new file
				String text = textArea.getText();
				//write file
				if (text != null) {
					bw.write(text);
				}
			}
			catch (IOException ie) {
				ie.printStackTrace();
			}
			finally {
				try {
					if (bw != null) {
						bw.close();
						fw.close();
					}
				}
				catch (IOException ie2) {
					ie2.printStackTrace();
				}
			}
		}
	}
	//method to open new window
	public void newWindow() {
		JMenuTest frame1 = new JMenuTest();
		frame1.setBounds(100, 100, 400, 400);
		frame1.setVisible(true);
	}
}
