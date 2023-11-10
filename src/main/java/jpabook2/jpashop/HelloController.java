package jpabook2.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) { // Model : data를 실어서 view에 넘김
        model.addAttribute("data", "hello!"); // {data: hello}
        return "hello"; // return: viewName => resouces/templates/hello
    }
}
