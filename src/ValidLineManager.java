/**
 * Created by Michael on 2/2/2016.
 * The purpose of this class is to determine if a given random int (1-9) can be inserted into the game board at a given location
 *
 * inputs: int gameBoard[][]  -> the current game being generated
 *         final int X_COORDINATE -> array location of gameBoard[X_COORDINATE][] where the new Value is being added to
 *         final int Y_COORDINATE -> array location of gameBoard[][Y_COORDINATE] where the new Value is being added to
 *         final int newValue -> the value that we want to add to the gameBoard and given coordinates.
 * expectation:
 *      gameBoard[X_COORDINATE][Y_COORDINATE] is unassigned (default 0)
 */
public class ValidLineManager
{
    public boolean isMoveValid(int gameBoard[][], final int X_COORDINATE, final int Y_COORDINATE, final int newValue)
    {
        boolean isValid = true;

        if(!isHorizontalValid(gameBoard,Y_COORDINATE, newValue))
            isValid = false;
        if(isValid && !isVerticalValid(gameBoard, X_COORDINATE, newValue))
            isValid = false;
        if(isValid && !isBoxValid(gameBoard, X_COORDINATE, Y_COORDINATE, newValue))
            isValid = false;
        return isValid;
    }
    private boolean isHorizontalValid(int gameBoard[][], final int Y_COORDINATE, final int newValue){
        int line[] = new int[9];
        for(int i = 0; i < 9; i++){
            line[i] = gameBoard[i][Y_COORDINATE];
        }
        return isLineValid(line, newValue);
    }
    private boolean isVerticalValid(int gameBoard[][], final int X_COORDINATE, final int newValue){
        int line[] = new int[9];
        for(int i = 0; i < 9; i++){
            line[i] = gameBoard[X_COORDINATE][i];
        }
        return isLineValid(line, newValue);
    }
    private boolean isBoxValid(int gameBoard[][], final int X_COORDINATE, final int Y_COORDINATE, final int newValue){
        int line[] = new int[9];
        int index = 0;
        int x_boxStart = X_COORDINATE % 3;
        int y_boxStart = Y_COORDINATE % 3;

        for(int y_pos = 0; y_pos < 3; y_pos++){
            for(int x_pos = 0; x_pos < 3; x_pos++){
                line[index] = gameBoard[x_boxStart + x_pos][y_boxStart + y_pos];
                index++;
            }
        }
        return isLineValid(line, newValue);
    }

    private boolean isLineValid(int line[], final int newValue){

        boolean isValid = true;

        for(int index = 0; index < 9; index++)
           if(line[index] == newValue)
               isValid = false;
        return isValid;
    }
}
