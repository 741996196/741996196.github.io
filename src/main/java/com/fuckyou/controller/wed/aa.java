package com.fuckyou.controller.wed;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 陈源熹 on 2017-06-27.
 */
@Controller
public class aa {


    @RequestMapping("/aa")
    public String d(String fkj){
   fkj=fkj+"";

        return "aa";
    }




}
