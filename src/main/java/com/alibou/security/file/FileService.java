package com.alibou.security.file;

import com.alibou.security.lecture.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    public File createFile(FileRequest fileRequest) {
        File file = File.builder()
                .name(fileRequest.getName())
                .type(fileRequest.getType())
                .url(fileRequest.getUrl())
                .size(fileRequest.getSize())
                .lecture(Lecture.builder().id(fileRequest.getLectureId()).build())
                .description(fileRequest.getDescription()) // Add this line
                .build();
        return fileRepository.save(file);

    }

    public File getFileById(Integer id) {
        Optional<File> file = fileRepository.findById(id);
        return file.orElseThrow(() -> new RuntimeException("File not found"));
    }

    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    public File updateFile(Integer id, FileRequest fileRequest) {
        File file = getFileById(id);
        file.setName(fileRequest.getName());
        file.setUrl(fileRequest.getUrl());
        file.setSize(fileRequest.getSize());
        file.setType(fileRequest.getType());
        file.setLecture(Lecture.builder().id(fileRequest.getLectureId()).build());
        file.setDescription(fileRequest.getDescription()); // Add this line
        return fileRepository.save(file);
    }

    public void deleteFile(Integer id) {
        fileRepository.deleteById(id);
    }
}