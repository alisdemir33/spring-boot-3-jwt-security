package com.alibou.security.section;

import com.alibou.security.common.ResultDto;
import com.alibou.security.section.dto.SectionDto;
import com.alibou.security.section.dto.SectionRequest;
import com.alibou.security.section.dto.SectionSearchFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sections")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    @GetMapping("/{id}")
    public ResponseEntity<SectionDto> getSectionById(@PathVariable Integer id) {
        SectionDto section = sectionService.getSectionById(id);
        return ResponseEntity.ok(section);
    }

    @GetMapping
    public ResponseEntity<List<Section>> getAllSections() {
        List<Section> sections = sectionService.getAllSections();
        return ResponseEntity.ok(sections);
    }

    @GetMapping("/search")
    public ResponseEntity<ResultDto<SectionDto>> searchSections(SectionSearchFormDto searchForm) {
        List<SectionDto> lst = sectionService.searchSections(searchForm);
        return ResponseEntity.ok(new ResultDto<>((long) lst.size(), lst));
    }


    @PostMapping
    public ResponseEntity<Section> createSection(@RequestBody SectionRequest sectionRequest) {
        Section section = sectionService.createSection(sectionRequest);
        return ResponseEntity.ok(section);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectionDto> updateSection(@PathVariable Integer id, @RequestBody SectionRequest sectionRequest) {
        SectionDto updatedSection = sectionService.updateSection(id, sectionRequest);
        return ResponseEntity.ok(updatedSection);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable Integer id) {
        sectionService.deleteSection(id);
        return ResponseEntity.noContent().build();
    }
}