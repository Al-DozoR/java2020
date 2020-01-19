import java.sql.ResultSet;
import java.util.Scanner;

//Меню
public class Menu{
    private final Scanner scanner; //сканер консоли
    private String command; //команды юзера
    private final DataBase db; //объект работы с БД

    //конструктор
    public Menu(Scanner scanner, DataBase db) {
        this.scanner = scanner;
        this.db=db;
    }

    //запуск меню
    public void start(){
        //главное меню
        String[] menu = {
                "Для выхода наберите exit",
                "Введите номер команды:",
                "1 - Показать все данные родитель - ребенок",
                "2 - Показать всех родителей",
                "3 - Показать всех детей",
                "4 - Создать родителя",
                "5 - Создать ребенка",
                "6 - Очистить БД"};
        app:    while(true){
                    //Отображаем меню
                    show(menu);
                    //Получаем команду юзера
                    command=scanner.nextLine().trim();
                    switch (command.toLowerCase()){
                        case "1":
                            menu1();
                            break;
                        case "2":
                            menu2();
                            break;
                        case "3":
                            menu3();
                            break;
                        case "4":
                            menu4();
                            break;
                        case "5":
                            menu5();
                            break;
                        case "6":
                            menu6();
                            break;
                        case "exit":
                            System.exit(0);
                        default:
                            System.out.println("Неизвестная команда");
                    }
                }
    }

    //Отобразить меню
    private void show(String[] menu){
        for(String str : menu){
            System.out.println(str);
        }
    }


    //Меню - Показать все данные родитель - ребенок"
    private void menu1(){
       app: while(true){
                try{
                    //получаем данные родитель - ребенок
                    ResultSet result = db.executeQuerySQL("select t1.id, t1.surname, t1.name, t1.age, " +
                            "t2.surname, t2.name, t2.age " +
                            "from PARENTS as t1 " +
                            "inner join " +
                            "PARENT_CHILD as t3 " +
                            "on t1.ID=t3.PARENT " +
                            "inner join " +
                            "CHILDREN as t2 " +
                            "on t2.ID=t3.CHILDREN");
                    //Выводим общую информацию в консоль
                    int id=0;
                    if(!result.next()){
                        System.out.println("Нет данных!");
                        break app;
                    }
                    info:   while(id!=result.getInt(1) || result.next()){
                                //id родителя
                                id = result.getInt(1);
                                System.out.println("Родитель: " + result.getString(2) + " " + result.getString(3) + "("+result.getInt(4)+")");
                                while(id==result.getInt(1)){
                                    System.out.println("\t"+result.getString(6)+"("+result.getString(7)+")");
                                    if(!result.next()){
                                        break info;
                                    }
                                }
                            }
                }catch (Exception e){
                    System.out.println("Ошибка выполнения SQL запроса к БД");
                }
                System.out.println("Возврат в гланове меню -> END, выход из приложения -> EXIT: ");
                //получаем команду юзера
                command=scanner.nextLine().trim();
                switch (command.toLowerCase()){
                    case "end":
                        //возвращаемся в меню выше
                        break app;
                    case "exit":
                        //выходим из приложения
                        System.exit(0);
                    default:
                        System.out.println("Комманда не распознана!");
                }
            }
    }

    //Меню - Показать всех родителей"
    private void menu2(){
        app: while(true){
                try{
                    //получаем данные о всех родителях
                    ResultSet result = db.executeQuerySQL("Select surname, name, age from parents");
                    //выводим инофрмаци в консоль
                    System.out.println("Таблица Родители:\n|\tФамилия\t|\tИмя\t|\tВозраст\t|\t\n------------------------------------------------------");
                    while(result.next()){
                        System.out.println("|\t" + result.getString(1) +"\t|\t"+result.getString(2) +"\t|\t"+result.getString(3)+"\t|");
                    }
                }catch (Exception e){
                    System.out.println("Ошибка выполнения SQL запроса к БД");
                }
                System.out.println("Возврат в гланове меню -> END, выход из приложения -> EXIT: ");
                //получаем команду юзера
                command=scanner.nextLine().trim();
                switch (command.toLowerCase()) {
                    case "end":
                        //возвращаемся в меню выше
                        break app;
                    case "exit":
                        //выходим из приложения
                        System.exit(0);
                    default:
                        System.out.println("Комманда не распознана!");
                }
            }
    }

    //Меню - Показать всеx детей"
    private void menu3(){
        app: while(true){
                try{
                    //получаем данные о всех родителях
                    ResultSet result = db.executeQuerySQL("Select surname, name, age from children");
                    //выводим инофрмаци в консоль
                    System.out.println("Таблица Дети:\n|\tФамилия\t|\tИмя\t|\tВозраст\t|\t\n------------------------------------------------------");
                    while(result.next()){
                        System.out.println("|\t" + result.getString(1) +"\t|\t"+result.getString(2) +"\t|\t"+result.getString(3)+"\t|");
                    }
                }catch (Exception e){
                    System.out.println("Ошибка выполнения SQL запроса к БД");
                }
                System.out.println("Возврат в гланове меню -> END, выход из приложения -> EXIT: ");
                //получаем команду юзера
                command=scanner.nextLine().trim();
                switch (command.toLowerCase()) {
                    case "end":
                        //возвращаемся в меню выше
                        break app;
                    case "exit":
                        //выходим из приложения
                        System.exit(0);
                    default:
                        System.out.println("Комманда не распознана!");
                }
        }
    }

