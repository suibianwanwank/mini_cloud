package com.ccsu.mapper;



import com.ccsu.pojo.Ware;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WareMapper {
    public Ware getWareById(Integer id);
}
