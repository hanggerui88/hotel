package com.zxd.service.impl;


import com.zxd.dao.ReservationDao;
import com.zxd.pojo.Reservation;
import com.zxd.service.ReservationService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Reservation)表服务实现类
 *
 * @author makejava
 * @since 2025-06-29 20:46:12
 */
@Service("reservationService")
public class ReservationServiceImpl implements ReservationService {
    @Resource
    private ReservationDao reservationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param reId 主键
     * @return 实例对象
     */
    @Override
    public Reservation queryById(Integer reId) {
        return this.reservationDao.queryById(reId);
    }

    /**
     * 分页查询
     *
     * @param reservation 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */



    @Override
    public Page<Reservation> queryByPage(Reservation reservation, PageRequest pageRequest) {
        long total = this.reservationDao.count(reservation);
        return new PageImpl<>(this.reservationDao.queryAllByLimit(reservation, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param reservation 实例对象
     * @return 实例对象
     */
    @Override
    public Reservation insert(Reservation reservation) {
        this.reservationDao.insert(reservation);
        return reservation;
    }

    /**
     * 修改数据
     *
     * @param reservation 实例对象
     * @return 实例对象
     */
    @Override
    public Reservation update(Reservation reservation) {
        this.reservationDao.update(reservation);
//        return this.queryById(reservation.getReId());
        return new Reservation();
    }


    /**
     * 通过主键删除数据
     *
     * @param reId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer reId) {
        return this.reservationDao.deleteById(reId) > 0;
    }
}
