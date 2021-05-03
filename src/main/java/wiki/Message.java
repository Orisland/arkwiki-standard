package wiki;

import com.alibaba.fastjson.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Message {
    static JSONArray Array = null;
    static JSONObject Object = null;

    public static Document connect(String name) throws IOException{
        String url = "http://prts.wiki/w/";
        Document document = Jsoup.connect(url + name).get();
        return document;
    }

    public static void Potential(Operator operator) throws IOException {
        JSONObject object = operator.getJsonobject();
        Document document = operator.getHTML();
        Object = new JSONObject();
        Array = new JSONArray();
        for (int i=1 ;i <= 5; i++){
            Object.put("Potential_"+i,
                    document.select("#mw-content-text > div > table:nth-child(33) > tbody > tr:nth-child("+i+") > td").text());
            Array.add(Object);
            Object = new JSONObject();
        }
        object.put("potential",Array);
    }

    public static void Name(Operator operator) throws IOException{
        JSONObject object = operator.getJsonobject();
        Document document = operator.getHTML();
        object.put("name",document.select("#firstHeading").text());
    }

    public static void Self_img(Operator operator){
        JSONObject object = operator.getJsonobject();
        Document document = operator.getHTML();
        Object = new JSONObject();
        Object.put("self_pic0",document.select("#img-stage0 > img").attr("src"));
        Object.put("self_pic1",document.select("#img-stage2 > img").attr("src"));
        object.put("self_pic",Object);
    }

    public static void Talent(Operator operator){
        JSONObject object = operator.getJsonobject();
        Document document = operator.getHTML();
        Elements table = document.select("table.wikitable.logo").get(4).select("tr");
        System.out.println(table.size());
        Object = new JSONObject();
        for (int i=1; i<table.size(); i++){
            for (int j=1; j<table.get(i).childrenSize(); j++){
                Element element = table.get(j);
                JSONObject temp = new JSONObject();
                temp.put("name", element.select("td:nth-child(2)").get(0).text());
                temp.put("condition", element.select("td:nth-child(3)").get(0).text());
                temp.put("content", element.select("td:nth-child(4)").get(0).text());
                Object.put("talent",temp);
                System.out.println(Object);
            }
        }
//        System.out.println(table.get(1));
//        System.out.println(table.get(1).childrenSize());
//        System.out.println(table.get(2));
    }

    public static void main(String[] args) throws IOException {
        Operator operator = new Operator("¿­¶ûÏ£");
        operator.setHTML(connect("¿­¶ûÏ£"));
//        Potential(operator);
//        Name(operator);
//        Self_img(operator);
        Talent(operator);
//        System.out.println(operator.getJsonobject());
    }
}
