package org.borb.commands.air;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AirPollutionCommands extends ListenerAdapter {
    private static final String COMMAND = "!air";
    private static final String API_URL = "http://api.airvisual.com/v2/nearest_city";
    
    private final RestTemplate restTemplate;
    private final String apiKey;
    private static final Logger logger = LoggerFactory.getLogger(AirPollutionCommands.class);

    @Autowired
    public AirPollutionCommands(RestTemplate restTemplate, @Value("${airvisual.api.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        String content = event.getMessage().getContentRaw();
        
        if (COMMAND.equals(content)) {
            Current weather = getAirForNearestCity();
            event.getChannel().sendMessage(weather.toString()).queue();
        }
    }

    private Current getAirForNearestCity() {
        try {
            String url = String.format("%s?key=%s", API_URL, apiKey);
            WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
            
            if (response != null && "success".equals(response.getStatus())) {
                return response.getCurrent();
            }
        } catch (Exception ex) {
            logger.error("Error fetching air pollution data", ex);
        }
        return new Current();
    }
}
