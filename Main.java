import java.util.*;

class Main
{

  private static char[][] board = new char[8][8];

  public static void main(String[] args)
  {
    Scanner keyboard = new Scanner(System.in);

    //Gets the FEN number from the user
    System.out.print("FEN number: ");
    String fen = keyboard.nextLine();

    decode(fen);
    printBoard();
  }

  private static void decode(String fen)
  {
    int row = 0;
    int col = 0;

    int numSpaces = 0;

    for(int i = 0; i < fen.length(); i++)
    {
      char ch = fen.charAt(i);

      //for when the FEN number is giving instructions about piece positions
      if(numSpaces = 0)
      {
        //for when the FEN number is indicating a new line
        if(ch == '/')
        {
          row++;
          col = 0;
        }

        //for when the FEN number is indicating a couple of squares without pieces
        if(Character.isDigit(ch))
        {
          addEmpty(row, col, Character.getNumericValue(ch));
          col += Character.getNumericValue(ch);
          
        }

        //for when the FEN number is indicating a piece on a square
        if(Character.isLetter(ch))
        {
          addPiece(row, col, ch);
          col++;
        }
      }

      if(Character.isWhiteSpace(ch))
        {
          numSpaces++;
        }
    }
  }

  //puts the piece into the board array
  private static void addPiece(int row, int col, char piece)
  {
    board[row][col] = piece; 
  }

  //adds a blank space for squares without pieces. Fixes formatting.
  private static void addEmpty(int row, int col, int amt)
  {
    for(int i = 0; i < amt; i++)
    {
      board[row][col + i] = ' ';
    }
  }

  //prints a image of the board to the console with info of whos turn it is and castling rights and enpassent possiblites
  private static void printBoard()
  {
    for(int r = 0; r < 8; r++)
    {
      System.out.println("\n_________________________________");
      for(int c = 0; c < 8; c++)
      {
        System.out.print("| " + board[r][c] + " ");
      }
      System.out.print("|");
    }
    System.out.println("\n_________________________________");
  }
}