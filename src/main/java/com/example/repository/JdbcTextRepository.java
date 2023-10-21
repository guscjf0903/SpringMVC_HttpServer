package com.example.repository;

import com.example.domain.TextSave;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTextRepository implements TextRepository{
    @Autowired
    Connection connection;
    @Override
    @Transactional
    public TextSave save(TextSave textSave) throws SQLException {
        String sql = "INSERT INTO http_test.texttable (text_id, text) VALUES (?, ?)";

        try(PreparedStatement pstmt = connection.prepareStatement(sql);){
            pstmt.setString(1, textSave.getTextId());
            pstmt.setString(2, textSave.getText());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException();
        }

        return null;
    }

    @Override
    @Transactional
    public Optional<String> findById(String textId) throws SQLException {
        String sql = "SELECT text_id, text FROM http_test.texttable WHERE text_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, textId);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                String text_id = resultSet.getString("text_id");
                String text = resultSet.getString("text");
                return Optional.of("TextId : " + text_id + ", Text : " + text);
            }
        } catch (SQLException e) {
            throw new SQLException();
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public List<String> textAll() throws SQLException {
        String sql = "SELECT text_id, text FROM http_test.texttable";
        ArrayList<String> textList = new ArrayList<>();

        try(PreparedStatement pstmt = connection.prepareStatement(sql)){
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                String text_id = resultSet.getString("text_id");
                String text = resultSet.getString("text");
                textList.add("TextId : " + text_id + ", Text : " + text);
            }
        }catch (SQLException e){
            throw new SQLException();
        }

        return textList;
    }

    @Override
    @Transactional
    public Optional<String> deleteText(String textId) throws SQLException {
        String sql = "DELETE FROM http_test.texttable WHERE text_id = ?";
        int result;
        try(PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, textId);
            result = pstmt.executeUpdate();
        }catch (SQLException e){
            throw new SQLException();
        }

        return result == 0 ? Optional.empty() : Optional.of(textId);
    }
}
