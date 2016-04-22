package graphicInterface;

/**
 * Created by dryflo on 4/22/2016.
 */
public class UpdateGui {
    private static GuiController controller;
    private static int currentPlayer;
    public static void update(){
        controller.getTimer().setText("30");
        controller.getCurrentPlayer().setText(Integer.toString(currentPlayer));
    }
    public static void setUpdateGui(GuiController control){
        controller=control;
    }
    public static void setCurrentPlayer(int current){
        currentPlayer=current;
    }

}
