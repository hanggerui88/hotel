package com.zxd.dao;


import com.zxd.pojo.VwDailyroomrate;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Date;
import java.util.List;

/**
 * (VwDailyroomrate)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-29 20:46:17
 */
public interface VwDailyroomrateDao {

    /**
     * 通过ID查询单条数据
     *
     * @param
     * @return 实例对象
     */
    VwDailyroomrate queryByDate( @Param("d") Date d);

    /**
     * 查询指定行数据
     *
     * @param vwDailyroomrate 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<VwDailyroomrate> queryAllByLimit(@Param ("vwrate")VwDailyroomrate vwDailyroomrate, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param vwDailyroomrate 查询条件
     * @return 总行数
     */
    long count(VwDailyroomrate vwDailyroomrate);

    /**
     * 新增数据
     *
     * @param vwDailyroomrate 实例对象
     * @return 影响行数
     */
    int insert(VwDailyroomrate vwDailyroomrate);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<VwDailyroomrate> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<VwDailyroomrate> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<VwDailyroomrate> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<VwDailyroomrate> entities);

    /**
     * 修改数据
     *
     * @param vwDailyroomrate 实例对象
     * @return 影响行数
     */
    int update(VwDailyroomrate vwDailyroomrate);

    /**
     * 通过主键删除数据
     *
     * @param
     * @return 影响行数
     */
    int deleteByDate( @Param("d") Date d);
}

