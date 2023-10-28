package com.example.repository;

import com.example.entity.TextSave;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcTextRepository {
    private final DataSource dataSource;
    @Transactional
    public void save(TextSave textSave) throws SQLException {
        String sql = "INSERT INTO http_test.texttable (text_id, text) VALUES (?, ?)";
        Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, textSave.getTextId());
        pstmt.setString(2, textSave.getText());
        pstmt.executeUpdate();
    }
    @Transactional
    public Integer delete(String text_id) throws SQLException {
        String sql = "DELETE FROM http_test.texttable WHERE text_id = ?";
        Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, text_id);
        return pstmt.executeUpdate();
    }

    @Transactional
    public String findByTextId(String text_id) throws SQLException {
        String sql = "SELECT text FROM http_test.texttable WHERE text_id = ?";
        Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, text_id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getString("text");
        } else {
            return null;
        }
    }

    @Transactional
    public HashMap<String, String> textAll() throws SQLException {
        String sql = "SELECT * FROM http_test.texttable";
        Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();
        if(!rs.next()){
            return null;
        }

        HashMap<String, String> textAll = new HashMap<>();
        do{
            textAll.put(rs.getString("text_id"), rs.getString("text"));
        }while (rs.next());

        return textAll;
    }

}
