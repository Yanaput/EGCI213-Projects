package Project3;

public class Graph {
   GraphNode[][] nodeArr;

   private final int rowCount, columnCount;

   public Graph(int row, int col) {
       this.rowCount = row; this.columnCount = col;
       this.nodeArr = new GraphNode[row][col];
       for (int i=0; i<row; i++) {
           for(int j=0; j<col; j++) {
               // Bit mask for checking which direction is available
               GraphNodeDirection direction = new GraphNodeDirection(0b1111);
               if (i == 0)         direction.disableDirection(GraphNode.NORTH);
               if (i == row - 1)   direction.disableDirection(GraphNode.SOUTH);
               if (j == 0)         direction.disableDirection(GraphNode.WEST);
               if (j == col - 1)   direction.disableDirection(GraphNode.EAST);
//                System.out.printf("%2d ", direction);
               nodeArr[i][j] = new GraphNode(direction);
           }
//            System.out.println();
       }
   }

        // Set neighbours
//        int [] tempDirection = {9, 1, 3, 2, 0, 6, 4, 12, 8};
//        for (int i=0; i<row; i++) {
//            for(int j=0; j<col; j++) {
//                for(int dy=0; dy<3; dy++) {
//                    for(int dx=0; dx<3; dx++) {
//                        if (i + dy - 1 < 0 || i + dy > row || j + dx - 1 < 0 || j + dx > col) continue;
//                        nodeArr[i][j].setNeighbour(tempDirection[dy*3+dx], nodeArr[i+dy-1][j+dx-1], dx + dy == 2 || dx == dy ? 10 : 14);
//                    }
//                }
//            }
//            System.out.println();
//        }

   private boolean isInBound(int y, int x) {
       return x >= 0 && x < this.columnCount && y >= 0 && y < this.rowCount;
   }

   public void placeWall(int y, int x) {
       if (!isInBound(y, x)) return;
       for (int i=y-1; i<=y+1; i++) {
           for (int j=x-1; j<=y+1; j++) {
               makeWallNode(y, x);
           }
       }
   }

   // Unfinished
   public void makeWallNode(int y, int x) {
       if (!isInBound(y, x)) return;
       for(int dir: GraphNode.DIRECTIONS) {
//           this.nodeArr[y][x].direction.disableDirection();
       }
   }
}
