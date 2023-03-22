public class Node implements Comparable<Node> {
  int row;
  int col;
  int g;
  int h;
  Node parent;

  public Node(int row, int col, int g, int h, Node parent) {
    this.row = row;
    this.col = col;
    this.g = g;
    this.h = h;
    this.parent = parent;
  }

  public int getF() {
    return g + h;
  }

  public int getG() {
    return g;
  }

  public int getH() {
    return h;
  }

  public int getCol() {
    return col;
  }

  public int getRow() {
    return row;
  }

  public Node getParent() {
    return parent;
  }

  @Override
  public int compareTo(Node other) {
    return Integer.compare(getF(), other.getF());
  }

  // cuando se verifica con contains() se revisa de esta manera el Objecto
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Node) {
      Node otroNodo = (Node) obj;
      return this.row == otroNodo.row && this.col == otroNodo.col;
    }
    return false;
  }
}
