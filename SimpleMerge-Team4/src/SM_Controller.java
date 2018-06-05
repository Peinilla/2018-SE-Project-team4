import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SM_Controller implements ActionListener {
	final static boolean LEFT = true;
	final static boolean RIGHT = false;
	
	private SM_Model model;
	private SM_View view;
	
	public SM_Controller(SM_Model _model,SM_View _view){
		model = _model;
		view = _view;
		_view.addListnerController(this);
		
		updateTextAll();
	}
	
	private void diff(){
		updateTextLineNumAll();

		view.diffView(LEFT, model.getDiffView_Blank(LEFT));
		view.diffView(RIGHT, model.getDiffView_Blank(RIGHT));
	}
	
	private void merge(boolean toLeft){ //toLeft가 트루면 두번째(오른쪽)텍스트를 의미함
		

			model.merge(toLeft);
		
		diff();

	}
	
	private void save(boolean isLeft){
		String str = view.getFileSave();
		
		updateText(isLeft);

		try {
			if(str != null) {
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
			if(str != null) {
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
				updateTextLineNumAll();
			}
			updateText(isLeft);
			view.setEdit(isLeft);
			model.setText(view.getUIText(isLeft), isLeft);
		}
	}
	
	private void updateText(boolean isLeft) {
		view.setUIText(model.getAll(isLeft), isLeft);
	}

	private void updateTextAll() {
		view.setUIText(model.getAll(LEFT), LEFT);
		view.setUIText(model.getAll(RIGHT), RIGHT);
	}
	
	private void updateTextLineNum(boolean isLeft) {
		view.setUIText(model.getAllLineNum(isLeft), isLeft);
	}
	
	private void updateTextLineNumAll() {
		view.setUIText(model.getAllLineNum(LEFT), LEFT);
		view.setUIText(model.getAllLineNum(RIGHT), RIGHT);
	}
	
	public void actionPerformed(ActionEvent e){
		switch (e.getActionCommand()) {
		case "Right_Load" :
			load(RIGHT);
			break;
		case "Right_Save" :
			save(RIGHT);
			break;
		case "Right_Edit" :
			edit(RIGHT);
			break;
		case "Left_Load" :
			load(LEFT);
			break;
		case "Left_Save" :
			save(LEFT);
			break;
		case "Left_Edit" :
			edit(LEFT);
			break;
		case "Diff" :
			diff();
			break;
		case "<<<" :
			merge(LEFT);
			break;
		case ">>>" :
			merge(RIGHT);
			break;
		default:
			break;
		}
	}

}