    //Меню - Создать родителя
    private void menu4(){
        app: while(true){
                System.out.println("Возврат в гланове меню -> END, выход из приложения -> EXIT: ");
                System.out.println("Введите через пробел Фамилию Имя Возраст родителя: ");
                //получаем команду юзера
                command=scanner.nextLine().trim();
                switch (command.toLowerCase()){
                    case "end":
                        //возвращаемся в меню выше
                        break app;
                    case "exit":
                        //выходим из приложения
                        System.exit(0);
                    default:
                        try {
                            //Создаем родителя в БД
                            //Создаем родителя
                            db.executeUpdateSQL("INSERT INTO PARENTS(SURNAME,NAME,AGE) VALUES('" + command.split(" ")[0] + "','"
                                    + command.split(" ")[1] + "',"
                                    + command.split(" ")[2] + ")");
                            //id родителя
                            int idParent = db.executeQuerySQL("Select last_insert_rowid()").getInt(1);

                            //Фамилия
                            String surname = command.split(" ")[0];

                            //Предлогаем добавить ребенка
                            while(true) {
                                System.out.print("Добавить ребенка? 1 - да, 2 - нет");
                                //берем ответ юзера
                                command = scanner.nextLine().trim();

                                if (command.equals("1")) {
                                    System.out.print("Введите через пробел имя возраст ребенка: ");
                                    command = scanner.nextLine().trim();
                                    //создаем родителя
                                    db.executeUpdateSQL("INSERT INTO CHILDREN(surname,name,age) VALUES('" + surname + "','"
                                            + command.split(" ")[0] + "','"
                                            + command.split(" ")[1] + "')");
                                    //добавляем данные в связующую таблицу
                                    db.executeUpdateSQL("INSERT INTO PARENT_CHILD VALUES(" + idParent + ", (select last_insert_rowid()))");
                                } else {
                                    //выходим из меню
                                    break;
                                }
                            }
                        }catch (Exception e){
                            System.out.println("Ошибка выполнения SQL запроса к БД");
                        }
                }
            }
    }

    //Меню - Создать ребенка
    private void menu5(){
        app: while(true){
            System.out.println("Возврат в гланове меню -> END, выход из приложения -> EXIT: ");
            System.out.println("Введите через пробел Фамилию Имя Возраст ребенка: ");
            //получаем команду юзера
            command=scanner.nextLine().trim();
            switch (command.toLowerCase()){
                case "end":
                    //возвращаемся в меню выше
                    break app;
                case "exit":
                    //выходим из приложения
                    System.exit(0);
                default:
                    try {
                        //Создаем ребенка
                        db.executeUpdateSQL("INSERT INTO Children(SURNAME,NAME,AGE) VALUES('" + command.split(" ")[0] + "','"
                                + command.split(" ")[1] + "',"
                                + command.split(" ")[2] + ")");
                        //id родителя
                        int idParent = db.executeQuerySQL("Select last_insert_rowid()").getInt(1);

                        //Фамилия
                        String surname = command.split(" ")[0];

                        //Предлогаем добавить ребенка
                        while(true) {
                            System.out.print("Добавить родителя? 1 - да, 2 - нет");
                            //берем ответ юзера
                            command = scanner.nextLine().trim();

                            if (command.equals("1")) {
                                System.out.print("Введите через пробел имя возраст родителя: ");
                                command = scanner.nextLine().trim();
                                //создаем родителя
                                db.executeUpdateSQL("INSERT INTO parents(surname,name,age) VALUES('" + surname + "','"
                                        + command.split(" ")[0] + "','"
                                        + command.split(" ")[1] + "')");
                                //добавляем данные в связующую таблицу
                                db.executeUpdateSQL("INSERT INTO PARENT_CHILD VALUES((select last_insert_rowid())," + idParent + ")");
                            } else {
                                //выходим из меню
                                break;
                            }
                        }
                    }catch (Exception e){
                        System.out.println("Ошибка выполнения SQL запроса к БД");
                    }
            }
        }
    }

    //Меню - Настройки"
    private void menu6(){
        try{
            //Удаляем таблицы
            db.executeUpdateSQL("Delete from Parents");
            db.executeUpdateSQL("Delete from Children");
            db.executeUpdateSQL("Delete from Parent_child");
            System.out.println("Таблицы очищены!");
        }catch (Exception e){
            System.out.println("Ошибка SQL запроса!");
        }
    }
}

