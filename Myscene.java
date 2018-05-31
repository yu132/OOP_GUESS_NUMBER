package guess_number;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

public class Myscene extends Scene {
	public Myscene(Pane pane,Group root) {
		super(root, 600, 500, Color.BLACK);
	
        Group circles = new Group();
        for (int i = 0; i < 30; i++) {
           Circle circle = new Circle(150, Color.web("white", 0.05));
           circle.setStrokeType(StrokeType.OUTSIDE);
           circle.setStroke(Color.web("white", 0.16));
           circle.setStrokeWidth(4);
           circles.getChildren().add(circle);
        }
        Rectangle colors= new Rectangle(getWidth(),getHeight(),

        	     new LinearGradient(0f, 1f, 1f, 0f, true,CycleMethod.NO_CYCLE, new

        	         Stop[]{

        	            new Stop(0,Color.web("#f8bd55")),

        	            new Stop(0.14,Color.web("#c0fe56")),

        	            new Stop(0.28,Color.web("#5dfbc1")),

        	            new Stop(0.43,Color.web("#64c2f8")),

        	            new Stop(0.57,Color.web("#be4af7")),

        	            new Stop(0.71,Color.web("#ed5fc2")),

        	            new Stop(0.85,Color.web("#ef504c")),

        	            new Stop(1,Color.web("#f2660f")),}));
        Group blendModeGroup =

        		   new Group(new Group(new Rectangle(getWidth(), getHeight(),

        		        Color.BLACK), circles), colors);

        colors.setBlendMode(BlendMode.OVERLAY);
        root.getChildren().add(blendModeGroup);
    	root.getChildren().add(pane);
        circles.setEffect(new BoxBlur(10, 10, 3));
        
        Timeline timeline = new Timeline();

        for (Node circle: circles.getChildren()) {

            timeline.getKeyFrames().addAll(

                new KeyFrame(Duration.ZERO, // setstart position at 0

                    new KeyValue(circle.translateXProperty(), Math.random() * 800),

                    new KeyValue(circle.translateYProperty(), Math.random() * 600)

                ),

                new KeyFrame(new Duration(80000),//set end position at 40s

                    new KeyValue(circle.translateXProperty(), Math.random() * 800),

                    new KeyValue(circle.translateYProperty(), Math.random() * 600)

                )

            );

        }

        // play 40s ofanimation

        timeline.play();
	}

}
