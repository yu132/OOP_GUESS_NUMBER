package guess_number;

import java.util.Arrays;

//import core.util.RandomUniqueNumber;

/**
 * 鐚滄暟瀛楁父鎴忕殑鍐呴儴鏍稿績閫昏緫
 * 
 * @author 87663
 *
 */
public class GuessNumber {
	
	private int[] numbers=new int[4];//瑕佺寽鐨勬暟瀛楃殑椤哄簭鍜屽ぇ灏�
	
	private int[] sortedNumbers=new int[4];//鎺掑簭杩囧悗鐨勬暟瀛楃殑澶у皬
	
	private int guessTime=0;//鐚滅殑娆℃暟
	
	private RandomUniqueNumber r=new RandomUniqueNumber(0, 9);//闅忔満鐢熸垚涓嶉噸澶嶇殑鏁板瓧

	/**
	 * 寮�濮嬫父鎴忥紝闅忔満鐢熸垚4涓暟瀛�
	 */
	public void startGame(){
		
		guessTime=0;//鐚滅殑娆℃暟
		
		r.reSet();//閲嶇疆闅忔満鏁扮敓鎴愬櫒
		
		for(int i=0;i<4;i++){//闅忔満浜х敓4涓暟瀛�
			numbers[i]=r.getNum();
			sortedNumbers[i]=numbers[i];
		}
		
		Arrays.sort(sortedNumbers);//灏嗘帓搴忔暟缁勪腑鐨勬暟瀛楁帓搴�
		
	}
	
	/**
	 * 鐚滄暟瀛楃殑鏂规硶
	 * 
	 * @param guess 鐚滅殑4涓暟瀛�
	 * 
	 * @return 鏄惁鐚滃鐨勬弿杩�
	 */
	public String guessNumber(int[] guess){
		if(guess.length!=4)//濡傛灉鐚滅殑鏁板瓧涓嶆槸4涓紝灏辫繑鍥為潪娉�
			return "ILLEGAL";
		
		guessTime++;//鐚滅殑娆℃暟鍔犱竴
		
		int count=0;//琛ㄧず鐚滃浣嶇疆鐨勬暟閲�
		int countx=0;//琛ㄧず浣嶇疆涓嶅浣嗘槸鏁板瓧鏄鐨勬暟閲�
		
		for(int i=0;i<4;i++){//鎸変綅缃瘮杈冭緭鍏ョ殑鏁板瓧鏄惁浣嶇疆鍜屾暟瀛楅兘瀵�
			if(guess[i]==numbers[i]){
				count++;
			}
		}
		
		Arrays.sort(guess);//鎶婄寽鐨勬暟瀛楁帓搴�
		
		for(int i=0,j=0;i<4&&j<4;){//姣旇緝涓や釜鏁扮粍涓槸鍚︽湁鐩稿悓鐨勬暟瀛�
			if(guess[i]==sortedNumbers[j]){//濡傛灉閮界寽瀵逛簡锛岄偅灏变綅缃兘鍔犱竴
				countx++;//鐚滃鏁板瓧鍔犱竴
				i++;
				j++;
			}else if(guess[i]<sortedNumbers[j])//濡傛灉鐚滅殑鏁板瓧姣旇緝灏忥紝鐚滅殑鏁板瓧鐨勪綅缃姞涓�
				i++;
			else//guess[i]>sortedNumbers[j]锛屽弽杩囨潵锛屾帓搴忔暟缁勭殑浣嶇疆鍔犱竴
				j++;
		}

		return count+"A"+(countx-count)+"B";//杩斿洖鐚滃浣嶇疆鐨勬暟瀛楃殑鏁伴噺锛屽拰鐚滃鏁板瓧浣嗘槸浣嶇疆涓嶅鐨勬暟瀛楃殑鏁伴噺
	}
	
	/**
	 * 杩斿洖鐚滅殑娆℃暟
	 * 
	 * @return 鐚滅殑娆℃暟
	 */
	public int getGuessTimes(){
		return guessTime;
	}
	public String getAnswer(){
		String str="";
		for(int i=0;i<=3;i++)
			str+=numbers[i];
		return str;
	}
	
}
