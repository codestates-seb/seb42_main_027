package ynzmz.server.event;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DataCrawlingTest {
    static void main(String[] args) {

        try {
            String Url = "https://www.megastudy.net/inside/event/event_list.asp?tab=0&order=1&smode=&sword=&page=1&intCP=NaN";
            Connection conn = Jsoup.connect(Url);

            Document doc = conn.get();

//            Elements contents = doc.select(".class #id");
            Elements classParses = doc.getElementsByClass("tbl tbl_ev_list");
            Element classParse = classParses.get(0);

//            for(int i = 0; i<)
//            Element titleUrl = classParse.getElementsByTag("tbody").get(i)
            System.out.println(classParse);

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
