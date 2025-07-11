package com.zxd.dao;


import com.zxd.pojo.Reservation;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Reservation)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-29 20:46:11
 */
public interface ReservationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param reId 主键
     * @return 实例对象
     */
    Reservation queryById(Integer reId);

    /**
     * 查询指定行数据
     *
     * @param reservation 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */

    List<Reservation> queryAllByLimit(@Param("reservation") Reservation reservation, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param reservation 查询条件
     * @return 总行数
     */
//    long count(Reservation reservation);
    long count(@Param("reservation") Reservation reservation);

    /**
     * 新增数据
     *
     * @param reservation 实例对象
     * @return 影响行数
     */
    int insert(@Param("reservation") Reservation reservation);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Reservation> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Reservation> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Reservation> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Reservation> entities);

    /**
     * 修改数据
     *
     * @param reservation 实例对象
     * @return 影响行数
     */
    int update(Reservation reservation);

    /**
     * 通过主键删除数据
     *
     * @param reId 主键
     * @return 影响行数
     */
    int deleteById(Integer reId);

}

