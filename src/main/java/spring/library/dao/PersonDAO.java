package spring.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spring.library.models.Person;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index(){
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id){
        return jdbcTemplate.query("select * from person where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("insert into person (fio, birth_year) values(?, ?)", person.getFio(), person.getBirthYear());
    }

    public void update(int id, Person updatedPerson){
        jdbcTemplate.update("update person set fio=?, birth_year=? where id=?",
                updatedPerson.getFio(),
                updatedPerson.getBirthYear(),
                id);
    }

    public void delete(int id){
        jdbcTemplate.update("delete from person where id=?", id);
    }
}
