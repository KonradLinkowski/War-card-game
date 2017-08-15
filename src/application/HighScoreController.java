package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import core.HighScore;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

public class HighScoreController extends Pane implements Initializable {

    @FXML
    private ListView listView;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		List <HighScore> ls = HighScore.loadData();
		listView.getItems().addAll(ls);
		
	}

}
