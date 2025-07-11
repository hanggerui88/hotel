package com.zxd.service;

import com.zxd.pojo.Rmstatusrule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Rmstatusrule)表服务接口
 *
 * @author makejava
 * @since 2025-06-29 20:46:13
 */
public interface RmstatusruleService {

    /**
     * 通过ID查询单条数据
     *
     * @param rmstatusruleId 主键
     * @return 实例对象
     */
    Rmstatusrule queryById(Integer rmstatusruleId);

    /**
     * 分页查询
     *
     * @param rmstatusrule 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Rmstatusrule> queryByPage(Rmstatusrule rmstatusrule, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param rmstatusrule 实例对象
     * @return 实例对象
     */
    Rmstatusrule insert(Rmstatusrule rmstatusrule);

    /**
     * 修改数据
     *
     * @param rmstatusrule 实例对象
     * @return 实例对象
     */
    Rmstatusrule update(Rmstatusrule rmstatusrule);

    /**
     * 通过主键删除数据
     *
     * @param rmstatusruleId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer rmstatusruleId);

}
