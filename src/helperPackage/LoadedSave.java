package helperPackage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadedSave {

    public static BufferedImage getSpriteAtlas() {
        BufferedImage img = null;
        InputStream is = LoadedSave.class.getClassLoader().getResourceAsStream("TD_sheet.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public static void createFile() {
        File txtFile = new File("res/TextFile.txt");

        try {
            txtFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createLevel(String name, int[] idArr) {
        File newLVL = new File("res/" + name + ".txt");

        if (newLVL.exists()) {
            System.out.println("File " + name + " already exists!");
        } else {
            try {
                newLVL.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            writeToFile(newLVL, idArr);
        }
    }

    private static void writeToFile(File f, int[] idArr) {
        try (PrintWriter pw = new PrintWriter(f)) {
            for (int i : idArr) {
                pw.println(i);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveLevel(String name, int[][] idArr) {
        File lvlFile = new File("res/" + name + ".txt");

        if(lvlFile.exists()) {
            writeToFile(lvlFile, Utliz.twoDto1DArr(idArr));
        } else {
            System.out.println("File " + name + "does not exist!");
            return;
        }
    }

    private static ArrayList<Integer> readFromFile(File file) {
        ArrayList<Integer> list = new ArrayList<>();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (!line.trim().isEmpty()) { // Check if the line is not empty
                    try {
                        list.add(Integer.parseInt(line.trim()));
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping invalid number: " + line);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static int[][] getLevelData(String name) {
        File lvlFile = new File("res/" + name + ".txt");

        if (lvlFile.exists()) {
            ArrayList<Integer> list = readFromFile(lvlFile);
            if (list.isEmpty()) {
                System.out.println("The file " + name + " is empty or contains no valid data.");
                return null;
            }
            try {
                return Utliz.ArrayListto2Dint(list, 20, 20);
            } catch (IndexOutOfBoundsException e) {
                System.err.println("Error converting list to 2D array: " + e.getMessage());
                return null;
            }
        } else {
            System.out.println("File " + name + " does not exist");
            return null;
        }
    }
}
