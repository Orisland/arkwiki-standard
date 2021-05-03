package wiki;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.nodes.Document;

public class Operator {
    private String name;
    private Document HTML;
    private JSONObject jsonobject = new JSONObject();

    public Operator(String name){
        this.name = name;
    }

    public Operator(){
        this.name = "»ª·¨ÁÖ";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Document getHTML() {
        return HTML;
    }

    public void setHTML(Document HTML) {
        this.HTML = HTML;
    }

    public JSONObject getJsonobject() {
        return jsonobject;
    }

    public void setJsonobject(JSONObject jsonobject) {
        this.jsonobject = jsonobject;
    }

    @Override
    public String toString() {
        return null;
    }
}
