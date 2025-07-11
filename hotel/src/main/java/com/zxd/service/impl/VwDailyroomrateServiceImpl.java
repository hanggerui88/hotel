package com.zxd.service.impl;

import com.zxd.dao.VwDailyroomrateDao;
import com.zxd.pojo.VwDailyroomrate;
import com.zxd.service.VwDailyroomrateService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.sql.Date;

/**
 * (VwDailyroomrate)表服务实现类
 *
 * @author makejava
 * @since 2025-06-29 20:46:17
 */
@Service("vwDailyroomrateService")
public class VwDailyroomrateServiceImpl implements VwDailyroomrateService {
    @Resource
    private VwDailyroomrateDao vwDailyroomrateDao;



    @Override
    public VwDailyroomrate queryByDate(Date d) {
        return this.vwDailyroomrateDao.queryByDate(d);
    }

    /**
     * 分页查询
     *
     * @param vwDailyroomrate 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<VwDailyroomrate> queryByPage(VwDailyroomrate vwDailyroomrate, PageRequest pageRequest) {
        long total = this.vwDailyroomrateDao.count(vwDailyroomrate);
        return new PageImpl<>(this.vwDailyroomrateDao.queryAllByLimit(vwDailyroomrate, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param vwDailyroomrate 实例对象
     * @return 实例对象
     */
    @Override
    public VwDailyroomrate insert(VwDailyroomrate vwDailyroomrate) {
        this.vwDailyroomrateDao.insert(vwDailyroomrate);
        return vwDailyroomrate;
    }

    /**
     * 修改数据
     *
     * @param vwDailyroomrate 实例对象
     * @return 实例对象
     */
    @Override
    public VwDailyroomrate update(VwDailyroomrate vwDailyroomrate) {
        this.vwDailyroomrateDao.update(vwDailyroomrate);
        return this.queryByDate((Date) vwDailyroomrate.getDdate());
    }

    @Override
    public boolean deleteByDate(Date d) {
        return this.vwDailyroomrateDao.deleteByDate(d) > 0;
    }


}
