package com.zxd.dao;

import com.zxd.pojo.Roomtype;
import com.zxd.pojo.page;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Roomtype)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-29 20:46:15
 */
public interface RoomtypeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param rmtypeId 主键
     * @return 实例对象
     */
    Roomtype queryById(@Param("id")Integer rmtypeId);


    /**
     * 查询指定行数据
     *
  分页对象
     * @return 对象列表
     */
    List<Roomtype> queryAll();
    List<Roomtype> queryAllByLimit(@Param("rt")Roomtype roomtype, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param roomtype 查询条件
     * @return 总行数
     */
    long count(Roomtype roomtype);

    /**
     * 新增数据
     *
     * @param roomtype 实例对象
     * @return 影响行数
     */
    int insert(Roomtype roomtype);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Roomtype> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Roomtype> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Roomtype> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Roomtype> entities);

    /**
     * 修改数据
     *
     * @param roomtype 实例对象
     * @return 影响行数
     */
    int update(@Param("rt") Roomtype roomtype);

    /**
     * 通过主键删除数据
     *
     * @param rmtypeId 主键
     * @return 影响行数
     */
    int deleteById(Integer rmtypeId);
    List<Roomtype> list(@Param("p") page page, @Param("record") Roomtype record);

}

