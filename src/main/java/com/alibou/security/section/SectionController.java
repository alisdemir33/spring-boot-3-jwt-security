package com.alibou.security.section;

import com.alibou.security.common.ResultDto;
import com.alibou.security.section.dto.BaseSectionDto;
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
        SectionDto sectionDto = sectionService.getSectionById(id);
        return ResponseEntity.ok(sectionDto);
    }

    @GetMapping
    public ResponseEntity<ResultDto<BaseSectionDto>> getAllSections() {
        List<BaseSectionDto> list = sectionService.getAllSections();
        return ResponseEntity.ok(new ResultDto<>((long) list.size(), list));
    }

    @GetMapping("/search")
    public ResponseEntity<ResultDto<BaseSectionDto>> searchSections(SectionSearchFormDto searchForm) {
        List<BaseSectionDto> lst = sectionService.searchSections(searchForm);
        return ResponseEntity.ok(new ResultDto<>((long) lst.size(), lst));
    }

    @PostMapping
    public ResponseEntity<SectionDto> createSection(@RequestBody SectionRequest sectionRequest) {
        SectionDto sectionDto = sectionService.createSection(sectionRequest);

        return ResponseEntity.ok(sectionDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseSectionDto> updateSection(@PathVariable Integer id, @RequestBody SectionRequest sectionRequest) {
        BaseSectionDto updatedSection = sectionService.updateSection(id, sectionRequest);
        return ResponseEntity.ok(updatedSection);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable Integer id) {
        sectionService.deleteSection(id);
        return ResponseEntity.noContent().build();
    }
}