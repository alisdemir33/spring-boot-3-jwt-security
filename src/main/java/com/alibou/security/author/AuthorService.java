package com.alibou.security.author;


import com.alibou.security.session.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository repository;

    private final SessionService sessionService;

    public void save(AuthorRequest request) {
        Author author = Author.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .age(request.getAge())
                .build();
        String currentUser = sessionService.getCurrentUser();
        author.setCreatedBy(currentUser);
        repository.save(author);
    }

    public void update(Integer id, AuthorRequest request) {
        Optional<Author> optionalAuthor = repository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            author.setFirstName(request.getFirstName());
            author.setLastName(request.getLastName());
            author.setEmail(request.getEmail());
            author.setAge(request.getAge());
            String currentUser = sessionService.getCurrentUser();
            author.setLastModifiedBy(currentUser);
            repository.save(author);
        } else {
            throw new RuntimeException("Author not found with id " + id);
        }
    }

    public List<Author> findAll() {
        return repository.findAll();
    }
}