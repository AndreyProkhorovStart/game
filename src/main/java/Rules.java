import java.util.ArrayList;
import java.util.List;

public class Rules {

    public List<Integer> winStrace(int countMove, int computerMove) {
        int countWin = (countMove - 1) / 2;
        List<Integer> indexWin = new ArrayList<Integer>();
        for(int i=0; i < countWin; i++){
            int index = (computerMove+i+1)%countMove;
            if (index==0) indexWin.add(countMove);
            else indexWin.add(index);
        }
        return indexWin;
    }

    public boolean checkUserWin(int countMove, int computerMove, int userMove){
        List<Integer> indexWin = winStrace(countMove, computerMove);
        if (indexWin.contains(userMove))
            return true;
        else
            return false;
    }
}
