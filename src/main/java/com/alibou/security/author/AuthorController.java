package com.alibou.security.author;


import com.alibou.security.author.dto.AuthorDto;
import com.alibou.security.author.dto.AuthorRequest;
import com.alibou.security.author.dto.AuthorSearchFormDto;
import com.alibou.security.common.ResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/_authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService service;

    @PostMapping
    public ResponseEntity<AuthorDto> save(@RequestBody AuthorRequest request) {
        AuthorDto authorDto = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> update(@PathVariable Integer id, @RequestBody AuthorRequest request) {
        AuthorDto authorDto = service.update(id, request);
        return ResponseEntity.ok(authorDto);
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> findAllAuthors() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/search")
    public  ResponseEntity<ResultDto<AuthorDto>> searchAuthors(@RequestBody AuthorSearchFormDto searchDto) {


        List<AuthorDto>  lst  = service.findAuthorsByCriteria(searchDto);
        return ResponseEntity.ok(new ResultDto<>( (long)lst.size(), lst));


    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findAuthorById(@PathVariable Integer id) {
        Optional<Author> author = service.findById(id);
        return author.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}