package com.zxd.service;

import com.zxd.pojo.Room;
import com.zxd.pojo.Roomtype;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Room)表服务接口
 *
 * @author makejava
 * @since 2025-06-29 20:46:14
 */
public interface RoomService {

    /**
     * 通过ID查询单条数据
     *
     * @param rmNumber 主键
     * @return 实例对象
     */
    Room queryById(Integer rmNumber);



    /**
     * 分页查询
     *
     * @param room 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Room> queryByPage(Room room, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param room 实例对象
     * @return 实例对象
     */
    Room insert(Room room);

    /**
     * 修改数据
     *
     * @param room 实例对象
     * @return 实例对象
     */
    Room update(Room room);

    /**
     * 通过主键删除数据
     *
     * @param rmNumber 主键
     * @return 是否成功
     */
    boolean deleteById(Integer rmNumber);

}
