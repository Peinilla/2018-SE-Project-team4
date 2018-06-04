package src;

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
	
	public void setAll(FileReader rd, boolean isLeft){
		//전부 set
	}

	public String ListToString(List<String> str) {
		String result;
		
		result = str.toString();
		result = result.substring(1, result.length() - 1);
		result = result.replaceAll(", ", "\n");
		
		return result;
	}
	
	public List<String> StringToList(String str){
		List<String> buf = new ArrayList<String>();
		buf = Arrays.asList(str.split("\n"));
		return buf;
	}
	
	public int[] getDiffView(boolean isTwo){
		_model_diff = new Model_diff(L_str, R_str);
		
		return _model_diff.getDiffView(isTwo);
	}
	
}
