package com.alibou.security.text;

import com.alibou.security.lecture.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TextService {

    private final TextRepository textRepository;

    public Text createText(TextRequest textRequest) {
        Text text = Text.builder()
                .name(textRequest.getName()) // Add this line
                .size(textRequest.getSize()) // Add this line
                .url(textRequest.getUrl()) // Add this line
                .content(textRequest.getContent())
                .lecture(Lecture.builder().id(textRequest.getLectureId()).build())
                .description(textRequest.getDescription())
                .build();
        return textRepository.save(text);
    }

    public Text getTextById(Integer id) {
        return textRepository.findById(id).orElseThrow(() -> new RuntimeException("Text not found"));
    }

    public List<Text> getAllTexts() {
        return textRepository.findAll();
    }

    public Text updateText(Integer id, TextRequest textRequest) {
        Text text = getTextById(id);
        text.setContent(textRequest.getContent());
        return textRepository.save(text);
    }

    public void deleteText(Integer id) {
        textRepository.deleteById(id);
    }
}