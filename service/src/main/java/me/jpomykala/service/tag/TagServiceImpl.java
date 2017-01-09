package me.jpomykala.service.tag;

import com.google.common.base.CharMatcher;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import me.jpomykala.model.tag.Tag;
import me.jpomykala.repository.tag.TagRepository;

import java.util.List;

/**
 * Created by Evelan-E6540 on 07/09/2015.
 */
@Service
@Transactional
public class TagServiceImpl implements TagService {

    @NonNull
    private final TagRepository tagRepo;

    private static final CharMatcher ALNUM = CharMatcher.inRange('a', 'z')
            .or(CharMatcher.ASCII)
            .or(CharMatcher.inRange('A', 'Z'))
            .or(CharMatcher.inRange('0', '9'))
            .precomputed();

    @Autowired
    public TagServiceImpl(TagRepository tagRepo) {
        this.tagRepo = tagRepo;
    }

    public Tag findByName(String name) {
        return tagRepo.findByName(name);
    }


    private String cleanString(String textToClean) {
        textToClean = textToClean.trim();
        textToClean = textToClean.replace(" ", "-");
        textToClean = ALNUM.retainFrom(textToClean);
        return textToClean;
    }

    @Override
    public Tag create(String name) {
        name = cleanString(name);
        Tag tag = findByName(name);

        if (tag == null) {
            Tag newTag = new Tag(name);
            tagRepo.save(newTag);
            return newTag;
        }
        return tag;
    }

    @Override
    public List<Tag> findAll(Pageable pageable) {
        return tagRepo.findAll(pageable).getContent();
    }
}