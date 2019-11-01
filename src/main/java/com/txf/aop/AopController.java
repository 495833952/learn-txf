package com.txf.aop;

import com.txf.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Integer.parseInt;

/**
*CreatedbyAdminon2019/10/18.
*/
@RestController
@RequestMapping("aop")
public class AopController {

@Autowired
private AreaService areaService;

    private static final Logger logger = LoggerFactory.getLogger(AopController.class);

    @GetMapping("hello")
    public String test(){
        logger.info("欢迎");

        return "i love java";
    }
}
