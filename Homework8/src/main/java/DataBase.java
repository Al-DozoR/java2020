import java.sql.*;

//класс работы с БД
public class DataBase{
    final private String url; //путь к базе данных
    private Connection connection; //подключение
    private Statement statement;

    //конструктор
    DataBase(String pathDB) throws SQLException {
        this.url="jdbc:sqlite:" + pathDB;
        //создаем подключение
        connection=DriverManager.getConnection(url);
        statement=connection.createStatement();
    }

    //Проверка текущего соединения с БД
    public boolean check(){
        try(Connection connection = DriverManager.getConnection(url)){
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    //Проверка подключения к базе данных
    static public boolean checkDataBase(String pathDB){
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:" + pathDB)){
            //успешно подключились
            return true;
        }catch (Exception e){
            //не удачное подключение
            return false;
        }
    }

    //Выполнене команд execute
    public boolean executeSQL(String SQL) throws SQLException {
        return statement.execute(SQL);
    }

    //Выполнение команды executeQuery:
    public ResultSet executeQuerySQL(String SQL) throws SQLException{
        return statement.executeQuery(SQL);
    }

    //Выполнение команд executeUpdate
    public int executeUpdateSQL(String SQL) throws SQLException{
        return statement.executeUpdate(SQL);
    }
}
