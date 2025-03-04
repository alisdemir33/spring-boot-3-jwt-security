package com.alibou.security.author;


import com.alibou.security.author.dto.AuthorDto;
import com.alibou.security.author.dto.AuthorRequest;
import com.alibou.security.author.dto.AuthorSearchFormDto;
import com.alibou.security.common.ResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/_authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService service;

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> findAuthorById(@PathVariable Integer id) {
        Optional<AuthorDto> author = service.findById(id);
        return author.map(ResponseEntity::ok).
                orElseGet(() -> ResponseEntity.notFound().build());
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}