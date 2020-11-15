import java.io.*;
import java.util.Scanner;

class Ozero implements Serializable {
    String name;    // название озера
    double square;  // площадь озера
    String place;   // место расположения озера
}

public class var_10_Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try {
        Scanner sc = new Scanner(System.in);
        // создается файл на диске
        File f = new File("E:\\Lab7\\var_10");
        f.createNewFile();

        // -------------ЗАПИСЬ ОБЪЕКТА В ФАЙЛ-------------
        // Создается поток для записи объекта
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        // Вводится информация об объекте (озере)
        Ozero ozero = new Ozero();

        /*System.out.print("Сколько озер надо записать в файл? => ");
        int count = sc.nextInt(); *//*ввести количество чисел*//*
        sc.nextLine();*/

        System.out.println("Введите информацию об озере: ");
        System.out.print("Название озера => ");
        ozero.name = sc.nextLine();
        System.out.print("Место расположения озера => ");
        ozero.place = sc.nextLine();
        System.out.print("Площадь озера => ");
        ozero.square = sc.nextDouble();

        oos.writeObject(ozero);
        oos.flush();
        oos.close();

        // -------------ЧТЕНИЕ ОБЪЕКТА ИЗ ФАЙЛА-------------
        // Создается поток для чтения объекта из файла
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream oin = new ObjectInputStream(fis);

        // Объект считывается, и на экран выводится требуемая информация
        ozero = (Ozero) oin.readObject();
        System.out.println(" Название озера - " + ozero.name);
        System.out.println(" Место расположения - " + ozero.place);
        System.out.println(" Площадь озера = " + ozero.square);

        // Поток закрывается
        oos.close();
        } catch (IOException e) {
            System.out.println("End of file " + e);
        }
    }
}