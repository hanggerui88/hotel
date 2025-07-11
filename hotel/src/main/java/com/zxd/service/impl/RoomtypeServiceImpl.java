package com.zxd.service.impl;

import com.zxd.dao.RoomtypeDao;
import com.zxd.pojo.Roomtype;
import com.zxd.pojo.page;
import com.zxd.service.RoomtypeService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Roomtype)表服务实现类
 *
 * @author makejava
 * @since 2025-06-29 20:46:15
 */
@Service("roomtypeService")
public class RoomtypeServiceImpl implements RoomtypeService {
    @Resource
    private RoomtypeDao roomtypeDao;
    @Resource
private RoomtypeService roomtypeService;

    /**
     * 通过ID查询单条数据
     *
     * @param rmtypeId 主键
     * @return 实例对象
     */
    @Override
    public Roomtype queryById(Integer rmtypeId) {
        return this.roomtypeDao.queryById(rmtypeId);
    }

    @Override
    public List<Roomtype> queryAll(){
        return this.roomtypeDao.queryAll();
    }
    /**
     * 分页查询
     *
     * @param roomtype 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Roomtype> queryByPage(Roomtype roomtype, PageRequest pageRequest) {
        long total = this.roomtypeDao.count(roomtype);
        return new PageImpl<>(this.roomtypeDao.queryAllByLimit(roomtype, pageRequest), pageRequest, total);
    }
//111
    @Override
    public List<Roomtype> list(page p, Roomtype roomType) {
        return roomtypeDao.list(p, roomType);
    }

    /**
     * 新增数据
     *
     * @param roomtype 实例对象
     * @return 实例对象
     */
    @Override
    public Roomtype insert(Roomtype roomtype) {
        this.roomtypeDao.insert(roomtype);
        return roomtype;
    }

    @Override
    public List<Roomtype> queryAllByLimit(Roomtype roomtype, Pageable pageable) {
        return roomtypeDao.queryAllByLimit(roomtype, pageable);
    }

    /**
     * 修改数据
     *
     * @param roomtype 实例对象
     * @return 实例对象
     */
    @Override
    public Roomtype update(Roomtype roomtype) {
        this.roomtypeDao.update(roomtype);
        return this.queryById(roomtype.getRmtypeId());
    }

    /**
     * 通过主键删除数据
     *
     * @param rmtypeId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer rmtypeId) {
        return this.roomtypeDao.deleteById(rmtypeId) > 0;
    }
}
