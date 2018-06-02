import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class SM_Controller implements ActionListener {
	private SM_Model model;
	private View_mainFrame view;
	private boolean isEdit;
	
	public SM_Controller(SM_Model _model,View_mainFrame _view){
		model = _model;
		view = _view;
		_view.addListnerController(this);
	}
	
	private void getDiff(){
		
		if(model.getDiffView(true).length > model.getDiffView(false).length) {
			view.diffView(model.getDiffView(true));
		} else {
			view.diffView(model.getDiffView(false));
		}
	}
	
	private void merge(boolean isLeft){ //isLeft�� Ʈ��� �ι�°(������)�ؽ�Ʈ�� �ǹ���
		
	}
	
	private void save(boolean isLeft){
		String str = view.getFileSave();
		
		view.setUIText(model.getAll(isLeft), isLeft);

		try {
			if(!str.equals(null)) {
				model.saveText(str, isLeft);
			}else {
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void load(boolean isLeft){
		
		String str = view.getFileOpen();
		
		view.setUIText(model.getAll(isLeft), isLeft);

		try {
			if(!str.equals(null)) {
				model.openText(str, isLeft);
			}else {
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		updateText(isLeft);
	}
	
	private void edit(boolean isLeft){
		view.setUIText(model.getAll(isLeft), isLeft);
		view.setEdit(isLeft);
		model.setText(view.getUIText(isLeft), isLeft);
	}
	
	private void updateText(boolean isLeft) {
		view.setUIText(model.getAll(isLeft), isLeft);
	}
	
	public void actionPerformed(ActionEvent e){
		switch (e.getActionCommand()) {
		case "Right_Load" :
			load(false);
			break;
		case "Right_Save" :
			save(false);
			break;
		case "Right_Edit" :
			edit(false);
			break;
		case "Left_Load" :
			load(true);
			break;
		case "Left_Save" :
			save(true);
			break;
		case "Left_Edit" :
			edit(true);
			break;
		case "Diff" :
			getDiff();
			break;
		case "Merge" :
			break;
		default:
			break;
		}
	}

}
