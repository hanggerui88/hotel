package com.zxd.service.impl;

import com.zxd.dao.StaffDao;
import com.zxd.pojo.Staff;
import com.zxd.pojo.page;
import com.zxd.service.StaffService;
import com.zxd.util.md5;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Staff)表服务实现类
 *
 * @author makejava
 * @since 2025-06-29 20:46:16
 */
@Service("staffService")
public class StaffServiceImpl implements StaffService {
    @Resource
    private StaffDao staffDao;


    /**
     * 通过ID查询单条数据
     *
     * @param staffId 主键
     * @return 实例对象
     */
    @Override
    public Staff queryById(Integer staffId) {
        return this.staffDao.queryById(staffId);
    }

    /**
     * 分页查询
     *
     * @param staff 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Staff> queryByPage(Staff staff, PageRequest pageRequest) {
        long total = this.staffDao.count(staff);
        return new PageImpl<>(this.staffDao.queryAllByLimit(staff, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param staff 实例对象
     * @return 实例对象
     */
    @Override
    public Staff insert(Staff staff,String pd) {
        staff.setPassword(md5.encrypt(pd));
        this.staffDao.insert(staff);
        return staff;
    }

    /**
     * 修改数据
     *
     * @param staff 实例对象
     * @return 实例对象
     */
    @Override
    public Staff update(Staff staff) {
        this.staffDao.update(staff);
        return this.queryById(staff.getStaffId());
    }

    /**
     * 通过主键删除数据
     *
     * @param staffId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer staffId) {
        return this.staffDao.deleteById(staffId) > 0;
    }

    @Override
    public List<Staff> list( Staff staff,page page1) {
        return this.staffDao.list(staff,page1);
    }
}
