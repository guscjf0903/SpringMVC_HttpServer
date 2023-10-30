package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "texttable")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TextEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "index_column")
    private Integer indexColumn;
    @Column(name = "text_id")
    private String textId;
    @Column(name = "text")
    private String text;
}
