package Day13_2;

import java.util.ArrayList;

public class Sheet {

    public boolean[][] sheet;
    public int width;
    public int height;

    public Sheet(int width, int height) {
        sheet = new boolean[height][width];
        this.width = width;
        this.height = height;
    }

    public Sheet(boolean[][] dotData) {
        this.width = dotData[0].length;
        this.height = dotData.length;
        sheet = new boolean[this.height][this.width];
        for (int r = 0; r < sheet.length; r++) {
            for (int c = 0; c < sheet[0].length; c++) {
                sheet[r][c] = dotData[r][c];
            }
        }
    }

    public int countDots() {
        int dotCount = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (sheet[y][x])
                    dotCount++;
            }
        }
        return dotCount;
    }

    public Sheet foldVertical(int yFoldLocation) {
        Sheet topHalf = this.getTopHalf(yFoldLocation);
        Sheet bottomHalf = this.getBottomHalf(yFoldLocation);
        Sheet bottomHalfFlipped = bottomHalf.flipVertical();
        Sheet result = topHalf.combineSheets(bottomHalfFlipped);
        return result;
    }

    public Sheet foldHorizontal(int xFoldLocation) {
        Sheet leftHalf = this.getLeftHalf(xFoldLocation);
        Sheet rightHalf = this.getRightHalf(xFoldLocation);
        Sheet rightHalfFlipped = rightHalf.flipHorizontal();
        Sheet result = leftHalf.combineSheets(rightHalfFlipped);
        return result;
    }

    public Sheet combineSheets(Sheet otherSheet) {
        Sheet resultSheet = new Sheet(this.width, this.height);
        //System.out.println("Sheet 1 width: " + this.width);
        //System.out.println("Sheet 2 width: " + otherSheet.width);
        //System.out.println("Sheet 1 height: " + this.height);
        //System.out.println("Sheet 2 height: " + otherSheet.height);
        for (int y = 0; y < this.height; y++) {   //////////////////////////////
            for (int x = 0; x < this.width; x++) { //////////////////////////
                if (this.sheet[y][x] || otherSheet.sheet[y][x]) {
                    resultSheet.sheet[y][x] = true;
                }
            }
        }
        return resultSheet;
    }

    public void addDot(int x, int y) {
        sheet[y][x] = true;
    }

    public void addDots(ArrayList<String> dotDataAsStrings) {
        for (String dotString : dotDataAsStrings) {
            String[] dotStringSplit = dotString.split(",");
            int x = Integer.parseInt(dotStringSplit[0]);
            int y = Integer.parseInt(dotStringSplit[1]);
            addDot(x, y);
        }
    }

    public Sheet flipVertical() {
        Sheet resultSheet = new Sheet(this.width, this.height);
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                //System.out.println(x + ", " + y);
                int invertedY = this.height - y - 1;
                //System.out.println(x + ", " + invertedY);
                //System.out.println();
                resultSheet.sheet[y][x] = this.sheet[invertedY][x];
            }
        }
        return resultSheet;
    }

    public Sheet flipHorizontal() {
        Sheet resultSheet = new Sheet(this.width, this.height);
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                int invertedX = this.width - x - 1;
                resultSheet.sheet[y][x] = this.sheet[y][invertedX];
            }
        }
        return resultSheet;
    }

    public Sheet getTopHalf(int yFoldLocation) {
        int startingY = 0;
        int endingY = yFoldLocation-1;
        int startingX = 0;
        int endingX = this.width-1;
        Sheet result = getSubArray(startingX, endingX, startingY, endingY);
        return result;
    }

    public Sheet getLeftHalf(int xFoldLocation) {
        int startingX = 0;
        int endingX = xFoldLocation - 1;
        int startingY = 0;
        int endingY = this.height - 1;
        Sheet result = getSubArray(startingX, endingX, startingY, endingY);
        return result;
    }

    public Sheet getBottomHalf(int yFoldLocation) {
        int startingY = yFoldLocation+1;
        int endingY = this.height-1;
        int startingX = 0;
        int endingX = this.width-1;
        Sheet result = getSubArray(startingX, endingX, startingY, endingY);
        return result;
    }

    public Sheet getRightHalf(int xFoldLocation) {
        int startingX = xFoldLocation + 1;
        int endingX = this.width - 1;
        int startingY = 0;
        int endingY = this.height - 1;
        Sheet result = getSubArray(startingX, endingX, startingY, endingY);
        return result;
    }

    public Sheet getSubArray(int minX, int maxX, int minY, int maxY) {
        int resultWidth = maxX - minX + 1;
        int resultHeight = maxY - minY + 1;
        boolean[][] resultBooleans = new boolean[resultHeight][resultWidth];
        //System.out.println(resultWidth + ", " + resultHeight);
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                //System.out.println(x + ", " + y);
                resultBooleans[y-minY][x-minX] = sheet[y][x];
            }
        }
        Sheet resultSheet = new Sheet(resultBooleans);

        return resultSheet;
    }

    public void print() {
        for (int r = 0; r < this.height; r++) {
            for (int c = 0; c < this.width; c++) {
                if (sheet[r][c])
                    System.out.print("#  ");
                else
                    System.out.print(".  ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
