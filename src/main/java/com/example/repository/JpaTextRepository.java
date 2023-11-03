package com.example.repository;

import com.example.entity.TextEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTextRepository extends JpaRepository<TextEntity, Integer> {

    @Query(value = "select texttable.text from TextEntity texttable where texttable.textId = :textId")
    String findTextByTextId(@Param("textId") String textId);

}
