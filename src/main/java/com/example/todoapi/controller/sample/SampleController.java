package com.example.todoapi.controller.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

//↓合成アノテーションといい、複数のアノテーションをまとめて付与できる
//この中に@ResponseBodyが含まれており、このクラス内の返り値はresponse bodyに変換される
@RestController
//↓GetMappingやPostMappingなどのアノテーションを使うときに、共通パスを指定できる
@RequestMapping("/samples")
public class SampleController {

    @GetMapping
    public SampleDto index() {
        //spring bootプロジェクト内のjacksonというライブラリがJavaオブジェクトを自動的にJSONに変換してくれる
        return new SampleDto("OK", LocalDateTime.now());
    }
}
