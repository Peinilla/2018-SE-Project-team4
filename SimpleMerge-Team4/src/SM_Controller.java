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
		
		updateTextLineNum();
	}
	
	private void getDiff(){
		updateTextLineNum();

		view.diffView(true, model.getDiffView_Blank(true));
		view.diffView(false, model.getDiffView_Blank(false));
	}
	
	private void merge(boolean toLeft){ //isLeft가 트루면 두번째(오른쪽)텍스트를 의미함
		
		if(toLeft) {
			model.merge_RtoL();
		}else {
			model.merge_LtoR();
		}
		getDiff();

	}
	
	private void save(boolean isLeft){
		String str = view.getFileSave();
		
		updateText(isLeft);

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
		
		updateText(isLeft);

		try {
			if(!str.equals(null)) {
				model.openText(str, isLeft);
			}else {
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		updateTextLineNum(isLeft);
	}
	
	private void edit(boolean isLeft){
		
		//updateTextLineNum(); // set text normal	
		if(view.isEditing(isLeft)) {
			view.setEdit(isLeft);
			model.setText(view.getUIText(isLeft), isLeft);
			updateTextLineNum(isLeft);
			
		}else {
			if(view.isEditing(!isLeft)) {
				updateTextLineNum(isLeft);
			}else {
				updateTextLineNum();
			}
			updateText(isLeft);
			view.setEdit(isLeft);
			model.setText(view.getUIText(isLeft), isLeft);
		}
	}
	
	private void updateText(boolean isLeft) {
		view.setUIText(model.getAll(isLeft), isLeft);
	}

	private void updateText() {
		view.setUIText(model.getAll(true), true);
		view.setUIText(model.getAll(false), false);
	}
	
	private void updateTextLineNum(boolean isLeft) {
		view.setUIText(model.getAllLineNum(isLeft), isLeft);
	}
	
	private void updateTextLineNum() {
		view.setUIText(model.getAllLineNum(true), true);
		view.setUIText(model.getAllLineNum(false), false);
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
		case "<<<" :
			merge(true);
			break;
		case ">>>" :
			merge(false);
			break;
		default:
			break;
		}
	}

}
