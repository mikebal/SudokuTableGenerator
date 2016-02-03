import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Michael on 2/2/2016.
 */
public class TableGenerator {

    public int[][] generateGame()
    {
        int gameBoardInstance[][] = new int[9][9];
        populateTable(gameBoardInstance, 81);
        printTable(gameBoardInstance);
        return gameBoardInstance;

    }

    private boolean populateTable(int gameBoard[][], int remaining) {

        if (remaining == 0) // if the whole table has been populated
            return true;

        ArrayList<Integer> possibilities = new ArrayList<Integer>();
        int cellToPopulate[] = getRandomEmptyCellToProcess(gameBoard);
         if(fillSingleCell(gameBoard, cellToPopulate[0], cellToPopulate[1], possibilities))
        { // if we can put something here
           for (int index = 0; index < possibilities.size(); index++)
           {
                gameBoard[cellToPopulate[0]][cellToPopulate[1]] = possibilities.get(index);
                if (populateTable(gameBoard, remaining - 1)) {
                    return true;
                }
            }
        }
        gameBoard[cellToPopulate[0]][cellToPopulate[1]] = 0;
        return false;
    }


    private void printTable(int gameBoard[][]) {
        for (int y = 0; y < 9; y++){
            for (int x = 0; x < 9; x++) {
                System.out.print(gameBoard[x][y] + ",");
            }
            System.out.println("");
      }
    }
    private int[] getRandomEmptyCellToProcess(int gameBoard[][]){
        int x_and_y[] = new int[2];

        for(int y = 0; y < 9; y++)
            for (int x = 0; x < 9; x++)
            {
                if(gameBoard[x][y] == 0)
                {
                    x_and_y[0] = x;
                    x_and_y[1] = y;
                  //  System.out.println("Looking at [" + x +"," + y +"]");
                    return x_and_y;
                }
            }
        /*  Random rand = new Random();
        int targetLocation[] = new int[2];
        final int X_COORDINATE = 0;
        final int Y_COORDINATE = 1;
        targetLocation[X_COORDINATE] = rand.nextInt(9);
        targetLocation[Y_COORDINATE] = rand.nextInt(9);
        int target = gameBoard[targetLocation[X_COORDINATE]][targetLocation[Y_COORDINATE]];

        while(target != 0) // search for empty cell
        {
            targetLocation[X_COORDINATE]++;
            if(targetLocation[X_COORDINATE] == 9) // Out of bounds check
            {
               targetLocation[X_COORDINATE] = 0;
               targetLocation[Y_COORDINATE]++;
               if (targetLocation[Y_COORDINATE] == 9)  // Out of bounds check
                   targetLocation[Y_COORDINATE] = 0;
            }
            target = gameBoard[targetLocation[X_COORDINATE]][targetLocation[Y_COORDINATE]];
        }
        return targetLocation;*/
        return x_and_y;
    }

    private boolean fillSingleCell(int gameBoard[][], final int X_COORDINATE, final int Y_COORDINATE, ArrayList<Integer> possibilities){

        Random rand = new Random();
        int value = rand.nextInt(9) + 1;  //Range of 1 - 9
        int attempts = 0;                  // number of attempts to populate cell
        boolean canBeFilled = true;    // assume value can/will be added
        ValidLineManager validLineManager = new ValidLineManager();

        while (attempts < 10){
            if(validLineManager.isMoveValid(gameBoard,X_COORDINATE,Y_COORDINATE, value))
               possibilities.add(value);
            value++;
            attempts++;

            if(value > 9)  // rollover
                value = 0;
        }
        if(possibilities.size() == 0)
            canBeFilled = false;

        return canBeFilled;
    }
}
