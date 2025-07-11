package com.zxd.service;

import com.zxd.pojo.VwDailyroomrate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.sql.Date;

/**
 * (VwDailyroomrate)表服务接口
 *
 * @author makejava
 * @since 2025-06-29 20:46:17
 */
public interface VwDailyroomrateService {

    /**
     * 通过ID查询单条数据
     *
     * @param
     * @return 实例对象
     */
    VwDailyroomrate queryByDate( Date d );

    /**
     * 分页查询
     *
     * @param vwDailyroomrate 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<VwDailyroomrate> queryByPage(VwDailyroomrate vwDailyroomrate, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param vwDailyroomrate 实例对象
     * @return 实例对象
     */
    VwDailyroomrate insert(VwDailyroomrate vwDailyroomrate);

    /**
     * 修改数据
     *
     * @param vwDailyroomrate 实例对象
     * @return 实例对象
     */
    VwDailyroomrate update(VwDailyroomrate vwDailyroomrate);

    /**
     * 通过主键删除数据
     *
     * @param
     * @return 是否成功
     */
    boolean deleteByDate(Date d );

}
