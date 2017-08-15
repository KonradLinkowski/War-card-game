package application;

import java.util.List;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.animation.Animation.Status;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.util.Duration;

public class AnimationManager {
	
	SequentialTransition sequTransition;
	
	private Duration duration = Duration.millis(500);
	
	private class ReverseTransition extends Transition {

		private CardVisualisation card;

		
		
		public ReverseTransition(CardVisualisation card) {
			this.card = card;
		}

		@Override
		protected void interpolate(double arg0) {
			card.reverse();
		}
		
		public void play () {
			card.reverse();
		}

	}
	
	public AnimationManager() {
		
	}
	
	/*
	public AnimationManager(Node enemyDeck, Node playerDeck, Node enemyPile, Node playerPile) {
		this.enemyDeck = new Point2D (enemyDeck.getLayoutX(), enemyDeck.getLayoutY());
		this.playerDeck = new Point2D (playerDeck.getLayoutX(), playerDeck.getLayoutY());
		this.enemyPile = new Point2D (enemyPile.getLayoutX(), enemyPile.getLayoutY());
		this.playerPile = new Point2D (playerPile.getLayoutX(), playerPile.getLayoutY());
	}
	*/
	private Point2D nodeToPoint (Node node) {
		return new Point2D (node.getLayoutX(), node.getLayoutY());
	}


	public ParallelTransition getCards (Node deck, List <CardVisualisation> cards) {
		Point2D deckPos = nodeToPoint (deck);
		Point2D cardPos;
		ParallelTransition paraTransition = new ParallelTransition ();
		for (CardVisualisation card : cards) {
			cardPos = new Point2D (card.getLayoutX(), card.getLayoutY());
			TranslateTransition tranTransition = new TranslateTransition(duration, card);
			tranTransition.setToX(deckPos.getX() - cardPos.getX());
			tranTransition.setToY(deckPos.getY() - cardPos.getY());
			paraTransition.getChildren().add(tranTransition);
		}
		return paraTransition;
		
	}
	
	public ParallelTransition putCard (Node deck, CardVisualisation card, Node pile) {
		Point2D deckPos = nodeToPoint (deck);
		Point2D pilePos = nodeToPoint (pile);
		card.relocate(deckPos.getX(), deckPos.getY());
		ParallelTransition paraTransition = new ParallelTransition ();
		
		TranslateTransition tranTransition = new TranslateTransition(duration, card);
		tranTransition.setToX(pilePos.getX() - deckPos.getX());
		tranTransition.setToY(pilePos.getY() - deckPos.getY());
		paraTransition.getChildren().add(tranTransition);
		
		SequentialTransition sequTransition = new SequentialTransition();
		
		RotateTransition rotaTransition = new RotateTransition (duration.divide(2), card);
		rotaTransition.setAxis(new Point3D (0, 1, 0));
		rotaTransition.setByAngle(90);
		sequTransition.getChildren().add(rotaTransition);
		
		rotaTransition = new RotateTransition(Duration.millis(0), card);
		rotaTransition.setAxis(new Point3D (0, 1, 0));
		rotaTransition.setByAngle(-180);
		sequTransition.getChildren().add(rotaTransition);
		
		ReverseTransition reveTrans = new ReverseTransition (card);
		sequTransition.getChildren().add(reveTrans);
		
		rotaTransition = new RotateTransition(duration.divide(2), card);
		rotaTransition.setAxis(new Point3D (0, 1, 0));
		rotaTransition.setByAngle(90);
		sequTransition.getChildren().add(rotaTransition);
		
		
		paraTransition.getChildren().add(sequTransition);
		
		return paraTransition;
	}
	
	public ParallelTransition putCardReversed (Node deck, CardVisualisation card, Node pile) {
		Point2D deckPos = nodeToPoint (deck);
		Point2D pilePos = nodeToPoint (pile);
		card.relocate(deckPos.getX(), deckPos.getY());
		ParallelTransition paraTransition = new ParallelTransition ();
		
		TranslateTransition tranTransition = new TranslateTransition(duration, card);
		tranTransition.setToX(pilePos.getX() - deckPos.getX());
		tranTransition.setToY(pilePos.getY() - deckPos.getY());
		paraTransition.getChildren().add(tranTransition);
		
		return paraTransition;
	}
	
	
	public void runAnimations (List <Animation> animations) {
		sequTransition = new SequentialTransition
				(animations.toArray(new Animation[animations.size()]));
		sequTransition.play();
	}
	
	public boolean isPlaying () {
		return sequTransition != null && sequTransition.getStatus() == Status.RUNNING ;
	}

}
