package ssf.crypto.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ssf.crypto.assessment.model.Articles;
import ssf.crypto.assessment.service.NewsService;

@Controller
@RequestMapping("/articles")
public class NewsController {

    @Autowired
    private NewsService newsSvc;

    @GetMapping
    public String getArticles(Model model, @RequestParam String news) {
        List<Articles> articles = newsSvc.getArticles(news);
        model.addAttribute("news", news); 
        model.addAttribute("articles", articles);
        return "articles";
    }
    
    //@PostMapping
    //public String postArticles(@RequestBody MultiValueMap<String, String> form, Model model) {

    //}
}
