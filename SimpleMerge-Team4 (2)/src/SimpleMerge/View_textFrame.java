package SimpleMerge;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class View_textFrame extends JPanel {
	
	JTextPane textPane = new JTextPane();
	JScrollPane jsp;
	StyledDocument styleDoc;
	
	JPanel panel_btn;
	JButton btn_save;
	JButton btn_load;
	JButton btn_edit;

	boolean isLeft;
	
	public View_textFrame(boolean isLeft) {
		this.isLeft = isLeft;
		init();
	}
	
	public void init() {
		textPane.setBackground(Color.WHITE);
		textPane.setEditable(false);
		jsp = new JScrollPane(textPane) {
			 @Override
	            public Dimension getPreferredSize() {
	                return new Dimension(550,650);
	            }
		};
        jsp.setViewportView(textPane);
        styleDoc = textPane.getStyledDocument();

		if(isLeft) {
			btn_save = new JButton("Left_Save");		
			btn_load = new JButton("Left_Load");
			btn_edit = new JButton("Left_Edit");
		}else {
			btn_save = new JButton("Right_Save");		
			btn_load = new JButton("Right_Load");
			btn_edit = new JButton("Right_Edit");
		}
		
		panel_btn = new JPanel(new FlowLayout());
		panel_btn.add(btn_save);
		panel_btn.add(btn_load);
		panel_btn.add(btn_edit);

		this.setLayout(new BorderLayout());
		this.add(panel_btn, "North");
		this.add(jsp,"Center");
	}
	
	public void setUIText(String str) {
		textClear();
		
		textPane.setText(str);
	}
	
	public String getUIText() {
		return textPane.getText();
	}
	
	/*
	 * Add SM_Controller as ActionListener to the Buttons
	 */
	public void addListnerController(SM_Controller ctrl) {
		btn_save.addActionListener(ctrl);
		btn_load.addActionListener(ctrl);
		btn_edit.addActionListener(ctrl);
	}
	
	public void setEdit(boolean flag) {
		textPane.setEditable(flag);
	}
	
	/*
	 * When 'ta' is editable,
	 * btn_save & btn_load DisEnabled
	 */
	public void btn_if_Editing(boolean isEdit) {
		btn_save.setEnabled(isEdit);
		btn_load.setEnabled(isEdit);
	}
	
	/*
	 * Use 'diffLine[]:int' ( 0 = Different line, 1 = Same line, 2 = Blank )
	 * Blank is not included in LineNum
	 * Blank used to match SameLine
	 */
	public void diffView(int[] diffLine) {
		SimpleAttributeSet Attribute = new SimpleAttributeSet();

		String[] str = getUIText().split("\n");
		int jnx = 0;
		
		textClear();
		
		for(int inx = 0; inx < diffLine.length; inx++) {
			String buf;
			if(jnx < str.length) {
				buf = str[jnx];
			}else {
				buf = "";
			}
			
			if(diffLine[inx] == 0) { //diff
				try {
			        StyleConstants.setBackground(Attribute, Color.YELLOW);
					styleDoc.insertString(styleDoc.getLength(), buf + "\t\n", Attribute);
					jnx++;
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}else if(diffLine[inx] == 1){ //same
				try {
			        StyleConstants.setBackground(Attribute, Color.WHITE);
					styleDoc.insertString(styleDoc.getLength(), buf + "\n", Attribute);
					jnx++;
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}else if(diffLine[inx] == 2) { //blank
				try {
			        StyleConstants.setBackground(Attribute, Color.GRAY);
					styleDoc.insertString(styleDoc.getLength(), "\t\n", Attribute);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
			else {
				
			}
		}
	}
	
	public void textClear() {
		textPane.setText(null);
	}
}


