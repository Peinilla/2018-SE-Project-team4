import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class SM_Model {
	
	List<String> L_str;
	List<String> R_str; 
	//FileReader 대신 한줄의 스트링 리스트를 저장
	
	Model_diff _model_diff;
	
	public SM_Model(){
		L_str = new ArrayList<String>();
		R_str = new ArrayList<String>();
	}
	
	public void openText(String path, boolean isLeft) throws FileNotFoundException{
		try {
			if(isLeft) {
				L_str = Files.readAllLines(Paths.get(path));
			}else {
				R_str = Files.readAllLines(Paths.get(path));
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void saveText(String path, boolean isLeft) throws FileNotFoundException{
		try {
			if(isLeft) {
				Files.write(Paths.get(path), L_str, Charset.defaultCharset());
			}else {
				Files.write(Paths.get(path), R_str, Charset.defaultCharset());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setText(String str, boolean isLeft) {
		if(isLeft) {
			L_str.clear();
			L_str.addAll(StringToList(str));
		}else {
			R_str.clear();
			R_str.addAll(StringToList(str));
		}
	}
	
	public String getAll(boolean isLeft){
		if(isLeft) {
			return ListToString(L_str);
		}else {
			return ListToString(R_str);
		}
	}
	
	public String getAllLineNum(boolean isLeft){
		if(isLeft) {
			return ListToStringLineNum(L_str);
		}else {
			return ListToStringLineNum(R_str);
		}
	}
	
	public void setAll(FileReader rd, boolean isLeft){
		//전부 set
	}

	public String ListToString(List<String> str) {
		String result = new String();
		
		for(int inx = 0; inx < str.size(); inx++) {
			result += str.get(inx) +"\n";
		}
		
		return result;
	}
	
	public String ListToStringLineNum(List<String> str) {
		List<String> buf = new ArrayList<String>();
		String result = new String();
		
		for(int inx = 0;inx < str.size(); inx++) {
			buf.add((inx+1) + " : " + str.get(inx));
		}
		
		for(int inx = 0; inx < str.size(); inx++) {
			result += buf.get(inx) +"\n";
		}
		
		return result;
	}
	
	public List<String> StringToList(String str){
		List<String> buf = new ArrayList<String>();
		buf = Arrays.asList(str.split("\n"));
	
		return buf;
	}
	
	public int[] getDiffView_Blank(boolean isLeft){
		int[] result;
		ArrayList<ArrayList<Integer>> buf = new ArrayList<ArrayList<Integer>>();
		int inx = 0;
		
		_model_diff = new Model_diff(L_str, R_str);
		buf = _model_diff.getDiffView_Blank();
		
		if(!isLeft) {
			inx = 1;
		}
		
		result = new int[buf.get(inx).size()];
		for(int jnx = 0; jnx < buf.get(inx).size(); jnx++) {
			result[jnx] = buf.get(inx).get(jnx);
		}
		
		return result;
	}
	
	public String setLineNum(String str) {
		int inx = 1;
		
		return 	str.replaceAll("\n", "\n" + inx++ + ":");
	}
	
	public void delLineNum(String str) {
		
	}
}
