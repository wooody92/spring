package dev.springboot.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/hello/{data}")
    public  EntityModel<Hello> hello(@PathVariable String data) {
        Hello hello = new Hello();
        hello.setPrefix("Hey,");
        hello.setName("Henry");

        // EntityModel = 우리가 제공할 리소스 + 링크 정보
        EntityModel<Hello> helloEntityModel = new EntityModel<>(hello);
        helloEntityModel.add(linkTo(methodOn(SampleController.class).hello(data)).withSelfRel());
        return helloEntityModel;
    }
}
