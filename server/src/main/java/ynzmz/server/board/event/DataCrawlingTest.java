package ynzmz.server.board.event;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ynzmz.server.board.event.their.entity.Event;
import ynzmz.server.board.event.their.service.EventService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class DataCrawlingTest {

    private final EventService eventService;

    public DataCrawlingTest(EventService eventService) {
        this.eventService = eventService;
    }

    public static void main(String[] args) {

//        String etoosUrl = "https://go3.etoos.com/hietoos/event/default.asp?etoos=myall&ING_FLAG=I&etgrd=go3";
//
//
//        try {
//            Connection eConn = Jsoup.connect(etoosUrl);
//            Document document = eConn.get();
//            Elements titles = document.getElementsByClass("tbl tbl_ev_list").select(" strong > a");
//            Elements date = document.getElementsByClass("tbl tbl_ev_list").select(" dl:nth-child(3) > dd");
//
//            for(int i = 0; i < titles.size(); i++){
//                Event event = new Event();
//                event.setSource("Etoos");
//                event.setDate(date.get(i).text());
//                event.setHyperLink(titles.get(i).attr("href"));
//                event.setTitle(titles.get(i).text());
//                System.out.println("제목: " + event.getTitle());
//                System.out.println("링크: " + event.getHyperLink());
//                System.out.println("날짜: " + event.getDate());
//            }
//
//        } catch (
//                IOException e) {
//            e.printStackTrace();
//        }
//        {
//            String daesungUrl1 = "https://www.mimacstudy.com/event/eventIngList.ds?evtType=&currPage=1&searchType=evtName&searchText=";
//            String daesungUrl2 = "https://www.mimacstudy.com/event/eventIngList.ds?evtType=&currPage=2&searchType=evtName&searchText=";
//            List<String> daesungs = new ArrayList<>();
//            daesungs.add(daesungUrl1);
//            daesungs.add(daesungUrl2);
//
//            try {
//
//                for (String s : daesungs) {
//                    Connection dConn = Jsoup.connect(s);
//                    Document document = dConn.get();
//
//                    Elements megaeventsLink = document.getElementsByClass("event_list").select(" h4 > a");
//                    Elements megaeventsdate1 = document.getElementsByClass("date").select("span:nth-child(1)");
//                    Elements megaeventsdate3 = document.getElementsByClass("date").select("span:nth-child(3)");
//
//
//
//                    Elements titles = document.getElementsByClass("event_list").select(" div.info > p.event_summary");
//                    Elements date = document.getElementsByClass("event_list").select(" div.info > p.period");
//                    Elements href = document.getElementsByClass("event_list").select(" div.img > a");
//
//                    for (int i = 0; i < titles.size(); i++) {
//                        Event event = new Event();
//                        event.setSource("DaeSung");
//                        event.setDate(date.get(i).text());
//                        String[] href1 = href.get(i).attr("href").substring(19).split("'");
//                        event.setHyperLink(href1[0]);
//                        event.setTitle(titles.get(i).text());
//                        System.out.println("제목: " + event.getTitle());
//                System.out.println("링크: " + event.getHyperLink());
//                System.out.println("날짜: " + event.getDate());
//                    }
//                }
//            } catch (
//                    IOException e) {
//                e.printStackTrace();
//            }
    }}