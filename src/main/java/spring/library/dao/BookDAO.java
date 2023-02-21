package spring.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spring.library.models.Book;
import spring.library.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id){
        return jdbcTemplate.query("select * from book where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book person){
        jdbcTemplate.update("insert into book ( author, book_year, name) values( ?, ?, ?)",  person.getAuthor(), person.getBookYear(), person.getName());
    }

    public void update(int id, Book updatedBook){
        jdbcTemplate.update("update book set author=?, book_year=?, name=? where id=?",
                updatedBook.getAuthor(),
                updatedBook.getBookYear(),
                updatedBook.getName(),
                id);
    }

    public void delete(int id){
        jdbcTemplate.update("delete from book where id=?", id);
    }

    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("select Person.* from Book join Person on Book.person_id = Person.id " +
                        "where Book.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }


    public void release(int id) {
        jdbcTemplate.update("update Book set person_id=null where id=?", id);
    }

    public void assign(int id, Person selectedPerson) {
        jdbcTemplate.update("update Book set person_id=? where id=?", selectedPerson.getId(), id);
    }
}
