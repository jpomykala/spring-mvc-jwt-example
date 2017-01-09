package me.jpomykala.repository.tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import me.jpomykala.model.tag.Tag;

/**
 * Created by Evelan-E6540 on 04/09/2015.
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findById(Long id);

    Tag findByName(String name);
}
