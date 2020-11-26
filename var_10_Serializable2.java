package lab7;

import java.io.*;
import java.util.Scanner;

class Ozero1 implements Serializable {
    String name; // название озера
    double square;// площадь озера
    String place; // место расположения озера
}

public class var_10_Serializable2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        File LakeData = new File("E:\\Lab7\\var_10_Serializable2.txt");
        File information = new File("E:\\Lab7\\var_10_Serializable_information.txt");
        if (information.exists()) {
            information.delete();
        }
        information.createNewFile();

        if (LakeData.exists()) {
            LakeData.delete();
        }
        LakeData.createNewFile();
        System.out.print("Сколько озер нужно записать в файл? => ");

        int count = sc.nextInt();
        int kol_oz_Russia = 0;

        FileOutputStream f1 = new FileOutputStream(LakeData);
        ObjectOutputStream oos = new ObjectOutputStream(f1);
        FileOutputStream fosInformation = new FileOutputStream(information);
        ObjectOutputStream oosInformation = new ObjectOutputStream(fosInformation);

        Ozero1 ozero = new Ozero1();
        sc.nextLine();

        for (int i = 0; i < count; i++) {
            ozero = new Ozero1();

            System.out.println("Введите название озера: ");
            ozero.name = sc.nextLine();

            System.out.println("Введите расположение озера: ");
            ozero.place = sc.nextLine();

            System.out.println("Введите площадь озера: ");
            ozero.square = sc.nextDouble();
            oos.writeObject(ozero);

            if (ozero.place.equals("Russia")) {
                oosInformation.writeObject(ozero);
                kol_oz_Russia += 1;
            }

            if (ozero.place.equals("Россия")) {
                oosInformation.writeObject(ozero);
                kol_oz_Russia += 1;
            }
        }
        FileInputStream fis = new FileInputStream(information);
        ObjectInputStream oin = new ObjectInputStream(fis);
        int kol = 1;

        System.out.println("Озёра в России: ");
        if (kol_oz_Russia != 0) {
            for (int i = 0; i < kol_oz_Russia; i++) {
                ozero = (Ozero1) oin.readObject();
                System.out.println("Озеро №" + kol);
                System.out.println("Название озера: " + ozero.name);
                System.out.println("Расположение озера: " + ozero.place);
                System.out.println("Площадь озера: " + ozero.square);
                System.out.println();
                kol += 1;
            }
        } else {
            System.out.println("Таких озер нет");
        }
        oos.flush();
        oos.close();

        fis = new FileInputStream(LakeData);
        oin = new ObjectInputStream(fis);

        for (int i = 0; i < count; i++) {
            ozero = (Ozero1) oin.readObject();
            System.out.println("Озеро №" + kol);
            System.out.println("Название озера: " + ozero.name);
            System.out.println("Расположение озера: " + ozero.place);
            System.out.println("Площадь озера: " + ozero.square);
            System.out.println();
            kol += 1;
        }
        oosInformation.flush();
        oosInformation.close();
    }
}
