package org.borb.commands.temperature;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class TemperatureCommands extends ListenerAdapter {
    private static final String COMMAND = "!temp";
    private static final String BASE_URL = "https://api.open-meteo.com/v1/forecast";

    String latitude = "54.3521"; // Latitude of Gdańsk
    String longitude = "18.6464";


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        String content = event.getMessage().getContentRaw();

        if (COMMAND.equals(content)) {
            String weather = null;
            try {
                weather = getAirForNearestCity();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            event.getChannel().sendMessage(weather.toString()).queue();
        }
    }

    private String getAirForNearestCity() throws Exception {
        String url = BASE_URL + "?latitude=" + latitude + "&longitude=" + longitude + "&current_weather=true";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("Failed to fetch weather data. HTTP Status Code: " + response.statusCode());
        }

        JSONObject jsonResponse = new JSONObject(response.body());
        if (jsonResponse.has("current_weather")) {
            JSONObject currentWeather = jsonResponse.getJSONObject("current_weather");
            double temperature = currentWeather.getDouble("temperature");
            double windSpeed = currentWeather.getDouble("windspeed");
            return String.format("Temperature: %.1f°C, Wind Speed: %.1f km/h for Gdańsk", temperature, windSpeed);
        } else {
            throw new Exception("Current weather data is not available in the response.");
        }
    }
}
