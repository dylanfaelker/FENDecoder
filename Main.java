import java.util.*;
import java.lang.Math;

class Main
{
  //FEN number
  private static String fen = new String();
  
  //Game information
  private static char[][] board = new char[8][8];
  private static String turn = new String();
  private static boolean CastleWKing = false;
  private static boolean CastleWQueen = false;
  private static boolean CastleBKing = false;
  private static boolean CastleBQueen = false;
  private static String enpassentOn = new String();
  private static int halfMoves = 0;
  private static int moveCount = 0;

  public static void main(String[] args)
  {
    Scanner keyboard = new Scanner(System.in);

    //Gets the FEN number from the user
    System.out.print("FEN number: ");
    fen = keyboard.nextLine();

    decode();
    printBoard();
  }

  private static void decode()
  {
    int row = 0;
    int col = 0;

    int numSpaces = 0;

    for(int i = 0; i < fen.length(); i++)
    {
      char ch = fen.charAt(i);

      //for when the FEN number is giving instructions about piece positions
      if(numSpaces == 0)
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

      //Determines whos turn is is
      if(numSpaces == 1)
      {
        if(ch == 'w')
        {
          turn = "White";
        }
        else
        {
          turn = "Black";
        }
      }

      //Determines castling rights
      if(numSpaces == 2)
      {
        if(ch == 'K')
        {
          CastleWKing = true;
        }
        if(ch == 'Q')
        {
          CastleWQueen = true;
        }
        if(ch == 'k')
        {
          CastleBKing = true;
        }
        if(ch == 'q')
        {
          CastleBQueen = true;
        }
      }

      //Determines where enpassent is possible
      if(numSpaces == 3)
      {
        if(ch != '-' && ch != ' ')
        {
          enpassentOn = fen.substring(i, i+2);
          i++;
        }
      }

      //Determines halfmoves since last capture/pawn move
      if(numSpaces == 4)
      {
        if(ch != ' ')
        {
          if(fen.charAt(i+1) == ' ')
          {
            halfMoves = Character.getNumericValue(ch);
          }
          else
          {
            halfMoves = Character.getNumericValue(ch)*10 + Character.getNumericValue(fen.charAt(i+1));
            i++;
          }
        }
      }

      //Determines number of moves played
      if(numSpaces == 5)
      {
        if(ch != ' ')
        {
          int chars = 1;
          for(int j = i+1; j < fen.length(); j++)
          {
            chars++;
          }

          for(int k = 0; k < chars; k++)
          {
            moveCount += Character.getNumericValue(fen.charAt(i+k)) * Math.pow(10, chars - k - 1);
          }
          i += chars;
        }
      }

      //Changes what the decoder is understanding
      if(ch == ' ')
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

  //prints a image of the board to the console with info of whos turn it is and castling rights and enpassent possiblites and move counts
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
    System.out.println("\n_________________________________\n");

    System.out.println("Turn: " + turn);
    System.out.println("\nCastling:\nWhite king side: " + CastleWKing + "\nWhite queen side: " + CastleWQueen  + "\nBlack king side: " + CastleBKing + "\nBlack queen side: " + CastleBQueen);
    System.out.println("\nEnpassent: " + enpassentOn);
    System.out.println("\nHalf moves since capture or pawn move: " + halfMoves);
    System.out.println("\nMove count: " + moveCount);
  }
}