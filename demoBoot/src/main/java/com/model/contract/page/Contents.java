package com.model.contract.page;

import com.alibaba.fastjson.annotation.JSONField;

public class Contents {

    @JSONField(name = "itemcoord")
    private ItemCoord itemCoord = new ItemCoord();

    @JSONField(name = "itemconf")
    private float itemconf = 0f;

    @JSONField(name = "itemstring")
    private String itemstring;

    @JSONField(name = "coords")
    private Coords[] coords = new Coords[]{};

    @JSONField(name = "words")
    private Words[] words = new Words[]{};

    @JSONField(name = "candword")
    private CandWord[] candWord = new CandWord[]{};

    @JSONField(name = "parag")
    private Parag parag = new Parag();

    @JSONField(name = "coordpoint")
    private CoordPoint coordPoint = new CoordPoint();

    @JSONField(name = "wordcoordpoint")
    private WordCoordPoint[] wordCoordPoint = new WordCoordPoint[]{};

    public ItemCoord getItemcoord() {
        return itemCoord;
    }

    public void setItemcoord(ItemCoord itemcoord) {
        this.itemCoord = itemCoord;
    }

    public float getItemconf() {
        return itemconf;
    }

    public void setItemconf(float itemconf) {
        this.itemconf = itemconf;
    }

    public String getItemstring() {
        return itemstring;
    }

    public void setItemstring(String itemstring) {
        this.itemstring = itemstring;
    }

    public Coords[] getCoords() {
        return coords;
    }

    public void setCoords(Coords[] coords) {
        this.coords = coords;
    }

    public Words[] getWords() {
        return words;
    }

    public void setWords(Words[] words) {
        this.words = words;
    }

    public CandWord[] getCandWord() {
        return candWord;
    }

    public void setCandWord(CandWord[] candWord) {
        this.candWord = candWord;
    }

    public Parag getParag() {
        return parag;
    }

    public void setParag(Parag parag) {
        this.parag = parag;
    }

    public CoordPoint getCoordPoint() {
        return coordPoint;
    }

    public void setCoordPoint(CoordPoint coordPoint) {
        this.coordPoint = coordPoint;
    }

    public WordCoordPoint[] getWordCoordPoint() {
        return wordCoordPoint;
    }

    public void setWordCoordPoint(WordCoordPoint[] wordCoordPoint) {
        this.wordCoordPoint = wordCoordPoint;
    }
}
