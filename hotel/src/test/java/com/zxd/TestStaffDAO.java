//package com.zxd;
//
//import com.zxd.dao.ReservationDao;
//import com.zxd.pojo.Reservation;
//import com.zxd.service.ReservationService;
//import org.apache.commons.lang3.time.DateUtils;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.sql.Date;
//
//public class TestStaffDAO extends BaseTest{
//
//	private static final Logger logger = LoggerFactory.getLogger(TestStaffDAO.class);
//
//	@Autowired
//	private ReservationDao reservationDao;
//
//	@Autowired
//	private ReservationService reservationService;
//
//	@Test
//	public void testQueryByPage_WithoutFilters() {
//		// 1. 创建一个没有任何过滤条件的查询
//		PageRequest pageRequest = PageRequest.of(0, 5);
//		Page<Reservation> result = reservationService.queryByPage(new Reservation(), pageRequest);
//
//		// 2. 打印分页结果详情
//		logger.info("分页查询结果:");
//		logger.info("总记录数: {}", result.getTotalElements());
//		logger.info("总页数: {}", result.getTotalPages());
//		logger.info("当前页码: {}", result.getNumber());
//		logger.info("当前页大小: {}", result.getSize());
//		logger.info("是否有内容: {}", result.hasContent());
//		logger.info("是否是首页: {}", result.isFirst());
//		logger.info("是否是末页: {}", result.isLast());
//
//		// 3. 如果有内容，打印前10条记录
//		if (result.hasContent()) {
//			logger.info("查询结果内容:");
//			result.getContent().stream()
//					.limit(10) // 只显示前10条
//					.forEach(reservation -> {
//						logger.info("ID: {}", reservation.getReId());
//						logger.info("入住日期: {} - 退房日期: {}",
//								reservation.getCinDate(), reservation.getCoutDate());
//						logger.info("客人姓名: {} - 电话: {}",
//								reservation.getCinName(), reservation.getCinPhone());
//						logger.info("成人: {} - 儿童: {} - 房间数: {}",
//								reservation.getAdultNum(), reservation.getChildNum(), reservation.getRmNum());
//						logger.info("状态: {}", reservation.getReStatus());
//						logger.info("-----------------------");
//					});
//		} else {
//			logger.warn("未查询到任何数据!");
//		}
//
//		// 4. 添加断言验证
//		assertNotNull("结果不应为null", result);
//		assertTrue("应查询到数据", result.getTotalElements() > 0);
//		assertTrue("当前页应包含数据", result.hasContent());
//	}
//
//	// 基本断言方法
//	private void assertNotNull(String message, Object obj) {
//		if (obj == null) {
//			throw new AssertionError(message);
//		}
//	}
//
//	private void assertTrue(String message, boolean condition) {
//		if (!condition) {
//			throw new AssertionError(message);
//		}
//	}
//}