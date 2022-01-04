import java.io.File;
import java.util.ArrayList;
import Day12.RuleHandler;
import Day12.Path;

public class Day12 {

    public static void main(String[] args) throws Exception {
        Advent2021 adv = new Advent2021();
        Day12 day12 = new Day12();
        File day12Input = new File("src/Day12/Input12.txt");
        ArrayList<String> inputAsStrings = adv.loadDataString(day12Input); // [start-A, start-b, A-c, A-b, b-d, A-end, b-end]

        RuleHandler ruleHandler = new RuleHandler(inputAsStrings);

        ArrayList<Path> paths = new ArrayList<Path>();
        paths.add(new Path("start"));

        while (!day12.areAllPathsEnded(paths)) {
            paths = day12.getNextStep(paths, ruleHandler);
        }

        System.out.println(paths.size());

    }

    public boolean areAllPathsEnded(ArrayList<Path> paths) {
        for (Path path : paths) {
            if (!path.isPathEnded())
                return false;
        }
        return true;
    }

    // given ArrayList of Paths, return next generation
    public ArrayList<Path> getNextStep(ArrayList<Path> initialPaths, RuleHandler ruleHandler) {
        ArrayList<Path> newPaths = new ArrayList<Path>();
        for (Path path : initialPaths) {
            if (path.isPathEnded())
                newPaths.add(path);
            else {
                ArrayList<String> connectedCaves = ruleHandler.getAllConnectedCaves(path.getLastCave());
                for (String connectedCave : connectedCaves) {
                    if (path.isCaveValid(connectedCave, ruleHandler)) {
                        Path newPath = path.addCave(connectedCave, ruleHandler);
                        newPaths.add(newPath);
                    }
                }
            }

        }
        return newPaths;
    }

}
