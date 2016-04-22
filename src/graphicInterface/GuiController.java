package graphicInterface;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

/**
 * Created by dryflo on 4/22/2016.
 */
public class GuiController {
    @FXML
    private ImageView player1;
    @FXML
    private ImageView player2;
    @FXML
    private TableView scoreTable;

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
}
