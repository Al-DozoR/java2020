import java.io.*;
import java.net.Socket;

//Клиент
public class Client {
    public static void main(String[] args){
        try(Socket socket = new Socket("localhost",10000)){
            //Исходящий поток байтов
            OutputStream out = socket.getOutputStream();
            //Входящий поток байтов
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Файл для отправки
            File file =new File("Example.txt");
            //Буфер
            byte [] buffer = new byte[1024];
            //Байты файла
            InputStream inFile = new FileInputStream(file);
            //Отправляем файл на сервер
            while (inFile.read(buffer)>0){
                out.write(buffer);
            }

            //Читаем ответ сервера
            String str = reader.readLine();
            System.out.println(str);

        }catch (IOException e){
            e.getStackTrace();
        }
    }
}

