package com.fuckyou.shiro;

import java.util.LinkedHashMap;

/**
 * Created by 陈源熹 on 2018-01-07.
 */
public class filterChainDefinitionMapBuider {

    public LinkedHashMap<String,String> buildFilterChainDefinitionMap(){
        LinkedHashMap<String,String> map=new LinkedHashMap<>();

        //也可以通过数据库读取数据来进行过滤，比写在配置文件里灵活。

        map.put("/login.jsp","anon");
         map.put("/login","anon");
          map.put("/logout","logout");
           map.put("/index.jsp","authc,roles[11]");
            map.put("/cc.jsp","authc,roles[12]");
            map.put("/list.jsp","user");
             map.put("/**","authc");
        return map;
    }
}
