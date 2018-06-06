package SimpleMerge;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class SM_Model {
	final static boolean LEFT = true;
	final static boolean RIGHT = false;
	
	public List<String> L_str;
	public List<String> R_str; 
	//FileReader 대신 한줄의 스트링 리스트를 저장
	
	SM_Controller_LCS _ctrl_dfiff;
	
	public SM_Model(){
		L_str = new ArrayList<String>();
		R_str = new ArrayList<String>();
		L_str.add("");
		R_str.add("");
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

	public String ListToString(List<String> str) {
		String result = new String();
		
		for(int inx = 0; inx < str.size(); inx++) {
			result +=  "\n" + str.get(inx);
		}

		result = result.replaceFirst("\n", "");
		
		return result;
	}
	
	public String ListToStringLineNum(List<String> str) {
		List<String> buf = new ArrayList<String>();
		String result = new String();
		
		for(int inx = 0;inx < str.size(); inx++) {
			buf.add((inx+1) + " : " + str.get(inx));
		}
		
		for(int inx = 0; inx < str.size(); inx++) {
			result += "\n" + buf.get(inx);
		}
		
		result = result.replaceFirst("\n", "");
		
		return result;
	}
	
	public List<String> StringToList(String str){
		List<String> buf = new ArrayList<String>();
		buf = Arrays.asList(str.split("\n"));
	
		return buf;
	}
	
	public String setLineNum(String str) {
		int inx = 1;
		
		return 	str.replaceAll("\n", "\n" + inx++ + ":");
	}
	
	public void delLineNum(String str) {
		
	}
	
	public void merge(boolean direct) {
		
	}
}