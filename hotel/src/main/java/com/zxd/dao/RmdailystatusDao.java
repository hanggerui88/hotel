package com.zxd.dao;

import com.zxd.pojo.Rmdailystatus;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Rmdailystatus)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-29 20:46:12
 */
public interface RmdailystatusDao {

    /**
     * 通过ID查询单条数据
     *
     * @param rmdailystatusId 主键
     * @return 实例对象
     */
    Rmdailystatus queryById(Integer rmdailystatusId);

    /**
     * 查询指定行数据
     *
     * @param rmdailystatus 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Rmdailystatus> queryAllByLimit(Rmdailystatus rmdailystatus, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param rmdailystatus 查询条件
     * @return 总行数
     */
    long count(Rmdailystatus rmdailystatus);

    /**
     * 新增数据
     *
     * @param rmdailystatus 实例对象
     * @return 影响行数
     */
    int insert(Rmdailystatus rmdailystatus);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Rmdailystatus> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Rmdailystatus> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Rmdailystatus> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Rmdailystatus> entities);

    /**
     * 修改数据
     *
     * @param rmdailystatus 实例对象
     * @return 影响行数
     */
    int update(Rmdailystatus rmdailystatus);

    /**
     * 通过主键删除数据
     *
     * @param rmdailystatusId 主键
     * @return 影响行数
     */
    int deleteById(Integer rmdailystatusId);

}

