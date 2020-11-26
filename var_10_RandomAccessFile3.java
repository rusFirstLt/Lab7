package lab7;

import java.io.*;
import java.util.Scanner;

public class var_10_RandomAccessFile3 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Сколько озер нужно записать в файл? => ");

        int count = sc.nextInt();
        sc.nextLine();

        RandomAccessFile rf, rf2;
        String name, place, square, Path1 = "E:\\Lab7\\var_10_RandomAccessFile1.txt", Path2 = "E:\\Lab7\\lab7.var_10_RandomAccessFile_information.txt";

        try {
            File f1 = new File(Path1);
            File f2 = new File(Path2);

            rf = new RandomAccessFile(f1, "rw");
            rf2 = new RandomAccessFile(f2, "rw");

            for (int i = 0; i < count; i++) {
                System.out.print("Введите название озера => ");
                name = sc.nextLine();
                rf.writeUTF(name);
                for (int j = 0; j < 20 - name.length(); j++) {
                    rf.writeByte(1);
                }

                System.out.println("Введите месторасположение оезра => ");
                place = sc.nextLine();
                rf.writeUTF(place);
                for (int j = 0; j < 20 - place.length(); j++) {
                    rf.writeByte(1);
                }

                System.out.println("Введите площадь озера => ");
                square = sc.nextLine();
                rf.writeUTF(square);

                for (int j = 0; j < 20 - square.length(); j++) {
                    rf.writeByte(1);
                }

                System.out.println();
                if (name.equals("Россия")) {
                    rf2.writeUTF(name);

                    for (int j = 0; j < 20 - name.length(); j++) rf2.writeByte(1);
                    rf2.writeUTF(place);

                    for (int j = 0; j < 20 - place.length(); j++) rf2.writeByte(1);
                    rf2.writeUTF(square);

                    for (int j = 0; j < 20 - square.length(); j++) rf2.writeByte(1);
                }
            }
        } catch (IOException e) {
            System.out.println("End of file " + e);
        }
    }
}