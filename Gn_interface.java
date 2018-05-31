package guess_number;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Gn_interface extends Application{

	private ArrayList<String> a1=new ArrayList<String>();
	private ArrayList<String> a2=new ArrayList<String>();
	private static ArrayList<User> user_score=new ArrayList<User>();
	String un;
	Label board[]=new Label[4];
	TextField t=new TextField();
	GuessNumber gn=new GuessNumber();

	private static final String FILE_PATH="d:GuessNumber.txt";
	private static void write(String username,String score){
		String msg=username+","+score;
		BufferedWriter bw=null;
		try{
			bw=new BufferedWriter(new FileWriter(FILE_PATH,true));
			bw.append(msg);
			bw.newLine();
			bw.flush();
		}
		catch(IOException ex){
			ex.printStackTrace();
		}finally{
			try{
				if(bw==null)
					bw.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	private static void read(){
		Gn_interface gni =new Gn_interface();
		String msg; 
		try{
			BufferedReader br=new BufferedReader(new FileReader(FILE_PATH));
			while((msg=br.readLine())!=null){//在文件中读取每行信息，若为空，停止读取
				String [] temp=msg.split(",");
				User user=gni.new User(temp[0],temp[1]);
				user_score.add(user);
			}
			br.close();//关闭读取
			}
		catch(Exception ex){
//			return false;
		}
	}

	private static int[] adapter(String str) throws Exception{
		int a[]=new int[str.length()];
		for(int i=0;i<str.length();i++){
			if(str.charAt(i)<'0'||str.charAt(i)>'9')
				throw new Exception();
			a[i]=str.charAt(i)-'0';
		}
		return a;
	}
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// TODO Auto-generated method stub
		
		t.setLayoutX(120);
		t.setLayoutY(128);		
		
		Label show[]=new Label[4];
		show[0]=new Mylabel("Guess Number",35,false);
		show[1]=new Mylabel("得分",35,false);
		show[2]=new Mylabel("已猜数字",25,false);
		show[3]=new Mylabel("准确度",25,false);
		show[0].setLayoutX(30);show[0].setLayoutY(20);
		show[1].setLayoutX(380);show[1].setLayoutY(20);
		show[2].setLayoutX(120);show[2].setLayoutY(200);
		show[3].setLayoutX(350);show[3].setLayoutY(200);

		
		Label button[]=new Label[4];
		button[0]=new Mylabel("输入",30,true);
		button[1]=new Mylabel("看答案",30,true);
		button[2]=new Mylabel("重新开始",30,true);
		button[3]=new Mylabel(" 排名 ",30,true);
		button[0].setLayoutX(380);button[0].setLayoutY(120);
		button[1].setLayoutX(30);button[1].setLayoutY(450);
		button[2].setLayoutX(320);button[2].setLayoutY(450);
		button[3].setLayoutX(480);button[3].setLayoutY(450);
		
		t.setOnKeyPressed(new EventHandler<KeyEvent>() {  
			  
		    @Override  
		    public void handle(KeyEvent event) {  
		        if(event.getCode() == KeyCode.ENTER){  
		        	String str=t.getText();
					String str1="",str2="";
					a1.add(str);
					try {
						a2.add(gn.guessNumber(adapter(str)));
					} catch (Exception ex) {
						a2.add("ILLEGAL");
					}
					for(int i=a1.size()-1;i>=0;i--){
						str1+=a1.get(i)+" "+"\n";
						str2+=a2.get(i)+" "+"\n";
					}
					board[0].setText(500-20*gn.getGuessTimes()+"");
					board[1].setText(str1);
					board[2].setText(str2);
					if(a2.get(a2.size()-1).equals("4A0B")){
						write(un,board[0].getText());
						t.setText("");
						t.setEditable(false);
						
					}
					t.setText("");
		        }  
		    }  
		});  
		
		for(int i=0;i<4;i++)
			board[i]=new Label();
		board[0].setLayoutX(470);board[0].setLayoutY(25);
		board[1].setLayoutX(120);board[1].setLayoutY(230);
		board[2].setLayoutX(350);board[2].setLayoutY(230);
		board[3].setLayoutX(150);board[3].setLayoutY(450);
		board[0].setText("500");
		board[1].setMaxSize(100, 200);
		board[2].setMaxSize(100, 200);
		board[0].setTextFill(Color.WHITE);
		board[1].setTextFill(Color.WHITE);
		board[2].setTextFill(Color.WHITE);
		board[3].setTextFill(Color.WHITE);
		board[0].setFont(new Font("Cambria", 30));
		board[1].setFont(new Font("Cambria", 15));
		board[2].setFont(new Font("Cambria", 15));
		board[3].setFont(new Font("Cambria", 30));

		
		button[0].setOnMouseClicked(new EventHandler<MouseEvent>() {//输入按钮
			public void handle(MouseEvent event) {
				String str=t.getText();
				String str1="",str2="";
				a1.add(str);
				try {
					a2.add(gn.guessNumber(adapter(str)));
				} catch (Exception e) {
					a2.add("ILLEGAL");
				}
				for(int i=a1.size()-1;i>=0;i--){
					str1+=a1.get(i)+" "+"\n";
					str2+=a2.get(i)+" "+"\n";
				}
				board[0].setText(500-20*gn.getGuessTimes()+"");
				board[1].setText(str1);
				board[2].setText(str2);
				if(a2.get(a2.size()-1).equals("4A0B")){
					write(un,board[0].getText());
					t.setText("");
					t.setEditable(false);
					
				}
				t.setText("");
			}
		});

		button[1].setOnMouseClicked(new EventHandler<MouseEvent>() {//看答案
			public void handle(MouseEvent event) {
				if(gn.getGuessTimes()>=8)
					board[3].setText(gn.getAnswer());
				else
					board[3].setText("猜"+(8-gn.getGuessTimes())+"次可看");
			}
		});
		
		button[2].setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				gn.startGame();
				board[0].setText("500");
				board[1].setText(null);
				board[2].setText(null);
				board[3].setText(null);
				t.setText(null);
				a1.clear();
				a2.clear();
				t.setEditable(true);

			}
		});
		
		Pane pane=new Pane();//游戏pane
		pane.getChildren().addAll(show);
		pane.getChildren().addAll(button);
		pane.getChildren().addAll(board);
		pane.getChildren().addAll(t);
		pane.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0), null, null)));
		Group root=new Group();
		Myscene scene=new Myscene(pane,root);
