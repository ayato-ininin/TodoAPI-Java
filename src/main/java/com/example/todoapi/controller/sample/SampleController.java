package com.example.todoapi.controller.sample;

import com.example.todoapi.service.sample.SampleService;
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

    //finalをつけることで、初期化後に値を変更できないようにする
    //new SampleService()とすることで、SampleServiceクラスのインスタンスを生成しているので、DIしていない。
    //これは、内部でインスタンス生成しているので、SampleServiceに依存している。
    //依存性の注入も結果依存ではあるが、外部でインスタンスを生成しているのが違いであり、
    //この場合、SampleServiceか何かしらの型のものであれば、モックサービスでも入れやすくなり、そのサービス自体とは疎結合になる
    //ここでインスタンス生成するとそのサービス一つに依存するが、注入だとその型を持つものに依存するので特定のものに依存しない。
    //    private final SampleService service = new SampleService();
    private final SampleService service;
    public SampleController(SampleService service) {
        this.service = service;
    }

    @GetMapping
    public SampleDto index() {
        var entity = service.find();
        //spring bootプロジェクト内のjacksonというライブラリがJavaオブジェクトを自動的にJSONに変換してくれる
        return new SampleDto(entity.getContent(), LocalDateTime.now());
    }
}
