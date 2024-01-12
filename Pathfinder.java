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

    // Generate rocks

    int rockStartX = rand.nextInt(width - 1);
    int rockStartY = rand.nextInt(height - 1);

    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {

        if (matrix[i][j] != 1) {
          matrix[rockStartX + i][rockStartY + j] = 2;
        }
      }
    }

    rockStartX = rand.nextInt(width - 1);
    rockStartY = rand.nextInt(height - 1);

    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {

        if (matrix[i][j] != 1) {
          matrix[rockStartX + i][rockStartY + j] = 2;
        }
      }
    }

    rockStartX = rand.nextInt(width - 1);
    rockStartY = rand.nextInt(height - 1);

    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {

        if (matrix[rockStartX + i][rockStartY + j] != 1) {
          matrix[rockStartX + i][rockStartY + j] = 2;
        }
      }
    }

    // Generate Miner
    int minerX = rand.nextInt(width);
    int minerY = rand.nextInt(height);

    if (matrix[minerX][minerY] == 0) {
      matrix[minerX][minerY] = 3;
    }

    return matrix;
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