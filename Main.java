import java.util.*;

class Main
{

  private static char[][] board = new char[8][8];

  public static void main(String[] args)
  {
    Scanner keyboard = new Scanner(System.in);

    //Gets the FEn number from the user
    System.out.print("FEN number: ");
    String fen = keyboard.nextLine();

    decode(fen);
    printBoard();
  }

  private static void decode(String fen)
  {
    int row = 0;
    int col = 0;

    for(int i = 0; i < fen.length(); i++)
    {
      char ch = fen.charAt(i);

      if(ch == '/')
      {
        row++;
        col = 0;
      }

      if(Character.isDigit(ch))
      {
        addEmpty(row, col, Character.getNumericValue(ch));
        col += Character.getNumericValue(ch);
        
      }

      if(Character.isLetter(ch))
      {
        addPiece(row, col, ch);
        col++;
      }

    }
  }

  
  private static void addPiece(int row, int col, char piece)
  {
    board[row][col] = piece; 
  }

  private static void addEmpty(int row, int col, int amt)
  {
    for(int i = 0; i < amt; i++)
    {
      board[row][col + i] = ' ';
    }
  }

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