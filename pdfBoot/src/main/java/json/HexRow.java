package json;

import java.util.ArrayList;

public class HexRow {

    private ArrayList<Item> row;

    public ArrayList<Item> getRow() {
        if(row==null){
            row=new ArrayList<Item>();
        }
        return row;
    }

    public void setRow(ArrayList<Item> items) {
        this.row = items;
    }
}
