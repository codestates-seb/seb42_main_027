package ynzmz.server.board.event.their.seedcrawler;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.lang.Nullable;
import ynzmz.server.board.event.their.entity.Event;
import ynzmz.server.board.event.their.service.EventService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
public class Megaseed {
    private final EventService eventService;
    String etoosUrl = "https://go3.etoos.com/hietoos/event/default.asp?etoos=myall&ING_FLAG=I&etgrd=go3";
    public void seedCrawler(){

    Connection eConn = Jsoup.connect(etoosUrl);

    String megaUrl2 = "https://www.megastudy.net/inside/event/event_list.asp?tab=0&order=1&smode=&sword=&page=2&intCP=NaN";
    String megaUrl3 = "https://www.megastudy.net/inside/event/event_list.asp?tab=0&order=1&smode=&sword=&page=3&intCP=NaN";
    String megaUrl4 = "https://www.megastudy.net/inside/event/event_list.asp?tab=0&order=1&smode=&sword=&page=4&intCP=NaN";
    String megaUrl5 = "https://www.megastudy.net/inside/event/event_list.asp?tab=0&order=1&smode=&sword=&page=5&intCP=NaN";

    List<String> megas = new ArrayList<>();

        megas.add(megaUrl2);
        megas.add(megaUrl3);
        megas.add(megaUrl4);
        megas.add(megaUrl5);

//        Connection eConn = Jsoup.connect(etoosUrl);

        try {

        for (String s : megas) {
            Connection mConn = Jsoup.connect(s);
            Document document = mConn.get();

            Elements megaeventsLink = document.getElementsByClass("event_list").select(" h4 > a");
            Elements megaeventsdate1 = document.getElementsByClass("date").select("span:nth-child(1)");
            Elements megaeventsdate3 = document.getElementsByClass("date").select("span:nth-child(3)");

            for (int i = 0; i < megaeventsLink.size(); i++) {
                Event events = new Event();
                events.setSource("Mega");
                events.setTitle(megaeventsLink.get(i).text());
                events.setHyperLink(megaeventsLink.get(i).attr("href"));
                int k = 0;
                //주소 기입
                if (megaeventsdate1.get(i).text().substring(0, 1).equals("이")) {
                    events.setDate(megaeventsdate1.get(i).text().substring(6));
                } else if(megaeventsdate3.size() != 0){//(megaeventsdate1.get(i).text().substring(0,1) != "이")

                    events.setDate(megaeventsdate3.get(k).text().substring(6));
                    k++;
                }


                System.out.println(events.getTitle() + " , "
                        + events.getHyperLink()
                        + "," + events.getDate());


            }
        }

    } catch (
    IOException e) {
        e.printStackTrace();
    }
}
}
