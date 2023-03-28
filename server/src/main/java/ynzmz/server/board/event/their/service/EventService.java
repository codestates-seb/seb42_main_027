package ynzmz.server.board.event.their.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ynzmz.server.global.error.exception.BusinessLogicException;
import ynzmz.server.global.error.exception.ExceptionCode;
import ynzmz.server.board.event.their.entity.Event;
import ynzmz.server.board.event.their.repository.EventRepository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventService {

    //Create와 Delete 만 구현 --> 더 필요할까? --> 야놀지말자 고유 이벤트도 포함시켜야 함
    private final EventRepository eventRepository;

    //--------------------------------------------CREATE--------------------------------------------------------
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> CreateEvents(List<Event> e) {
        return eventRepository.saveAll(e);
    }


    //--------------------------------------------READ----------------------------------------------------------
    public Event findEvent(long eventId) {
        Optional<Event> event = eventRepository.findById(eventId);

        return event.orElseThrow(() -> new BusinessLogicException(ExceptionCode.EVENT_NOT_FOUND));
    }


    public Page<Event> findAllTEvents(int page, int size) {

        return eventRepository.findAll(PageRequest.of(page, size, Sort.by("theirId")));
    }

    public List<Event> findAllMegaEvents(){
        return eventRepository.findEventsBySource("Mega");
    }
    public Page<Event> pageFindAllMegaEvents(int page){
        return eventRepository.findEventsBySourcePage("Mega" , PageRequest.of(page-1, 15,Sort.by("theirId")));
    }
    public List<Event> findAllDaeSungEvents(){
        return eventRepository.findEventsBySource("DaeSung");
    }
    public List<Event> findAllEtoosEvents(){
        return eventRepository.findEventsBySource("Etoos");
    }
    public Page<Event> pageFindAllEtoosEvents(int page){
        return eventRepository.findEventsBySourcePage("Etoos", PageRequest.of(page-1,15,Sort.by("theirId")));
    }

    //페이지 필요? 몰루?
//-------------------------------------------UPDATE---------------------------------------------------------

    public Event updateEvent(Event event){
        Event findEvent = findEvent(event.getTheirId());


        Optional.ofNullable(event.getTitle()).ifPresent(findEvent::setTitle);
        Optional.ofNullable(event.getDate()).ifPresent(findEvent::setDate);
        Optional.ofNullable(event.getHyperLink()).ifPresent(findEvent::setHyperLink);
        Optional.ofNullable(event.getSource()).ifPresent(findEvent::setSource);

        return eventRepository.save(findEvent);

    }


    //-------------------------------------------DELETE---------------------------------------------------------
    public void deleteAll() {
        eventRepository.deleteAll();
    }

    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }

    //----------------------------------------------------포스트컨스트럭트 데이터크롤링------------------------------------------------------------

//    @PostConstruct
//    public void megaCrawler() {
//        String megaUrl1 = "https://www.megastudy.net/inside/event/event_list.asp?tab=0&order=1&smode=&sword=&page=1&intCP=NaN";
//        String megaUrl2 = "https://www.megastudy.net/inside/event/event_list.asp?tab=0&order=1&smode=&sword=&page=2&intCP=NaN";
//        String megaUrl3 = "https://www.megastudy.net/inside/event/event_list.asp?tab=0&order=1&smode=&sword=&page=3&intCP=NaN";
//        String megaUrl4 = "https://www.megastudy.net/inside/event/event_list.asp?tab=0&order=1&smode=&sword=&page=4&intCP=NaN";
//        String megaUrl5 = "https://www.megastudy.net/inside/event/event_list.asp?tab=0&order=1&smode=&sword=&page=5&intCP=NaN";
//
//        List<String> megas = new ArrayList<>();
//        megas.add(megaUrl1);
//        megas.add(megaUrl2);
//        megas.add(megaUrl3);
//        megas.add(megaUrl4);
//        megas.add(megaUrl5);
//
////        Connection eConn = Jsoup.connect(etoosUrl);
//
//        try {
//
//            for (String s : megas) {
//                Connection mConn = Jsoup.connect(s);
//                Document document = mConn.get();
//
//                Elements megaeventsLink = document.getElementsByClass("event_list").select(" h4 > a");
//                Elements dateList = document.select( "div.date > span > strong");
//                Elements dateList2 = new Elements();
//                List<String> dateListString = new ArrayList<>();
//
//                for(Element e:dateList){
//                    if(e.text().equals("이벤트 기간")){
//                        dateList2.add(e);}
//                    else if(e.text().equals("선착순")){
//                        dateList2.add(e);
//                    }
//                }
//                Elements dateList3 = new Elements();
//
//
//                for(Element e:dateList2) {
//                    if(e.text().equals("이벤트 기간")){
//                        dateList3.add(e.parent());}
//                    else if(e.text().equals("선착순")){
//                        dateList3.add(e);
//                    }
////                    System.out.println(e.parent().text().substring(6));
//                }
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
//                        createEvent(events);
//
//                    //주소 기입
////
////                    System.out.println(megaeventsLink.get(i).text() + " , "
////                            + megaeventsLink.get(i).attr("href")
////                            + "," + events.getDate());
//
//                }
//            }
//
//        } catch (
//                IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @PostConstruct
//    public void etoosCrawler() {
//            String etoosUrl = "https://go3.etoos.com/hietoos/event/default.asp?etoos=myall&ING_FLAG=I&etgrd=go3";
//
//
//            try {
//                Connection eConn = Jsoup.connect(etoosUrl);
//                Document document = eConn.get();
//                Elements titles = document.getElementsByClass("tbl tbl_ev_list").select(" strong > a");
//                Elements date = document.getElementsByClass("tbl tbl_ev_list").select(" dl:nth-child(3) > dd");
//
//                for (int i = 0; i < titles.size(); i++) {
//                    Event event = new Event();
//                    event.setSource("Etoos");
//                    event.setDate(" " + date.get(i).text());
//                    event.setHyperLink(titles.get(i).attr("href"));
//                    event.setTitle(titles.get(i).text());
//
////                     System.out.println("제목: " + event.getTitle());
////                     System.out.println("링크: " + event.getHyperLink());
////                     System.out.println("날짜: " + event.getDate());
//
//                    createEvent(event);
//                }
//
//            } catch (
//                    IOException e) {
//                e.printStackTrace();
//            }
//    }
//
//
//    @PostConstruct
//    public void daeSungCrawler(){
//        String daesungUrl1 = "https://www.mimacstudy.com/event/eventIngList.ds?evtType=&currPage=1&searchType=evtName&searchText=";
//        String daesungUrl2 = "https://www.mimacstudy.com/event/eventIngList.ds?evtType=&currPage=2&searchType=evtName&searchText=";
//        List<String> daesungs = new ArrayList<>();
//        daesungs.add(daesungUrl1);
//        daesungs.add(daesungUrl2);
//
//        try {
//
//            for (String s : daesungs) {
//                Connection dConn = Jsoup.connect(s);
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
//                    System.out.println("제목: " + event.getTitle());
//                    System.out.println("링크: " + event.getHyperLink());
//                    System.out.println("날짜: " + event.getDate());
//                    createEvent(event);
//                }
//            }
//        } catch (
//                IOException e) {
//            e.printStackTrace();
//        }
//    }



