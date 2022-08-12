package com.insta.api.insta.persistence.repository.tag;

import com.insta.api.insta.persistence.model.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITagRepository extends JpaRepository<Tag,Long> {
    Optional<Tag> findByTag(String tag);
}
