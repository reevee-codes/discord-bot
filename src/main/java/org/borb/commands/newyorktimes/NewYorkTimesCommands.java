package org.borb.commands.newyorktimes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.borb.commands.model.nytmostpopular.Article;
import org.borb.commands.model.nytmostpopular.NytResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewYorkTimesCommands extends ListenerAdapter {
    RestTemplate restTemplate;


    //NAbawOR3bz4BA4NBLrGPMAGmpOCIr5a0
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String content = message.getContentRaw();
        MessageChannel channel = event.getChannel();
        final ObjectMapper mapper = new ObjectMapper();
        if (content.equals("!world")) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.nytimes.com/svc/topstories/v2/world.json?api-key=NAbawOR3bz4BA4NBLrGPMAGmpOCIr5a0"))
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            ObjectMapper objectMapper = new ObjectMapper();
            HttpResponse<String> response = null;
            try {

                response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//                Map<String, Object> map = new HashMap<String, Object>();
//                map = mapper.readValue(response.toString(), new TypeReference<HashMap<String, Object>>() {
//                });
                //tu sie wyjebalo i jest zero
                JSONObject jsonObj = new JSONObject(response);

                JSONArray JsonArray = jsonObj.getJSONArray("results");
                List<JSONObject> lista = new ArrayList<>();

                for (int i = 0; i < JsonArray.length(); i++) {
                    JSONObject jsonObject = JsonArray.getJSONObject(i);
                    lista.add(jsonObject);
                }
//                List<Result> listOfResults = objectMapper.readValue(response.toString(), new TypeReference<List<Result>>() {
//                });
//                map.get("results").toString();
                System.out.println(lista);
//                channel.sendMessage(listOfResults.toString()).queue();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println(response.body());
        }
    }

    public List<Article> getMostPopular() {
        NytResponse response = null;
        try {
            response = restTemplate.getForObject("https://api.nytimes.com/svc/topstories/v2/world.json?api-key=NAbawOR3bz4BA4NBLrGPMAGmpOCIr5a0", NytResponse.class);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        List<Article> articles = new ArrayList<>();
        if (response != null && response.getStatus().equals("OK")) {
            Article[] allArticles = response.getResults();
            for (Article article : allArticles) {
                if (article.getMedia().length > 0) {
                    article.setImageUrl(article.getMedia()[0].getMedia_metadata()[1].getUrl());
                    articles.add(article);
                }
            }
        } else {
            articles.add(new Article("Sorry, having trouble fetching Most Popular.."));
        }
        return articles;
    }
}

