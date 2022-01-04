package Day12;

import java.util.ArrayList;

public class Path {

    public ArrayList<String> path = new ArrayList<String>();

    public Path(String firstCave) {
        path.add(firstCave);
    }

    public Path(ArrayList<String> initialPath) {
        this.path = initialPath;
    }

    public Path addCave(String newCave, RuleHandler ruleHandler) {
        if (isCaveValid(newCave, ruleHandler)) {
            ArrayList<String> pathCopy = new ArrayList<String>();
            for (String cave : path) {
                pathCopy.add(cave);
            }
            pathCopy.add(newCave);
            Path newPath = new Path(pathCopy);
            return newPath;
        }
        return null;
    }

    public String getLastCave() {
        return path.get(path.size()-1);
    }

    public boolean isPathEnded() {
        if (path.get(path.size()-1).equals("end"))
            return true;
        return false;
    }

    public boolean haveWeVisitedAnySmallCavesTwice(RuleHandler ruleHandler) {
        // make array of all small caves
        ArrayList<String> allCaves = ruleHandler.getAllCaves();
        ArrayList<String> smallCaves = new ArrayList<String>();
        for (String cave : allCaves) {
            if (Character.isLowerCase(cave.charAt(0)))
                smallCaves.add(cave);
        }

        int[] smallCavesCount = new int[smallCaves.size()];
        // for each small cave
        for (int smallCaveI = 0; smallCaveI < smallCaves.size(); smallCaveI++) {
            // for each cave in path
            for (String cave : path) {
                if (cave.equals(smallCaves.get(smallCaveI))) {
                    smallCavesCount[smallCaveI]++;
                }
            }
        }
        for (int i = 0; i < smallCavesCount.length; i++) {
            //System.out.println(smallCaves.get(i) + ": " + smallCavesCount[i]);
            if (smallCavesCount[i] > 1) {
                return true;
            }
        }
        return false;
    }

    public boolean isCaveValid(String cave, RuleHandler ruleHandler) {
        if (cave.equals("start"))
            return false;
        if (Character.isLowerCase(cave.charAt(0))) {
            if (!haveWeVisitedAnySmallCavesTwice(ruleHandler)) {
                return true;
            }
            else {
                // check if we have visited the cave at all yet
                boolean alreadyBeenToCave = false;
                for (String caveToCheck : path) {
                    if (caveToCheck.equals(cave)) {
                        alreadyBeenToCave = true;
                    }
                }
                if (alreadyBeenToCave)
                    return false;
                else
                    return true;
            }
        }
        return true;
    }

//    public boolean isCaveValid(String cave) {
//        if (cave.equals("start"))
//            return false;
//        if (Character.isLowerCase(cave.charAt(0))) {
//            // check if the lower case letter has appeared before
//            boolean alreadyBeenToCave = false;
//            for (String caveToCheck : path) {
//                if (caveToCheck.equals(cave)) {
//                    alreadyBeenToCave = true;
//                }
//            }
//            if (alreadyBeenToCave)
//                return false;
//            else
//                return true;
//        }
//        return true;
//    }

    public void print() {
        System.out.println(path.toString());
    }

}
