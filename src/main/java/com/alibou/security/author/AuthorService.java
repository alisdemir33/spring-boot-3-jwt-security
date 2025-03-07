package com.alibou.security.author;


import com.alibou.security.author.dto.AuthorDto;
import com.alibou.security.author.dto.AuthorRequest;
import com.alibou.security.author.dto.AuthorSearchFormDto;
import com.alibou.security.author.dto.BaseAuthorDto;
import com.alibou.security.author.util.AuthorMapper;
import com.alibou.security.author.util.AuthorRepository;
import com.alibou.security.author.util.AuthorSpecification;
import com.alibou.security.course.dto.CourseDto;
import com.alibou.security.session.SessionService;
import com.alibou.security.utils.QueryUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository repository;

    private final SessionService sessionService;

    public Optional<BaseAuthorDto> findById(Integer id) {
        Optional<Author> author = repository.findById(id);
       // return author.map(this::convertToDto);
        return author.map(AuthorMapper.INSTANCE::toBaseAuthorDto);

    }

    public BaseAuthorDto save(AuthorRequest request) {
        Author author = Author.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .age(request.getAge())
                .build();
        String currentUser = sessionService.getCurrentUser();
        author.setCreatedBy(currentUser);
        Author savedAuthor = repository.save(author);
        return convertToDto(savedAuthor);
    }

    public BaseAuthorDto update(Integer id, AuthorRequest request) {
        Optional<Author> optionalAuthor = repository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            author.setFirstName(request.getFirstName());
            author.setLastName(request.getLastName());
            author.setEmail(request.getEmail());
            author.setAge(request.getAge());
            String currentUser = sessionService.getCurrentUser();
            author.setLastModifiedBy(currentUser);
            Author updatedAuthor = repository.save(author);
            return convertToDto(updatedAuthor);
        } else {
            throw new EntityNotFoundException("Author not found");
        }
    }

    private BaseAuthorDto convertToDto(Author author) {
        List<CourseDto> courses = author.getCourses() != null ? author.getCourses().stream().map(course -> new CourseDto(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                null,
                null
        )).collect(Collectors.toList()) : Collections.emptyList();

        return AuthorMapper.INSTANCE.toBaseAuthorDto(author);
//        return new AuthorDto(
//                author.getId(),
//                author.getFirstName(),
//                author.getLastName(),
//                author.getEmail(),
//                author.getAge()
//                //courses
//        );
    }


    public List<AuthorDto> findAll() {

        return repository.findAll().stream().map(author -> {
            AuthorDto authorDto = new AuthorDto();
            authorDto.setId(author.getId());
            authorDto.setFirstName(author.getFirstName());
            authorDto.setLastName(author.getLastName());
            authorDto.setEmail(author.getEmail());
            authorDto.setAge(author.getAge());
            // Assuming you have a method to convert courses to CourseDto
            authorDto.setCourses(author.getCourses().stream().map(course -> {
                CourseDto courseDto = new CourseDto();
                courseDto.setId(course.getId());
                courseDto.setTitle(course.getTitle());
                courseDto.setDescription(course.getDescription());
                // Add other fields as necessary
                return courseDto;
            }).collect(Collectors.toList()));
            return authorDto;
        }).collect(Collectors.toList());
    }

    public ResponseEntity<?> delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    public List<BaseAuthorDto> findAuthorsByCriteria(AuthorSearchFormDto searchDto) {
        var pageable = QueryUtils.getPageable(searchDto, "id");

        Specification<Author> spec = Specification.where(AuthorSpecification.hasAgeEqualTo(searchDto.getAge()))
                .and(AuthorSpecification.hasFirstName(searchDto.getFirstName()))
                .and(AuthorSpecification.hasLastName(searchDto.getLastName()))
                .and(AuthorSpecification.hasEmail(searchDto.getEmail()));

        Page<Author> page = repository.findAll(spec, pageable);

        List<BaseAuthorDto> authorDtos = page.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return authorDtos;



    }

}