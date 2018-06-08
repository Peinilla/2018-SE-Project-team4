package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import org.junit.Test;

import SimpleMerge.SM_Model;

public class SM_ModelTest {

	final static boolean LEFT = true;
	final static boolean RIGHT = false;
	
	List<String> L_testStr = new ArrayList<String>(); 
	List<String> R_testStr = new ArrayList<String>(); 
	SM_Model model;
	
	public SM_ModelTest(){
		L_testStr.add("line 1"); 
		L_testStr.add("line 2"); 

		R_testStr.add("R-line1"); 
		R_testStr.add("R-line2"); 
		R_testStr.add("R-line3"); 
		R_testStr.add("R-line4");
		
		model = new SM_Model();
		
		model.setText(model.ListToString(L_testStr), LEFT);
		model.setText(model.ListToString(R_testStr), RIGHT);
		
		try {
			Files.write(Paths.get("./test_R.txt"), R_testStr, Charset.defaultCharset());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * create R_str and compare test_R.txt created by constructor
	 */
	@Test
	public void testOpenText() {
		List<String> R_str = new ArrayList<String>();
		
		SM_Model openModel = new SM_Model();
		
		try {
			openModel.openText("./test_R.txt", false);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		R_str.add("R-line1");
		R_str.add("R-line2");
		R_str.add("R-line3");
		R_str.add("R-line4");

		assertTrue(openModel.ListToString(R_str).equals(openModel.getAll(RIGHT)));

		File f = new File("./test_R.txt");
		if(f.exists()) {
			f.deleteOnExit();
		}
	}

	/*
	 * Use saveModel.setText() to change saveModel.L_str to L_saveStr 
	 * Use saveModel.saveText() save in L_str_new.txt
	 * saveModel.openText() and change saveModel.L_str
	 * Compare saveModel.L_str and L_saveStr
	 */
	@Test
	public void testSaveText() {
		List<String> L_saveStr = new ArrayList<String>();
		SM_Model saveModel = new SM_Model();
	
		L_saveStr.add("L-line1");
		L_saveStr.add("L-line2");
		L_saveStr.add("L-line3");
		L_saveStr.add("L-line4");
		
		/** Start : L_str Save test */
		saveModel.setText(saveModel.ListToString(L_saveStr), true);
		
		try {
			saveModel.saveText("./L_str_new.txt", true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			saveModel.openText("./L_str_new.txt", true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(saveModel.ListToString(L_saveStr).equals(saveModel.getAll(LEFT)));
		/** End : L_str Save test */
		
		File f = new File("./L_str_new.txt");
		if(f.exists()) {
			f.deleteOnExit();
		}
		
	}

	/*
	 * Compare model.R_str and L_testStr after model.setText().
	 */
	@Test
	public void testSetText() {
		model.setText(model.ListToString(L_testStr), RIGHT);
		assertTrue(model.getAll(LEFT).equals(model.getAll(RIGHT)));
	}

	/*
	 * Compare model.ListToString() and strTest already created
	 */
	@Test
	public void testListToString() {
		String strTest = "line 1\nline 2";
		String test = model.ListToString(L_testStr);
		
		assertTrue(strTest.equals(test));
	}

	/*
	 * Compare model.ListToStringLineNum() and strTest already created
	 */
	@Test
	public void testListToStringLineNum() {
		String strTest = "1 : line 1\n2 : line 2";
		String test = model.ListToStringLineNum(L_testStr);
		
		assertTrue(strTest.equals(test));
	}

	@Test
	public void testStringToList() {
		String strTest = model.ListToString(L_testStr);
		
		List<String> test = new ArrayList<String>();
		test = model.StringToList(strTest);
		
		assertTrue(test.equals(L_testStr));
	}
}
