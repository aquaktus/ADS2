import java.io.*;
import java.util.*;

public class main {
  public static void main (String[] args) throws IOException {

    Scanner sc    = new Scanner(new File(args[0]));
    sc = new Scanner(new File(args[0]));
    int noCustomers  = sc.nextInt();
    node[] customers = new node[noCustomers];

    Integer xMin = 0;
    Integer xMax = 0;
    Integer yMin = 0;
    Integer yMax = 0;

    for (int i=0; i<noCustomers; i++){
      int x = sc.nextInt();
      int y = sc.nextInt();
      xMin  = min(xMin,x);
      xMax  = max(xMax,x);
      yMin  = min(yMin,y);
      yMax  = max(yMax,y);

      customers[i] = new node(x, y);

    }

    dep[] depots = new dep[Integer.parseInt(args[1])];
    for (int i=0; i<Integer.parseInt(args[1]); i++){
      depots[i] = new dep(randInt(xMin,xMax),randInt(yMin,yMax));
      System.out.println(depots[i].x);
    }
  }

  public static void calculateDist(node[] nodes, dep[] depots){
    for (int i = 0; i<nodes.length; i++){
      dep closest = depots[0];
      int nodex = nodes[i].x;
      for (int j = 0; j<depots.length; j++){

      }
    }
  }

  public static int max(int a, int b){
    if (a >= b) return a;
    return b;
  }

  public static int min(int a, int b){
    if (a <= b) return a;
    return b;
  }



  public static int randInt(int min, int max) {

    Random rand = new Random();

    
    int randomNum = rand.nextInt((max - min) + 1) + min;

    return randomNum;
  }
}