@Scheduled(cron ="0 0 12 1/1 * ?")
public void etoosEventScheduler() {

    String etoosUrl = "https://go3.etoos.com/hietoos/event/default.asp?etoos=myall&ING_FLAG=I&etgrd=go3";


    try {
        Connection eConn = Jsoup.connect(etoosUrl);
        Document document = eConn.get();
        Elements titles = document.getElementsByClass("tbl tbl_ev_list").select(" strong > a");
        Elements date = document.getElementsByClass("tbl tbl_ev_list").select(" dl:nth-child(3) > dd");
        List<Event> etoosList = findAllEtoosEvents();
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
            for (int i = etoosList.size()-1; i > etoosList.size() - 11; i--) {
                if (e.getHyperLink().equals(etoosList.get(i).getHyperLink())) {
                    sim = true;
                    break;
                } else {
                    sim = false;
                }
            }
            if (sim == false) {
                createEvent(e);
            }
        }
    }
    catch (
            IOException e) {
        e.printStackTrace();
    }
}

    @Scheduled(cron ="0 0 12 1/1 * ?")
    public void daesungEventScheduler() {

        String daesungUrl1 = "https://www.mimacstudy.com/event/eventIngList.ds?evtType=&currPage=1&searchType=evtName&searchText=";
        List<Event> etoosList = findAllDaeSungEvents();
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
                for (int i = etoosList.size()-1; i > etoosList.size() - 11; i--) {
                    if (e.getHyperLink().equals( etoosList.get(i).getHyperLink())) {
                        sim = true;
                        break;
                    } else {
                        sim = false;
                    }
                }
                if (sim == false) {
                    createEvent(e);
                }
            }
        }
        catch (
                IOException e) {
            e.printStackTrace();
        }

    }


    @Scheduled(cron ="0 0 12 1/1 * ?")
    public void megaEventScheduler() {

        String megaUrl1 = "https://www.megastudy.net/inside/event/event_list.asp?tab=0&order=1&smode=&sword=&page=1&intCP=NaN";
        try {
            Connection mConn = Jsoup.connect(megaUrl1);
            Document document = mConn.get();

            Elements megaeventsLink = document.getElementsByClass("event_list").select(" h4 > a");
            Elements dateList = document.select( "div.date > span > strong");
            Elements dateList2 = new Elements();
            List<String> dateListString = new ArrayList<>();
            List<Event> onlyMegaEvents = findAllMegaEvents();
            List<Event> eventList = new ArrayList<>();

            for(Element e:dateList){
                if(e.text().equals("이벤트 기간")){
                    dateList2.add(e);}
                else if(e.text().equals("선착순")){
                    dateList2.add(e);
                }
            }
            Elements dateList3 = new Elements();

            for(Element e:dateList2) {
                if(e.text().equals("이벤트 기간")){
                    dateList3.add(e.parent());}
                else if(e.text().equals("선착순")){
                    dateList3.add(e);
                }



                for (int i = 0; i < megaeventsLink.size(); i++) {
                    Event events = new Event();
                    events.setSource("Mega");
                    events.setTitle(megaeventsLink.get(i).text());
                    events.setHyperLink(megaeventsLink.get(i).attr("href"));

                    if(dateList3.get(i).text().equals("선착순")){
                        events.setDate(dateList3.get(i).text());
                    }
                    else{
                        events.setDate(dateList3.get(i).text().substring(6));
                    }


                    //주소 기입
//
//                    System.out.println(megaeventsLink.get(i).text() + " , "
//                            + megaeventsLink.get(i).attr("href")
//                            + "," + events.getDate());
                    eventList.add(events);
                }

            }
            for (Event e : eventList) {
                boolean sim = false;
                for (int i = onlyMegaEvents.size()-1; i > onlyMegaEvents.size() - 11; i--) {
                    if (e.getHyperLink().equals(onlyMegaEvents.get(i).getHyperLink())) {
                        sim = true;
                        break;
                    } else {
                        sim = false;
                    }
                }
                if (sim == false) {
                    createEvent(e);
                }
            }
        }
        catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}

