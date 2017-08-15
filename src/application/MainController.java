/**
 * 
 */
package application;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.sound.midi.Synthesizer;

import core.Game;
import core.HighScore;
import core.Turn;
import core.cards.NormalCard;
import core.cards.Rank;
import core.cards.Suit;
import core.cards.Unicorn;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Konrad Linkowski
 *
 */
public class MainController extends BorderPane implements Initializable{

	@FXML
	private Label winLabel;
	
	@FXML
	private Label playerDeckLabel;
	
	@FXML
	private Label enemyDeckLabel;
	
	@FXML
    private AnchorPane gamePane;

    @FXML
    private ImageView playerPileNode;

    @FXML
    private ImageView playerDeckNode;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ImageView enemyPileNode;

    @FXML
    private ImageView enemyDeckNode;

    @FXML
    private Label consoleLabel;

	/*
	private CardVisualisation playerDeck;
	
	private CardVisualisation enemyDeck;
	
	private CardVisualisation playerCard;
	
	private CardVisualisation enemyCard;
	
	private Point2D playerPile;
	
	private Point2D enemyPile;
	*/
	private Game game;
	
	private Parent creditsScreen;
	
	private Turn turn;
	
	private AnimationManager animManager;
	
	private List <CardVisualisation> cardsImages = new LinkedList <CardVisualisation> ();
	
	/**
	 * 
	 */
	
	@FXML
	private void showSomething () {
		TextInputDialog dialog = new TextInputDialog("walter");
		dialog.setTitle("Text Input Dialog");
		dialog.setHeaderText(null);
		dialog.setContentText("Please enter your name:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			HighScore.saveData(new HighScore (result.get(), game.getTurnIndex()));
			Stage appStage = (Stage) gamePane.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            Parent parent = null;
			try {
				parent = loader.load(getClass().getResource("HighScoreScreen.fxml").openStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            HighScoreController mainSceneController = (HighScoreController)loader.getController();
            appStage.setTitle("HighScores");
            appStage.setScene(new Scene(parent));
            appStage.setResizable(false);
            appStage.show();
		}
	}
	
	@FXML
	private void startNewGame (ActionEvent event) {
		beginNewGame();
	}
	
	@FXML
	private void startNextTurn (MouseEvent event) {
		if (animManager.isPlaying()) {
			return;
		}
		turn = game.nextTurn(turn);
		List <Animation> animationList = new LinkedList <Animation> ();
		if (turn.isEnd()) {
			winLabel.setText (turn.getGameWinner() == Game.enemyDeck ? "Enemy wins" : "Player wins");
			showSomething ();
			return;
		}
		playerDeckLabel.setText("Cards left: " + Game.playerDeck.getSize());
		enemyDeckLabel.setText("Cards left: " + Game.enemyDeck.getSize());
		CardVisualisation playerCard = new CardVisualisation (turn.getLastPlayerCard());
		cardsImages.add(playerCard);
		gamePane.getChildren().add(playerCard);
		CardVisualisation enemyCard = new CardVisualisation (turn.getLastEnemyCard());
		cardsImages.add(enemyCard);
		gamePane.getChildren().add(enemyCard);
		if (turn.isCardsReversed()) {
			animationList.add(animManager.putCardReversed(playerDeckNode, playerCard, playerPileNode));
			animationList.add(animManager.putCardReversed(enemyDeckNode, enemyCard, enemyPileNode));
		} else {
			animationList.add(animManager.putCard(playerDeckNode, playerCard, playerPileNode));
			animationList.add(animManager.putCard(enemyDeckNode, enemyCard, enemyPileNode));
		}
		if (!turn.isWar()) {
			Node winDeck = turn.getTurnWinner() == Game.enemyDeck ? enemyDeckNode : playerDeckNode;
			animationList.add(animManager.getCards(winDeck, cardsImages));
			cardsImages.clear();
		}
		animManager.runAnimations(animationList);
		
	}
	
	@FXML
	public void openCredits(ActionEvent event) {
		if (creditsScreen == null) {
			creditsScreen = new AnchorPane ();
			Label text = new Label ("Wojna, ulepszona gra karciana.\nAutor:\nKonrad Linkowski");
			text.setFont(new Font ("Arial", 30));
			((AnchorPane) creditsScreen).getChildren().add(text);
			Stage stage = new Stage();
			stage.setTitle("About");
			stage.setScene(new Scene(creditsScreen, 450, 200));
			stage.show();
		}
    }
	
	private void beginNewGame () {
		game = new Game ();
		turn = new Turn ();
		cardsImages.clear();
		playerDeckLabel.setText("Cards left: " + Game.playerDeck.getSize());
		enemyDeckLabel.setText("Cards left: " + Game.enemyDeck.getSize());
		animManager = new AnimationManager();
		enemyDeckNode.setImage(new Image ("cardback.png"));
		playerDeckNode.setImage(new Image ("cardback.png"));
		playerPileNode.setImage(new Image ("deckbackvert.png"));
		enemyPileNode.setImage(new Image ("deckbackvert.png"));
		//CardVisualisation img = new CardVisualisation(new Unicorn ());
		//img.reverse();
		//img.relocate(300, 300);
		//RotateTransition rt = new RotateTransition (Duration.seconds(5), img);
		//rt.setByAngle(90);
		//rt.play();
		//gamePane.getChildren().add(img);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/*
		playerPile = new Point2D (gamePane.getPrefWidth() / 2 + 150, gamePane.getPrefHeight() / 2 - 100);
		enemyPile = new Point2D (gamePane.getPrefWidth() / 2 - 150, gamePane.getPrefHeight() / 2 - 100);
		*/
		beginNewGame();
	}

}
