package com.epoint.mealorder.bizlogic.orderinfo;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.epoint.mealorder.bizlogic.orderinfo.domain.Orderinfo;
import com.epoint.mealorder.dao.OrderinfoDao;

public class OrderinfoService
{
    private OrderinfoDao orderinfoDao = new OrderinfoDao();

    /**
     * 查詢订单信息信息
     * 
     * @param orderid 订单id
     * @return 订单对象
     */
    public Orderinfo queryOrderinfoByID(String orderid) {
        return orderinfoDao.queryOrderinfoByID(orderid);
    }

  
     /** 分页查询
     * 
     * @param pageIndex 当前页数
     * @param pageSize  显示个数
     * @param sortField 排序id
     * @param sortOrder 排序方式
     * @return
     */
    public List<Orderinfo> queryOrderinfoByKeyWords(int pageIndex, int pageSize, String sortField, String sortOrder) {
        return orderinfoDao.queryOrderinfoByKeyWords(pageIndex, pageSize, sortField, sortOrder);
    }

    /**
     * 查询所有数量
     * 
     * @return 返回数量
     */
    public int findLength() {
        return orderinfoDao.findLength();
    }


    /**
     * 添加
     * 
     * @param orderinfo 订单对象
     * @return 添加行数
     */
    public String insertOrderinfo(Orderinfo orderinfo) {

        Date date=new Date();
        orderinfo.setOrderId(UUID.randomUUID().toString());
        orderinfo.setOrdertime(date);
       
        int count = orderinfoDao.addOrderinfo(orderinfo);
        if (count > 0) {
            return "添加成功";
        }
        else {
            return "添加失败";
        }
    }
    
    public String deleteOrderinfoByOrderId(String orderId) {
        int count = orderinfoDao.deleteOrderinfoByOrderId(orderId);
                if (count > 0) {
                    return "删除成功";
                }
                else {
                    return "删除失败";
                }
    }
    
}
