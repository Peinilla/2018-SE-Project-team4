import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

public class SM_View extends JFrame {
	// UI ����
	View_mainFrame vmf = new View_mainFrame();
	
	public SM_View (){
		//UI set
	}
	
	public void setUIText(List<String> str, boolean isTwo) {
		vmf.setUIText(str, isTwo);
	}
	
	public String getUIText(boolean isTwo) {
		return vmf.getUIText(isTwo);
		//UI ���� �ؽ�Ʈ�� ������
	}
	
	public void canEdit(boolean isEdit) {
		
	}
	
	public void addListnerController(SM_Controller ctrl) {
		vmf.addListnerController(ctrl);
	}
	
	public String getFileOpen() {
		return vmf.getFileOpen();
	}
	
	public String getFileSave() {
		return vmf.getFileSave();
	}

}
