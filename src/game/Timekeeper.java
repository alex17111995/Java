package game;

import graphicInterface.GuiController;
import graphicInterface.UpdateGui;

/**
 * Created by Alex on 4/22/2016.
 */
public class Timekeeper implements Runnable{
    @Override
    public void run() {
        long time=0;
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            time+=1;
            UpdateGui.updateTime(time);

        }
    }
}
