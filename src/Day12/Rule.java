package Day12;

public class Rule {

    public String cave1;
    public String cave2;

    public Rule(String unparsedRule) {
        String[] splitString = unparsedRule.split("-");
        this.cave1 = splitString[0];
        this.cave2 = splitString[1];
    }

}
