package com.zxd.service;


import com.zxd.pojo.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * (Reservation)表服务接口
 *
 * @author makejava
 * @since 2025-06-29 20:46:12
 */
public interface ReservationService {

    /**
     * 通过ID查询单条数据
     *
     * @param reId 主键
     * @return 实例对象
     */

    Reservation queryById(Integer reId);

    /**
     * 分页查询
     *
     * @param reservation 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Reservation> queryByPage(Reservation reservation, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param reservation 实例对象
     * @return 实例对象
     */
    Reservation insert(Reservation reservation);

    /**
     * 修改数据
     *
     * @param reservation 实例对象
     * @return 实例对象
     */
    Reservation update(Reservation reservation);

    /**
     * 通过主键删除数据
     *
     * @param reId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer reId);

}
