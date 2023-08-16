package com.example.todoapi.controller.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//↓合成アノテーションといい、複数のアノテーションをまとめて付与できる
@RestController
//↓GetMappingやPostMappingなどのアノテーションを使うときに、共通パスを指定できる
@RequestMapping("/samples")
public class SampleController {

    @GetMapping
    public String index() {
        return "Hello World";
    }
}
