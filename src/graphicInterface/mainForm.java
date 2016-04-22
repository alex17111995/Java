package graphicInterface;

import com.sun.javaws.exceptions.InvalidArgumentException;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by dryflo on 4/22/2016.
 */
public class mainForm extends Application {
    private GuiController controller = new GuiController();
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "wordGame.fxml"
                )
        );
        loader.setController(controller);
        Parent frame = loader.load();
        try{
            setImage(controller.getPlayer1());
            setImage(controller.getPlayer2());
        }catch(InvalidArgumentException e){
            System.out.println("sada");
        }
        buildTableScore(controller.getScoreTable());
        frame.setStyle("-fx-background-color: rgba(216, 243, 255, 0.72);");
        primaryStage.setTitle("Word Game");
        primaryStage.setScene(new Scene(frame, 688, 483));




        primaryStage.show();
    }

    protected void setImage(ImageView imageView)throws  InvalidArgumentException{
        String filePath=System.getProperty("user.dir");
        File file = new File(filePath,"rsz_images.png");
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
