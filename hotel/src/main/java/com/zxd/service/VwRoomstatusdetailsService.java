package com.zxd.service;


import com.zxd.pojo.VwRoomstatusdetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.sql.Date;

/**
 * (VwRoomstatusdetails)表服务接口
 *
 * @author makejava
 * @since 2025-06-29 20:46:18
 */
public interface VwRoomstatusdetailsService {

    /**
     * 通过ID查询单条数据
     *
     * @param
     * @return 实例对象
     */
    VwRoomstatusdetails queryByDate( Date d);

    /**
     * 分页查询
     *
     * @param vwRoomstatusdetails 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<VwRoomstatusdetails> queryByPage(VwRoomstatusdetails vwRoomstatusdetails, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param vwRoomstatusdetails 实例对象
     * @return 实例对象
     */
    VwRoomstatusdetails insert(VwRoomstatusdetails vwRoomstatusdetails);

    /**
     * 修改数据
     *
     * @param vwRoomstatusdetails 实例对象
     * @return 实例对象
     */
    VwRoomstatusdetails update(VwRoomstatusdetails vwRoomstatusdetails);

    /**
     * 通过主键删除数据
     *
     * @param
     * @return 是否成功
     */
    boolean deleteByDate(Date d );

}
