package crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WorkbenchIndexController {

    //这里写的路径要和方法处理资源返回的路径一致
    @RequestMapping("/workbench/index.do")
    public String index(){
        //直接跳转到业务主页面
        return "workbench/index";

    }
}
