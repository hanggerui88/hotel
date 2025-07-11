package com.zxd.dao;


import com.zxd.pojo.Rmtypedailyrate;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Rmtypedailyrate)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-29 20:46:13
 */
public interface RmtypedailyrateDao {

    /**
     * 通过ID查询单条数据
     *
     * @param rmtypedailyrateId 主键
     * @return 实例对象
     */
    Rmtypedailyrate queryById(Integer rmtypedailyrateId);

    /**
     * 查询指定行数据
     *
     * @param rmtypedailyrate 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Rmtypedailyrate> queryAllByLimit(Rmtypedailyrate rmtypedailyrate, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param rmtypedailyrate 查询条件
     * @return 总行数
     */
    long count(Rmtypedailyrate rmtypedailyrate);

    /**
     * 新增数据
     *
     * @param rmtypedailyrate 实例对象
     * @return 影响行数
     */
    int insert(Rmtypedailyrate rmtypedailyrate);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Rmtypedailyrate> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Rmtypedailyrate> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Rmtypedailyrate> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Rmtypedailyrate> entities);

    /**
     * 修改数据
     *
     * @param rmtypedailyrate 实例对象
     * @return 影响行数
     */
    int update(Rmtypedailyrate rmtypedailyrate);

    /**
     * 通过主键删除数据
     *
     * @param rmtypedailyrateId 主键
     * @return 影响行数
     */
    int deleteById(Integer rmtypedailyrateId);

}

