package com.epoint.mealorder.action.orderinfo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.epoint.mealorder.bizlogic.orderinfo.OrderinfoService;
import com.epoint.mealorder.bizlogic.orderinfo.domain.Orderinfo;
@WebServlet("/OrderinfoAddAction")
public class OrderinfoAddAction extends HttpServlet
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
        if (method.equals("addOrderinfo")) {
            addOrderinfo(req, resp);
        }
    }
    

   /**
    * 添加订单信息
    * @param req
    * @param resp
    * @throws IOException
    */
    private void addOrderinfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // TODO Auto-generated method stub
        OrderinfoService  orderinfoService = new  OrderinfoService();
        String json = req.getParameter("data");
        Orderinfo orderinfo = JSON.parseObject(json, Orderinfo.class);
        String str =orderinfoService.insertOrderinfo(orderinfo);
        resp.getWriter().write(str);
    }
}
