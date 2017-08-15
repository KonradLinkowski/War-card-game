package application;

import core.cards.Card;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class CardVisualisation extends ImageView {

	private Image frontImage;
	private Image backImage = new Image ("cardback.png");
	private boolean front;
	
	public CardVisualisation(Card card) {
		parseCard (card);
	}

	public void reverse () {
		super.setImage(front ? backImage : frontImage);
		front = !front;
	}
	
	public void parseCard (Card card) {
		frontImage = textToImage (card);
		front = false;
		super.setImage(backImage);
	}
	
	private Image textToImage(Card card) {
		Rectangle rect = new Rectangle (0, 0, 150, 200);
		rect.setFill(new Color (0, 0, 0, 0));
		rect.setStrokeWidth(7);
		rect.setStroke(new Color (0.7, 0.3, 1.0, 1));
		rect.setStrokeType(StrokeType.INSIDE);
		
		Label rank = new Label (card.getRank().getLetter ());
		rank.setStyle("-fx-font: 20px Tahoma; -fx-background-color: white; -fx-text-fill:red;");
		rank.setWrapText(true);
		rank.setMinSize(50, 20);
		rank.setMaxSize(50, 20);
		rank.setPrefSize(50, 20);
		rank.relocate(12, 7);
		
		Label suit = new Label (card.getSuit().getLetter ());
		suit.setStyle("-fx-font: 20px Tahoma; -fx-background-color: white; -fx-text-fill:red;");
		suit.setWrapText(true);
		suit.setMinSize(50, 20);
		suit.setMaxSize(50, 20);
		suit.setPrefSize(50, 20);
		suit.relocate(12, 25);
		
		Label rank2 = new Label (card.getRank().getLetter ());
		rank2.setStyle("-fx-font: 20px Tahoma; -fx-background-color: white; -fx-text-fill:red;");
		rank2.setWrapText(true);
		rank2.setMinSize(50, 20);
		rank2.setMaxSize(50, 20);
		rank2.setPrefSize(50, 20);
		rank2.relocate(130, 155);
		
		Label suit2 = new Label (card.getSuit().getLetter ());
		suit2.setStyle("-fx-font: 20px Tahoma; -fx-background-color: white; -fx-text-fill:red;");
		suit2.setWrapText(true);
		suit2.setMinSize(50, 20);
		suit2.setMaxSize(50, 20);
		suit2.setPrefSize(50, 20);
		suit2.relocate(130, 170);
		
	    Label value = new Label(card.getValue () + "");
	    value.setStyle("-fx-font: 20px Tahoma;-fx-background-color: white; -fx-text-fill:magenta;");
	    value.setWrapText(true);
	    value.setMinSize(100, 20);
	    value.setMaxSize(100, 20);
	    value.setPrefSize(100, 20);
	    value.relocate(130, 7);
	    
	    Label value2 = new Label(card.getValue () + "");
	    value2.setStyle("-fx-font: 20px Tahoma;-fx-background-color: white; -fx-text-fill:magenta;");
	    value2.setWrapText(true);
	    value2.setMinSize(100, 20);
	    value2.setMaxSize(100, 20);
	    value2.setPrefSize(100, 20);
	    value2.relocate(12, 170);
	    
	    Label label = new Label(card.getName());
	    label.setMinSize(150, 80);
	    label.setMaxSize(150, 80);
	    label.setPrefSize(150, 80);
	    label.setStyle("-fx-font: 25px Tahoma;-fx-background-color: white; -fx-text-fill:black;");
	    label.setWrapText(true);
	    label.relocate(0, 50);
	    label.setAlignment(Pos.TOP_CENTER);
	    
	    Label dis = new Label(card.getDiscription());
	    dis.setMaxSize(110, 100);
	    dis.setStyle("-fx-font: 12px Tahoma;-fx-background-color: white; -fx-text-fill:black;");
	    dis.setWrapText(true);
	    dis.relocate(20, 100);
	    dis.setAlignment(Pos.BOTTOM_CENTER);
	    label.setContentDisplay(ContentDisplay.CENTER);
	    
	    Scene scene = new Scene(new Group(label, dis, value, suit2, rank2, suit, rank, rect, value2));
	    WritableImage img = new WritableImage(150, 200) ;
	    scene.snapshot(img);
	    return img ;
	}

}
