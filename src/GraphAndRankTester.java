/* CS 314 STUDENTS: FILL IN THIS HEADER.
 *
 * Student information for assignment:
 *
 *  On my honor, <Teegan Rodgers>, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID:tkr496
 *  email address:teegan@utexas.edu
 *  TA name:
 */



/*
 * Question.
 *
Here are the questions:
1. The assignment presents three ways to rank teams using graphs. The results, especially
for the last two methods, are reasonable. However, if all results from all college football
teams are included some unexpected results occur. Explain the unexpected results.
*
* When running the program, it seems that there is significant human bias in the AP rankings. With
* many of the highest ranked teams (by AP) are predicted MUCH lower. A possible explanation for
* this is a combination of human bias, judging the schools based of their division/size, and our
* predictive analysis not watching the games and seeing the difficulty (or ease) of specific games.
* We really only take into account the wins and losses, but not how dominant a team looked or how
* shaky a team looked. The human committee that does these rankings is exposed to all of this, and
* is therefore impacted by it.
*
*
2. Suggest another way/method of ranking teams using the results from the graph.
Thoroughly explain your method. The method can build on one of the three existing
algorithms.
*
*
* My idea is to adjust the teams ranking based of the difficulty of their schedule. Instead of just
* ranking teams only based on their centrality, we would boost teams predicted rankings if they
* beat strong teams. Conversely, we would penalize teams for only playing against weak teams, ie
* they beat teams with low win percentage or only a couple transitive wins. This would incentivize
* good teams, like UT, to play against other good teams. This would both make our predictive
* rankings more accurate to the AP rankings AND give us more exciting games with big teams playing
* eachother more often.
 */

public class GraphAndRankTester {

    /**
     * Runs tests on Graph classes and FootballRanker class.
     * Experiments involve results from college football
     * teams. Central nodes in the graph are compared to
     * human rankings of the teams.
     *
     * @param args None expected.
     */
    public static void main(String[] args) {
        //Student Tests

        //dijkstra tests
        //dijkstra test 1
        String[][] g1Edges = {{"apple", "bob", "1"},
                {"bob", "C", "3"},
                {"apple", "C", "7"},
                {"bob", "dilly", "21"},
                {"C", "F", "3"},
                {"apple", "G", "17"},
                {"dilly", "F", "4"},
                {"dilly", "G", "5"},
                {"dilly", "E", "6"}};
        Graph g1 = getGraph(g1Edges, false);

        g1.dijkstra("apple");
        String actualPath = g1.findPath("E").toString();
        String expected = "[apple, bob, C, F, dilly, E]";
        if (actualPath.equals(expected)) {
            System.out.println("Passed dijkstra path test graph 0.");
        } else {
            System.out.println("Failed dijkstra path test graph 0. Expected: " + expected + " actual " + actualPath);
        }

        //dijkstra test 2
        g1Edges = new String[][]{{"apple", "bob", "1"},
                {"bob", "cat", "3"},
                {"apple", "cat", "7"},
                {"bob", "dilly", "21"},
                {"C", "fubby", "3"},
                {"apple", "G", "17"},
                {"dilly", "fubby", "4"},
                {"dilly", "G", "5"},
                {"dilly", "elliot", "6"}};
        g1 = getGraph(g1Edges, false);
        g1.dijkstra("apple");
        actualPath = g1.findPath("E").toString();
        expected = "[apple, bob, cat, fubby, dilly, elliot]";
        if (actualPath.equals(expected)) {
            System.out.println("Passed dijkstra path test graph 1.");
        } else {
            System.out.println("Failed dijkstra path test graph 1. Expected: " + expected + " actual " + actualPath);
        }



        //findAllPathsInfo unweighted tests
        g1Edges = new String[][]{{"apple", "bob", "1"},
                {"bob", "C", "3"},
                {"apple", "C", "7"},
                {"bob", "dilly", "21"},
                {"C", "F", "3"},
                {"apple", "G", "17"},
                {"dilly", "F", "4"},
                {"dilly", "G", "5"},
                {"dilly", "E", "6"}};
        g1 = getGraph(g1Edges, false);
        //findAllPathsInfo unweighted test 1
        String[] expectedPaths = {"Name: dilly                    cost per path: 1.3333, num paths: 6",
                "Name: bob                    cost per path: 1.5000, num paths: 6",
                "Name: apple                    cost per path: 1.6667, num paths: 6",
                "Name: C                    cost per path: 1.6667, num paths: 6",
                "Name: F                    cost per path: 1.6667, num paths: 6",
                "Name: G                    cost per path: 1.6667, num paths: 6",
                "Name: E                    cost per path: 2.1667, num paths: 6"};
        doAllPathsTests("Graph 0", g1, false, 3, 3.0, expectedPaths);
        g1Edges = new String[][]{{"apple", "bob", "1"},
                {"bob", "cat", "3"},
                {"apple", "cat", "7"},
                {"bob", "dilly", "21"},
                {"cat", "fubby", "3"},
                {"apple", "G", "17"},
                {"dilly", "fubby", "4"},
                {"dilly", "G", "5"},
                {"dilly", "elliot", "6"}};
        g1 = getGraph(g1Edges, false);
        //findAllPathsInfo unweighted test 2
        expectedPaths = new String[]{"Name: dilly                    cost per path: 1.3333, num paths: 6",
                "Name: bob                    cost per path: 1.5000, num paths: 6",
                "Name: apple                    cost per path: 1.6667, num paths: 6",
                "Name: cat                    cost per path: 1.6667, num paths: 6",
                "Name: fubby                    cost per path: 1.6667, num paths: 6",
                "Name: G                    cost per path: 1.6667, num paths: 6",
                "Name: elliot                    cost per path: 2.1667, num paths: 6"};
        doAllPathsTests("Graph 0", g1, false, 3, 3.0, expectedPaths);



        //findAllPathsInfo weighted tests
        g1Edges = new String[][]{{"apple", "bob", "1"},
                {"bob", "C", "3"},
                {"apple", "C", "7"},
                {"bob", "dilly", "21"},
                {"C", "F", "3"},
                {"apple", "G", "17"},
                {"dilly", "F", "4"},
                {"dilly", "G", "5"},
                {"dilly", "E", "6"}};
        g1 = getGraph(g1Edges, false);
        //findAllPathsInfo weighted test 1
        expectedPaths = new String[]{"Name: F                    cost per path: 6.5000, num paths: 6",
                "Name: C                    cost per path: 7.0000, num paths: 6",
                "Name: dilly                    cost per path: 7.1667, num paths: 6",
                "Name: bob                    cost per path: 8.5000, num paths: 6",
                "Name: apple                    cost per path: 9.3333, num paths: 6",
                "Name: G                    cost per path: 11.3333, num paths: 6",
                "Name: E                    cost per path: 12.1667, num paths: 6"};
        doAllPathsTests("Graph 0", g1, true, 5, 17.0, expectedPaths);
        g1Edges = new String[][]{{"apple", "bob", "1"},
                {"bob", "cat", "3"},
                {"apple", "cat", "7"},
                {"bob", "dilly", "21"},
                {"C", "fubby", "3"},
                {"apple", "G", "17"},
                {"dilly", "fubby", "4"},
                {"dilly", "G", "5"},
                {"dilly", "elliot", "6"}};
        g1 = getGraph(g1Edges, false);
        //findAllPathsInfo weighted test 2
        expectedPaths = new String[]{"Name: fubby                    cost per path: 6.5000, num paths: 6",
                "Name: cat                    cost per path: 7.0000, num paths: 6",
                "Name: dilly                    cost per path: 7.1667, num paths: 6",
                "Name: bob                    cost per path: 8.5000, num paths: 6",
                "Name: apple                    cost per path: 9.3333, num paths: 6",
                "Name: G                    cost per path: 11.3333, num paths: 6",
                "Name: elliot                    cost per path: 12.1667, num paths: 6"};
        doAllPathsTests("Graph 0", g1, true, 5, 17.0, expectedPaths);
    }


