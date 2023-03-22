import java.util.*;
import java.util.PriorityQueue;
import java.util.Set;

public class AStarTree {

  private static final int ROWS = 6;
  private static final int COLS = 8;
  // private static final int MAX_ITERATIONS = ROWS * COLS;

  private static final int[][] NEIGHBORS = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

  private static int heuristic(int row1, int col1, int row2, int col2) {
    // Heuristic function: Euclidean distance
    int dRow = row1 - row2;
    int dCol = col1 - col2;
    return (int) Math.sqrt(dRow * dRow + dCol * dCol);
  }

  private static int distance(int row1, int col1, int row2, int col2) {
    // Heuristic function: Euclidean distance
    int dRow = row1 - row2;
    int dCol = col1 - col2;
    return (int) Math.abs(dRow) + Math.abs(dCol);
  }

  public static List<Node> getNeighbors(Node node, boolean[][] grid, int[] end) {
    List<Node> neighbors = new ArrayList<>();
    for (int i = 0; i < NEIGHBORS.length; i++) {
      int newRow = node.row + NEIGHBORS[i][0];
      int newCol = node.col + NEIGHBORS[i][1];
      if (newRow >= 0 && newRow < ROWS && newCol >= 0 && newCol < COLS && !grid[newRow][newCol]) {
        int cost = distance(node.getRow(), node.getCol(), newRow, newCol);
        Node neighbor = new Node(newRow, newCol, node.g + cost, heuristic(newRow, newCol, end[0], end[1]), node);
        neighbors.add(neighbor);
      }
    }
    return neighbors;
  }

  public static Node aStar(boolean[][] grid, int[] start, int[] end) {
    Node startNode = new Node(start[0], start[1], 0, 0, null);
    PriorityQueue<Node> openSet = new PriorityQueue<>();
    Set<Node> closedSet = new HashSet<>();
    openSet.offer(startNode);

    int iterations = 0;
    while (!openSet.isEmpty() && iterations < MAX_ITERATIONS) {
      iterations++;
      Node current = openSet.poll();
      System.out.println();
      System.out.print("current Node: ");
      System.out.println("[" + current.getRow() + "," + current.getCol() + "]");
      System.out.print("openSet:");
      for (Node n : openSet) {
        System.out.print("[" + n.getRow() + "," + n.getCol() + "]");
      }
      System.out.println();
      System.out.print("closedSet");
      for (Node n : closedSet) {
        System.out.print("[" + n.getRow() + "," + n.getCol() + "]");
      }
      System.out.println();
      System.out.println("---------------------------------------------------");

      if (current.row == end[0] && current.col == end[1]) {
        return current;
      }
      closedSet.add(current);
      for (Node neighbor : getNeighbors(current, grid, end)) {
        if (closedSet.contains(neighbor)) {
          System.out.println("CONTAINS NEIGHBORS");
          continue;
        }
        int tentativeG = current.g + distance(current.getRow(), current.getCol(), neighbor.getRow(), neighbor.getCol());
        // int tentativeG = current.g + 1;
        if (!openSet.contains(neighbor) || tentativeG < neighbor.g) {
          neighbor.g = tentativeG;
          neighbor.parent = current;
          if (openSet.contains(neighbor)) {
            System.out.println("INSIDE NEIGHBORS");
            System.out.print("openSet:");
            for (Node n : openSet) {
              System.out.print("[" + n.getRow() + "," + n.getCol() + "]");
              System.out.println("F: " + n.getF());
            }
            System.out.println("-------------------------");
            System.out.println();
          }
          if (!openSet.contains(neighbor)) {
            System.out.println("ADDING NEIGHBORS");
            System.out.println("[" + neighbor.getRow() + "," + neighbor.getCol() + "]");
            openSet.offer(neighbor);
          }
        }
      }
    }
    return null;
  }
}
