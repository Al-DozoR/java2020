import java.util.Scanner;

public class Main {
    public static void main (String[] args){
        final Scanner scanner = new Scanner(System.in); //сканер консоли
        String command; //команды юзера

        while (true){
            System.out.println("Для выхода наберите exit");
            System.out.print("Введите название города: ");

            //Получаем команду юзера
            command = scanner.nextLine().trim();

            switch (command.toLowerCase()) {
                case "exit":
                    //Выход
                    System.exit(0);
                default:
                    //Отображаем информацию о погоде
                    System.out.println(WeatherUtil.readWeatherCity(command));
            }
        }
    }
}
