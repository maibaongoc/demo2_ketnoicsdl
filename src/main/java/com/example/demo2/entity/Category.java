package com.example.demo2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.*;

@Data
@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    @NotEmpty(message = "Name of category must not be empty")
//    @Size(max=50, min=1, message = "Name of category must be less than 50 characters")
    private String name;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Book> books;
}
