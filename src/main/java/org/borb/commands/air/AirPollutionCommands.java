package org.borb.commands.air;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.web.client.RestTemplate;

public class AirPollutionCommands extends ListenerAdapter {

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String content = message.getContentRaw();
        MessageChannel channel = event.getChannel();
        if (content.equals("!air")) {

            Current weather = getAirForNearestCity();
                channel.sendMessage(weather.toString()).queue();
        }
    }

    public Current getAirForNearestCity() {
        WeatherResponse response = null;
        try {
            response = restTemplate
                    .getForObject
                            ("http://api.airvisual.com/v2/nearest_city?key=875e3a96-eeaf-4f6c-9650-163fbdfa6884", WeatherResponse.class);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if (response != null && response.getStatus().equals("success")) {
            Current weather = response.getCurrent();
            return weather;
            }
        return new Current();
    }
}
