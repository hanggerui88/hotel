package com.zxd.service.impl;


import com.zxd.dao.RoomDao;
import com.zxd.pojo.Room;
import com.zxd.pojo.Roomtype;
import com.zxd.service.RoomService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Room)表服务实现类
 *
 * @author makejava
 * @since 2025-06-29 20:46:14
 */
@Service("roomService")
public class RoomServiceImpl implements RoomService {
    @Resource
    private RoomDao roomDao;

    /**
     * 通过ID查询单条数据
     *
     * @param rmNumber 主键
     * @return 实例对象
     */
    @Override
    public Room queryById(Integer rmNumber) {
        return this.roomDao.queryById(rmNumber);
    }



    /**
     * 分页查询
     *
     * @param room 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Room> queryByPage(Room room, PageRequest pageRequest) {
        long total = this.roomDao.count(room);
        return new PageImpl<>(this.roomDao.queryAllByLimit(room, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param room 实例对象
     * @return 实例对象
     */
    @Override
    public Room insert(Room room) {
        this.roomDao.insert(room);
        return room;
    }

    /**
     * 修改数据
     *
     * @param room 实例对象
     * @return 实例对象
     */
    @Override
    public Room update(Room room) {
        this.roomDao.update(room);
        return this.queryById(room.getRmNumber());
    }

    /**
     * 通过主键删除数据
     *
     * @param rmNumber 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer rmNumber) {
        return this.roomDao.deleteById(rmNumber) > 0;
    }
}
