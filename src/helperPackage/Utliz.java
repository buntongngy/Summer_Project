package helperPackage;

import java.util.ArrayList;

public class Utliz {
    public static int[][] ArrayListto2Dint(ArrayList<Integer> list, int ySize, int xSize) {

        int[][] newArr = new int[ySize][xSize];

        for (int j =0; j < newArr.length; j++) {
            for (int i=0; i< newArr[j].length; i++) {
                int index = j*ySize + i;
                newArr[j][i] = list.get(index);

            }
        }
        return newArr;
        }
}