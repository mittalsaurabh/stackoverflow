package org.example.Service;

import org.example.Entity.Tag;
import org.example.Repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    public Tag addTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public List<Tag> getTags(List<String> tagNames) {
        List<Tag> tags = new ArrayList<>();
        for (String tagName : tagNames) {
            Tag tag = getTag(tagName);
            tags.add(tag);
        }
        return tags;
    }

    public Tag getTag(String tagName) {
        Tag tag = getTagByName(tagName);
        if (tag == null) {
            tag = new Tag(tagName);
            addTag(tag);
        }
        return tag;
    }

}
