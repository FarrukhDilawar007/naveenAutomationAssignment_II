package utils;

import org.testng.annotations.DataProvider;

public class dataProvider {
	
	@DataProvider
    public Object[][] getData() {
        
		
		Object[][] param = new Object[10][2];
		
		param[0][0] = 1;
		param[0][1] = 5;
		
		param[1][0] = 3;
		param[1][1] = 8;
		
		param[2][0] = 5;
		param[2][1] = 8;
		
		param[3][0] = 6;
		param[3][1] = 9;
		
		param[4][0] = 1;
		param[4][1] = 3;
		
		param[5][0] = 4;
		param[5][1] = 9;
		
		param[6][0] = 6;
		param[6][1] = 1;
		
		param[7][0] = 7;
		param[7][1] = 2;
		
		param[8][0] = 2;
		param[8][1] = 3;
		
		param[9][0] = 4;
		param[9][1] = 7;
		
		
		return param;
		
				
    }

}
