package json;

import java.awt.*;
import java.util.List;



public class Item {
    private String itemstring;
    private Rectangle itemcoord;
    //单字坐标  x,y,width,height
    private List<String> coords;
    private String hightlight;

    private Parag parag;

    public String getItemstring() {
        return itemstring;
    }

    public void setItemstring(String itemstring) {
        this.itemstring = itemstring;
    }

    public Rectangle getItemcoord() {
        return itemcoord;
    }

    public void setItemcoord(Rectangle itemcoord) {
        this.itemcoord = itemcoord;
    }

    public List<String> getCoords() {
        return coords;
    }

    public void setCoords(List<String> coords) {
        this.coords = coords;
    }

    public String getHightlight() {
        return hightlight;
    }

    public void setHightlight(String hightlight) {
        this.hightlight = hightlight;
    }

    public Parag getParag() {
        return parag;
    }

    public void setParag(Parag parag) {
        this.parag = parag;
    }
}
