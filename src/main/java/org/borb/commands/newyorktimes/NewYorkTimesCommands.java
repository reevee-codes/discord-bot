package org.borb.commands.newyorktimes;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.borb.commands.model.nytmostpopular.Article;
import org.borb.commands.model.nytmostpopular.NytResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import java.util.List;

@Service
public class NewYorkTimesCommands extends ListenerAdapter {
    RestTemplate restTemplate = new RestTemplate();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String content = message.getContentRaw();
        MessageChannel channel = event.getChannel();
        List<Article> articles = getMostPopular();
        if (content.equals("!world")) {
            EmbedBuilder result = new EmbedBuilder();
            channel.sendMessage(articles.get(0).toString()).queue();
        }
    }

    public List<Article> getMostPopular() {
        NytResponse response = null;
        try {
            response = restTemplate
                    .getForObject
                            ("https://api.nytimes.com/svc/mostpopular/v2/viewed/1.json?api-key=NAbawOR3bz4BA4NBLrGPMAGmpOCIr5a0", NytResponse.class);
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

