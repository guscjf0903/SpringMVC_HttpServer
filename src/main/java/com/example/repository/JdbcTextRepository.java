package com.example.repository;

import com.example.entity.TextEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JdbcTextRepository extends JpaRepository<TextEntity, Integer> {
    @Modifying
    @Query("INSERT INTO texttable (text_id, text) VALUES (:text_id, :text)")
    void saveText(@Param("text_id") String textId, @Param("text") String text);

}
