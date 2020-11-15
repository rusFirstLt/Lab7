import java.io.*;
import java.util.Scanner;

public class var_10_RandomAccessFile2 {
    public static void main(String[] args) {
        try {
            File folder = new File("E:\\Lab7");
            if (!folder.exists())
                folder.mkdir();

            File f1 = new File("E:\\Lab7\\var_10_RandomAccessFile2.txt");
            if (!f1.exists())
                f1.createNewFile();
            RandomAccessFile rf = new RandomAccessFile(f1,"rw"); // чтение и запись

            long fSize = rf.length(); // размер файла
            Scanner sc = new Scanner(System.in);
            System.out.print("Введите количество озер для записи в файл => ");
            int kol = sc.nextInt();
            sc.nextLine(); // очистка буфера после ввода числа

            String name, place;
            int area;


            for (int i = 0; i < kol; i++) {
                System.out.print("Введите название " + (i + 1) + " озера => ");
                name = sc.next();
                rf.seek(rf.length());
                rf.writeUTF(name);
                for (int j = 0; j < 20 - name.length(); j++) rf.writeByte(1);

                System.out.print("Введите местоположение озера => ");
                place = sc.next();
                rf.writeUTF(place);
                for (int j = 0; j < 20 - place.length(); j++) rf.writeByte(1);

                System.out.print("Введите площадь озера => ");
                area = sc.nextInt();
                sc.nextLine();
                rf.writeInt(area);
            }
            rf.close();

            
            rf = new RandomAccessFile(f1, "r");
            rf.seek(0);
            System.out.println("Информация об озерах\n" + "Название озера \t\t Место \t\t Площадь");
            for (int i = 0; i < kol; i++) {
                name = rf.readUTF();
                for (int j = 0; j < 20 - name.length(); j++) rf.readByte();

                place = rf.readUTF();
                for (int j = 0; j < 20 - place.length(); j++) rf.readByte();

                area = rf.readInt();
                System.out.println(name + "\t\t\t" + place + "\t\t\t" + area);
            }
            rf.close();
        } catch (IOException e) {
            System.out.println("End of file " + e);
        }
    }
}
