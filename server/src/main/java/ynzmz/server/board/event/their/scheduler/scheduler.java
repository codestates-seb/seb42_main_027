package ynzmz.server.board.event.their.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ynzmz.server.board.event.their.entity.Event;
import ynzmz.server.board.event.their.service.EventService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class scheduler {
    private final EventService eventService;
    @Scheduled(cron ="0 0 12 1/1 * ? *")
    public void etoosEventScheduler() {

            String etoosUrl = "https://go3.etoos.com/hietoos/event/default.asp?etoos=myall&ING_FLAG=I&etgrd=go3";


            try {
                Connection eConn = Jsoup.connect(etoosUrl);
                Document document = eConn.get();
                Elements titles = document.getElementsByClass("tbl tbl_ev_list").select(" strong > a");
                Elements date = document.getElementsByClass("tbl tbl_ev_list").select(" dl:nth-child(3) > dd");
                List<Event> etoosList = eventService.findAllEtoosEvents();
                List<Event> eventList = new ArrayList<>();
                for (int i = 0; i < titles.size(); i++) {
                    Event event = new Event();
                    event.setSource("Etoos");
                    event.setDate(" " + date.get(i).text());
                    event.setHyperLink(titles.get(i).attr("href"));
                    event.setTitle(titles.get(i).text());
//                    System.out.println("제목: " + event.getTitle());
//                    System.out.println("링크: " + event.getHyperLink());
//                    System.out.println("날짜: " + event.getDate());
                    eventList.add(event);
                }


                for (Event e : eventList) {
                    boolean sim = false;
                    for (int i = etoosList.size(); i > etoosList.size() - 10; i--) {
                        if (e.getHyperLink() == etoosList.get(i).getHyperLink()) {
                            sim = true;
                            break;
                        } else {
                            sim = false;
                        }
                    }
                    if (sim == false) {
                        eventService.createEvent(e);
                    }
                }
            }
            catch (
                    IOException e) {
                e.printStackTrace();
            }
}

    @Scheduled(cron ="0 0 12 1/1 * ? *")
    public void daesungEventScheduler() {

        String daesungUrl1 = "https://www.mimacstudy.com/event/eventIngList.ds?evtType=&currPage=1&searchType=evtName&searchText=";
        List<Event> etoosList = eventService.findAllDaeSungEvents();
        List<Event> eventList = new ArrayList<>();

        try {


                Connection dConn = Jsoup.connect(daesungUrl1);
                Document document = dConn.get();


                Elements titles = document.getElementsByClass("event_list").select(" div.info > p.event_summary");
                Elements date = document.getElementsByClass("event_list").select(" div.info > p.period");
                Elements href = document.getElementsByClass("event_list").select(" div.img > a");

                for (int i = 0; i < titles.size(); i++) {
                    Event event = new Event();
                    event.setSource("DaeSung");
                    event.setDate(" " +date.get(i).text());
                    String[] href1 = href.get(i).attr("href").substring(19).split("'");
                    event.setHyperLink(href1[0]);
                    event.setTitle(titles.get(i).text());
//                    System.out.println("제목: " + event.getTitle());
//                    System.out.println("링크: " + event.getHyperLink());
//                    System.out.println("날짜: " + event.getDate());
                    eventList.add(event);
                }

            for (Event e : eventList) {
                boolean sim = false;
                for (int i = etoosList.size(); i > etoosList.size() - 10; i--) {
                    if (e.getHyperLink() == etoosList.get(i).getHyperLink()) {
                        sim = true;
                        break;
                    } else {
                        sim = false;
                    }
                }
                if (sim == false) {
                    eventService.createEvent(e);
                }
            }
        }
        catch (
                IOException e) {
            e.printStackTrace();
        }

    }


    @Scheduled(cron ="0 0 12 1/1 * ? *")
    public void megaEventScheduler() {

        String megaUrl1 = "https://www.megastudy.net/inside/event/event_list.asp?tab=0&order=1&smode=&sword=&page=1&intCP=NaN";
        try {
            Connection mConn = Jsoup.connect(megaUrl1);
            Document document = mConn.get();

            Elements megaeventsLink = document.getElementsByClass("event_list").select(" h4 > a");
            Elements megaeventsdate1 = document.getElementsByClass("date").select("span:nth-child(1)");
            Elements megaeventsdate3 = document.getElementsByClass("date").select("span:nth-child(3)");
            List<Event> onlyMegaEvents = eventService.findAllMegaEvents();
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
            for (Event e : eventList) {
                boolean sim = false;
                for (int i = onlyMegaEvents.size(); i > onlyMegaEvents.size() - 10; i--) {
                    if (e.getHyperLink() == onlyMegaEvents.get(i).getHyperLink()) {
                        sim = true;
                        break;
                    } else {
                        sim = false;
                    }
                }
                if (sim == false) {
                    eventService.createEvent(e);
                }
            }
        }
        catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
