package com.mike.demo.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//数据库访问对象类，作用是对数据库中的表进行增删改查操作
@Dao
public interface CommodityDao {
    //增
    @Insert
    public void insertCommodities(Commodity...commodities);

    //删
    @Delete
    public void deleteCommodities(Commodity...commodities);

    //删除全部
    @Query("DELETE FROM Commodity")
    public void deleteAllCommodities();

    //改
    @Update
    public void updateCommodities(Commodity...commodities);

    //查
    @Query("SELECT * FROM Commodity ORDER BY ID ASC")
    public List<Commodity> getAllCommodities();
}
