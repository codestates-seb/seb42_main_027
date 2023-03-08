package ynzmz.server.tag.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ynzmz.server.tag.Tag;
import ynzmz.server.tag.repository.TagRepository;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    public List<Tag.Type> findTagsByType(List<String> stringTags){
        List<Tag.Type> types = new ArrayList<>();
        for(String stringTag : stringTags){
            types.add(Tag.Type.valueOf(stringTag));
        }
        return types;
    }
}
