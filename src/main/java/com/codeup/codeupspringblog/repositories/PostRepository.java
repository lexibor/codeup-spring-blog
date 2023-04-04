package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.models.Category;
import com.codeup.codeupspringblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Long>
{
    Post findById(long id);
    Set<Category> categories(long post_id);
}
