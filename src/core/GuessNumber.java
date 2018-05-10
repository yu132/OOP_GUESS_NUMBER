package core;

import java.util.Arrays;

import core.util.RandomUniqueNumber;

public class GuessNumber {
	
	private int[] numbers=new int[4];
	
	private int[] sortedNumbers=new int[4];
	
	private int guessTime=0;
	
	private RandomUniqueNumber r=new RandomUniqueNumber(0, 9);

	public void startGame(){
		guessTime=0;
		r.reSet();
		for(int i=0;i<4;i++){
			numbers[i]=r.getNum();
			sortedNumbers[i]=numbers[i];
		}
		Arrays.sort(sortedNumbers);
	}
	
	public String guessNumber(int[] guess){
		if(guess.length!=4)
			return "ILLEGAL";
		
		guessTime++;
		
		/*for(int i=0;i<4;i++){
			System.out.println(guess[i]+" "+numbers[i]);
		}*/
		
		int count=0;
		int countx=0;
		for(int i=0;i<4;i++){
			if(guess[i]==numbers[i]){
				count++;
			}
		}
		
		Arrays.sort(guess);
		for(int i=0,j=0;i<4&&j<4;){
			if(guess[i]==sortedNumbers[j]){
				countx++;
				i++;
				j++;
			}else if(guess[i]<sortedNumbers[j])
				i++;
			else//guess[i]>sortedNumbers[j]
				j++;
		}
		if(countx==0)
			return "";
		if(count==0)
			return countx+"B";
		return count+"A"+(countx-count)+"B";
	}
	
	public int getGuessTimes(){
		return guessTime;
	}
	
}
