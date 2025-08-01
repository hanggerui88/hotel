package com.zxd.dao;


import com.zxd.pojo.Raterule;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Raterule)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-29 21:09:03
 */
public interface RateruleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param rateruleId 主键
     * @return 实例对象
     */
    Raterule queryById(Integer rateruleId);

    /**
     * 查询指定行数据
     *
     * @param raterule 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Raterule> queryAllByLimit(@Param("rr") Raterule raterule, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param raterule 查询条件
     * @return 总行数
     */
    long count(@Param("rr") Raterule raterule);

    /**
     * 新增数据
     *
     * @param raterule 实例对象
     * @return 影响行数
     */
    int insert(Raterule raterule);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Raterule> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Raterule> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Raterule> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Raterule> entities);

    /**
     * 修改数据
     *
     * @param raterule 实例对象
     * @return 影响行数
     */
    int update(Raterule raterule);

    /**
     * 通过主键删除数据
     *
     * @param rateruleId 主键
     * @return 影响行数
     */
    int deleteById(Integer rateruleId);

}

