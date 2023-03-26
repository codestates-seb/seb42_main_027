package ynzmz.server.board.event.their.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ynzmz.server.board.event.their.entity.Event;
import ynzmz.server.board.event.their.service.EventService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class scheduler {
        EventService eventService;
    @Scheduled(cron ="0 0 12 1/1 * ? *")
    public void eventScheduler() {
        String megaUrl1 = "https://www.megastudy.net/inside/event/event_list.asp?tab=0&order=1&smode=&sword=&page=1&intCP=NaN";
        try {
            Connection mConn = Jsoup.connect(megaUrl1);
            Document document = mConn.get();

            Elements megaeventsLink = document.getElementsByClass("event_list").select(" h4 > a");
            Elements megaeventsdate1 = document.getElementsByClass("date").select("span:nth-child(1)");
            Elements megaeventsdate3 = document.getElementsByClass("date").select("span:nth-child(3)");
            List<Event> eventList = new ArrayList<>();
            for (int i = 0; i < megaeventsLink.size(); i++) {
                Event events = new Event();
                events.setSource("Mega");
                events.setTitle(megaeventsLink.get(i).text());
                events.setHyperLink(megaeventsLink.get(i).attr("href"));
                int k = 0;
                //주소 기입
                if (megaeventsdate1.get(i).text().substring(0, 1).equals("이")) {
                    events.setDate(megaeventsdate1.get(i).text().substring(6));
                } else if (megaeventsdate3.size() != 0) {//(megaeventsdate1.get(i).text().substring(0,1) != "이")

                    events.setDate(megaeventsdate3.get(k).text().substring(6));
                    k++;
                } else {//(megaeventsdate1.get(i).text().substring(0,1) != "이")

                    events.setDate(" 선착순");
                }
                        eventList.add(events);



            }
            for(Event e : eventList){
                for(int i = 0 ; i < 11; i++){

                }
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
