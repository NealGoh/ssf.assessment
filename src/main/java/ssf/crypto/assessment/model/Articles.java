package ssf.crypto.assessment.model;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Articles {
    
    private String id;
    private Long published_on;
    private String title;
    private String url;
    private String imageurl;
    private String body;
    private String tags;
    private String categories;

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public Long getPublished_on() {return published_on;}
    public void setPublished_on(Long published_on) {this.published_on = published_on;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getUrl() {return url;}
    public void setUrl(String url) {this.url = url;}

    public String getImageurl() {return imageurl;}
    public void setImageurl(String imageurl) {this.imageurl = imageurl;}

    public String getBody() {return body;}
    public void setBody(String body) {this.body = body;}

    public String getTags() {return tags;}
    public void setTags(String tags) {this.tags = tags;}

    public String getCategories() {return categories;}
    public void setCategories(String categories) { this.categories = categories; }

    public static Articles create(JsonObject jo) {
        Articles a = new Articles();
        a.setId(jo.getString("id"));
        a.setPublished_on(Long.parseLong(jo.getString("published_on")));
        a.setTitle(jo.getString("title"));
        a.setUrl(jo.getString("url"));
        a.setImageurl(jo.getString("imageurl"));
        a.setBody(jo.getString("body"));
        a.setTags(jo.getString("tags"));
        a.setCategories(jo.getString("categories"));
        return a;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("id", id)
            .add("published_on", published_on)
            .add("title", title)
            .add("url", url)
            .add("imageurl", imageurl)
            .add("body", body)
            .add("tags", tags)
            .add("categories", categories)
            .build();
    }
    
}
