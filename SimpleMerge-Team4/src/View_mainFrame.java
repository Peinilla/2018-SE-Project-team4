import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class View_mainFrame extends JFrame{
	final static boolean LEFT = true;
	final static boolean RIGHT = false;
	
	final int WIDTH=1200, HEIGHT=800;

	JPanel panel_ta;
	View_textFrame text1 = new View_textFrame(LEFT);
	View_textFrame text2 = new View_textFrame(RIGHT);
	
	JPanel panel_btn;
	JButton btn_merge_L;
	JButton btn_merge_R;
	JButton btn_diff;
	
	JFileChooser jfc;
	
	public View_mainFrame() {
		init();
	}
	
	
	public void init() {
		this.setTitle("Simple Merge");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(RIGHT);
		this.setVisible(LEFT);
		// Frame Init

		panelInit();

		this.setLayout(new BorderLayout());
		this.add(panel_btn, "North");
		this.add(panel_ta, "Center");
		// Frame Layout Controll
		
		jfc = new JFileChooser();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			// JFileChooser 의 UI 변경. 시스템의 룩앤필과 동일하게 변경한다.
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		jfc.updateUI();
        jfc.setFileFilter(new FileNameExtensionFilter("txt", "txt"));
        jfc.setMultiSelectionEnabled(RIGHT);
        
        setDiffMode(RIGHT);
	}
	
	public void panelInit() {
		panel_ta = new JPanel(new FlowLayout());
		panel_ta.add(text1);
		panel_ta.add(text2);
		///textFrame panel Init
		
		btn_merge_L = new JButton("<<<");
		btn_merge_R = new JButton(">>>");
		btn_diff = new JButton("Diff");
		
		panel_btn = new JPanel(new FlowLayout());
		panel_btn.add(btn_diff);
		panel_btn.add(btn_merge_L);
		panel_btn.add(btn_merge_R);
	}
	
	public void setUIText(String str, boolean isLeft) {
		if(isLeft) {
			text1.setUIText(str);
		}else {
			text2.setUIText(str);
		}
	}
	
	public String getUIText(boolean isLeft) {
		if(isLeft) {
			return text1.getUIText();
		}else {
			return text2.getUIText();
		}
	}
	
	public void addListnerController(SM_Controller ctrl) {
		btn_diff.addActionListener(ctrl);
		btn_merge_L.addActionListener(ctrl);
		btn_merge_R.addActionListener(ctrl);

		text1.addListnerController(ctrl);
		text2.addListnerController(ctrl);
	}
	
	public String getFileOpen() {
		if(jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			String str = jfc.getSelectedFile().toString();
			if(str.substring(str.length()-4).equals(".txt")) {
				return str;
			}
			else {
				return null;
			}
		}else {
			return null;
		}
	}
	
	public String getFileSave() {
		if(jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			String str = jfc.getSelectedFile().toString();
			if(str.substring(str.length()-4).equals(".txt")) {
				return str;
			}
			else {
				return str + '.' + jfc.getFileFilter().getDescription(); 
			}
		}else {
			return null;
		}
	}
	
	public void setEdit(boolean isLeft) {
		setDiffMode(RIGHT); // disEnable DiffMode
		
		if(isLeft) {
			boolean flag = text1.ta.isEditable();
			text1.setEdit(!flag);
			text1.btn_if_Editing(flag);
		}else {
			boolean flag = text2.ta.isEditable();
			text2.setEdit(!flag);
			text2.btn_if_Editing(flag);
		}
		
		if(text1.ta.isEditable() || text2.ta.isEditable()) {
			btn_diff.setEnabled(RIGHT);
		}else {
			btn_diff.setEnabled(LEFT);
		}
	}
	
	public boolean isEditing(boolean isLeft) {
		boolean bool;
		
		if(isLeft) {
			bool = text1.ta.isEditable();
		}else {
			bool = text2.ta.isEditable();
		}
		return bool;
	}
	
	public void diffView(boolean isLeft, int[] diffLine) {
		setDiffMode(LEFT); // Enable DiffMode
		
		if(isLeft) {
			text1.diffView(diffLine);
		}else {
			text2.diffView(diffLine);
		}
	}
	
	public void setDiffMode(boolean bool) {
		if(bool) {
			btn_merge_L.setEnabled(LEFT);
			btn_merge_R.setEnabled(LEFT);
		}else {
			btn_merge_L.setEnabled(RIGHT);
			btn_merge_R.setEnabled(RIGHT);
		}
	}
}
