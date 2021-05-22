package com.winlin.roomdb;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//database的访问对象
@androidx.room.Dao//对表的增删改查
public interface dao {
   //增
    @Insert
    void insert(entity...entities);
    //改
    @Update
    void update(entity...entities);
    //条件删除
    void delete(entity...entities);
    //删除所有
    @Query("delete from entity")
    void deleteAll();
    //查询所有
    @Query("select * from entity")
    List<entity> findAll();

}