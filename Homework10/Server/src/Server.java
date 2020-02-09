import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server extends Thread {
    private static Integer numThread=0; //Номер потока
    private InputStream in;//Входящий поток данных
    private OutputStream out;//Исходящий поток данных

    //Конструктор
    public Server(Socket socket) throws IOException{
        //Создаем потоки данных
        in = socket.getInputStream();
        out = socket.getOutputStream();
        //Увеличиваем счетчик потоков
        numThread++;
    }

    public void run(){
        try {
            System.out.println("Серверный поток № " + numThread + " запущен!");
            //Создаем файл
            File file = createFile();

            //Если файл создан
            if (file != null) {
                //Создаем буфер для чтения данных
                byte[] buffer = new byte[1024];

                //Считываем данные из потока, записываем в файл
                while (in.available() > 0) {
                    //Считываем из потока
                    in.read(buffer);
                    //Пишем данные в файл
                    Files.write(Paths.get(file.getPath()),buffer, StandardOpenOption.APPEND);
                }
                System.out.println("Сервер получил файл!!");

            //Исходящий поток
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            //Отправляем клинту фразу
            writer.write("Принят файл!!!");
            writer.newLine();
            writer.flush();
            }
        }catch (IOException e){
            System.out.println("Ошибка серверного потока № " + numThread);
        }finally {
            System.out.println("Серверный поток № " + numThread + " завершен!");
        }
    }

    //Создаем новый файл
    private File createFile(){
        //Текущее время
        String nowTime = new SimpleDateFormat("dd_mm_yyyy_hh_mm_ss").format(new Date());

        try{
            //Создаем файл
            File file = new File("data_" + numThread + "_" + nowTime +".txt");

            //Если файл не создался пробуем пересоздать с другим именем
            while (!file.createNewFile()){
                file = new File ("data" + numThread + nowTime +".txt");
            }

            //Возвращаем новый файл
            return file;
        }catch (IOException e){
            System.out.println("Сервер не может создать файл!");
            return null;
        }
    }
}
