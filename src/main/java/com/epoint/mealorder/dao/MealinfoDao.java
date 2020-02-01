package com.epoint.mealorder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epoint.mealorder.bizlogic.mealinfo.domain.Mealinfo;
import com.epoint.mealorder.util.JDBCUtil;

public class MealinfoDao
{

    /**
     * 根据菜单查询详细信息
     * 
     * @param cpId 菜单id
     * @return 菜单对象
     */
    public Mealinfo queryMealinfoByID(String cpId) {
        Connection conn = JDBCUtil.getConnection();
        String sql = "select * from mealinfo where cpid=?";
        Mealinfo mealinfo = null;
        try {
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, cpId);
            ResultSet resultSet = preStatement.executeQuery();

            while (resultSet.next()) {
                mealinfo = new Mealinfo(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getDouble(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getTimestamp(7), resultSet.getString(8));
            }
            JDBCUtil.closeCon(conn, resultSet, preStatement);
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mealinfo;
    }

    
    /**
     * 查重
     * 
     * @param cpId 菜单id
     * @return 菜单对象
     */
    public int queryMealinfoByName(String cpName) {
        Connection conn = JDBCUtil.getConnection();
        String sql = "select * from mealinfo where cpname=?";
        Mealinfo mealinfo = null;
        int count=0;
        try {
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, cpName);
            ResultSet resultSet = preStatement.executeQuery();
            List<Mealinfo> list=new ArrayList<Mealinfo>();
            while (resultSet.next()) {
              
              mealinfo = new Mealinfo(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getDouble(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getTimestamp(7), resultSet.getString(8));
              list.add(mealinfo);
            }
            JDBCUtil.closeCon(conn, resultSet, preStatement);
            count=list.size();
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
     
        return count;
    }
    /**
     * 修改信息
     * 
     * @param mealinfo 菜单对象
     * @return 修改行数
     */
    public int updateMealinfoByCpId(Mealinfo mealinfo) {
        int count = 0;
        Connection conn = JDBCUtil.getConnection();
        String sql = "update mealinfo set cpName=?,cptype=?,money=?,canpack=?,count=?,addtime=?,note=? where cpid =?";

        try {
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, mealinfo.getCpName());
            preStatement.setInt(2, mealinfo.getCpType());
            preStatement.setDouble(3, mealinfo.getMoney());
            preStatement.setObject(4, mealinfo.getCanPack());
            preStatement.setInt(5, mealinfo.getCount());
            preStatement.setObject(6, mealinfo.getAddTime());
            preStatement.setString(7, mealinfo.getNote());
            preStatement.setString(8, mealinfo.getCpId());
            count = preStatement.executeUpdate();
            JDBCUtil.closeCon(conn, null, preStatement);
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 根据菜单号删除，联级删除
     * 
     * @param cpid 菜单id
     * @return 删除数量
     */
    public int deleteMealinfoByCpId(String cpid) {
        int mealCount = 0;
        int orderCount=0;
        Connection conn = JDBCUtil.getConnection();
        String sql = "delete from mealinfo where cpid=?";
        String sql2 = "delete from orderinfo where cpid=?";
        try {
            // 删除菜单信息列表的信息
            conn.setAutoCommit(false);
            PreparedStatement preStatement = conn.prepareStatement(sql);
           
            preStatement.setString(1, cpid);
            mealCount = preStatement.executeUpdate();
            conn.commit();
            JDBCUtil.closeCon(conn, null, preStatement);
            // 菜单信息删除成功后删除订单页面
            if (mealCount > 0) {
                Connection conn2 = JDBCUtil.getConnection();
                conn2.setAutoCommit(false);
                PreparedStatement preStatement2 = conn2.prepareStatement(sql2);
                preStatement2.setString(1, cpid);
                orderCount = preStatement2.executeUpdate();
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
        return mealCount;
    }

    /**
     * 添加
     * 
     * @param mealinfo 菜单对象
     * @return 添加的行数
     */
    public int addMealinfo(Mealinfo mealinfo) {
        int count = 0;
        Connection conn = JDBCUtil.getConnection();
        String sql = "insert into mealinfo values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, mealinfo.getCpId());
            preStatement.setString(2, mealinfo.getCpName());
            preStatement.setInt(3, mealinfo.getCpType());
            preStatement.setDouble(4, mealinfo.getMoney());
            preStatement.setObject(5, mealinfo.getCanPack());
            preStatement.setInt(6, mealinfo.getCount());
            preStatement.setObject(7, mealinfo.getAddTime());
            preStatement.setObject(8, mealinfo.getNote());
            count = preStatement.executeUpdate();
            JDBCUtil.closeCon(conn, null, preStatement);
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 分页，模糊查询
     * 
     * @param pageIndex 页数
     * @param pageSize  每页数量
     * @param cpName    菜品名称
     * @param cptype    菜种类
     * @return
     */
    public List<Mealinfo> queryMealinfoByKeyWords(int pageIndex, int pageSize, String cpName, int cpType) {
        Connection conn = JDBCUtil.getConnection();
        int firstIndex = (pageIndex) * pageSize;
        String sql = "select * from mealinfo ";
        List<Mealinfo> list = new ArrayList<Mealinfo>();
        PreparedStatement preStatement = null;
        try {
            if (cpName != null && !"".equals(cpName) && cpType != 0) {
                sql = sql + "where  cpname like ? and cptype=? limit ?,?";
                preStatement = conn.prepareStatement(sql);
                preStatement.setString(1, "%" + cpName + "%");
                preStatement.setInt(2, cpType);
                preStatement.setInt(3, firstIndex);
                preStatement.setInt(4, pageSize);

            }
            else if (cpName != null && !"".equals(cpName) && cpType == 0) {
                sql = sql + "where  cpName like ? limit ?,?";
                preStatement = conn.prepareStatement(sql);
                preStatement.setString(1, "%" + cpName + "%");
                preStatement.setInt(2, firstIndex);
                preStatement.setInt(3, pageSize);
            }
            else if ((cpName == null || "".equals(cpName)) && cpType != 0) {
                sql = sql + "where  cpType=? limit ?,?";
                preStatement = conn.prepareStatement(sql);
                preStatement.setInt(1, cpType);
                preStatement.setInt(2, firstIndex);
                preStatement.setInt(3, pageSize);
            }
            else {
                sql = sql + "limit ?,?";
                preStatement = conn.prepareStatement(sql);
                preStatement.setInt(1, firstIndex);
                preStatement.setInt(2, pageSize);
            }

            ResultSet resultSet = preStatement.executeQuery();
            while (resultSet.next()) {
                Mealinfo mealinfo = new Mealinfo(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getDouble(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getTimestamp(7), resultSet.getString(8));
                list.add(mealinfo);
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
     * 查询表中所有数据的条数
     * 
     * @return 返回条数
     */
    public int findLength() {
        Connection conn = JDBCUtil.getConnection();
        String sql = "select count(*) from mealinfo ";
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
     * 生成id
     * 
     * @return 生成最大id
     */
    public String FindMaxId() {
        Connection conn = JDBCUtil.getConnection();
        String sql = "select lpad(max(right(cpid,4))+1, 4, 0) as maxnum from mealinfo";
        String maxid = null;
        PreparedStatement preStatement;
        try {
            preStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preStatement.executeQuery();
            while (resultSet.next()) {
                maxid = resultSet.getString(1);
            }
            if (maxid == null) {
                maxid = "0001";
            }
            maxid = "CP" + maxid;
            JDBCUtil.closeCon(conn, resultSet, preStatement);
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return maxid;
    }
}
