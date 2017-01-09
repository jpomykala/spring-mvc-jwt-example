package me.jpomykala.service.tag;

import org.springframework.data.domain.Pageable;
import me.jpomykala.model.tag.Tag;

import java.util.List;

/**
 * Created by evelan on 12/21/15.
 */
public interface TagService {

    Tag findByName(String name);
    Tag create(String name);
    List<Tag> findAll(Pageable pageable);

}