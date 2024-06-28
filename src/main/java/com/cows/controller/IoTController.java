package com.cows.controller;

import com.cows.commons.api.JsonResult;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/v1")
@Schema(name="返回内容是一个html页面", description="返回内容是一个html页面")
@Tag(name = "返回内容是一个html页面")
@SessionAttributes("isGo") // 使用Spring的@SessionAttribute或者@SessionAttributes来在多个请求之间共享数据。
public class IoTController {

    @GetMapping("/page")
    public String getPage() {
        return "TestPage";
    }

    @GetMapping("/action")
    @ResponseBody
    public JsonResult<String> doAction(@RequestParam boolean isGo, Model model) {
        // 在这里处理isGo参数
        // 指定你想要存储在session中的属性名称
        model.addAttribute("isGo", isGo);
        return new JsonResult<>("actionResult");
    }

    @GetMapping("/getIsGo")
    @ResponseBody
    public JsonResult<Boolean> getIsGo(Model model) {
        Boolean isGo = (Boolean) model.getAttribute("isGo");
        return new JsonResult<>(isGo);
    }
}
