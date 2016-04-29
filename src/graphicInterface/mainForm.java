package graphicInterface;

import com.sun.javaws.exceptions.InvalidArgumentException;
import game.Game;
import game.Player;
import game.Timekeeper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.commons.lang3.math.NumberUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dryflo on 4/22/2016.
 */
public class mainForm extends Application {
    private Game game = new Game();
    private GuiController controller = new GuiController();
    private List<Button> myLetters=new ArrayList<>();
    private List<Button> letters=new ArrayList<>();
    private final ObservableList<TableRow> data= FXCollections.observableArrayList();
    private int numberOfInteractivePlayers=0;
    private int numberOfAutomatedPlayers=0;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane frame=new Pane();
        Button button=new Button("generate players");
        TextField inputInteractivePlayers=new TextField();
        TextField inputAutomatedPlayers=new TextField();
        Label errorMsg=new Label();
        Label interactive=new Label("interactive players number");
        Label automated=new Label("automated players number");

        button.setOnAction(e->{
            if(!this.checkPlayerInputNumber(inputInteractivePlayers.getText(),inputAutomatedPlayers.getText())){
                errorMsg.setText("incorect number of players");
            }else{
                this.numberOfAutomatedPlayers=Integer.parseInt(inputAutomatedPlayers.getText());
                this.numberOfInteractivePlayers=Integer.parseInt(inputInteractivePlayers.getText());
                this.actualGameWindow(primaryStage);
            }
        });
        interactive.setLayoutX(100);
        interactive.setLayoutY(50);
        inputInteractivePlayers.setLayoutX(100);
        inputInteractivePlayers.setLayoutY(75);
        automated.setLayoutX(100);
        automated.setLayoutY(125);
        inputAutomatedPlayers.setLayoutX(100);
        inputAutomatedPlayers.setLayoutY(150);
        button.setLayoutX(95);
        button.setLayoutY(200);
        errorMsg.setLayoutX(100);
        errorMsg.setLayoutY(250);

        frame.getChildren().addAll(interactive,inputInteractivePlayers,automated,inputAutomatedPlayers,button,errorMsg);
        primaryStage.setTitle("Word Game");
        primaryStage.setScene(new Scene(frame, 350, 350));





