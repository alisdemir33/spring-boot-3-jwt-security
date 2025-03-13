package com.alibou.security.file;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping
    public ResponseEntity<File> createFile(@RequestBody FileRequest fileRequest) {
        File createdFile = fileService.createFile(fileRequest);
        return ResponseEntity.ok(createdFile);
    }

    @GetMapping("/{id}")
    public ResponseEntity<File> getFileById(@PathVariable Integer id) {
        File file = fileService.getFileById(id);
        return ResponseEntity.ok(file);
    }

    @GetMapping
    public ResponseEntity<List<File>> getAllFiles() {
        List<File> files = fileService.getAllFiles();
        return ResponseEntity.ok(files);
    }

    @PutMapping("/{id}")
    public ResponseEntity<File> updateFile(@PathVariable Integer id, @RequestBody FileRequest fileRequest) {
        File updatedFile = fileService.updateFile(id, fileRequest);
        return ResponseEntity.ok(updatedFile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable Integer id) {
        fileService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }
}