package Day12;

import java.util.ArrayList;

public class RuleHandler {

    ArrayList<Rule> rules = new ArrayList<Rule>();
    ArrayList<String> caves = new ArrayList<String>();

    public RuleHandler(ArrayList<String> rulesAsStrings) {
        for (String rule : rulesAsStrings) {
            rules.add(new Rule(rule));
        }
        // now add all caves to the caves array
        for (Rule rule : rules) {
            boolean isCave1InCaves = false;
            boolean isCave2InCaves = false;
            for (String cave : caves) {
                if (cave.equals(rule.cave1))
                    isCave1InCaves = true;
                if (cave.equals(rule.cave2))
                    isCave2InCaves = true;
            }
            if (!isCave1InCaves) {
                caves.add(rule.cave1);
            }
            if (!isCave2InCaves) {
                caves.add(rule.cave2);
            }
        }
    }

    public ArrayList<String> getAllCaves() {
        return caves;
    }

    public boolean areCavesConnected(String cave1, String cave2) {
        for (Rule rule : rules) {
            if (rule.cave1.equals(cave1) && rule.cave2.equals(cave2)) {
                return true;
            }
            else if(rule.cave1.equals(cave2) && rule.cave2.equals(cave1)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getAllConnectedCaves(String startingCave) {
        ArrayList<String> connectedCaves = new ArrayList<String>();
        for (String cave : caves) {
            if (areCavesConnected(startingCave, cave)) {
                connectedCaves.add(cave);
            }
        }
        return connectedCaves;
    }

}
