package utils;

import org.testng.annotations.DataProvider;

public class dataProvider {
	
	@DataProvider
    public int[][] getData() {
        
		
		int[][] param = new int[2][2];
		
		param[0][0] = 2;
		param[0][1] = 3;
		
		param[1][0] = 4;
		param[1][1] = 7;
		
		return param;
		
				
    }

}
