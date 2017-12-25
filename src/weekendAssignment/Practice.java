package weekendAssignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Practice {
	public static void main(String[] args){
		Practice p = new Practice();
		int[] input = {1,2,3,4,5,6,7};
		System.out.println(Arrays.toString(p.kSubString(input, 3)));
		p.printPascal(4);
		int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
		p.spiralOrder(matrix);
		String input1 = "hello";
		System.out.println(p.removeChar(input1, 'l'));
		int[] input2 = {1,2,3,3,3,6,7};
		System.out.println(Arrays.toString(p.positions(input2, 3)));
		p.generateSpiral(4);
		int[] input3 = {3,3,2,1};
		p.permuteArray(input3);
	}
	// Question 1
	public int[] kSubString(int[] input,int k){
		int j = 0;
		int[] array = new int[input.length];
		for(int i = 0; i < k; i++){
			array[i] = input[input.length - k + i];
		}
		for(int i = k; i < array.length; i++){
			array[i] = input[j];
			j++;
		}
		return array;
	}
	//Question 2
	public void printPascal(int row){
		for(int i = 0; i < row ; i++)
		{
			for(int j = row; j > i; j--)
			{
				System.out.print(" ");
			}
            int number = 1;
			for(int j = 0; j <= i; j++)
			{
				 System.out.print(number+ " ");
                 number = number * (i - j) / (j + 1);
			}
			System.out.println();
		}
	}
	//Question 3
	public void spiralOrder(int[][] matrix) {      
        if (matrix.length == 0) {
            return;
        } 
        int rowBegin = 0;
        int rowEnd = matrix.length-1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;
        
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // Traverse Right
            for (int j = colBegin; j <= colEnd; j ++) {
                System.out.print(matrix[rowBegin][j] + " ");
            }
            rowBegin++;
            
            // Traverse Down
            for (int j = rowBegin; j <= rowEnd; j ++) {
            	 System.out.print(matrix[j][colEnd] + " ");
            }
            colEnd--;
            
            if (rowBegin <= rowEnd) {
                // Traverse Left
                for (int j = colEnd; j >= colBegin; j --) {
                 System.out.print(matrix[rowEnd][j] + " ");
                }
            }
            rowEnd--;
            
            if (colBegin <= colEnd) {
                // Traverse Up
                for (int j = rowEnd; j >= rowBegin; j --) {
                System.out.print(matrix[j][colBegin] + " ");
                }
            }
            colBegin ++;
        }
        
        return;
    }
	//Question 4
	public String removeChar(String input, Character c){
		if (input == null || input.length() <= 1)
            return input;
        char[] inputArray = input.toCharArray();
        char[] outputArray = new char[inputArray.length];
        int outputArrayIndex = 0;
        for (int i = 0; i < inputArray.length; i++) {
            char p = inputArray[i];
            if (p != c) {
                outputArray[outputArrayIndex] = p;
                outputArrayIndex++;
            }

        }
        return new String(outputArray, 0, outputArrayIndex);
	}
	//Question 5
	public int[] positions(int[] array, int target){
		int[] result = new int[2];
		for(int i = 0; i < array.length; i++){
			if(array[i] < target){
				continue;
			}
			else{
				result[0] = i;
				break;
			}
		}
		for(int i = 0; i < array.length; i++){
			if(array[i] > target){
				result[1] = i - 1;
				break;
			}
		}
			return result;
		
	}
	public List<Interval> mergeInterval(List<Interval> intervals) {
		// sort start&end
		int n = intervals.size();
		int[] starts = new int[n];
		int[] ends = new int[n];
		for (int i = 0; i < n; i++) {
			starts[i] = intervals.get(i).start;
			ends[i] = intervals.get(i).end;
		}
		Arrays.sort(starts);
		Arrays.sort(ends);
		// loop through
		List<Interval> res = new ArrayList<Interval>();
		for (int i = 0, j = 0; i < n; i++) { // j is start of interval.
			if (i == n - 1 || starts[i + 1] > ends[i]) {
				res.add(new Interval(starts[j], ends[i]));
				j = i + 1;
			}
		}
		return res;
	}
	public List<Interval> insertInterval(List<Interval> intervals, Interval newInterval) {
	    List<Interval> result = new LinkedList<>();
	    int i = 0;
	    // add all the intervals ending before newInterval starts
	    while (i < intervals.size() && intervals.get(i).end < newInterval.start)
	        result.add(intervals.get(i++));
	    // merge all overlapping intervals to one considering newInterval
	    while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
	        newInterval = new Interval( // we could mutate newInterval here also
	                Math.min(newInterval.start, intervals.get(i).start),
	                Math.max(newInterval.end, intervals.get(i).end));
	        i++;
	    }
	    result.add(newInterval); // add the union of intervals we got
	    // add all the rest
	    while (i < intervals.size()) result.add(intervals.get(i++)); 
	    return result;
	}
	public void generateSpiral(int n){
		int[][] spiral = new int[n][n];
        
        int value = 1;
         
        int minCol = 0;
         
        int maxCol = n-1;
         
        int minRow = 0;
         
        int maxRow = n-1;
         
        while (value <= n*n)
        {
            for (int i = minCol; i <= maxCol; i++)
            {
                spiral[minRow][i] = value;
                     
                value++;
            }
             
            for (int i = minRow+1; i <= maxRow; i++) 
            { 
                spiral[i][maxCol] = value; 
                 
                value++; 
            } 
             
            for (int i = maxCol-1; i >= minCol; i--)
            {
                spiral[maxRow][i] = value;
                             
                value++;
            }
             
            for (int i = maxRow-1; i >= minRow+1; i--) 
            {
                spiral[i][minCol] = value;
                 
                value++;
            }
             
            minCol++;
             
            minRow++;
             
            maxCol--;
             
            maxRow--;
        }
         
        for (int i = 0; i < spiral.length; i++)
        {
            for (int j = 0; j < spiral.length; j++)
            {
                System.out.print(spiral[i][j]+ "\t");
            }
             
            System.out.println();
        }
	}
	public void permuteArray(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		permute(num, 0, result);
		return;
	}
	 
	void permute(int[] num, int start, ArrayList<ArrayList<Integer>> result) {
	 
		if (start >= num.length) {
			ArrayList<Integer> item = convertArrayToList(num);
			System.out.println(Arrays.toString(item.toArray()));;
		}
	 
		for (int j = start; j <= num.length - 1; j++) {
			swap(num, start, j);
			permute(num, start + 1, result);
			swap(num, start, j);
		}
	}
	static Set<String> palindrome(String str) {
	    String[] strArray = str.split("");
	    List<String> list = Arrays.asList(strArray);
	    list = list.subList(1, list.size());
	    //Set does'nt allow duplicates.
	    //Sublist is required because split method gives an extra space.
	    Set<String> palindromeSet = new HashSet<>(list);
	    String palindromeStr = null;
	    for(int i = 0;i<list.size();i++){
	        palindromeStr = list.get(i);
	        for(int j = i+1;j<list.size();j++){
	            palindromeStr = palindromeStr+list.get(j);
	            if(isPalindrome(palindromeStr)){
	                palindromeSet.add(palindromeStr);
	            }
	        }
	    }
	    return palindromeSet;
	}

	static boolean isPalindrome(String str){
	    char[] chars = str.toCharArray();
	    for(int i =0;i<(chars.length/2);i++){
	        if(chars[i] != chars[chars.length-1-i]){
	            return false;
	        }
	    }
	    return true;
	}
	private ArrayList<Integer> convertArrayToList(int[] num) {
		ArrayList<Integer> item = new ArrayList<Integer>();
		for (int h = 0; h < num.length; h++) {
			item.add(num[h]);
		}
		return item;
	}
	 
	private void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	public class Interval {
		      int start;
		      int end;
		      Interval() { start = 0; end = 0; }
		      Interval(int s, int e) { start = s; end = e; }
		  }
}
