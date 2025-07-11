package com.zxd.service;


import com.zxd.pojo.Rmtypedailyrate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Rmtypedailyrate)表服务接口
 *
 * @author makejava
 * @since 2025-06-29 20:46:14
 */
public interface RmtypedailyrateService {

    /**
     * 通过ID查询单条数据
     *
     * @param rmtypedailyrateId 主键
     * @return 实例对象
     */
    Rmtypedailyrate queryById(Integer rmtypedailyrateId);

    /**
     * 分页查询
     *
     * @param rmtypedailyrate 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Rmtypedailyrate> queryByPage(Rmtypedailyrate rmtypedailyrate, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param rmtypedailyrate 实例对象
     * @return 实例对象
     */
    Rmtypedailyrate insert(Rmtypedailyrate rmtypedailyrate);

    /**
     * 修改数据
     *
     * @param rmtypedailyrate 实例对象
     * @return 实例对象
     */
    Rmtypedailyrate update(Rmtypedailyrate rmtypedailyrate);

    /**
     * 通过主键删除数据
     *
     * @param rmtypedailyrateId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer rmtypedailyrateId);

}
