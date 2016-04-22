package graphicInterface;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by dryflo on 4/22/2016.
 */
public class TableRow {
    private final SimpleStringProperty Player;
    private final SimpleStringProperty Word;
    private final SimpleStringProperty Points;
    private final SimpleStringProperty Total;


    public TableRow(String Player, String Word, String Points,String Total) {
        this.Player=new SimpleStringProperty(Player);
        this.Word=new SimpleStringProperty(Word);
        this.Points=new SimpleStringProperty(Points);
        this.Total=new SimpleStringProperty(Total);
    }

    public String getPlayer() {
        return Player.get();
    }

    public SimpleStringProperty playerProperty() {
        return Player;
    }

    public void setPlayer(String player) {
        this.Player.set(player);
    }

    public String getWord() {
        return Word.get();
    }

    public SimpleStringProperty wordProperty() {
        return Word;
    }

    public void setWord(String word) {
        this.Word.set(word);
    }

    public String getPoints() {
        return Points.get();
    }

    public SimpleStringProperty pointsProperty() {
        return Points;
    }

    public void setPoints(String points) {
        this.Points.set(points);
    }

    public String getTotal() {
        return Total.get();
    }

    public SimpleStringProperty totalProperty() {
        return Total;
    }

    public void setTotal(String total) {
        this.Total.set(total);
    }
}
