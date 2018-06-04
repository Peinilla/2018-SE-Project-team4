
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SM_Controller controll;
		SM_Model model;
		View_mainFrame view;
		model = new SM_Model();
		view = new View_mainFrame();
		controll = new SM_Controller(model, view);
	}
}
