import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length == 0 || args.length == 1) {
            System.out.println("Please, enter an odd >= 3 number of possible moves");
            return;
        } else if (args.length % 2 == 0) {
            System.out.println("Please, enter an odd number of moves >= 3");
            return;
        }
        ArrayList<String> moves = new ArrayList<>(Arrays.asList(args));
        long countMoves = moves.stream().distinct().count();
        if(countMoves != moves.size()){
            System.out.println("There should be no duplicates in the list of moves");
            System.out.println("rock paper scissors");
            return;
        }
        GenerationHMAC gen = new GenerationHMAC();
        int computerMove = (int) ( Math.random() * (countMoves));
        String HMACkey = gen.generationKey();
        String HMAC = gen.generationHMAC(args[computerMove]);
        System.out.println("HMAC: " + HMAC);

        boolean flagError = false;
        while (!flagError) {
            System.out.println("Available moves:\n");
            for (int i = 0; i < moves.size(); i++) {
                System.out.println(i + 1 + " - " + moves.get(i));
            }
            System.out.println("0 - exit");
            System.out.println("? - help");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String selectUser = reader.readLine();
            if (selectUser.equals("0")){
                System.out.println("Exit....");
                flagError = true;
            }
            else if(selectUser.equals("?")){
                TableHelp tableHelp = new TableHelp();
                moves.add(0, " ");
                String[] temp = moves.toArray(new String[0]);
                tableHelp.showHelpTable(args.length, temp);
                System.out.println("\n");
                moves.remove(0);
            }
            else if(!isDigit(selectUser)){
                System.out.println("You entered an invalid character!");
            }
            else if(Integer.parseInt(selectUser)>=1 && Integer.parseInt(selectUser)<= args.length){
                Rules rules = new Rules();
                System.out.println("Enter you move: " + selectUser);
                System.out.println("You move: " + args[Integer.parseInt(selectUser)-1]);
                System.out.println("Computer move: " + args[computerMove]);
                if(rules.checkUserWin(args.length, computerMove, Integer.parseInt(selectUser)+1)){
                    System.out.println("You win!");
                }
                else{
                    System.out.println("Computer win!");
                }
                System.out.println("HMAC key " + HMACkey);
                flagError = true;
            }
            else{
                System.out.println("You entered a number that is greater than the maximum allowed\n" +
                        "Enter the number 1 - " + args.length);
            }
        }
    }

    public static boolean isDigit(String str){
        try{
            Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException ex){
            return false;
        }
    }
}
