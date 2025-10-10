package com.example.touristguide3.repository;

import com.example.touristguide3.model.Tag;
import com.example.touristguide3.model.TouristAttraction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class AttractionRepository {
    private final JdbcTemplate jdbc;

    public AttractionRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<TouristAttraction> rowMapper = (rs, rowNum) ->
            new TouristAttraction(
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("city"),
                    getTagsForAttraction(rs.getInt("id"))
            );

    public List<TouristAttraction> getAttractions() {
        return jdbc.query("SELECT * FROM tourist_attraction", rowMapper);
    }

    public TouristAttraction getAttractionFromName(String name) {
        List<TouristAttraction> list = jdbc.query(
                "SELECT * FROM tourist_attraction WHERE name = ?", rowMapper, name);
        return list.isEmpty() ? null : list.getFirst();
    }

    public void saveAttraction(TouristAttraction attraction) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO tourist_attraction (name, description, city) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, attraction.getName());
            ps.setString(2, attraction.getDescription());
            ps.setString(3, attraction.getCity());
            return ps;
        }, keyHolder);

        Number id = keyHolder.getKey();
        if (id != null && attraction.getTags() != null) {
            for (Tag tag : attraction.getTags()) {
                Integer tagId = getTagId(tag.name());
                if (tagId != null) {
                    jdbc.update("INSERT INTO attraction_tag (attraction_id, tag_id) VALUES (?,?)",
                            id.intValue(), tagId);
                }
            }
        }
    }

    public void deleteAttraction(String name) {
        jdbc.update("DELETE FROM tourist_attraction WHERE name = ?", name);
    }

    public void updateAttraction(TouristAttraction attraction) {
        jdbc.update("UPDATE tourist_attraction SET description=?, city=? WHERE name=?",
                attraction.getDescription(), attraction.getCity(), attraction.getName());
    }

    public List<String> getCities() {
        return jdbc.query("SELECT DISTINCT city FROM tourist_attraction", (rs, i) -> rs.getString("city"));
    }

    public List<Tag> getTags() {
        return jdbc.query("SELECT name FROM tag", (rs, i) -> Tag.valueOf(rs.getString("name")));
    }

    private List<Tag> getTagsForAttraction(int attractionId) {
        return jdbc.query("SELECT t.name FROM tag t JOIN attraction_tag at ON t.id = at.tag_id WHERE at.attraction_id = ?",
                (rs, rowNum) -> Tag.valueOf(rs.getString("name")), attractionId);
    }

    private Integer getTagId(String tagName) {
        List<Integer> ids = jdbc.query("SELECT id FROM tag WHERE name = ?", (rs, i) -> rs.getInt("id"), tagName);
        return ids.isEmpty() ? null : ids.getFirst();
    }
}