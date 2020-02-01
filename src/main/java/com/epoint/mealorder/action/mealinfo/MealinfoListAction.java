package com.epoint.mealorder.action.mealinfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.epoint.mealorder.bizlogic.mealinfo.MealinfoService;
import com.epoint.mealorder.bizlogic.mealinfo.domain.Mealinfo;

@WebServlet("/MealinfoListAction")
public class MealinfoListAction extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub

        doPost(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        if (method.equals("queryList")) {
            queryList(req, resp);
        }
        else if (method.equals("allMeal")) {
            allMeal(req, resp);
        }

    }

    /**
     * 获取所有菜品编号和菜品名称
     * 
     * @param req
     * @param resp
     * @throws IOException
     */
    private void allMeal(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        MealinfoService mealinfoService = new MealinfoService();
        int length = mealinfoService.findLength();
        List<Mealinfo> mealinfoList = mealinfoService.queryMealinfoByKeyWords(0, length, null, 0);
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map;
        for (Mealinfo bi : mealinfoList) {
            map = new HashMap<String, String>();
            if (bi.getCpName() != null) {
                map.put("cpId", bi.getCpId());
                map.put("cpName", bi.getCpName());
                list.add(map);
            }
        }

        resp.getWriter().write(JSONObject.toJSONString(list));
    }

    /**
     * 获取所有菜品的信息，并分页
     * 
     * @param req
     * @param resp
     * @throws IOException
     */
    private void queryList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // TODO Auto-generated method stub

        MealinfoService mealinfoService = new MealinfoService();
        String cpName = "";
        String cpTypeSearch = req.getParameter("cptypesearch");

        int pageIndex = Integer.parseInt(req.getParameter("pageIndex"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        int length = mealinfoService.findLength();
        int mealinfoListTotalSize = 0;

        cpName = req.getParameter("cpnamesearch");
        if (req.getParameter("cpnamesearch") != null) {
            cpName = req.getParameter("cpnamesearch").trim();
        }

        if (cpTypeSearch == null || cpTypeSearch == "") {
            cpTypeSearch = "0";
        }
        int cpTypeSearch2 = Integer.parseInt(cpTypeSearch);
        mealinfoListTotalSize = mealinfoService.queryMealinfoByKeyWords(0, length, cpName, cpTypeSearch2).size();
        List<Mealinfo> list = mealinfoService.queryMealinfoByKeyWords(pageIndex, pageSize, cpName, cpTypeSearch2);
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("data", list);
        json.put("total", mealinfoListTotalSize);
        resp.getWriter().write(JSONObject.toJSONStringWithDateFormat(json, "yyyy-MM-dd"));
    }

}
