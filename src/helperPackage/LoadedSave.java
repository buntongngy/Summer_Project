package helperPackage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class LoadedSave {

    public static BufferedImage getSpriteAtlas() {

        BufferedImage img =null;
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
            System.out.println("File" + name + "Already Exist!");
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

        try {
            PrintWriter pw = new PrintWriter(f);
            for (Integer i : idArr) {
                pw.println();
            }
            pw.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFromFile() {
        File txtFile = new File("res/text.txt");
        try {
            Scanner sc =new Scanner(txtFile);
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


}
