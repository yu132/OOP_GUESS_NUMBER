package guess_number;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Mylabel extends Label{
	private String str;
	private int px;
	private boolean b;
	public Mylabel(String str,int px,boolean b){
		this.str=str;
		this.px=px;
		this.b=b;
		setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0), null, null)));
		setText(str);
		setFont(new Font("Cambria", px));
		setTextFill(Color.WHITE);
		if(b){
			setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, 
					null, new BorderWidths(3))));
			setOnMouseEntered(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
					setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));
				}
			});
			setOnMouseExited(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
					setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), null, null)));
				}
			});
		}
	}
}
