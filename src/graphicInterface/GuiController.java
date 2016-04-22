package graphicInterface;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

/**
 * Created by dryflo on 4/22/2016.
 */
public class GuiController {
    @FXML private ImageView player1;
    @FXML private ImageView player2;
    @FXML private TableView scoreTable;
    @FXML private Button letter1;
    @FXML private Button letter2;
    @FXML private Button letter3;
    @FXML private Button letter4;
    @FXML private Button letter5;
    @FXML private Button letter6;
    @FXML private Button letter7;
    @FXML private Button myLetter1;
    @FXML private Button myLetter2;
    @FXML private Button myLetter3;
    @FXML private Button myLetter4;
    @FXML private Button myLetter5;
    @FXML private Button myLetter6;
    @FXML private Button myLetter7;
    @FXML private Button submitWord;
    @FXML private Label player;
    @FXML private Label currentPlayer;
    @FXML private Label timer;

    public ImageView getPlayer2() {
        return player2;
    }

    public void setPlayer2(ImageView player2) {
        this.player2 = player2;
    }

    public ImageView getPlayer1() {
        return player1;
    }

    public void setPlayer1(ImageView player1) {
        this.player1 = player1;
    }

    public TableView getScoreTable() {
        return scoreTable;
    }

    public void setScoreTable(TableView scoreTable) {
        this.scoreTable = scoreTable;
    }

    public Button getLetter1() {
        return letter1;
    }

    public void setLetter1(Button letter1) {
        this.letter1 = letter1;
    }

    public Button getLetter2() {
        return letter2;
    }

    public void setLetter2(Button letter2) {
        this.letter2 = letter2;
    }

    public Button getLetter3() {
        return letter3;
    }

    public void setLetter3(Button letter3) {
        this.letter3 = letter3;
    }

    public Button getLetter4() {
        return letter4;
    }

    public void setLetter4(Button letter4) {
        this.letter4 = letter4;
    }

    public Button getLetter5() {
        return letter5;
    }

    public void setLetter5(Button letter5) {
        this.letter5 = letter5;
    }

    public Button getLetter6() {
        return letter6;
    }

    public void setLetter6(Button letter6) {
        this.letter6 = letter6;
    }

    public Button getLetter7() {
        return letter7;
    }

    public void setLetter7(Button letter7) {
        this.letter7 = letter7;
    }

    public Button getMyLetter1() {
        return myLetter1;
    }

    public void setMyLetter1(Button myLetter1) {
        this.myLetter1 = myLetter1;
    }

    public Button getMyLetter2() {
        return myLetter2;
    }

    public void setMyLetter2(Button myLetter2) {
        this.myLetter2 = myLetter2;
    }

    public Button getMyLetter3() {
        return myLetter3;
    }

    public void setMyLetter3(Button myLetter3) {
        this.myLetter3 = myLetter3;
    }

    public Button getMyLetter4() {
        return myLetter4;
    }

    public void setMyLetter4(Button myLetter4) {
        this.myLetter4 = myLetter4;
    }

    public Button getMyLetter5() {
        return myLetter5;
    }

    public void setMyLetter5(Button myLetter5) {
        this.myLetter5 = myLetter5;
    }

    public Button getMyLetter6() {
        return myLetter6;
    }

    public void setMyLetter6(Button myLetter6) {
        this.myLetter6 = myLetter6;
    }

    public Button getMyLetter7() {
        return myLetter7;
    }

    public void setMyLetter7(Button myLetter7) {
        this.myLetter7 = myLetter7;
    }

    public Button getSubmitWord() {
        return submitWord;
    }

    public void setSubmitWord(Button submitWord) {
        this.submitWord = submitWord;
    }

    public Label getPlayer() {
        return player;
    }

    public void setPlayer(Label player) {
        this.player = player;
    }

    public Label getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Label currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Label getTimer() {
        return timer;
    }

    public void setTimer(Label timer) {
        this.timer = timer;
    }
}
