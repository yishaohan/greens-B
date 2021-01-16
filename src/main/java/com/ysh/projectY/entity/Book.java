package com.ysh.projectY.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, length = 32, nullable = false)
    private Integer id;

    @NotNull
    @Column(columnDefinition = "varchar(32) comment '书名'")
    private String name;

    @NotNull
    @Column(name = "author", nullable = false)
    private String author;

    @Column(columnDefinition = "varchar(255) comment '描述'")
    private String description;

    @NotNull
    @ColumnDefault("1")
    @Column(columnDefinition = "tinyint(1) comment '是否存在'")
    private Boolean status;

    @ColumnDefault("99")
    @Column(name = "price", nullable = false)
    private Float price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", price=" + price +
                '}';
    }
}
