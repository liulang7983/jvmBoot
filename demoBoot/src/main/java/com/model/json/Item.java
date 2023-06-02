package com.model.json;

import java.awt.*;
import java.util.ArrayList;

public class Item {
    private String itemstring;
    private Rectangle itemcoord;
    private ArrayList<String> coords;
    private String hightlight;

    private Parag parag;

    public String getHightlight() {
        return hightlight;
    }

    public void setHightlight(String hightlight) {
        this.hightlight = hightlight;
    }

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

    public ArrayList<String> getCoords() {
        return coords;
    }

    public void setCoords(ArrayList<String> coords) {
        this.coords = coords;
    }

    public Parag getParag() {
        return parag;
    }

    public void setParag(Parag parag) {
        this.parag = parag;
    }
}
