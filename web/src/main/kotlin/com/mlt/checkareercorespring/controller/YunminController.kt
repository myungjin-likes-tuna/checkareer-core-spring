package com.mlt.checkareercorespring.controller

import com.mlt.checkareercorespring.domain.yunmin.service.YunminService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
class YunminController(
    private val yunminService: YunminService
) {
    // consumes: 요청으로는 데이터 타입을 명시함
    @GetMapping("/yunmin")
    fun returnYunmin(): String {
        yunminService.testYunmin()
        return "Hi, I'm Yunmin"
    }
}