package com.codeup.codeupspringblog.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "posts")
public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(11) UNSIGNED", nullable = false)
    private long id;

    @Column(columnDefinition = "VARCHAR(128)", nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToMany(cascade = CascadeType.PERSIST)
//    @JoinTable(
//            name = "posts_categories",
//            joinColumns = {@JoinColumn(name = "post_id")},
//            inverseJoinColumns = {@JoinColumn(name = "category_id")}
//    )
//    private Set<Category> categories;

    public Post()
    {

    }

    public Post(long id, String title, String body)
    {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Post(String title, String body)
    {
        this.title = title;
        this.body = body;
    }

    public Post(String title, String body, User user)
    {
        this.title = title;
        this.body = body;
        this.user = user;
    }

//    public Post(String title, String body, User user, Set<Category> categorySet) {
//        this.title = title;
//        this.body = body;
//        this.user = user;
//        this.categories = categorySet;
//    }
//
//    public Post(long id, String title, String body, User user, Set<Category> categorySet) {
//        this.id = id;
//        this.title = title;
//        this.body = body;
//        this.user = user;
//        this.categories = categorySet;
//    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public Set<Category> getCategories() {
//        return categories;
//    }
//
//    public void setCategories(Set<Category> categories) {
//        this.categories = categories;
//    }

//    @Override
//    public String toString() {
//        return "Post{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", body='" + body + '\'' +
//                ", user=" + user +
//                ", categories=" + categories +
//                '}';
//    }
}
