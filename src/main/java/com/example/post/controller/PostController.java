package com.example.post.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @PostMapping("/notice")
    public NoticeResponseDto createNotice(@RequestBody NoticeRequestDto requestDto) {
        return noticeService.createNotice(requestDto);
    }

    @GetMapping("/notice")
    public List<NoticeResponseDto> getNotice() {
        return noticeService.getNotice();

    }

    @PutMapping("/notice/{id}")
    public Long updateNotice(@PathVariable Long id, @RequestBody NoticeRequestDto requestDto) {
        return noticeService.updateNotice(id, requestDto);
    }

    @DeleteMapping("/notice/{id}")
    public Long deleteNotice(@PathVariable Long id) {
        return noticeService.deleteNotice(id);
    }

}





}
