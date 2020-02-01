package com.epoint.mealorder.action.mealinfo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.epoint.mealorder.bizlogic.mealinfo.MealinfoService;
import com.epoint.mealorder.bizlogic.mealinfo.domain.Mealinfo;

@WebServlet("/MealinfoDetailAction")
public class MealinfoDetailAction extends HttpServlet
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
        if (method.equals("queryMealinfo")) {
            queryMealinfo(req, resp);
        }

    }

    /**
     * 查询单条菜品信息
     * 
     * @param req
     * @param resp
     * @throws IOException
     */
    private void queryMealinfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // TODO Auto-generated method stub
        MealinfoService mealinfoService = new MealinfoService();

        String cpId = req.getParameter("cpId");

        Mealinfo mealinfo = mealinfoService.queryMealinfoByID(cpId);
        resp.getWriter().write(JSONObject.toJSONStringWithDateFormat(mealinfo, "yyyy-MM-dd"));
    }

}
