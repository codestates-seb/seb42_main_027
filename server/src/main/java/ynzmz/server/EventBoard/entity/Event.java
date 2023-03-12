package ynzmz.server.EventBoard.entity;


import lombok.Getter;
import lombok.Setter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.swing.text.Document;
import java.io.IOException;



@Entity
@Getter
@Setter
public class Event {
@Id @AutoConfigureOrder
    long EventId;






}
