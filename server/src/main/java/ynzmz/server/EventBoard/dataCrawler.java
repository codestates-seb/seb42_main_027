package ynzmz.server.EventBoard;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.net.URL;


public class dataCrawler {
    public static void main(String[] args) {
        String etoosUrl = "https://go3.etoos.com/hietoos/event/default.asp?etoos=myall&ING_FLAG=I&etgrd=go3";
        String megaUrl = "https://www.megastudy.net/inside/event/event_list.asp?tab=0&order=1&smode=&sword=&page=1&intCP=NaN";
        Connection eConn = Jsoup.connect(etoosUrl);
        Connection mConn = Jsoup.connect(megaUrl);
//mega크롤링
        try {




            Document document = mConn.get();
//            document = Jsoup.parse(new URL(megaUrl).openStream(),"euc-kr",megaUrl);

//            Document document = Jsoup.parse(new URL(megaUrl).openStream(),"euc-kr",megaUrl);


            Elements megaeventsLink = document.getElementsByClass("event_list").select(" h4 > a");
//            System.out.println(document.outerHtml());
//            Elements title = document.select("#wrap_2014 > div.column_main > div.column_right > div > div.event_list > ul > li:nth-child(1) > div.info > h4 > a");
//            System.out.println(title);


            for(Element element: megaeventsLink){
                System.out.println(element.attr("abs:href"));
                System.out.println(element.select("a"));
            }

        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
