package guess_number;

import java.util.Scanner;

//import core.GuessNumber;

public class cli {
	
	private static GuessNumber game=new GuessNumber();
	
	private static boolean gamehasstarted=false;

	public static boolean dealinput(String cmd){
		String[] cmdsp=cmd.split(" ");
		
		l:
		switch(cmdsp[0]){
		case "startgame":
		case "sg":
			if(!gamehasstarted){
				game.startGame();
				gamehasstarted=true;
				System.out.println("Game start successfully.");
			}else
				System.err.println("Game has started!");
			break;
			
		case "restart":
		case "re":
			if(gamehasstarted){
				game.startGame();
				System.out.println("Game restart successfully.");
			}else
				System.err.println("Game has not started!");
			break;
			
		case "guessnmber":
		case "gn":
			if(gamehasstarted){
				if(cmdsp.length!=2&&cmdsp.length!=5){
					System.err.println("Illegal input for guessnmber!");
					break;
				}
				int[] guess=new int[4];
				if(cmdsp.length==2){
					if(cmdsp[1].length()!=4){
						System.err.println("Illegal input for guessnmber!");
						break;
					}
					for(int i=0;i<4;i++){
						guess[i]=cmdsp[1].charAt(i)-'0';
					}
				}else{
					for(int i=1;i<=4;i++){
						if(cmdsp[i].length()!=1){
							System.err.println("Illegal input for guessnmber!");
							break l;
						}
						guess[i-1]=Integer.parseInt(cmdsp[i]);
					}
				}
				String result=game.guessNumber(guess);
				if(result=="ILLEGAL"){
					System.err.println("Illegal input for guessnmber!");
				}else if(result==""){
					System.out.println("All the numbers are incorrect.");
				}else if(result.contains("A")){
					int a=(result.charAt(0)-'0');
					int b=(result.charAt(2)-'0');
					
					if(a==4){
						System.out.println("All the numbers are correct!");
						break;
					}
					
					if(a==1){
						System.out.print(a+" number is in the right place,");
					}else
						System.out.print(a+" numbers are in the right place,");
					
					if(b==1){
						System.out.println(b+" number is in the wrong place.");
					}else
						System.out.println(b+" numbers are in the wrong place.");
				}else{
					int a=(result.charAt(0)-'0');
					if(a==1){
						System.out.println(a+" number is in the wrong place.");
					}else
						System.out.println(a+" numbers are in the wrong place.");
				}
				
			}else
				System.err.println("Game has not started!");
			break;
			
		case "showguesstime":
		case "sgt":
			if(gamehasstarted){
				System.out.println("You has guess "+game.getGuessTimes()+" time(s).");
			}else
				System.err.println("Game has not started!");
			break;
			
		case "stop":
		case "s":
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		while(scan.hasNext()){
			String cmd=scan.nextLine();
			
			if(dealinput(cmd))
				break;
		}
		scan.close();
	}
	
}
