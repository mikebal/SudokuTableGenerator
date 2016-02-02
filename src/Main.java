
/**
 * Created by Michael on 2/2/2016.
 */
public class Main{
    public static void main(String args[])
    {
        int gameBoard[][] = new int[9][9];
        TableGenerator tableGenerator = new TableGenerator();
        tableGenerator.generateGame();
    }
}
