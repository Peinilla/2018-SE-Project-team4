package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class SM_ModelTest {
	
	
	/**
	@Test
	final void testSM_Model() {
	
	}
	*/
	
	
	@Test
	void testOpenText() {
		List<String> L_str = new ArrayList<String>();
		List<String> R_str = new ArrayList<String>();
		
		code.SM_Model model = new code.SM_Model();
		
		try {
			model.openText("./L_str.txt", true); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			model.openText("./R_str.txt", false);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		L_str.add("L-line1");R_str.add("R-line1");
		L_str.add("L-line2");R_str.add("R-line2");
		L_str.add("L-line3");R_str.add("R-line3");
		
		assertEquals(L_str.get(0), model.L_str.get(0));
		assertEquals(L_str.get(1), model.L_str.get(1));
		assertEquals(L_str.get(2), model.L_str.get(2));
		
		assertEquals(R_str.get(0), model.R_str.get(0));
		assertEquals(R_str.get(1), model.R_str.get(1));
		assertEquals(R_str.get(2), model.R_str.get(2));
		
	
	}
	
	
	
	@Test
	void testSaveText() {
		
		List<String> L_str = new ArrayList<String>();
		List<String> R_str = new ArrayList<String>();
		code.SM_Model model = new code.SM_Model();
	
		L_str.add("L-line1");R_str.add("R-line1");
		L_str.add("L-line2");R_str.add("R-line2");
		L_str.add("L-line3");R_str.add("R-line3");
		L_str.add("L-line4");R_str.add("R-line4");
		
		/** Start : L_str Save test */
		model.setText(model.ListToString(L_str), true);
		
		try {
			model.saveText("./L_str_new.txt", true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			model.openText("./L_str_new.txt", true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(L_str.get(0), model.L_str.get(0));
		assertEquals(L_str.get(1), model.L_str.get(1));
		assertEquals(L_str.get(2), model.L_str.get(2));
		/** End : L_str Save test */
		
		
		/** Start : R_str Save test */
		model.setText(model.ListToString(R_str), false);

		try {
			model.saveText("./R_str_new.txt", false);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			model.openText("./R_str_new.txt", false);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(R_str.get(0), model.R_str.get(0));
		assertEquals(R_str.get(1), model.R_str.get(1));
		assertEquals(R_str.get(2), model.R_str.get(2));
		/** End : R_str Save test */
		
		
		
	}
	
	
	/**
	@Test
	void testSetText() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAll() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllLineNum() {
		fail("Not yet implemented");
	}

	@Test
	void testListToString() {
		fail("Not yet implemented");
	}

	@Test
	void testListToStringLineNum() {
		fail("Not yet implemented");
	}

	@Test
	void testStringToList() {
		fail("Not yet implemented");
	}

	@Test
	void testGetDiffView_Blank() {
		fail("Not yet implemented");
	}

	@Test
	void testSetLineNum() {
		fail("Not yet implemented");
	}

	@Test
	void testDelLineNum() {
		fail("Not yet implemented");
	}

	@Test
	void testMerge() {
		fail("Not yet implemented");
	}
	*/
}
