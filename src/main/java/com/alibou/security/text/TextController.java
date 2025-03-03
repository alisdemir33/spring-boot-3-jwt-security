package com.alibou.security.text;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/texts")
@RequiredArgsConstructor
public class TextController {

    private final TextService textService;

    @PostMapping
    public ResponseEntity<Text> createText(@RequestBody TextRequest textRequest) {
        Text createdText = textService.createText(textRequest);
        return ResponseEntity.ok(createdText);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Text> getTextById(@PathVariable Integer id) {
        Text text = textService.getTextById(id);
        return ResponseEntity.ok(text);
    }

    @GetMapping
    public ResponseEntity<List<Text>> getAllTexts() {
        List<Text> texts = textService.getAllTexts();
        return ResponseEntity.ok(texts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Text> updateText(@PathVariable Integer id, @RequestBody TextRequest textRequest) {
        Text updatedText = textService.updateText(id, textRequest);
        return ResponseEntity.ok(updatedText);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteText(@PathVariable Integer id) {
        textService.deleteText(id);
        return ResponseEntity.noContent().build();
    }
}