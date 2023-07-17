package com.fastcampus.boardprojectadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** root 페이지 컨트롤러 */
@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "forward:/management/articles";
    }

}
