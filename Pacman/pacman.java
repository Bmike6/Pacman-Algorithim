import java.util.*;
import java.io.*;

public class pacman
{
    public static void main(String[] args) throws IOException
    {
        Scanner scan = new Scanner(new File("pacman.txt"));
        int lenOfArray = Integer.parseInt(scan.nextLine());
        char[][] pacmanGrid = new char[lenOfArray][lenOfArray];
        int cordsX = 0;
        int cordsY = 0;
        String line = "";
        
        for (int i=0; i<pacmanGrid.length; i++)
        {
            line = scan.nextLine();
            if (line.contains("C"))
            {
                cordsY = i;
                cordsX = line.indexOf('C');
            }
            pacmanGrid[i] = line.toCharArray();

        }
        int[] arguments = {
            0, // solveable?
            0, // steps taken
        };
        int x = Integer.parseInt(String.valueOf('2'));
        solvePacman(pacmanGrid, arguments, false, cordsX, cordsY);
        if (arguments[0]==0)
        {
            System.out.println("Pacman should retire");
        }


    }
 
    static void solvePacman(char[][] pacmanGrid, int[] args, boolean powerEnabled, int x, int y)
    {
        if (x<0||y<0||x>=pacmanGrid.length||y>=pacmanGrid[0].length||pacmanGrid[x][y]=='#'||pacmanGrid[x][y]=='A'&&!powerEnabled||pacmanGrid[x][y]=='-')
        {
            return;
        }
        else if (pacmanGrid[x][y]=='X')
        {
            args[0] = 1;
            System.out.println("PacMan can escape in "+args[1]+" moves.");
            return; 
        }
        
        else if (pacmanGrid[x][y]=='@')
        {
            for (int i=0; i<pacmanGrid.length; i++)
            {
                for (int j=0; j<pacmanGrid[i].length; j++)
                {
                    if (pacmanGrid[i][j]=='-')
                    {
                        pacmanGrid[i][j] = '.';
                    }
                }
            }
            powerEnabled = true;
        }
        args[1] += 1;
        pacmanGrid[x][y] = '-';
        solvePacman(pacmanGrid, args, powerEnabled, x-1, y); // left
        solvePacman(pacmanGrid, args, powerEnabled, x+1, y); // right
        solvePacman(pacmanGrid, args, powerEnabled, x, y+1); // up
        solvePacman(pacmanGrid, args, powerEnabled, x, y-1); // down

    }

}