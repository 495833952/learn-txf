package com.txf.dao;

import com.txf.entity.Area;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 2019/10/18.
 */
@Repository
public interface AreaMapper {

    /**
     * 查询全部
     * @return
     */
    List<Area> selectAreaAll();

}


