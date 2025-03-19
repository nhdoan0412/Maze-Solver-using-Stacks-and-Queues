/* Class to implement a Maze solver */

public abstract class MazeSolver {
	
    public static Square solve(Maze maze, SearchWorklist wl) {
        wl.add(maze.start);
        maze.start.visit();
    
        while (!wl.isEmpty()) {
            Square curr = wl.remove();
    
            if (curr == maze.finish) {
                return curr;
            }
            else {
                exploreNeighbor(maze, curr.getRow() - 1, curr.getCol(), wl, curr);
                exploreNeighbor(maze, curr.getRow() + 1, curr.getCol(), wl, curr);
                exploreNeighbor(maze, curr.getRow(), curr.getCol() + 1, wl, curr);
                exploreNeighbor(maze, curr.getRow(), curr.getCol() - 1, wl, curr);
            }
        }
        return null;
    }
    
    private static void exploreNeighbor(Maze maze, int row, int col, SearchWorklist wl, Square current) {
        if (row >= 0 && row < maze.rows && col >= 0 && col < maze.cols) {
            Square neighbor = maze.contents[row][col];
            if (!neighbor.getIsWall() && !neighbor.isVisited()) {
                wl.add(neighbor);
                neighbor.visit();
                neighbor.setPrevious(current);
            }
        }
    }
    
}