package ynzmz.server.board.event.their.seedcrawler;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ynzmz.server.board.event.their.entity.Event;
import ynzmz.server.board.event.their.service.EventService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class Megaseed {
    private final EventService eventService;

    @PostConstruct
    public void megaCrawler() {
        String megaUrl1 = "https://www.megastudy.net/inside/event/event_list.asp?tab=0&order=1&smode=&sword=&page=1&intCP=NaN";
        String megaUrl2 = "https://www.megastudy.net/inside/event/event_list.asp?tab=0&order=1&smode=&sword=&page=2&intCP=NaN";
        String megaUrl3 = "https://www.megastudy.net/inside/event/event_list.asp?tab=0&order=1&smode=&sword=&page=3&intCP=NaN";
        String megaUrl4 = "https://www.megastudy.net/inside/event/event_list.asp?tab=0&order=1&smode=&sword=&page=4&intCP=NaN";
        String megaUrl5 = "https://www.megastudy.net/inside/event/event_list.asp?tab=0&order=1&smode=&sword=&page=5&intCP=NaN";

        List<String> megas = new ArrayList<>();
        megas.add(megaUrl1);
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
                Elements dateList = document.select("div.date > span > strong");
                Elements dateList2 = new Elements();


                for (Element e : dateList) {
                    if (e.text().equals("이벤트 기간")) {
                        dateList2.add(e);
                    } else if (e.text().equals("선착순")) {
                        dateList2.add(e);
                    }
                }
                Elements dateList3 = new Elements();


                for (Element e : dateList2) {
                    if (e.text().equals("이벤트 기간")) {
                        dateList3.add(e.parent());
                    } else if (e.text().equals("선착순")) {
                        dateList3.add(e);
                    }
//                    System.out.println(e.parent().text().substring(6));
                }


                for (int i = 0; i < megaeventsLink.size(); i++) {
                    Event events = new Event();
                    events.setSource("Mega");
                    events.setTitle(megaeventsLink.get(i).text());
                    events.setHyperLink(megaeventsLink.get(i).attr("href"));

                    if (dateList3.get(i).text().equals("선착순")) {
                        events.setDate(dateList3.get(i).text());
                    } else {
                        events.setDate(dateList3.get(i).text().substring(6));
                    }
                    eventService.createEvent(events);

                    //주소 기입
//
//                    System.out.println(megaeventsLink.get(i).text() + " , "
//                            + megaeventsLink.get(i).attr("href")
//                            + "," + events.getDate());

                }
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void etoosCrawler() {
        String etoosUrl = "https://go3.etoos.com/hietoos/event/default.asp?etoos=myall&ING_FLAG=I&etgrd=go3";


        try {
            Connection eConn = Jsoup.connect(etoosUrl);
            Document document = eConn.get();
            Elements titles = document.getElementsByClass("tbl tbl_ev_list").select(" strong > a");
            Elements date = document.getElementsByClass("tbl tbl_ev_list").select(" dl:nth-child(3) > dd");

            for (int i = 0; i < titles.size(); i++) {
                Event event = new Event();
                event.setSource("Etoos");
                event.setDate(" " + date.get(i).text());
                event.setHyperLink(titles.get(i).attr("href"));
                event.setTitle(titles.get(i).text());

//                     System.out.println("제목: " + event.getTitle());
//                     System.out.println("링크: " + event.getHyperLink());
//                     System.out.println("날짜: " + event.getDate());

                eventService.createEvent(event);
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }


    @PostConstruct
    public void daeSungCrawler() {
        String daesungUrl1 = "https://www.mimacstudy.com/event/eventIngList.ds?evtType=&currPage=1&searchType=evtName&searchText=";
        String daesungUrl2 = "https://www.mimacstudy.com/event/eventIngList.ds?evtType=&currPage=2&searchType=evtName&searchText=";
        List<String> daesungs = new ArrayList<>();
        daesungs.add(daesungUrl1);
        daesungs.add(daesungUrl2);

        try {

            for (String s : daesungs) {
                Connection dConn = Jsoup.connect(s);
                Document document = dConn.get();


                Elements titles = document.getElementsByClass("event_list").select(" div.info > p.event_summary");
                Elements date = document.getElementsByClass("event_list").select(" div.info > p.period");
                Elements href = document.getElementsByClass("event_list").select(" div.img > a");

                for (int i = 0; i < titles.size(); i++) {
                    Event event = new Event();
                    event.setSource("DaeSung");
                    event.setDate(" " + date.get(i).text());
                    String[] href1 = href.get(i).attr("href").substring(19).split("'");
                    event.setHyperLink(href1[0]);
                    event.setTitle(titles.get(i).text());
//                    System.out.println("제목: " + event.getTitle());
//                    System.out.println("링크: " + event.getHyperLink());
//                    System.out.println("날짜: " + event.getDate());
                    eventService.createEvent(event);
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
