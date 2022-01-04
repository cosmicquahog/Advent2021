import java.io.File;
import java.util.ArrayList;
import Day13_2.Sheet;

public class Day13_2 {

    // Sheet 1
    // Sheet 2
    // Sheet 2 is always below sheet 1
    public Sheet combineSheetsVertical(Sheet sheet1, Sheet sheet2) {



        // if sheet 1 is bigger
        // result sheet is size of sheet 1
        // copy all of sheet 1 over to result
        if (sheet1.height > sheet2.height) {
            Sheet resultSheet = new Sheet(sheet1.width, sheet1.height);
            for (int y = 0; y < resultSheet.height; y++) {
                for (int x = 0; x < resultSheet.width; x++) {
                    resultSheet.sheet[y][x] = sheet1.sheet[y][x];
                }
            }
            //resultSheet.print();
            // flip the bottom half vertically
            Sheet sheet2Flipped = sheet2.flipVertical();
            //sheet2Flipped.print();

            // add the flipped bottom from the bottom of the top half
            int startingY = sheet1.height - sheet2.height;
            for (int y = startingY; y < sheet1.height; y++) {
                for (int x = 0; x < sheet1.width; x++) {
                    int sheet2y = y - startingY;
                    int sheet2x = x;
                    if (sheet2Flipped.sheet[sheet2y][sheet2x]) {
                        resultSheet.sheet[y][x] = true;
                    }
                }
            }





            return resultSheet;
        }
        // WRONG. FLIP THE BOTTOM AND ADD THE TOP UN FLIPPED. FUCK. 
        else if (sheet2.height > sheet1.height) {
            Sheet resultSheet = new Sheet(sheet2.width, sheet2.height);
            // copy all of sheet 2 over to result
            for (int y = 0; y < resultSheet.height; y++) {
                for (int x = 0; x < resultSheet.width; x++) {
                    resultSheet.sheet[y][x] = sheet2.sheet[y][x];
                }
            }
            //add the flipped top from the top of the bottom half
            Sheet sheet1Flipped = sheet1.flipVertical();
            for (int y = 0; y < sheet1Flipped.height; y++) {
                for (int x = 0; x < sheet1Flipped.width; x++) {
                    if (sheet1Flipped.sheet[y][x])
                        resultSheet.sheet[y][x] = true;
                }
            }
            return resultSheet;
        }
        else {
            // same size arrays
            Sheet resultSheet = new Sheet(sheet1.width, sheet1.height);
            for (int y = 0; y < sheet1.height; y++) {
                for (int x = 0; x < sheet1.width; x++) {
                    if (sheet1.sheet[y][x] || sheet2.sheet[y][x]) {
                        resultSheet.sheet[y][x] = true;
                    }
                }
            }
            return resultSheet;
        }


    }

    public static void main(String[] args) throws Exception {
        Day13_2 day13 = new Day13_2();
        Advent2021 adv = new Advent2021();
        File day13Input = new File("src/Day13_2/Input13.txt");

        ArrayList<String> dataAsString = adv.loadDataString(day13Input);
        // [6,10, 0,14, 9,10, 0,3, 10,4, 4,11, 6,0, 6,12, 4,1, 0,13, 10,12, 3,4, 3,0, 8,4, 1,10, 2,14, 8,10, 9,0, , fold along y=7, fold along x=5]

        ArrayList<String> dotDataStrings = new ArrayList<String>();
        ArrayList<String> foldDataStrings = new ArrayList<String>();

        for (String dataString : dataAsString) {
            if (!dataString.equals("")) {
                if (Character.isDigit(dataString.charAt(0))) {
                    dotDataStrings.add(dataString);
                }
                else if (Character.isAlphabetic(dataString.charAt(0))) {
                    foldDataStrings.add(dataString);
                }
            }
        }
        // dotDataStrings: [6,10, 0,14, 9,10, 0,3, 10,4, 4,11, 6,0, 6,12, 4,1, 0,13, 10,12, 3,4, 3,0, 8,4, 1,10, 2,14, 8,10, 9,0]
        // foldDataStrings: [fold along y=7, fold along x=5]

        // find max X and Y from dotDataStrings
        int maxX = 0;
        int maxY = 0;
        for (String dotData : dotDataStrings) {
            String[] dotDataSplit = dotData.split(",");
            int x = Integer.parseInt(dotDataSplit[0]);
            int y = Integer.parseInt(dotDataSplit[1]);
            if (x > maxX)
                maxX = x;
            if (y > maxY)
                maxY = y;
        }
        // the width and height of the sheet are 1 more than max
        int sheetWidth = maxX + 1;
        int sheetHeight = maxY + 1;

        Sheet sheet = new Sheet(sheetWidth, sheetHeight);
        sheet.addDots(dotDataStrings);
        sheet.print();

        //Sheet sheetTop = sheet.getTopHalf(9);
        //Sheet sheetBottom = sheet.getBottomHalf(9);
        //sheetTop.print();
        //sheetBottom.print();

        //Sheet combinedSheets = day13.combineSheetsVertical(sheetTop, sheetBottom);
        //combinedSheets.print();

        Sheet sheetTop = sheet.getTopHalf(5);
        Sheet sheetBottom = sheet.getBottomHalf(5);
        sheetTop.print();
        sheetBottom.print();

        System.out.println(sheet.countDots());


    }

}
