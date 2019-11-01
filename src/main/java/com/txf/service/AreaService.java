package com.txf.service;

import com.txf.entity.Area;

import java.util.List;

/**
 * Created by Admin on 2019/10/18.
 */
public interface AreaService {



    List<
                        Area
                        >
    selectAreaAll
            (
                    Integer
                            pageNum
                    ,
                    Integer
                            pageSize
            );
}
