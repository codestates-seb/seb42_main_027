package ynzmz.server.board.event.their.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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

//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class scheduler {
//    private final EventService eventService;
//    @Scheduled(cron ="0 0 12 1/1 * ? *")
//    public void etoosEventScheduler() {
//
//            String etoosUrl = "https://go3.etoos.com/hietoos/event/default.asp?etoos=myall&ING_FLAG=I&etgrd=go3";
//
//
//            try {
//                Connection eConn = Jsoup.connect(etoosUrl);
//                Document document = eConn.get();
//                Elements titles = document.getElementsByClass("tbl tbl_ev_list").select(" strong > a");
//                Elements date = document.getElementsByClass("tbl tbl_ev_list").select(" dl:nth-child(3) > dd");
//                List<Event> etoosList = eventService.findAllEtoosEvents();
//                List<Event> eventList = new ArrayList<>();
//                for (int i = 0; i < titles.size(); i++) {
//                    Event event = new Event();
//                    event.setSource("Etoos");
//                    event.setDate(" " + date.get(i).text());
//                    event.setHyperLink(titles.get(i).attr("href"));
//                    event.setTitle(titles.get(i).text());
////                    System.out.println("제목: " + event.getTitle());
////                    System.out.println("링크: " + event.getHyperLink());
////                    System.out.println("날짜: " + event.getDate());
//                    eventList.add(event);
//                }
//
//
//                for (Event e : eventList) {
//                    boolean sim = false;
//                    for (int i = etoosList.size(); i > etoosList.size() - 10; i--) {
//                        if (e.getHyperLink() == etoosList.get(i).getHyperLink()) {
//                            sim = true;
//                            break;
//                        } else {
//                            sim = false;
//                        }
//                    }
//                    if (sim == false) {
//                        eventService.createEvent(e);
//                    }
//                }
//            }
//            catch (
//                    IOException e) {
//                e.printStackTrace();
//            }
//}
//
//    @Scheduled(cron ="0 0 12 1/1 * ? *")
//    public void daesungEventScheduler() {
//
//        String daesungUrl1 = "https://www.mimacstudy.com/event/eventIngList.ds?evtType=&currPage=1&searchType=evtName&searchText=";
//        List<Event> etoosList = eventService.findAllDaeSungEvents();
//        List<Event> eventList = new ArrayList<>();
//
//        try {
//
//
//                Connection dConn = Jsoup.connect(daesungUrl1);
//                Document document = dConn.get();
//
//
//                Elements titles = document.getElementsByClass("event_list").select(" div.info > p.event_summary");
//                Elements date = document.getElementsByClass("event_list").select(" div.info > p.period");
//                Elements href = document.getElementsByClass("event_list").select(" div.img > a");
//
//                for (int i = 0; i < titles.size(); i++) {
//                    Event event = new Event();
//                    event.setSource("DaeSung");
//                    event.setDate(" " +date.get(i).text());
//                    String[] href1 = href.get(i).attr("href").substring(19).split("'");
//                    event.setHyperLink(href1[0]);
//                    event.setTitle(titles.get(i).text());
////                    System.out.println("제목: " + event.getTitle());
////                    System.out.println("링크: " + event.getHyperLink());
////                    System.out.println("날짜: " + event.getDate());
//                    eventList.add(event);
//                }
//
//            for (Event e : eventList) {
//                boolean sim = false;
//                for (int i = etoosList.size(); i > etoosList.size() - 10; i--) {
//                    if (e.getHyperLink() == etoosList.get(i).getHyperLink()) {
//                        sim = true;
//                        break;
//                    } else {
//                        sim = false;
//                    }
//                }
//                if (sim == false) {
//                    eventService.createEvent(e);
//                }
//            }
//        }
//        catch (
//                IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//    @Scheduled(cron ="0 0 12 1/1 * ? *")
//    public void megaEventScheduler() {
//
//        String megaUrl1 = "https://www.megastudy.net/inside/event/event_list.asp?tab=0&order=1&smode=&sword=&page=1&intCP=NaN";
//        try {
//            Connection mConn = Jsoup.connect(megaUrl1);
//            Document document = mConn.get();
//
//            Elements megaeventsLink = document.getElementsByClass("event_list").select(" h4 > a");
//            Elements dateList = document.select( "div.date > span > strong");
//            Elements dateList2 = new Elements();
//            List<String> dateListString = new ArrayList<>();
//            List<Event> onlyMegaEvents = eventService.findAllMegaEvents();
//            List<Event> eventList = new ArrayList<>();
//
//            for(Element e:dateList){
//                if(e.text().equals("이벤트 기간")){
//                    dateList2.add(e);}
//                else if(e.text().equals("선착순")){
//                    dateList2.add(e);
//                }
//            }
//
//            Elements dateList3 = new Elements();
//
//            for(Element e:dateList2) {
//                if(e.text().equals("이벤트 기간")){
//                    dateList3.add(e.parent());}
//                else if(e.text().equals("선착순")){
//                    dateList3.add(e);
//                }
//
//
//
//                for (int i = 0; i < megaeventsLink.size(); i++) {
//                    Event events = new Event();
//                    events.setSource("Mega");
//                    events.setTitle(megaeventsLink.get(i).text());
//                    events.setHyperLink(megaeventsLink.get(i).attr("href"));
//
//                    if(dateList3.get(i).text().equals("선착순")){
//                        events.setDate(dateList3.get(i).text());
//                    }
//                    else{
//                        events.setDate(dateList3.get(i).text().substring(6));
//                    }
//
//
//                    //주소 기입
////
////                    System.out.println(megaeventsLink.get(i).text() + " , "
////                            + megaeventsLink.get(i).attr("href")
////                            + "," + events.getDate());
//                    eventList.add(events);
//                }
//
//            }
//            for (Event e : eventList) {
//                boolean sim = false;
//                for (int i = onlyMegaEvents.size(); i > onlyMegaEvents.size() - 10; i--) {
//                    if (e.getHyperLink() == onlyMegaEvents.get(i).getHyperLink()) {
//                        sim = true;
//                        break;
//                    } else {
//                        sim = false;
//                    }
//                }
//                if (sim == false) {
//                    eventService.createEvent(e);
//                }
//            }
//        }
//        catch (
//                IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
