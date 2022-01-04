import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Advent2021 {

    public Advent2021() {}

    public ArrayList<String> loadDataString(File file) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));
        int count = 0;
        while(br.readLine() != null)
            count++;

        br = new BufferedReader(new FileReader(file));
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            result.add(br.readLine());
        }
        return result;
    }

    public ArrayList<Integer> convertDataToInt(ArrayList<String> stringData) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (String line : stringData) {
            result.add(Integer.parseInt(line));
        }
        return result;
    }

    public static void main(String[] args) throws Exception {

    }

}
