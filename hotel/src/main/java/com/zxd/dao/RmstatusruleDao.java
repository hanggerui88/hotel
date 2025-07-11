package com.zxd.dao;


import com.zxd.pojo.Rmstatusrule;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Rmstatusrule)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-29 20:46:12
 */
public interface RmstatusruleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param rmstatusruleId 主键
     * @return 实例对象
     */
    Rmstatusrule queryById(Integer rmstatusruleId);

    /**
     * 查询指定行数据
     *
     * @param rmstatusrule 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Rmstatusrule> queryAllByLimit(@Param("rm_r")Rmstatusrule rmstatusrule, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param rmstatusrule 查询条件
     * @return 总行数
     */
    long count(Rmstatusrule rmstatusrule);

    /**
     * 新增数据
     *
     * @param rmstatusrule 实例对象
     * @return 影响行数
     */
    int insert(Rmstatusrule rmstatusrule);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Rmstatusrule> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Rmstatusrule> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Rmstatusrule> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Rmstatusrule> entities);

    /**
     * 修改数据
     *
     * @param rmstatusrule 实例对象
     * @return 影响行数
     */
    int update(Rmstatusrule rmstatusrule);

    /**
     * 通过主键删除数据
     *
     * @param rmstatusruleId 主键
     * @return 影响行数
     */
    int deleteById(Integer rmstatusruleId);

}

