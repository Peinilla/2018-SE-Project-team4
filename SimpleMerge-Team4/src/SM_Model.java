import java.io.*;

public class SM_Model {
	private FileReader reader1; //���ϸ���. �ٸ��� ���ϸ� �ٲ㵵 �ɵ�
	private FileReader reader2;
	
	public SM_Model(){
	}
	
	public boolean openText(String path1,String path2) throws FileNotFoundException{
		reader1 = new FileReader(path1);
		reader2 = new FileReader(path2);
		return false;
	}
	
	public boolean saveText(String path1,String path2) throws FileNotFoundException{
		reader1 = new FileReader(path1);
		reader2 = new FileReader(path2);
		return false;
	}
	
	public FileReader getAll(boolean isTwo){
		if(isTwo){ return reader2; }
		return reader1;
	}
	public void setAll(FileReader rd, boolean isTwo){
		//���� set
	}
	
	
}
