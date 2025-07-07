package com.example.edu_system.repository;

import com.example.edu_system.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TeacherRepository {
    private final JdbcTemplate jdbcTemplate;

    //@RequiredArgsConstructor 로 대체
    //public TeacherRepository(JdbcTemplate jdbcTemplate){
    //    this.jdbcTemplate = jdbcTemplate;
    //}

    private final RowMapper<Teacher> mapper =
            (rs, rowNum) -> Teacher.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .build();

    public List<Teacher> findAll(){
        return jdbcTemplate.query("select * from teacher order by name", mapper);
    }

    public Teacher findById(int id){
        return  jdbcTemplate.queryForObject(
                "select * from teacher where id = ?",mapper,id);

    }
    public int save(Teacher teacher){
        return jdbcTemplate.update(
                "insert into teacher (name) values (?)", teacher.getName()
        );
    }
    public int update(Teacher teacher){
        return jdbcTemplate.update(
                "update teacher set name = ? where id = ? ",
                teacher.getName(), teacher.getId());
    }

    public int deleteById(int id){
        return  jdbcTemplate.update(
                "delete from teacher where id = ?",id);
    }
}
