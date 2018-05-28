import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SM_Controller implements ActionListener {
	private SM_Model model;
	private SM_View view;
	private boolean isEdit;
	
	public SM_Controller(SM_Model _model,SM_View _view){
		model = _model;
		view = _view;
		_view.addListnerController(this);
	}
	
	private void diff(){
		
	}
	
	private void merge(boolean isTwo){ //isTwo�� Ʈ��� �ι�°(������)�ؽ�Ʈ�� �ǹ���
		
	}
	
	private void save(boolean isTwo){
		if(isTwo) {
			System.out.println("Left_Save : " + view.getFileSave());
		}else{
			System.out.println("Right_Save : " + view.getFileSave());
		}
	}
	
	private void load(boolean isTwo){
		String str = view.getFileOpen();
		try {
			if(!str.equals(null)) {
				model.openText(str, isTwo);
			}else {
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		updateText();
	}
	
	private void edit(){
		
	}
	
	private void updateText() {
		view.setUIText(model.getAll(true), true);
		view.setUIText(model.getAll(false), false);
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println(e.getActionCommand());
		switch (e.getActionCommand()) {
		case "Right_Load" :
			load(false);
			break;
		case "Right_Save" :
			save(false);
			break;
		case "Left_Load" :
			load(true);
			break;
		case "Left_Save" :
			save(true);
			break;
		default:
			break;
		}
	}

}
