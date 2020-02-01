package com.epoint.mealorder.action.orderinfo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.epoint.mealorder.bizlogic.mealinfo.MealinfoService;
import com.epoint.mealorder.bizlogic.mealinfo.domain.Mealinfo;
import com.epoint.mealorder.bizlogic.orderinfo.OrderinfoService;
import com.epoint.mealorder.bizlogic.orderinfo.domain.Orderinfo;


@WebServlet("/OrderinfoDetailAction")
public class OrderinfoDetailAction extends HttpServlet
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
        if (method.equals("queryOrderinfo")) {
            queryOrderinfo(req, resp);
        }

    }
    /**
     * 获取单条订单信息
     * @param req
     * @param resp
     * @throws IOException
     */
    private void queryOrderinfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // TODO Auto-generated method stub
        OrderinfoService orderinfoService = new OrderinfoService();

        String orderId = req.getParameter("orderId");

        Orderinfo orderinfo =orderinfoService.queryOrderinfoByID(orderId);
        resp.getWriter().write(JSONObject.toJSONString(orderinfo));
    }

}
