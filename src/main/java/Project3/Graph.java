package Project3;

public class Graph {
    GraphNode[][] nodeArr;
    public Graph(int row, int col) {
        this.nodeArr = new GraphNode[row][col];
        for (int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                // Bit mask for checking which direction is available
                int direction = 0b1111;
                if (i == 0)         direction &= ~GraphNode.NORTH;
                if (i == row - 1)   direction &= ~GraphNode.SOUTH;
                if (j == 0)         direction &= ~GraphNode.WEST;
                if (j == col - 1)   direction &= ~GraphNode.EAST;
//                System.out.printf("%2d ", direction);
                nodeArr[i][j] = new GraphNode(direction);
            }
//            System.out.println();
        }

        // Set neighbours
        int [] tempDirection = {9, 1, 3, 2, 0, 6, 4, 12, 8};
        for (int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                for(int dy=0; dy<3; dy++) {
                    for(int dx=0; dx<3; dx++) {
                        if (i + dy - 1 < 0 || i + dy > row || j + dx - 1 < 0 || j + dx > col) continue;
                        nodeArr[i][j].setNeighbour(tempDirection[dy*3+dx], nodeArr[i+dy-1][j+dx-1], dx + dy == 2 || dx == dy ? 10 : 14);
                    }
                }
            }
//            System.out.println();
        }
    }
}
