import java.util.Random;

public class Pathfinder {
  public static void main(String[] args) {
    int height = 10;
    int width = 10;

    int[][] matrix = createMatrix(width, height);
    displayMatrix(matrix);
  }

  public static int[][] createMatrix(int width, int height) {
    int[][] matrix = new int[width][height];

    Random rand = new Random();
    int starting_point = rand.nextInt(width);
    int stopping_point = rand.nextInt(height);

    // Generate graph and snake
    for (int i = 0; i < width; i++) {
      if (i == 0) {
        matrix[0][starting_point] = 1;
      }

      for (int j = 0; j < height; j++) {
        if (i != 0 && matrix[i - 1][j] == 1 && i <= stopping_point) {
          matrix[i][j] = 1;
        } else {
          if (matrix[i][j] != 1)
            matrix[i][j] = 0;
        }
      }
    }

    // Generate 3 rocks
    for (int i = 0; i < 3; i++) {
      generateRock(rand, width, height, matrix);
    }

    // Generate Miner
    generateMiner(rand, width, height, matrix);

    return matrix;
  }

  private static void generateRock(Random rand, int width, int height, int[][] matrix) {
    int rockStartX = rand.nextInt(width - 1);
    int rockStartY = rand.nextInt(height - 1);

    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {

        if (matrix[i][j] != 1) {
          matrix[rockStartX + i][rockStartY + j] = 2;
        }
      }
    }
    // return matrix;
  }

  private static void generateMiner(Random rand, int width, int height, int[][] matrix) {

    // left vs right or top vs down; zero means left or up, one means right or down
    int position = rand.nextInt(2);

    // generate numbers to determine if we are doing right left or up down
    int num1 = rand.nextInt(width);
    int num2 = rand.nextInt(height);

    // determine coordinates of miners, this takes care of if minerX != 0 and
    // position == 1
    int minerX = (num1 > num2) ? num1 : 0;
    int minerY = 0;

    if (minerX == 0 && position == 0) {
      minerY = num2;
    } else if (minerX == 0 && position == 1) {
      minerY = num2;
      minerX = width - 1;
    } else if (minerX != 0 && position == 1) {
      minerY = height - 1;
    }

    if (matrix[minerY][minerX] == 0) {
      matrix[minerY][minerX] = 3;
    } else {
      generateMiner(rand, width, height, matrix);
    }
  }

  public static void displayMatrix(int[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }
}