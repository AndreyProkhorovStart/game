
import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;

import java.util.ArrayList;
import java.util.List;

public class TableHelp {
    private Rules rules = new Rules();
    private List<List<Integer>> winTable = new ArrayList<List<Integer>>();

    private void fillWinTable(int countMove){
        for (int i=0; i<countMove; i++){
            winTable.add(i, rules.winStrace(countMove, i+1));
        }
    }

    public void showHelpTable(int countMove, String[] headers){
        fillWinTable(countMove);
        String[][] data = new String[countMove][countMove+1];
        for(int i=0; i < headers.length-1; i++){
            data[i][0] = headers[i+1];
            for(int j=1; j < headers.length; j++){
                if (i+1 == j){
                    data[i][j] = "Draw";
                }
                else if(winTable.get(i).contains(j)) {
                    data[i][j] = "Win";
                }
                else{
                    data[i][j] = "Lose";
                }
            }
        }
        System.out.println("Table help");
        System.out.println(AsciiTable.getTable(headers, data));
    }
}
