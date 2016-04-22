package graphicInterface;

import com.sun.javaws.exceptions.InvalidArgumentException;
import game.Game;
import game.Player;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "wordGame.fxml"
                )
        );
        loader.setController(controller);
        Parent frame = loader.load();
        UpdateGui.setUpdateGui(controller);
        UpdateGui.setGame(game);
        try{
            setImage(controller.getPlayer1());
            setImage(controller.getPlayer2());
        }catch(InvalidArgumentException e){
            System.out.println("image not found");
        }

        //building table score
        this.buildTableScore(controller.getScoreTable());

        //adding gui buttons to list
        this.addButtons();

        //adding players
        // TODO: 4/22/2016  

        //initializing buttons before each turn
        // TODO: 4/22/2016


        for(int i=0;i<5;i++){//NUMBER of players
            this.game.generatePlayer(); //genereaza si incepe threadul
        }
        UpdateGui.setCurrentPlayer(game.getCurrentPlayer());

        this.myLettersAction();
        this.lettersAction();
        UpdateGui.update();


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

        frame.setStyle("-fx-background-color: rgba(216, 243, 255, 0.72);");
        primaryStage.setTitle("Word Game");
        primaryStage.setScene(new Scene(frame, 688, 483));





        primaryStage.show();
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
        for(int current=0;current<this.myLetters.size();current++){
            this.myLetters.get(current).setText(player.getTiles().get(current).toString());
        }
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
    protected void buildTableScore(TableView tableView){




        //// TODO: 4/22/2016 ma fut mai tarziu cu tabelu asta;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
