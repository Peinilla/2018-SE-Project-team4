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
	
	List<String> L_str;
	List<String> R_str; 
	//FileReader 대신 한줄의 스트링 리스트를 저장
	
	Model_diff _model_diff;
	
	public SM_Model(){
		L_str = new ArrayList<String>();
		R_str = new ArrayList<String>();
		
		init();
	}
	
	public void init() {
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
	
	public void setAll(FileReader rd, boolean isLeft){
		//전부 set
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
	
	public void merge_LtoR() {
		int[] L_Line = getDiffView_Blank(LEFT);
		int[] R_Line = getDiffView_Blank(RIGHT);
		int inx = 0;
		int jnx = 0;
		
		int L_index = 0;
		int R_index = 0;
		
		boolean isLeftLarge = L_Line.length > R_Line.length;
		
		int minLength = Math.min(L_Line.length, R_Line.length);
		
		for(inx = 0; inx < minLength; inx++) {
			if(L_Line[inx] != 1) { // if L is not 1, also R is not 1
				if(L_Line[inx] == 2) { 
					// L == 2 , R == 0
					R_str.remove(R_index);
				}else if(L_Line[inx] == 0) {
					if(R_Line[inx] ==2) {
						// L == 0 , R == 2
						R_str.add(R_index, L_str.get(L_index));
					}else {
						// L == 0 , R == 0
						R_str.set(R_index, L_str.get(L_index));
					}
				}else {
				}
				return;
			}else {
				if(L_Line[inx] != 2) {
					L_index ++;
				}
				if(R_Line[inx] != 2) {
					R_index ++;
				}
			}
		} // check until min Length
		
		if(isLeftLarge) { // L string is Large, add to Right
			R_str.add(R_index, L_str.get(L_index));
		}
		else if(L_Line.length == R_Line.length){
		}else { // L string is short, remove Rigth str
			R_str.remove(R_index);
		}
	}

	public void merge_RtoL() {
		int[] L_Line = getDiffView_Blank(LEFT);
		int[] R_Line = getDiffView_Blank(RIGHT);
		int inx = 0;
		int jnx = 0;
		
		int L_index = 0;
		int R_index = 0;
		
		boolean isLeftLarge = L_Line.length > R_Line.length;
		
		int minLength = Math.min(L_Line.length, R_Line.length);
		
		for(inx = 0; inx < minLength; inx++) {
			if(L_Line[inx] != 1) { // if L is not 1, also R is not 1
				if(L_Line[inx] == 2) { 
					// L == 2 , R == 0
					L_str.add(L_index, R_str.get(R_index));	
				}else if(L_Line[inx] == 0) {
					if(R_Line[inx] ==2) {
						// L == 0 , R == 2
						L_str.remove(L_index);
					}else {
						// L == 0 , R == 0
						L_str.set(L_index, R_str.get(R_index));
					}
				}else {
				}
				return;
			}else {
				if(L_Line[inx] != 2) {
					L_index ++;
				}
				if(R_Line[inx] != 2) {
					R_index ++;
				}
			}
		} // check until min Length
		
		if(isLeftLarge) { // L string is Large, remove Left str
			L_str.remove(L_index);
		}
		else if(L_Line.length == R_Line.length){
		}else { // L string is short, str to Left
			L_str.add(L_index, R_str.get(R_index));	
		}
	}
}
