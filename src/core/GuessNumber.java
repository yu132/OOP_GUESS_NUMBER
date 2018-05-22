package core;

import java.util.Arrays;

import core.util.RandomUniqueNumber;

/**
 * 猜数字游戏的内部核心逻辑
 * 
 * @author 87663
 *
 */
public class GuessNumber {
	
	private int[] numbers=new int[4];//要猜的数字的顺序和大小
	
	private int[] sortedNumbers=new int[4];//排序过后的数字的大小
	
	private int guessTime=0;//猜的次数
	
	private RandomUniqueNumber r=new RandomUniqueNumber(0, 9);//随机生成不重复的数字

	/**
	 * 开始游戏，随机生成4个数字
	 */
	public void startGame(){
		
		guessTime=0;//猜的次数
		
		r.reSet();//重置随机数生成器
		
		for(int i=0;i<4;i++){//随机产生4个数字
			numbers[i]=r.getNum();
			sortedNumbers[i]=numbers[i];
		}
		
		Arrays.sort(sortedNumbers);//将排序数组中的数字排序
		
	}
	
	/**
	 * 猜数字的方法
	 * 
	 * @param guess 猜的4个数字
	 * 
	 * @return 是否猜对的描述
	 */
	public String guessNumber(int[] guess){
		if(guess.length!=4)//如果猜的数字不是4个，就返回非法
			return "ILLEGAL";
		
		guessTime++;//猜的次数加一
		
		int count=0;//表示猜对位置的数量
		int countx=0;//表示位置不对但是数字是对的数量
		
		for(int i=0;i<4;i++){//按位置比较输入的数字是否位置和数字都对
			if(guess[i]==numbers[i]){
				count++;
			}
		}
		
		Arrays.sort(guess);//把猜的数字排序
		
		for(int i=0,j=0;i<4&&j<4;){//比较两个数组中是否有相同的数字
			if(guess[i]==sortedNumbers[j]){//如果都猜对了，那就位置都加一
				countx++;//猜对数字加一
				i++;
				j++;
			}else if(guess[i]<sortedNumbers[j])//如果猜的数字比较小，猜的数字的位置加一
				i++;
			else//guess[i]>sortedNumbers[j]，反过来，排序数组的位置加一
				j++;
		}

		return count+"A"+(countx-count)+"B";//返回猜对位置的数字的数量，和猜对数字但是位置不对的数字的数量
	}
	
	/**
	 * 返回猜的次数
	 * 
	 * @return 猜的次数
	 */
	public int getGuessTimes(){
		return guessTime;
	}
	
}
