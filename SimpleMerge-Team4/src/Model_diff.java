import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Model_diff {

	ArrayList<String> L_str = new ArrayList<String>();
	ArrayList<String> R_str = new ArrayList<String>();

	int[][] LCS;
	int[][] diff;
	
	public Model_diff(List<String> l_str2, List<String> r_str2) {
		L_str.addAll(l_str2);
		R_str.addAll(r_str2);
		
		LCS = new int[L_str.size() + 1][R_str.size() + 1];
		diff = new int[L_str.size() + 1][R_str.size() + 1];
		
		setLCS();
	}

	public void setLCS(){
		for(int inx = 1; inx <= L_str.size(); inx++) {
			String buf1 = L_str.get(inx - 1);
			
			for(int jnx = 1; jnx <= R_str.size(); jnx++) {
				String buf2 = R_str.get(jnx - 1);
				
				if(buf1.equals(buf2)) {
					LCS[inx][jnx] = LCS[inx - 1][jnx - 1] + 1;
					diff[inx][jnx] = 3; // 3 == diagonal
				}else {
					LCS[inx][jnx] = Math.max(LCS[inx - 1][jnx], LCS[inx][jnx - 1]);
					
					if(LCS[inx][jnx] == LCS[inx - 1][jnx]) {
						diff[inx][jnx] = 2; // 2 == top
					}else {
						diff[inx][jnx] = 1; // 1 == left
					}
				}
			}
		}
	}
	
	public ArrayList<String> getDiff(){
		ArrayList<String> diffStr = new ArrayList<String>();
		
		int inx = L_str.size();
		int jnx = R_str.size();
		
		while(diff[inx][jnx] != 0) {
			if(diff[inx][jnx] == 3) {
				diffStr.add(L_str.get(inx - 1));
				inx --;
				jnx --;
			}else if(diff[inx][jnx] == 2) {
				inx --;
			}else if(diff[inx][jnx] == 1) {
				jnx --;
			}
		}
		
		Collections.reverse(diffStr); // diffStr Reverse
		
		return diffStr;
	}
	
	public int[] getDiffView(boolean isLeft) {
		int[] diffView;
		ArrayList<String> diffStr = getDiff();
		ArrayList<String> buf = new ArrayList<String>();
		int jnx = 0;
		
		if(isLeft) {
			diffView = new int[L_str.size()];
			buf.addAll(L_str);
		}else {
			diffView = new int[R_str.size()];
			buf.addAll(R_str);
		}
		for(int inx = 0; inx < diffView.length && jnx < diffStr.size(); inx++) {
			if(buf.get(inx).equals(diffStr.get(jnx))) {
				diffView[inx] = 1;
				jnx++;
			}else {
				diffView[inx] = 0;
			}
			
		}
		
		//assert(diffStr.size() == jnx);
		
		return diffView;
	}
}
