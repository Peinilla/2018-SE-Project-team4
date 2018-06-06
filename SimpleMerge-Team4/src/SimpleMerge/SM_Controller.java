package SimpleMerge;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SM_Controller implements ActionListener {
	final static boolean LEFT = true;
	final static boolean RIGHT = false;
	
	private SM_Model model;
	private SM_View view;
	private SM_Controller_LCS ctrlDiff;

	public SM_Controller(SM_Model _model,SM_View _view){
		model = _model;
		view = _view;
		_view.addListnerController(this);
		ctrlDiff = new SM_Controller_LCS(model.L_str, model.R_str);
		
		updateTextAll();
	}
	
	private void diff(){
		updateTextLineNumAll();
		
		view.diffView(LEFT, getDiffView(LEFT));
		view.diffView(RIGHT, getDiffView(RIGHT));
	}
	
	private int[] getDiffView(boolean isLeft) {
		int[] result;
		
		int jnx = 0;
		if(!isLeft) {
			jnx = 1;
		}

		ctrlDiff = new SM_Controller_LCS(model.L_str, model.R_str);
		ArrayList<ArrayList<Integer>> buf = new ArrayList<ArrayList<Integer>>();
		buf = ctrlDiff.getDiffView_Blank();
	
		result = new int[buf.get(jnx).size()];

		for(int inx = 0; inx < buf.get(jnx).size(); inx++) {
			result[inx] = buf.get(jnx).get(inx);
		}
		
		return result;
	}
	
	private void merge(boolean toLeft){ 
		ArrayList<String> origin_str = new ArrayList<String>();
		ArrayList<String> target_str = new ArrayList<String>();
	
		if(toLeft) {
			origin_str = (ArrayList<String>) model.R_str;
			target_str = (ArrayList<String>) model.L_str;
		}else {
			origin_str = (ArrayList<String>) model.L_str;
			target_str = (ArrayList<String>) model.R_str;
		}
		
		int[] origin = getDiffView(!toLeft);
		int[] target = getDiffView(toLeft);
		
		int inx = 0;
		
		int target_index = 0;
		int origin_index = 0;
		
		boolean isLeftLarge = target.length > origin.length;
		
		int minLength = Math.min(target.length, origin.length);
		
		for(inx = 0; inx < minLength; inx++) {
			if(target[inx] != 1) { // if target is not 1, also origin is not 1
				if(target[inx] == 2) { 
					// target == 2 , origin == 0
					target_str.add(target_index, origin_str.get(origin_index));	
				}else if(target[inx] == 0) {
					if(origin[inx] ==2) {
						// target == 0 , origin == 2
						target_str.remove(target_index);
					}else {
						// target == 0 , origin == 0
						target_str.set(target_index, origin_str.get(origin_index));
					}
				}else {
				}
				return;
			}else {
				if(target[inx] != 2) {
					target_index ++;
				}
				if(origin[inx] != 2) {
					origin_index ++;
				}
			}
		} // check until min Length
		
		if(isLeftLarge) { // target string is Large, remove target str
			target_str.remove(target_index);
		}
		else if(target.length == origin.length){
		}else { // target string is short, str to target
			target_str.add(target_index, origin_str.get(origin_index));	
		}
	
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
