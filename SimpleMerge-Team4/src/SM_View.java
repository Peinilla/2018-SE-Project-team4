import java.awt.event.ActionListener;
import javax.swing.*;

public class SM_View extends JFrame {
	// UI ����
	View_mainFrame vmf = new View_mainFrame();
	
	public SM_View (){
		//UI set
	}
	
	public void setUIText(String text, boolean isTwo) {
		vmf.setUIText(text, isTwo);
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
}