//		Scene scene = new Scene(pane, 600, 500);
//		root.getChildren().add(pane);
		
		Label logo=new Mylabel("Guess Number",40,false);
		Label user=new Mylabel("用户名",20,false);
		Label login=new Mylabel("进入",30,true);
		TextField username=new TextField();
		logo.setLayoutX(150);logo.setLayoutY(50);
		user.setLayoutX(150);user.setLayoutY(150);
		username.setLayoutX(230);username.setLayoutY(150);
		login.setLayoutX(260);login.setLayoutY(250);
		
		username.setOnKeyPressed(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				un=username.getText();
				if(!un.equals("")&&event.getCode()==KeyCode.ENTER){
					gn.startGame();
					primaryStage.setScene(scene);
				}
			}
			
		});

		login.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				un=username.getText();
				if(!un.equals("")){
					gn.startGame();
					primaryStage.setScene(scene);
				}
			}
		});
		
		Pane pane2=new Pane();
		pane2.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0), null, null)));
		pane2.getChildren().addAll(logo,user,username,login);
		Group root2=new Group();
		Myscene scene2=new Myscene(pane2,root2);
//		Scene scene2=new Scene(pane2,600,500);
		
		Label logo2=new Mylabel("Guess Number",40,false);
		Label back=new Mylabel("返回",30,true);
		Label rank=new Label();
		logo2.setLayoutX(30);logo2.setLayoutY(20);
		back.setLayoutX(400);back.setLayoutY(20);
		rank.setLayoutX(80);rank.setLayoutY(100);
		rank.setMaxSize(300, 400);
		rank.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0,0), null, null)));
		rank.setTextFill(Color.WHITE);
		rank.setFont(new Font("Cambria", 20));
		
		back.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
					primaryStage.setScene(scene);
					user_score.clear();
			}
		});
		Pane pane3=new Pane();
		pane3.getChildren().addAll(logo2,back,rank);
		pane3.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0), null, null)));
		Group root3=new Group();
		Myscene scene3=new Myscene(pane3,root3);
//		Scene scene3=new Scene(pane3,600,500);
		button[3].setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				String str="";
//				primaryStage.setScene(scene3);
				read();
				User[] user=user_score.toArray(new User[user_score.size()]);
				Arrays.sort(user);
				for(int i=user.length-1;i>=0;i--){
					str+=user[i].name+"       "+user[i].score+"\n";
				}
				rank.setText(str);
				primaryStage.setScene(scene3);
			}
		});

		primaryStage.setScene(scene2);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	class User implements Comparable<User>{
		String name;
		String score;
		
		public User(String name,String score){
			this.name=name;
			this.score=score;
		}

		public int compareTo(User o) {
			return Integer.parseInt(score)-Integer.parseInt(o.score);
		}
	}
}
