package com.zxd.service.impl;

import com.zxd.dao.VwRoomstatusdetailsDao;
import com.zxd.pojo.VwRoomstatusdetails;
import com.zxd.service.VwRoomstatusdetailsService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.sql.Date;


/**
 * (VwRoomstatusdetails)表服务实现类
 *
 * @author makejava
 * @since 2025-06-29 20:46:18
 */
@Service("vwRoomstatusdetailsService")
public class VwRoomstatusdetailsServiceImpl implements VwRoomstatusdetailsService {
    @Resource
    private VwRoomstatusdetailsDao vwRoomstatusdetailsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param
     * @return 实例对象
     */
    @Override
    public VwRoomstatusdetails queryByDate(Date d ) {
        return this.vwRoomstatusdetailsDao.queryByDate(d);
    }

    /**
     * 分页查询
     *
     * @param vwRoomstatusdetails 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<VwRoomstatusdetails> queryByPage(VwRoomstatusdetails vwRoomstatusdetails, PageRequest pageRequest) {
        long total = this.vwRoomstatusdetailsDao.count(vwRoomstatusdetails);
        return new PageImpl<>(this.vwRoomstatusdetailsDao.queryAllByLimit(vwRoomstatusdetails, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param vwRoomstatusdetails 实例对象
     * @return 实例对象
     */
    @Override
    public VwRoomstatusdetails insert(VwRoomstatusdetails vwRoomstatusdetails) {
        this.vwRoomstatusdetailsDao.insert(vwRoomstatusdetails);
        return vwRoomstatusdetails;
    }

    /**
     * 修改数据
     *
     * @param vwRoomstatusdetails 实例对象
     * @return 实例对象
     */
    @Override
    public VwRoomstatusdetails update(VwRoomstatusdetails vwRoomstatusdetails) {
        this.vwRoomstatusdetailsDao.update(vwRoomstatusdetails);
        return this.queryByDate((Date) vwRoomstatusdetails.getDdate());
    }

    /**
     * 通过主键删除数据
     *
     * @param
     * @return 是否成功
     */
    @Override
    public boolean deleteByDate(Date d ) {
        return this.vwRoomstatusdetailsDao.deleteByDate(d) > 0;
    }
}
