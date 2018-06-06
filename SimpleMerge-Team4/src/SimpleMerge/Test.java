package SimpleMerge;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SM_Controller controll;
		SM_Model model;
		SM_View view;
		model = new SM_Model();
		view = new SM_View();
		controll = new SM_Controller(model, view);
	}
}
