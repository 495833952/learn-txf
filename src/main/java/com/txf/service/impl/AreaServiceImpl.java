package com.txf.service.impl;

import com.github.pagehelper.PageHelper;
import com.txf.dao.AreaMapper;
import com.txf.entity.Area;
import com.txf.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Admin on 2019/10/18.
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<Area> selectAreaAll(Integer pageNum, Integer pageSize) {
        //这个要在你的查询之前加哦
        PageHelper.startPage ( pageNum , pageSize );
        //这里直接查询全部就行了，分页插件会替你做分页，也无需担心性能问题，会自动补上limit的
        List<Area> areaList = areaMapper.selectAreaAll();
        return areaList  ;
    }
}
