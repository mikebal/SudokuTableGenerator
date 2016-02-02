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
        return gameBoardInstance;

    }

    private boolean populateTable(int gameBoard[][], int remaining) {

        if (remaining == 1) { // if the whole table has been populated
            System.out.println("**************************************  " + remaining);
            return true;
        }
        int subtractedREMAINING = remaining - 1;
        ArrayList<Integer> possibilities = new ArrayList<Integer>();
        boolean solved = false;
        int cellToPopulate[] = getRandomEmptyCellToProcess(gameBoard);

        if (fillSingleCell(gameBoard, cellToPopulate[0], cellToPopulate[1], possibilities)) { // if we can put something here
            int value1 = possibilities.get(0);
            gameBoard[cellToPopulate[0]][cellToPopulate[1]] =  value1;
            possibilities.remove(0);
            System.out.println("**************************************  " + remaining);
            solved = populateTable(gameBoard, subtractedREMAINING);
        }
        else {
            gameBoard[cellToPopulate[0]][cellToPopulate[1]] = 0;
            return false;
        }

        if (!solved) {
            
  //          gameBoard[cellToPopulate[0]][cellToPopulate[1]] = 0;
            for (int index = 0; index < possibilities.size(); index++) {
                int value = possibilities.get(0);
                gameBoard[cellToPopulate[0]][cellToPopulate[1]] =value;
                possibilities.remove(0);
                if (populateTable(gameBoard, subtractedREMAINING)) {
                    solved = true;
                    break;
                }
            }
          //  return false;
        }


        //else
      //  gameBoard[cellToPopulate[0]][cellToPopulate[1]] = 0;
        if(!solved) {
            gameBoard[cellToPopulate[0]][cellToPopulate[1]] = 0;
            return false;
        }
        else
            return true;
       // if(!solved)
       //    return false;
       // else
        //   return true;
    }

    /*    if(fillSingleCell(gameBoard, cellToPopulate[0],cellToPopulate[1], possibilities))
        {
            System.out.println("Working on cell: [" + cellToPopulate[0] + "," + cellToPopulate[1] +"]      Remaining: " + remaining + "                 " + possibilities.get(0)  );
            printTable(gameBoard);
            gameBoard[cellToPopulate[0]][cellToPopulate[1]] = possibilities.get(0);
            possibilities.remove(0);
            if (!populateTable(gameBoard, remaining--)) {
                for(int index = 0; index < possibilities.size(); index++)
                {
                    System.out.println("Working on cell: [" + cellToPopulate[0] + "," + cellToPopulate[1] +"]      Remaining: " + remaining  + "                 " +  possibilities.get(0));
                    gameBoard[cellToPopulate[0]][cellToPopulate[1]] = possibilities.get(0);
                    possibilities.remove(0);
                    if(populateTable(gameBoard, remaining--)) {
                        solved = true;
                        break;
                    }
                }

            }

        }
        else{
            gameBoard[cellToPopulate[0]][cellToPopulate[1]] = 0;
            return false;
        }

        populateTable(gameBoard, remaining);
        if(solved)
           return true;
        else
            return false;*/
      //  System.out.println("Remaining: " + remaining + " [" + cellToPopulate[0] + "," + cellToPopulate[1] + "]");
     //   populateTable(gameBoard, remaining-1);
       // return true;

    private void printTable(int gameBoard[][]) {
        for (int y = 0; y < 9; y++){
            for (int x = 0; x < 9; x++) {
                System.out.print(gameBoard[x][y] + ",");
            }
            System.out.println("");
      }
    }
    private int[] getRandomEmptyCellToProcess(int gameBoard[][]){
        Random rand = new Random();
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
        return targetLocation;
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
