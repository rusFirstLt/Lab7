import java.io.*;
import java.util.Scanner;

public class primer1 {
    public static void main(String[] args) {
        try {
            File folder = new File("E:\\Lab7");
            if (!folder.exists());
            folder.mkdir();

            File f1 = new File("E:\\Lab7\\Primer1.txt");
            f1.createNewFile();

            Scanner sc = new Scanner(System.in);
            System.out.print("Сколько чисел надо записать в файл? \n => ");
            int count = sc.nextInt(); /*ввести количество чисел*/

//            Открыть файл одновременно для чтения и записи "rw"
            RandomAccessFile rf = new RandomAccessFile(f1, "rw");
            System.out.println("Исходный размер файла в байтах =" + rf.length() + ", указатель стоит на " + rf.getFilePointer() + "-м байте");
            System.out.println("Введите числа:");
            for (int i = 0; i < count; i++) {
                rf.writeInt(sc.nextInt()); /*Записать числа в файл*/
            }                              /*Число типа int приходится 4 байта*/
            System.out.println("Новый размер файла в байтах =" + rf.length() + ", указатель стоит на " + rf.getFilePointer() + "-м байте");
            System.out.println("Количество стоит на " + rf.getFilePointer() + "-м байте");
            rf.close();

//            Открыть файл только для чтения "r"
            rf = new RandomAccessFile(f1, "r");

//            Прочитать числа из файла и вывести на экран
            System.out.println("\n Числа в файле:");
            for (int i = 0; i < count; i++) {   /*i - текущий номер числа*/
                rf.seek(i * 4);             /*Перевод указателся на текущее число -> i * 4 байта
                                                  В данной ситации при последовательном считывании
                                                  операцию seek() можно было не использовать*/
            System.out.println("Число" + i + ": " + rf.readInt());
            }

            System.out.println("Числа в обратном порядке: ");
            for (int i = count - 1; i >= 0; i--) {
                rf.seek(i * 4); /*Операцию использовать обязательно!*/
                System.out.println("Число" + i + ": " + rf.readInt());
            }
            rf.seek(rf.getFilePointer() - 4); /*Перевод указателя на последнее число*/
            System.out.println(" Количество чисел в файле = " + rf.length()/4 + ", последнее число = " + rf.readInt());

            /*Поиск заданного числа в файле и определение его номера (номеров)*/
            System.out.println("\nВведите число, которое нужно найти в файле => ");
            int x = sc.nextInt();
            int kol = 0;
            for (int i = 0; i < count; i++) {
                rf.seek(i * 4);
                int number = rf.readInt();
                if (number == x){
                    kol++;
                    System.out.print("номер " + i + ", ");
                }
            }
            System.out.println("Количество искомых чисел = " + kol);
            rf.close();

            /*СОРТИРОВКА ЧИСЕЛ В ФАЙЛЕ МЕТОДОМ «ПУЗЫРЬКА»*/
            rf = new RandomAccessFile(f1, "rw");    // открыт для чтения и записи

            for (int k = 0; k < count; k++) {           // k – номер просмотра
                for (int i = 0; i < count - k; i++) {   // i – номер числа
                    rf.seek(i * 4);                // переход к i-тому числу
                    int number1 = rf.readInt();         //чтение i-того и
                    int number2 = rf.readInt();         //(i+1)-го чисел в переменные
                    if (number1 > number2) {
                        rf.seek(i * 4);         //возврат указателя на i-тое число и
                        rf.writeInt(number2);       //перестановка (запись чисел в обратном
                        rf.writeInt(number1);       //порядке)
                    }
                }
            }

            System.out.println("\nЧисла, отсортированные в файле: ");
            for (int i = 0; i < count; i++) {
                rf.seek(i * 4);
                System.out.print(" " + rf.readInt());
            }
            rf.close();
        } catch (IOException e) {
            System.out.println("End of file " + e);
        }
    }
}
