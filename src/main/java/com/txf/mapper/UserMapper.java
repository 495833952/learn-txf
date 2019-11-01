package com.txf.mapper;

import com.txf.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by Admin on 2019/11/1.
 */
@Mapper
@CacheConfig(cacheNames = "users")
public interface UserMapper {

    @Insert("insert into t_user(name,age) values(#{name},#{age})")
    int addUser(@Param("name")String name,@Param("age")String age);

    @Select("select * from t_user where id =#{id}")
    @Cacheable(key ="#p0")
    User findById(@Param("id") String id);

    @CachePut(key = "#p0")
    @Update("update t_user set name=#{name} where id=#{id}")
    void updataById(@Param("id")String id,@Param("name")String name);

    //如果指定为 true，则方法调用后将立即清空所有缓存
    @CacheEvict(key ="#p0",allEntries=false)
    @Delete("delete from t_user where id=#{id}")
    void deleteById(@Param("id")String id);

}