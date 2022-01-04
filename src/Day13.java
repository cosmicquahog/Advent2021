import java.io.File;
import java.util.ArrayList;
import Day13.Sheet;

public class Day13 {

    public static void main(String[] args) {
        Advent2021 adv = new Advent2021();
        Day13 day13 = new Day13();
        File day12Input = new File("src/Day13/Input13.txt");
        ArrayList<String> inputAsStrings = null;
        try {
            inputAsStrings = adv.loadDataString(day12Input);
        } catch (Exception e) { System.out.println("Failed to load data"); }

        //separate the dot locations from the raw data
        ArrayList<String> dotLocationStrings = new ArrayList<String>();
        for (String line : inputAsStrings) {
            if (!line.equals("") && Character.isDigit(line.charAt(0))) {
                dotLocationStrings.add(line);
            }
        }
        Sheet sheet = new Sheet(dotLocationStrings);


        // go back through input as strings and form a new string array for fold instructions
        ArrayList<String> foldStrings = new ArrayList<String>();
        for (String line : inputAsStrings) {
            if (!line.equals("") && Character.toString(line.charAt(0)).equals("f")) {
                foldStrings.add(line);
            }
        }
        // [fold along y=7, fold along x=5]
        // strip 'fold along '
        ArrayList<String> newFoldStrings = new ArrayList<String>();
        for (String foldString : foldStrings) {
            newFoldStrings.add(foldString.substring(foldString.length()-3));
        }

        sheet.printSheet();

        // newFoldStrings: [y=7, x=5]
        Sheet topHalf = sheet.getTopHalf(7);
        topHalf.printSheet();

        Sheet bottomHalf = sheet.getBottomHalf(7);
        bottomHalf.printSheet();

        Sheet bottomHalfMirrored = bottomHalf.mirrorVertical();
        bottomHalfMirrored.printSheet();
    }

}
