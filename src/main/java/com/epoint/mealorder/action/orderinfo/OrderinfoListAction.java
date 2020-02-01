package com.epoint.mealorder.action.orderinfo;

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
import com.epoint.mealorder.bizlogic.orderinfo.OrderinfoService;
import com.epoint.mealorder.bizlogic.orderinfo.domain.Orderinfo;

@WebServlet("/OrderinfoListAction")
public class OrderinfoListAction extends HttpServlet
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
    }

    /**
     * 生成订单列表
     * 
     * @param req
     * @param resp
     * @throws IOException
     */
    private void queryList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // TODO Auto-generated method stub
        OrderinfoService orderinfoService = new OrderinfoService();
        MealinfoService mealinfoService = new MealinfoService();

        int pageIndex = Integer.parseInt(req.getParameter("pageIndex"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        String sortField = req.getParameter("sortField");
        String sortorder = req.getParameter("sortOrder");
        int orderinfoListTotalSize = 0;
        int length = orderinfoService.findLength();
        orderinfoListTotalSize = orderinfoService.queryOrderinfoByKeyWords(0, length, sortField, sortorder).size();
        List<Orderinfo> list = orderinfoService.queryOrderinfoByKeyWords(pageIndex, pageSize, sortField, sortorder);
        List<Map<String, Object>> newlist = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        for (int i = 0; i < list.size(); i++) {
            map = new HashMap<String, Object>();
            map.put("orderId", list.get(i).getOrderId());
            map.put("cpName", mealinfoService.queryMealinfoByID(list.get(i).getCpId()).getCpName());
            map.put("orderName", list.get(i).getOrderName());
            map.put("orderTime", list.get(i).getOrdertime());
            map.put("phone", list.get(i).getPhone());
            map.put("count", list.get(i).getCount());
            map.put("address", list.get(i).getAddress());
            map.put("price", list.get(i).getPrice());
            newlist.add(map);
        }
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("data", newlist);
        json.put("total", orderinfoListTotalSize);
        resp.getWriter().write(JSONObject.toJSONStringWithDateFormat(json, "yyyy-MM-dd HH:mm:ss"));
    }

}
