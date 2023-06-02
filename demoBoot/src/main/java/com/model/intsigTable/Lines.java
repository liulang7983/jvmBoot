package com.model.intsigTable;

import com.alibaba.fastjson.annotation.JSONField;

public class Lines {
    @JSONField(name = "angle")
    private int angle;

    @JSONField(name = "char_candidates_score")
    private Float[][] charCandidatesScore = new Float[][]{};

    @JSONField(name = "char_scores")
    private Float[] charScores = new Float[]{};

    @JSONField(name = "text")
    private String text;

    @JSONField(name = "char_positions")
    private int[][] charPositions = new int[][]{};

    @JSONField(name = "direction")
    private int direction;

    @JSONField(name = "char_candidates")
    private String[][] charCandidates = new String[][]{};

    @JSONField(name = "position")
    private int[] position = new int[]{};

    @JSONField(name = "score")
    private float score;

    @JSONField(name = "type")
    private String type;

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public Float[][] getCharCandidatesScore() {
        return charCandidatesScore;
    }

    public void setCharCandidatesScore(Float[][] charCandidatesScore) {
        this.charCandidatesScore = charCandidatesScore;
    }

    public Float[] getCharScores() {
        return charScores;
    }

    public void setCharScores(Float[] charScores) {
        this.charScores = charScores;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int[][] getCharPositions() {
        return charPositions;
    }

    public void setCharPositions(int[][] charPositions) {
        this.charPositions = charPositions;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String[][] getCharCandidates() {
        return charCandidates;
    }

    public void setCharCandidates(String[][] charCandidates) {
        this.charCandidates = charCandidates;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
