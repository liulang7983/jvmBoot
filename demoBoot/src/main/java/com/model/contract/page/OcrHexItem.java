package com.model.contract.page;

/**
 * Created by kang on 2019-1-25.
 */
public class OcrHexItem {

    private ItemCoord itemcoord = new ItemCoord();

    private double itemconf = 0.0;

    private String itemstring = "";

    private Words[] words = new Words[]{};

    private CandWord[] candword = new CandWord[]{};

    private CoordPoint coordpoint = new CoordPoint();

    private CoordPoint[] wordcoordpoint = new CoordPoint[]{};

    private Parag parag = new Parag();

    public CandWord[] getCandword() {
        return candword;
    }

    public void setCandword(CandWord[] candword) {
        this.candword = candword;
    }

    public Parag getParag() {
        return parag;
    }

    public void setParag(Parag parag) {
        this.parag = parag;
    }

    public ItemCoord getItemcoord() {
        return itemcoord;
    }

    private WordRectangle[] coords = new WordRectangle[]{};

    public WordRectangle[] getCoords() {
        return coords;
    }

    public void setCoords(WordRectangle[] coords) {
        this.coords = coords;
    }

    public void setItemcoord(ItemCoord itemcoord) {
        this.itemcoord = itemcoord;
    }

    public double getItemconf() {
        return itemconf;
    }

    public void setItemconf(double itemconf) {
        this.itemconf = itemconf;
    }

    public String getItemstring() {
        return itemstring;
    }

    public void setItemstring(String itemstring) {
        this.itemstring = itemstring;
    }

    public Words[] getWords() {
        return words;
    }

    public void setWords(Words[] words) {
        this.words = words;
    }

    public CoordPoint getCoordpoint() {
        return coordpoint;
    }

    public void setCoordpoint(CoordPoint coordpoint) {
        this.coordpoint = coordpoint;
    }

    public CoordPoint[] getWordcoordpoint() {
        return wordcoordpoint;
    }

    public void setWordcoordpoint(CoordPoint[] wordcoordpoint) {
        this.wordcoordpoint = wordcoordpoint;
    }
}
