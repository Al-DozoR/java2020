import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherUtil {
    private static final String KEY = "3b8f0c9aeacf4655685b7e433d493005";

    public static String readWeatherCity(String city){
        try {
            //Запрос API
            URL url = new URL("https://api.openweathermap.org/data/2.5/find?q=" + city + "&appid=" + KEY + "&units=metric");
            //Соединение
            HttpURLConnection hpCon = (HttpURLConnection) url.openConnection();
            hpCon.setRequestMethod("POST");

            //Буфер
            StringBuilder response = new StringBuilder();
            BufferedReader in = new BufferedReader(new InputStreamReader(hpCon.getInputStream()));

            //Считываем данные в буфер
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            //Парсим ответ
            JSONObject obj = new JSONObject(response.toString());
            Double currentTemperature = obj.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp");//Текущая температура
            Double pressure = obj.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("pressure");//Давление
            Double Deg = obj.getJSONArray("list").getJSONObject(0).getJSONObject("wind").getDouble("deg");//Направление ветра
            Double windSpeed = obj.getJSONArray("list").getJSONObject(0).getJSONObject("wind").getDouble("speed");//Скорость ветра

            //Возвращаем информацю о погоде
            return "Температура воздуха: " + currentTemperature.toString() + "\n" +
                    "Давление: " + pressure.toString() + "\n" +
                    "Ветер - " + windDeg(Deg) + " " + windSpeed + " м/с";

        }catch (JSONException e){
            return "Город не найден";
        }catch (IOException e){
            return "Сервер не доступен";
        }
    }

    //Направления ветра
    private static String windDeg(Double deg){
        String[] directions = {"Северный","Северо-восточный","Восточный","Юго-Восточный","Южный","Юго-Западный","Западный","Северо-Западный"};
        return directions[(int)Math.floor(((deg + 22.5)%360)/45)];
    }
}
