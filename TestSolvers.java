import java.beans.Transient;
import java.util.ArrayList;

/*
 * Write your JUnit tests here
 * Use the formatMaze() method to get nicer JUnit output
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class TestSolvers {

    /* Helper method to compare two mazes */
    public void checkMaze(SearchWorklist wl, Maze startMaze, String[] expected) {
        Square s = MazeSolver.solve(startMaze, wl);
        if(expected == null) { assertNull(s); }
        else {
            ArrayList<Square> sp = startMaze.storePath();
            String actualStr = formatMaze(startMaze.showSolution(sp));
            String expectedStr = formatMaze(expected);
            assertEquals(expectedStr, actualStr);
        }
    }   

    /* Helper method to format String[] output as String */
    public String formatMaze(String[] arr) {
        String result = "";
        for (String s: arr)
            result += "\n"+s;
        return (result+"\n");
    }

    @Test
    public void stackAdd() {
        StackWorklist stack = new StackWorklist();
        Square square = new Square(0, 0, false);
        stack.add(square);
        assertEquals(false, stack.isEmpty());
    }

    @Test
    public void stackRemove() {
        StackWorklist stack = new StackWorklist();
        Square square = new Square(0, 0, false);
        stack.add(square);
        stack.remove();
        assertEquals(true, stack.isEmpty());
    }

    @Test
    public void stackIsEmpty() {
        StackWorklist stack = new StackWorklist();
        Square square = new Square(0, 0, false);
        stack.add(square);
        assertEquals(false, stack.isEmpty());
    }

    @Test
    public void queueAdd() {
        QueueWorklist queue = new QueueWorklist();
        Square square = new Square(0, 0, false);
        queue.add(square);
        assertEquals(false, queue.isEmpty());
    }

    @Test
    public void queueRemove() {
        QueueWorklist queue = new QueueWorklist();
        Square square = new Square(0, 0, false);
        queue.add(square);
        queue.remove();
        assertEquals(true, queue.isEmpty());
    }

    @Test
    public void queueIsEmpty() {
        QueueWorklist queue = new QueueWorklist();
        Square square = new Square(0, 0, false);
        queue.add(square);
        assertEquals(false, queue.isEmpty());
    }

    @Test
    public void chaffsolve_diagonalmoves() {
        // Create a maze where diagonal moves are necessary for the optimal solution
        String[] mazeSpec = {
            "S_#",
            "_#_",
            "_#F"
        };
        Maze maze = new Maze(mazeSpec);
        SearchWorklist wl = new QueueWorklist(); // Choose any worklist
        Square solution = MazeSolver.solve(maze, wl);
        assertNull(solution); // Assert that a solution is found
    }

    @Test
    public void chaffsolve_stopearly() {
        // Create a maze with multiple possible paths but the solver should explore all
        String[] mazeSpec = {
            "S__",
            "_#_",
            "__F"
        };
        Maze maze = new Maze(mazeSpec);
        SearchWorklist wl = new StackWorklist(); // Choose any worklist
        Square solution = MazeSolver.solve(maze, wl);
        assertNotNull(solution); // Assert that a solution is found
    }

    @Test
    public void chaffsolve_difforder() {
        // Create a maze where the order of exploring neighboring squares affects the solution
        String[] mazeSpec = {
            "S__",
            "___",
            "_#F"
        };
        Maze maze = new Maze(mazeSpec);
        SearchWorklist wl = new QueueWorklist(); // Choose any worklist
        Square solution = MazeSolver.solve(maze, wl);
        assertNotNull(solution); // Assert that a solution is found
    }

    @Test
    public void chaffwalls_ignore() {
        // Create a maze with walls where the optimal path goes around the walls
        String[] mazeSpec = {
            "S__",
            "_#_",
            "_#F"
        };
        Maze maze = new Maze(mazeSpec);
        SearchWorklist wl = new QueueWorklist(); // Choose any worklist
        Square solution = MazeSolver.solve(maze, wl);
        assertNotNull(solution); // Assert that a solution is found
    }

    @Test
    public void chaffwall_get_storepath() {
        // Create a maze with walls where the optimal path goes around the walls
        String[] mazeSpec = {
            "S__",
            "_#_",
            "_#F"
        };
        String[] expected = {
            "S**",
            "_#*",
            "_#F"
        };
        Maze maze = new Maze(mazeSpec);
        SearchWorklist wl = new QueueWorklist(); // Choose any worklist
        checkMaze(wl, maze, expected);
    }

    @Test
    public void chaffwall_next() {
        // Create a maze with walls where the optimal path goes around the walls
        String[] mazeSpec = {
            "SF_",
            "_#_",
            "_#_"
        };
        String[] expected = {
            "SF_",
            "_#_",
            "_#_"
        };
        Maze maze = new Maze(mazeSpec);
        SearchWorklist wl = new QueueWorklist(); // Choose any worklist
        checkMaze(wl, maze, expected);
    }

    @Test
    public void testStorePathEmpty() {
    // Create a maze with walls where there's no path from start to finish
        String[] mazeSpec = {
        "S#F",
        "_#_",
        "_#_"
    };
    Maze maze = new Maze(mazeSpec);
    SearchWorklist wl = new QueueWorklist(); // Choose any worklist
    ArrayList<Square> path = maze.storePath();
    assertEquals(0, path.size()); // Assert that the path is empty
}



    @Test
    public void chaffsolve_difforder1() {
        // Create a maze where the order of exploring neighboring squares affects the solution
        String[] mazeSpec = {
            "S__",
            "___",
            "_#F"
        };
        String[] expected = {
            "S__",
            "***",
            "_#F"
        };
        Maze maze = new Maze(mazeSpec);
        SearchWorklist wl = new QueueWorklist(); // Choose any worklist
        checkMaze(wl, maze, expected);
    }

    @Test
    public void chaffsolve_difforder2() {
        // Create a maze where the order of exploring neighboring squares affects the solution
        String[] mazeSpec = {
            "S__",
            "___",
            "_#F"
        };
        String[] expected = {
            "S**",
            "__*",
            "_#F"
        };
        Maze maze = new Maze(mazeSpec);
        SearchWorklist wl = new StackWorklist(); // Choose any worklist
        checkMaze(wl, maze, expected);
    }

    @Test
    public void nowallqueue() {
        // Create a maze where the order of exploring neighboring squares affects the solution
        String[] mazeSpec = {
            "_S_",
            "___",
            "_F_"
        };
        String[] expected = {
            "_S_",
            "_*_",
            "_F_"
        };
        Maze maze = new Maze(mazeSpec);
        SearchWorklist wl = new QueueWorklist(); // Choose any worklist
        checkMaze(wl, maze, expected);
    }

    @Test
    public void nowallstack() {
        // Create a maze where the order of exploring neighboring squares affects the solution
        String[] mazeSpec = {
            "_S_",
            "___",
            "_F_"
        };
        String[] expected = {
            "*S_",
            "*__",
            "*F_"
        };
        Maze maze = new Maze(mazeSpec);
        SearchWorklist wl = new StackWorklist(); // Choose any worklist
        checkMaze(wl, maze, expected);
    }

    @Test
    public void teststorepathfail() {
        String[] mazeSpec = {
            "S#F",
            "###",
            "___"
        };
        Maze maze = new Maze(mazeSpec);
        SearchWorklist wl = new StackWorklist(); // Choose any worklist
        Square solution = MazeSolver.solve(maze, wl);
        ArrayList<Square> sp = maze.storePath();
        assertArrayEquals(sp.toArray(), sp.toArray());
    }
}