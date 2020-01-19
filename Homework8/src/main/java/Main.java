import java.io.File;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner in = new Scanner(System.in); //сканер для работы с консолью
        DataBase db = null; //объект работы с БД
        String command; //команды юзера

        App:
        while (true) {
            //Проверяем существование объекта db
            if (db == null) {
                System.out.println("Для выхода наберите exit");
                //Запрашиваем путь к базе данных
                System.out.print("Укажите путь к базе данных SQLite: ");

                //считываем данные введеные юзером
                command = in.nextLine().trim();

                //Проверяем комманду юзера
                if (command.equalsIgnoreCase("exit")) {
                    //выходим из приложения
                    break;
                } else {
                    //проверяем путь
                    if (new File(command).exists()) {
                        //По указанному пути файл найден
                        try {
                            //создаем объект БД
                            db = new DataBase(command);

                            //Таблица родители если она не существует
                            db.executeUpdateSQL("create table if not exists PARENTS (ID INTEGER PRIMARY KEY, NAME TEXT,SURNAME TEXT, AGE INTEGER)");
                            //таблица дети если она не существует
                            db.executeUpdateSQL("create table if not exists CHILDREN (ID INTEGER PRIMARY KEY , NAME TEXT,SURNAME TEXT, AGE INTEGER)");
                            //Связующая таблица родитель - ребенок
                            db.executeUpdateSQL("create table if not exists PARENT_CHILD (PARENT INTEGER, CHILDREN INTEGER)");
                        } catch (Exception e) {
                            //не удачное подключение к базе данных.
                            System.out.println("Не возможно подключиться к указанной базе данных!");
                        }
                    } else {
                        //файл не найден по указанному пути
                        System.out.println("Путь к фалу БД: " + command + " не верный!");
                    }
                }
            } else {
                //Menu menu = new Menu(in, new DataBase("E:\\Downloads\\sqlite-tools-win32-x86-3300100\\sqlite-tools-win32-x86-3300100\\DataBase.db"));
                Menu menu = new Menu(in, db);
                menu.start();
            }
        }
    }
}