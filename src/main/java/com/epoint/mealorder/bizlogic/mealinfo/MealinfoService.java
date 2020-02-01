package com.epoint.mealorder.bizlogic.mealinfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.epoint.mealorder.bizlogic.mealinfo.domain.Mealinfo;
import com.epoint.mealorder.dao.MealinfoDao;

public class MealinfoService
{
    private MealinfoDao mealinfoDao = new MealinfoDao();

    /**
     * 添加信息
     * 
     * @param mealinfo 菜品对象
     * @return 返回消息
     */
    public String insertMealInfo(Mealinfo mealinfo) {
        // mealinfo.setMealId(UUID.randomUUID().toString());
        if(mealinfoDao.queryMealinfoByName(mealinfo.getCpName())>0) {
            return "菜名重复，请重新添加";
        }
        
        if (mealinfoDao.addMealinfo(mealinfo) > 0) {
            return "添加成功";
        }
        else {
            return "添加失败";
        }

    }

    /**
     * 查找单条信息
     * 
     * @param cpid 菜品id
     * @return mealinfo 菜品对象
     */
    public Mealinfo queryMealinfoByID(String cpid) {
        Mealinfo mealinfo = mealinfoDao.queryMealinfoByID(cpid);
        return mealinfo;
    }

    /**
     * 更新信息
     * 
     * @param mealinfo 菜品对象
     * @return 返回消息
     */
    public String updateMealinfoByMealId(Mealinfo mealinfo) {
        int i = mealinfoDao.updateMealinfoByCpId(mealinfo);
        return "更新成功";
    }

    /**
     * 分页，模糊查询
     * 
     * @param pageIndex 页数
     * @param pageSize  每页数量
     * @param cpName    菜品名称
     * @param cptype    菜种类
     * @return 菜品列表
     */
    public List<Mealinfo> queryMealinfoByKeyWords(int pageIndex, int pageSize, String cpName, int cpType) {
        return mealinfoDao.queryMealinfoByKeyWords(pageIndex, pageSize, cpName, cpType);
    }

    /**
     * 删除信息
     * 
     * @param bookid
     * @return
     */

    public String deleteBookinfoByBookId(String cpid) {
        MealinfoDao mealinfoDao = new MealinfoDao();
        String[] cpids = cpid.split(",");
        List<String> list = new ArrayList<String>();
        for (String id : cpids) {
            mealinfoDao.deleteMealinfoByCpId(id);
            list.add(id);
            }
        String msg1 = Arrays.toString(list.toArray());
        msg1 = "[]".equals(msg1) ? "" : msg1 + "删除成功";
        return msg1;
    }

    /**
     * 查询表中所有数据的条数
     * 
     * @return 返回条数
     */
    public int findLength() {
        return mealinfoDao.findLength();
    }

    /**
     * 生成id
     * 
     * @return 生成最大id
     */
    public String FindMaxId() {
        return mealinfoDao.FindMaxId();
    }
}