    // return a Graph based on the given edges
    private static Graph getGraph(String[][] edges, boolean directed) {
        Graph result = new Graph();
        for (String[] edge : edges) {
            result.addEdge(edge[0], edge[1], Double.parseDouble(edge[2]));
            // If edges are for an undirected graph add edge in other direction too.
            if (!directed) {
                result.addEdge(edge[1], edge[0], Double.parseDouble(edge[2]));
            }
        }
        return result;
    }

    // Tests the all paths method. Run each set of tests twice to ensure the Graph
    // is correctly reseting each time
    private static void doAllPathsTests(String graphNumber, Graph g, boolean weighted,
                                        int expectedDiameter, double expectedCostOfLongestShortestPath,
                                        String[] expectedPaths) {

        System.out.println("\nTESTING ALL PATHS INFO ON " + graphNumber + ". ");
        for (int i = 0; i < 2; i++) {
            System.out.println("Test run = " + (i + 1));
            System.out.println("Find all paths weighted = " + weighted);
            g.findAllPaths(weighted);
            int actualDiameter = g.getDiameter();
            double actualCostOfLongestShortesPath = g.costOfLongestShortestPath();
            if (actualDiameter == expectedDiameter) {
                System.out.println("Passed diameter test.");
            } else {
                System.out.println("FAILED diameter test. "
                        + "Expected = " + expectedDiameter +
                        " Actual = " + actualDiameter);
            }
            if (actualCostOfLongestShortesPath == expectedCostOfLongestShortestPath) {
                System.out.println("Passed cost of longest shortest path. test.");
            } else {
                System.out.println("FAILED cost of longest shortest path. "
                        + "Expected = " + expectedCostOfLongestShortestPath +
                        " Actual = " + actualCostOfLongestShortesPath);
            }
            testAllPathsInfo(g, expectedPaths);
            System.out.println();
        }

    }

    // Compare results of all paths info on Graph to expected results.
    private static void testAllPathsInfo(Graph g, String[] expectedPaths) {
        int index = 0;

        for (AllPathsInfo api : g.getAllPaths()) {
            if (expectedPaths[index].equals(api.toString())) {
                System.out.println(expectedPaths[index] + " is correct!!");
            } else {
                System.out.println("ERROR IN ALL PATHS INFO: ");
                System.out.println("index: " + index);
                System.out.println("EXPECTED: " + expectedPaths[index]);
                System.out.println("ACTUAL: " + api.toString());
                System.out.println();
            }
            index++;
        }
        System.out.println();
    }
}