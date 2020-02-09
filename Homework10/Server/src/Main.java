import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void  main(String[] args){
        try(ServerSocket serverSocket = new ServerSocket(9998)){
            while (true){
                //Ждем входящее подключение
                Socket socket = serverSocket.accept();
                //Запускаем Серверный поток
                Server server = new Server(socket);
                server.run();
            }
        }catch (IOException e){
            System.out.println(e.fillInStackTrace());
        }
    }
}
