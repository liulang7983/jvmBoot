package json;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(
        name = "tableRes"
)
@XmlAccessorType(XmlAccessType.FIELD)
public class TableRes {

    public ArrayList<Table> getTables() {
        return tables;
    }

    public void setTables(ArrayList<Table> tables) {
        this.tables = tables;
    }

    @XmlElementWrapper(
            name = "tables"
    )
    @XmlElement(
            name = "e"
    )
    private ArrayList<Table> tables;

}
