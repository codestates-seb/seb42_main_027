package ynzmz.server.event.inner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynzmz.server.event.inner.mapper.YjEventMapper;
import ynzmz.server.event.inner.repository.YjEventRepository;
import ynzmz.server.event.inner.service.YjEventService;

@RestController
@RequestMapping("/inner-event")
@RequiredArgsConstructor
public class YjEventController {

    YjEventService service;
    YjEventRepository repository;
    YjEventMapper mapper;


}