        primaryStage.show();
    }

    protected void actualGameWindow(Stage primaryStage){
        Parent frame=null;
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "wordGame.fxml"
                    )
            );
            loader.setController(controller);
            frame = loader.load();
        }catch(IOException e){
            System.out.println("fxml file not found");
        }
        UpdateGui.setUpdateGui(controller);
        UpdateGui.setGame(game);
        UpdateGui.setTable(data);
        try{
            setImage(controller.getPlayer1());
            setImage(controller.getPlayer2());
        }catch(InvalidArgumentException e){
            System.out.println("image not found");
        }
        this.initializeTableScore();
        //adding gui buttons to list
        this.addButtons();
        Thread timerkeeper= new Thread(new Timekeeper());
        timerkeeper.setDaemon(true);
        timerkeeper.start();
        for(int i=0;i<this.numberOfInteractivePlayers;i++){//NUMBER of players
            this.game.generatePlayer(); //genereaza si incepe threadul
        }
        for(int i=0;i<this.numberOfAutomatedPlayers;i++){//NUMBER of players
            this.game.generateAutomatedPlayer();//genereaza si incepe threadul
        }

        UpdateGui.setCurrentPlayer(game.getCurrentPlayer());

        this.myLettersAction();
        this.lettersAction();
        UpdateGui.update(game.getPlayer(),false,"");


        controller.getSubmitWord().setOnAction(e->{
            String word="";
            for(Button letter:this.letters){
                word+=letter.getText();
                letter.setText("");
            }

            if(!word.isEmpty()) {
                Player player = game.getPlayer();
                player.postWord(word);
                this.changePlayerLetters(player);
                //UpdateGui.setCurrentPlayer(player.playerNumber);
                System.out.println(word);
            }
        });

        primaryStage.setOnCloseRequest(e -> {
            game.setGameOver(null);
            Platform.exit();

        });
        frame.setStyle("-fx-background-color: rgba(216, 243, 255, 0.72);");
        primaryStage.setTitle("Word Game");
        primaryStage.setScene(new Scene(frame, 688, 483));
    }
    protected Boolean checkPlayerInputNumber(String interactivePlayer,String automatedPlayer){
        if(interactivePlayer==null||interactivePlayer.isEmpty()||!NumberUtils.isNumber(interactivePlayer)){
            return false;
        }
        if(automatedPlayer==null||automatedPlayer.isEmpty()||!NumberUtils.isNumber(automatedPlayer)){
            return false;
        }
        if(Integer.parseInt(interactivePlayer)>15 || Integer.parseInt(interactivePlayer)<1 || Integer.parseInt(automatedPlayer)>15 || Integer.parseInt(automatedPlayer)<0){
            return false;
        }
        return true;
    }
    protected void lettersAction(){
        for(Button letter:this.letters){
            letter.setOnAction(e->{
                if(findEmptyLetter(this.myLetters,letter.getText())){
                    letter.setText("");
                }
            });
        }
    }
    protected void myLettersAction(){
        for(Button letter:this.myLetters){
            letter.setOnAction(e->{
                if(findEmptyLetter(this.letters,letter.getText())){
                    letter.setText("");
                }
            });
        }
    }
    protected Boolean findEmptyLetter(List<Button> buttons,String character){
        for(Button letter:buttons){
            if(letter.getText().equals("")){
                letter.setText(character);
                return true;
            }
        }
        return false;
    }
    protected void changePlayerLetters(Player player){

    }
    protected void addButtons(){
        this.myLetters.add(controller.getMyLetter1());
        this.myLetters.add(controller.getMyLetter2());
        this.myLetters.add(controller.getMyLetter3());
        this.myLetters.add(controller.getMyLetter4());
        this.myLetters.add(controller.getMyLetter5());
        this.myLetters.add(controller.getMyLetter6());
        this.myLetters.add(controller.getMyLetter7());

        this.letters.add(controller.getLetter1());
        this.letters.add(controller.getLetter2());
        this.letters.add(controller.getLetter3());
        this.letters.add(controller.getLetter4());
        this.letters.add(controller.getLetter5());
        this.letters.add(controller.getLetter6());
        this.letters.add(controller.getLetter7());
    }
    protected void setImage(ImageView imageView)throws  InvalidArgumentException{

        Path currentRelativePath = Paths.get("");
        String filePath = currentRelativePath.toAbsolutePath().toString();
        File file = new File(new File(filePath,"src").getAbsolutePath(),"graphicInterface");
        file=new File(file.getAbsolutePath(),"rsz_images.png");

        if(!file.exists()){
            throw new InvalidArgumentException(new String[]{"invalid path"});
        }
        try {
            BufferedImage buff = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(buff, null);

            imageView.setImage(image);


        }catch(IOException e){
            System.out.println("not a image");
        }
    }
    protected void buildTableScore(Player player,String word){

        TableRow entry =new TableRow("player "+Integer.toString(player.getPlayerNumber()),word,Integer.toString(word.length()),Integer.toString(player.getPlayerPoints()));

        data.add(entry);
    }
    protected void initializeTableScore(){
        controller.getPlayerColumn().setCellValueFactory(new PropertyValueFactory<TableRow,String>("Player"));
        controller.getWordColumn().setCellValueFactory(new PropertyValueFactory<TableRow,String>("Word"));
        controller.getScoreColumn().setCellValueFactory(new PropertyValueFactory<TableRow,String>("Points"));
        controller.getTotalColumn().setCellValueFactory(new PropertyValueFactory<TableRow,String>("Total"));
        controller.getScoreTable().setItems(data);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
