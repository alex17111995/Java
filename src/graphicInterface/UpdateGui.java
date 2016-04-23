package graphicInterface;

import game.Game;
import game.Player;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import java.util.List;

/**
 * Created by dryflo on 4/22/2016.
 */
public class UpdateGui {
    private static GuiController controller;
    private static int currentPlayer;
    private static ObservableList<TableRow> data= FXCollections.observableArrayList();

    public static Game getGame() {
        return game;
    }
    public static void setTable(ObservableList<TableRow> table){
        data=table;
    }
    public static void setGame(Game game) {
        UpdateGui.game = game;
    }

    private static Game game;
    public static void updateTime(long  number){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                controller.getTimer().setText(""+Long.toString(number));
            }});


    }
    public static void update(Player playerUpdated,boolean isValid,String word){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Player player = game.getPlayer();
                List<Character> tiles= player.getTiles();
                Button[] buttons= new Button[7];
                buttons[0]=controller.getMyLetter1();
                buttons[1]=controller.getMyLetter2();
                buttons[2]=controller.getMyLetter3();
                buttons[3]=controller.getMyLetter4();
                buttons[4]=controller.getMyLetter5();
                buttons[5]=controller.getMyLetter6();
                buttons[6]=controller.getMyLetter7();
                for (int i=0;i<tiles.size();i++) {
                    buttons[i].setText(tiles.get(i).toString());
                }
                for(int i=tiles.size();i<7;i++){
                    buttons[i].setText("");
                }
                controller.getCurrentPlayer().setText("Player "+player.getPlayerNumber());

                if(isValid){
                    TableRow entry =new TableRow("player "+Integer.toString(playerUpdated.getPlayerNumber()),word,Integer.toString(word.length()),Integer.toString(playerUpdated.getPlayerPoints()));
                    data.add(entry);
                }
                controller.getCurrentPlayer().setText("Player "+player.getPlayerNumber());
                return;
            }
        });
    }

    public static void setUpdateGui(GuiController control){
        controller=control;
    }
    public static void setCurrentPlayer(int current){
        currentPlayer=current;
    }

}
