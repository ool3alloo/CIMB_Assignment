package main;

public class CIMBApplication {

	// CompareVersion Contants
	public static int SAME_VERSION = 0;
	public static int NEW_VERSION = 1;
	public static int OLD_VERSION = -1;
	
	public static void main(String args[])
    {
		// Test CalculateMaxProfit
//		int prices[] = {7, 1, 5, 3, 6, 4};
//		int prices[] = {7, 6, 4, 3, 1};
//		int result = CalculateMaxProfit(prices);		
		
		// Test CompareVersion
//		String version1 = "1.01";
//		String version2 = "1.001";
		String version1 = "1.0";
		String version2 = "1.0.0";
//		String version1 = "0.1";
//		String version2 = "1.1";
		int result = CompareVersion(version1, version2);	
		
		// Test ClimbingStairCase
//		int n = 2;
//		int n = 45;
//		long result = ClimbingStairCase(n);				
		
        System.out.println(result);
    }
	
	/*
	 * Calculate Maximum Profit
	 * @author Nattawut S.
	 * @since 2024/03/25
	 * @param prices[]
	 * @return max_profit
	 */
	public static int CalculateMaxProfit(int prices[]) {
		int min_value = prices[0];
		int max_value = 0;
		int max_profit = 0;
		
		for(int i=1; i < prices.length; i++) {

			if(prices[i] < min_value) {
				min_value = prices[i];
				max_value = prices[i];
			}

			if(prices[i] > max_value) {
				max_value = prices[i];
				if(max_value - min_value > max_profit) {
					max_profit = max_value - min_value;
				}
			}
		}		
		return max_profit;
	}
	
	/*
	 * Climbing a staircase
	 * @author Nattawut S.
	 * @since 2024/03/25
	 * @param Number of Staircase
	 * @return Count all Climbing Method 
	 */
	public static long ClimbingStairCase(int staircase_number) {
		
			long numerator = 0;
			long denumerator = 0;
			int single_step = staircase_number - 2;
			int double_step = 1;
			long count_method = 0;
			int max_double_step = staircase_number/2;
			
			int case_double_step_zero = 1; // EX. staircase 4 : [1,1,1,1] 
			int case_double_step_one = single_step+double_step; // EX. staircase 4 : [2,1,1] 
			int case_double_step_max;

			if (staircase_number%2 == 0) { 
				case_double_step_max = 1; // EX. staircase 4 : [2, 2]
			}
			else { 
				case_double_step_max = max_double_step+1; // EX. staircase 5 : [2, 2, 1]
			}			
			
			for (int i=1 ; i<(max_double_step - 1) ; i++) {			
				single_step = single_step - 2;
				double_step = double_step + 1;

				if (single_step < double_step) {					
					numerator = CalculateNumerator(single_step + double_step, single_step);
					denumerator = CalculateDenumerator(single_step);
				}
				else {
					numerator = CalculateNumerator(single_step + double_step, double_step);
					denumerator = CalculateDenumerator(double_step);
				}				
				count_method = count_method + (numerator/denumerator);
			}

			if (staircase_number == 2) { // case staircase 2 have 2 case [1, 1] and [2]
				count_method = count_method + case_double_step_zero +  case_double_step_max;
			}
			else {
				count_method = count_method + case_double_step_zero + case_double_step_one +  case_double_step_max;
			}
			
		return count_method;
	}
	
	/*
	 * Calculate Numerator
	 * @author Nattawut S.
	 * @since 2024/03/25
	 * @param Total Step
	 * @param Least Duplicate Step
	 * @return Numerator 
	 */
	public static long CalculateNumerator(int total_step, int least_step) {		
		long numerator = 1;

		for(int i=0; i< least_step ; i++) {
			numerator = numerator* (total_step-i);
		}

		return numerator;
	}
	
	/*
	 * Calculate Denumerator
	 * @author Nattawut S.
	 * @since 2024/03/25
	 * @param Least Duplicate Step
	 * @return Denumerator
	 */
	public static long CalculateDenumerator(int least_step) {
		long denumerator = 1;

		for(int i=1 ; i<= least_step ; i++) {
			denumerator = denumerator*i;
		}

		return denumerator;
	}
	
	/*
	 * Compare Version
	 * @author Nattawut S.
	 * @since 2024/03/25
	 * @param version1
	 * @param version2
	 * @return NEW_VERSION when version > version2
	 * @return OLD_VERSION when version < version2
	 * @return SAME_VERSION when version == version2
	 */
	public static int CompareVersion(String version1, String version2) {
		String[] split_v1 = version1.split("\\.");
		String[] split_v2 = version2.split("\\.");
		int lenght1 = split_v1.length;
		int lenght2 = split_v2.length;
		int max_lenght = 0;
		int result = 0;
		boolean exit_flag = false;
		
		if (lenght1 > lenght2) {
			max_lenght = lenght1;
		}
		else {
			max_lenght = lenght2;
		}
		
		for (int i=0 ; i<max_lenght ; i++) {
			String temp1 = (i < lenght1) ? split_v1[i] : "0";
			String temp2 = (i < lenght2) ? split_v2[i] : "0";
			
			// remove leading zero
			temp1 = RemoveLeadingZeros(temp1);
			temp2 = RemoveLeadingZeros(temp2);
			
			// check digit
			if (temp1.length() > temp2.length()) {
				result = NEW_VERSION;
				break;
			}
			else if (temp1.length() < temp2.length()) {
				result = OLD_VERSION;
				break;
			}
			else {
				for (int j=0 ; j<temp1.length() ; j++) {
					if (temp1.charAt(j) > temp2.charAt(j)) {
						result = NEW_VERSION;
						exit_flag = true;
						break;
					}
					else if (temp1.charAt(j) < temp2.charAt(j)) {
						result = OLD_VERSION;
						exit_flag = true;
						break;
					}
				}
				if (exit_flag) {
					break;
				}
			}
		}
		return result;
	}
	
	/*
	 * Remove Leading Zeros
	 * @author Nattawut S.
	 * @since 2024/03/25
	 * @param Numeric String
	 * @return Numeric String Without Leading Zeros
	 */
	public static String RemoveLeadingZeros(String num)
	{
		// no non-zero character, return "0"
		String res = "0"; 
	    
	    for (int i = 0; i < num.length(); i++) {	

	        // check for the first non-zero character
	        if (num.charAt(i) != '0') {

	            // return the remaining string
	            res = num.substring(i);
	            break;
	        }
	    }
	    return res;
	}
	
}
