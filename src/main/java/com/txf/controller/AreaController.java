package com.txf.controller;
import com.github.pagehelper.PageInfo;
import com.txf.common.PageResultBean;
import com.txf.entity.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.txf.service.AreaService;

import java.util.List;

/**
*CreatedbyAdminon2019/10/18.
*/
@RestController
@RequestMapping("area")
public class AreaController{

@Autowired
private AreaService areaService;




    /**
    *这里的@RequestParam(name="pagesize",required=false,defaultValue="10")
    *-required为是否为必须传输的参数
    */
    @RequestMapping( "query")
    public List<Area>areaList(@RequestParam(name="pagenum",required=false,defaultValue="1")Integer pageNum,
        @RequestParam(name="pagesize",required=false,defaultValue="10")Integer pageSize){
        return areaService.selectAreaAll(pageNum,pageSize);

    }

    @GetMapping("query1" )
    public PageResultBean <List<Area>> areaList1(@RequestParam(name= "pagenum",required =false ,defaultValue  ="1" ) Integer  pageNum,
            @RequestParam( name = "pagesize"  , required  =   false , defaultValue   = "10"  ) Integer pageSize  ){
            return new PageResultBean<List<Area>>(new PageInfo(areaService.selectAreaAll(pageNum, pageSize)));
    }
}
