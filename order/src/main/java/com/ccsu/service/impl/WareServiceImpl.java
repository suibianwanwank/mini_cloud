package com.ccsu.service.impl;

import com.ccsu.mapper.WareMapper;
import com.ccsu.pojo.Ware;
import com.ccsu.service.WareService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class WareServiceImpl implements WareService {

    @Resource
    public WareMapper wareMapper;
    @Override
    public Ware getWare(Integer id) {
        Ware ware = wareMapper.getWareById(id);

        return ware;
    }
}
