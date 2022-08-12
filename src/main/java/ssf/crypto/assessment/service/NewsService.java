package ssf.crypto.assessment.service;

import java.io.Reader;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import ssf.crypto.assessment.model.Articles;
import ssf.crypto.assessment.repository.NewsRepository;

@Service
public class NewsService {

    private static final String URL = "https://min-api.cryptocompare.com/data/v2/news/?lang=EN";

    @Value("${API_KEY}")
    private String key;

    @Autowired
    private NewsRepository newsRepo;

    public List<Articles> getArticles(String news) {

        Optional<String> opt = newsRepo.get(news);
        String payload;

        if (opt.isEmpty()) {

            System.out.println("Getting latest news articles from CryptoCompare");

            try {

                String url = UriComponentsBuilder.fromUriString(URL)
                    .queryParam("q", URLEncoder.encode(news, "UTF-8"))
                    .queryParam("appid", key)
                    .toUriString();

                RequestEntity<Void> req = RequestEntity.get(url).build();

                RestTemplate template = new RestTemplate();
                ResponseEntity<String> resp;

                resp = template.exchange(req, String.class);

                payload = resp.getBody();
                //System.out.println("payload: " + payload);

                newsRepo.save(news, payload);

            } catch (Exception ex) {
                System.err.printf("Error: %s\n", ex.getMessage()); 
                return Collections.emptyList();
            }
        
        } else {
            payload = opt.get();
            //System.out.printf("Cache: %s\n", payload);
        }

        Reader strReader = new StringReader(payload);
        JsonReader jsReader = Json.createReader(strReader);

        JsonObject articlesResult = jsReader.readObject();
        JsonArray newsArticles = articlesResult.getJsonArray("articles");
        List<Articles> list = new LinkedList<>();
        for (int i = 0; i < newsArticles.size(); i++) {
            JsonObject jo = newsArticles.getJsonObject(i);
            list.add(Articles.create(jo));
        }

        return list;

    }
    
}
