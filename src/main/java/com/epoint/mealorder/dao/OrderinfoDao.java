package com.epoint.mealorder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.epoint.mealorder.bizlogic.orderinfo.domain.Orderinfo;
import com.epoint.mealorder.util.JDBCUtil;

public class OrderinfoDao
{
    /**
               * 根据orderid查找详细数据
     * 
     * @param orderid 订单id
     * @return 订单对象
     */
    public Orderinfo queryOrderinfoByID(String orderid) {
        Connection conn = JDBCUtil.getConnection();
        String sql = "select * from orderinfo where orderid=?";
        Orderinfo orderinfo = null;
        try {
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, orderid);
            ResultSet resultSet = preStatement.executeQuery();

            while (resultSet.next()) {
                orderinfo = new Orderinfo(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getTimestamp(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getDouble(8));
            }
            JDBCUtil.closeCon(conn, resultSet, preStatement);
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return orderinfo;
    }



    /**
     * 添加信息
     * 
     * @param orderinfo 订单对象,联级添加
     * @return 添加行数
     */
    public int addOrderinfo(Orderinfo orderinfo) {
        int count = 0;
        Connection conn = JDBCUtil.getConnection();
        String sql = "insert into orderinfo values(?,?,?,?,?,?,?,?)";
        String sql2 = "update mealinfo set count=count+? where cpid=?";
        try {
            
            //添加order对象信息
            conn.setAutoCommit(false);
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, orderinfo.getOrderId());
            preStatement.setString(2, orderinfo.getCpId());
            preStatement.setString(3, orderinfo.getOrderName());
            preStatement.setObject(4, orderinfo.getOrdertime());
            preStatement.setString(5, orderinfo.getPhone());
            preStatement.setInt(6, orderinfo.getCount());
            preStatement.setString(7, orderinfo.getAddress());
            preStatement.setDouble(8, orderinfo.getPrice());
            count = preStatement.executeUpdate();
            conn.commit();
            JDBCUtil.closeCon(conn, null, preStatement);
           //添加meal对象信息
            if (count > 0) {
                Connection conn2 = JDBCUtil.getConnection();
                conn2.setAutoCommit(false);
                PreparedStatement preStatement2 = conn2.prepareStatement(sql2);
                preStatement2.setObject(1, orderinfo.getCount());
                preStatement2.setString(2, orderinfo.getCpId());
                preStatement2.executeUpdate();
                conn2.commit();
                JDBCUtil.closeCon(conn2, null, preStatement2);
            }
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            try {
                conn.rollback();
            }
            catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 分页查询
     * 
     * @param pageIndex 当前页数
     * @param pageSize  显示个数
     * @param sortField 排序id
     * @param sortOrder 排序方式
     * @return
     */

    public List<Orderinfo> queryOrderinfoByKeyWords(int pageIndex, int pageSize, String sortField, String sortOrder) {
        Connection conn = JDBCUtil.getConnection();
        int firstIndex = (pageIndex) * pageSize;

        String sql = "select * from orderinfo  order by+" + sortField + " " + sortOrder + " limit ?,?";
        List<Orderinfo> list = new ArrayList<Orderinfo>();
        PreparedStatement preStatement = null;
        try {
            preStatement = conn.prepareStatement(sql);
            preStatement.setInt(1, firstIndex);
            preStatement.setInt(2, pageSize);
            ResultSet resultSet = preStatement.executeQuery();
            while (resultSet.next()) {
                Orderinfo orderinfo = new Orderinfo(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getTimestamp(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getDouble(8));
                list.add(orderinfo);
            }
            JDBCUtil.closeCon(conn, resultSet, preStatement);
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询数据库条数
     * 
     * @return 返回条数
     */
    public int findLength() {
        Connection conn = JDBCUtil.getConnection();
        String sql = "select count(*) from orderinfo ";
        int count = 0;
        try {
            PreparedStatement preStatement = conn.prepareStatement(sql);
            ResultSet rs = preStatement.executeQuery();
            while (rs.next()) {
                count = Integer.parseInt(rs.getString(1));
            }
            JDBCUtil.closeCon(conn, rs, preStatement);
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return count;
    }

    
/**
 * 删除订单信息
 * @param orderId 订单id
 * @return 输出的条数
 */
   public int deleteOrderinfoByOrderId(String orderId) {
       int count = 0;
       Connection conn = JDBCUtil.getConnection();
       String sql = "delete from orderinfo where orderid=?";

       try {
           PreparedStatement preStatement = conn.prepareStatement(sql);
           preStatement.setString(1, orderId);
           count = preStatement.executeUpdate();
           JDBCUtil.closeCon(conn, null, preStatement);
       }
       catch (SQLException e) {
           // TODO Auto-generated catch block
           try {
               conn.rollback();
           }
           catch (SQLException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
           }
           e.printStackTrace();
       }
       return count;
   }
}
