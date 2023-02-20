package spring.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spring.library.models.Book;

import java.util.List;

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
        jdbcTemplate.update("update book set person_id=?, author=?, book_year=?, name=? where id=?",
                updatedBook.getPersonId(),
                updatedBook.getAuthor(),
                updatedBook.getBookYear(),
                updatedBook.getName(),
                id);
    }

    public void delete(int id){
        jdbcTemplate.update("delete from book where id=?", id);
    }
}
