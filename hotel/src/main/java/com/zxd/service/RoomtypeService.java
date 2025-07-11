package com.zxd.service;

import com.zxd.pojo.Roomtype;


import com.zxd.pojo.page;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Roomtype)表服务接口
 *
 * @author makejava
 * @since 2025-06-29 20:46:15
 */
public interface RoomtypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param rmtypeId 主键
     * @return 实例对象
     */
    Roomtype queryById(Integer rmtypeId);
    List<Roomtype> queryAll();
    /**
     * 分页查询
     *
     * @param roomtype 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Roomtype> queryByPage(Roomtype roomtype, PageRequest pageRequest);

    List<Roomtype> list(page p, Roomtype roomType);
    /**
     * 新增数据
     *
     * @param roomtype 实例对象
     * @return 实例对象
     */
    Roomtype insert(Roomtype roomtype);

    List<Roomtype> queryAllByLimit(Roomtype roomtype, Pageable pageable);

    /**
     * 修改数据
     *
     * @param roomtype 实例对象
     * @return 实例对象
     */
    Roomtype update(Roomtype roomtype);

    /**
     * 通过主键删除数据
     *
     * @param rmtypeId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer rmtypeId);

}
