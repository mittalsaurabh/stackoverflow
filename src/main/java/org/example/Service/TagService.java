package org.example.Service;

import org.example.Entity.Tag;
import org.example.Repository.TagRepository;
import org.springframework.stereotype.Service;

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
}
