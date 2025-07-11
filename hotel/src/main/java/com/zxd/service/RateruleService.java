package com.zxd.service;


import com.zxd.pojo.Raterule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Raterule)表服务接口
 *
 * @author makejava
 * @since 2025-06-29 21:09:03
 */
public interface RateruleService {

    /**
     * 通过ID查询单条数据
     *
     * @param rateruleId 主键
     * @return 实例对象
     */
    Raterule queryById(Integer rateruleId);

    /**
     * 分页查询
     *
     * @param raterule 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Raterule> queryByPage(Raterule raterule, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param raterule 实例对象
     * @return 实例对象
     */
    Raterule insert(Raterule raterule);

    /**
     * 修改数据
     *
     * @param raterule 实例对象
     * @return 实例对象
     */
    Raterule update(Raterule raterule);

    /**
     * 通过主键删除数据
     *
     * @param rateruleId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer rateruleId);

}
