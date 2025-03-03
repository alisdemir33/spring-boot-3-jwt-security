package com.alibou.security.section;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sections")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    @PostMapping
    public ResponseEntity<Section> createSection(@RequestBody SectionRequest sectionRequest) {
       Section section = Section.builder()
    .name(sectionRequest.getName())
    .description(sectionRequest.getDescription())
    .sectionOrder(sectionRequest.getSectionOrder())
    .build();
        section = sectionService.addSectionToCourse(sectionRequest.getCourseId(), section);
       // Section createdSection = sectionService.createSection(section);
        return ResponseEntity.ok(section);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Section> getSectionById(@PathVariable Integer id) {
        Section section = sectionService.getSectionById(id);
        return ResponseEntity.ok(section);
    }

    @GetMapping
    public ResponseEntity<List<Section>> getAllSections() {
        List<Section> sections = sectionService.getAllSections();
        return ResponseEntity.ok(sections);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Section> updateSection(@PathVariable Integer id, @RequestBody SectionRequest sectionRequest) {
        Section updatedSection = sectionService.updateSection(id, sectionRequest);
        return ResponseEntity.ok(updatedSection);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable Integer id) {
        sectionService.deleteSection(id);
        return ResponseEntity.noContent().build();
    }
}