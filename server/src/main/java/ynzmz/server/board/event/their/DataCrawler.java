package ynzmz.server.board.event.their;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;


public class DataCrawler {


    public static void main(String[] args) {
        String etoosUrl = "https://go3.etoos.com/hietoos/event/default.asp?etoos=myall&ING_FLAG=I&etgrd=go3";
        String megaUrl = "https://www.megastudy.net/inside/event/event_list.asp?tab=0&order=1&smode=&sword=&page=1&intCP=NaN";
        Connection eConn = Jsoup.connect(etoosUrl);
        Connection  mConn = Jsoup.connect(megaUrl);

        String title =
                "";
        try {


            Document document = mConn.get();
//            document = Jsoup.parse(new URL(megaUrl).openStream(),"euc-kr",megaUrl);
//            Document document = Jsoup.parse(new URL(megaUrl).openStream(),"euc-kr",megaUrl);

            Elements megaeventsLink = document.getElementsByClass("event_list").select(" h4 > a");
            Elements megaeventsdate = document.getElementsByClass("date").select("span:nth-child(3)");

            for(Element element: megaeventsLink){
                System.out.println(element.text() +","+ element.attr("abs:href"));

//                System.out.println(element.select("a"));

//                title = element.text() + element.attr("href");
            }
            for(Element element : megaeventsdate){
                System.out.println(element.text().substring(7));
            }


        }
        catch (IOException e) {
            System.out.println(e.toString());
        }

    }

    String etoosUrl = "https://go3.etoos.com/hietoos/event/default.asp?etoos=myall&ING_FLAG=I&etgrd=go3";
    String megaUrl = "https://www.megastudy.net/inside/event/event_list.asp?tab=0&order=1&smode=&sword=&page=1&intCP=NaN";
    Connection eConn = Jsoup.connect(etoosUrl);
    Connection  mConn = Jsoup.connect(megaUrl);


    public String megaLinkCrawl() {
//mega크롤링
        String title ="";
        try {


            Document document = mConn.get();
//            document = Jsoup.parse(new URL(megaUrl).openStream(),"euc-kr",megaUrl);
//            Document document = Jsoup.parse(new URL(megaUrl).openStream(),"euc-kr",megaUrl);

            Elements megaeventsLink = document.getElementsByClass("event_list").select(" h4 > a");

            for(Element element: megaeventsLink){
//                System.out.println(element.attr("abs:href"));
//                System.out.println(element.select("a"));

               title = element.text() + element.attr("href");
            }


        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
        return title;
    }
}
