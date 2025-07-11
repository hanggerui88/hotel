package com.zxd.service;


import com.zxd.pojo.Rmdailystatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Rmdailystatus)表服务接口
 *
 * @author makejava
 * @since 2025-06-29 20:46:12
 */
public interface RmdailystatusService {

    /**
     * 通过ID查询单条数据
     *
     * @param rmdailystatusId 主键
     * @return 实例对象
     */
    Rmdailystatus queryById(Integer rmdailystatusId);

    /**
     * 分页查询
     *
     * @param rmdailystatus 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Rmdailystatus> queryByPage(Rmdailystatus rmdailystatus, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param rmdailystatus 实例对象
     * @return 实例对象
     */
    Rmdailystatus insert(Rmdailystatus rmdailystatus);

    /**
     * 修改数据
     *
     * @param rmdailystatus 实例对象
     * @return 实例对象
     */
    Rmdailystatus update(Rmdailystatus rmdailystatus);

    /**
     * 通过主键删除数据
     *
     * @param rmdailystatusId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer rmdailystatusId);

}
