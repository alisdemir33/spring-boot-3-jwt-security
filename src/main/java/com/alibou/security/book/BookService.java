package com.alibou.security.book;

import com.alibou.security.author.Author;
import com.alibou.security.author.AuthorRepository;
import com.alibou.security.section.SectionDto;
import com.alibou.security.session.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final AuthorRepository authorRepository;
    private final SessionService sessionService;

    public void save(BookRequest request) {
        Set<Author> authors = authorRepository.findAllById(request.getAuthorIds()).stream().collect(Collectors.toSet());
        var book = Book.builder()
                .id(request.getId())
                .title(request.getTitle())
                .description(request.getDescription())
                .isbn(request.getIsbn())
                .authors(authors)
                .build();
        String currentUser = sessionService.getCurrentUser();
        book.setCreatedBy(currentUser);
        repository.save(book);
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public BookDto findById(Integer id) {
       var book = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
       return new BookDto(
           book.getId(),
           book.getTitle(),
           book.getAuthors().stream().map(Author::getFirstName).collect(Collectors.joining(", ")),
           book.getIsbn()
       );
    }

    public void update(Integer id, BookRequest request) {
        Book book = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        Set<Author> authors = authorRepository.findAllById(request.getAuthorIds()).stream().collect(Collectors.toSet());
        book.setTitle(request.getTitle());
        book.setDescription(request.getDescription());
        book.setIsbn(request.getIsbn());
        book.setAuthors(authors);
        repository.save(book);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}