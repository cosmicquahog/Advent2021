package Day13;

import java.util.ArrayList;

public class Sheet {

    public int maxX;
    public int maxY;
    public boolean[][] sheet;

    public Sheet(ArrayList<String> dotLocationStrings) {
        maxX = getMaxX(dotLocationStrings);
        maxY = getMaxY(dotLocationStrings);
        sheet = new boolean[maxY+1][maxX+1];
        for (String dotData : dotLocationStrings) {
            addDotToSheet(dotData);
        }
    }

    public Sheet(boolean[][] initialData) {
        maxX = initialData[0].length;
        maxY = initialData.length;
        sheet = initialData;
    }

    public Sheet mirrorVertical() {
        boolean result[][] = new boolean[maxY+1][maxX+1];

        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                //System.out.println(x + ", " + y);
                //System.out.println(x + ", " + (maxY-y));
                //System.out.println();
                result[y][x] = this.sheet[maxY-y][x];
            }
        }

        Sheet resultSheet = new Sheet(result);
        return resultSheet;
    }

    // given a vertical fold (y) location, return top half of sheet as boolean[][]
    public Sheet getTopHalf(int yLocation) {
        boolean[][] result;
        int resultHeight = (maxY - 1)/2 + 1;
        int resultWidth = maxX + 1;
        result = new boolean[resultHeight][resultWidth];

        for (int x = 0; x < resultWidth; x++) {
            for (int y = 0; y < resultHeight; y++) {
                result[y][x] = sheet[y][x];
            }
        }

        return new Sheet(result);
    }

    public Sheet getBottomHalf(int yLocation) {
        boolean[][] result;
        int resultHeight = (maxY - 1)/2 + 1;
        int resultWidth = maxX + 1;
        result = new boolean[resultHeight][resultWidth];

        for (int x = 0; x < resultWidth; x++) {
            for (int y = yLocation+1; y < yLocation+1+resultHeight; y++) {
                //System.out.println(x + ", " + y);
                result[y-yLocation-1][x] = sheet[y][x];
            }
        }
        return new Sheet(result);
    }

    public void printSheet() {
        for (int r = 0; r < sheet.length; r++) {
            for (int c = 0; c < sheet[0].length; c++) {
                if (sheet[r][c] == false)
                    System.out.print(".  ");
                else
                    System.out.print("#  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void addDotToSheet(String rawDotData) {
        String[] splitData = rawDotData.split(",");
        int x = Integer.parseInt(splitData[0]);
        int y = Integer.parseInt(splitData[1]);
        //System.out.println("Adding dot to row: " + row + " col: " + col);
        sheet[y][x] = true;
    }

    public int getMaxY(ArrayList<String> dotLocationStrings) {
        int maxRow = 0;
        for (String string : dotLocationStrings) {
            //System.out.println(string);
            String[] splitDotString = string.split(",");
            int row = Integer.parseInt(splitDotString[1]);
            if (row > maxRow)
                maxRow = row;
        }
        return maxRow;
    }

    public int getMaxX(ArrayList<String> dotLocationStrings) {
        int maxColumn = 0;
        for (String string : dotLocationStrings) {
            //System.out.println(string);
            String[] splitDotString = string.split(",");
            int column = Integer.parseInt(splitDotString[0]);
            if (column > maxColumn)
                maxColumn = column;
        }
        return maxColumn;
    }

}
