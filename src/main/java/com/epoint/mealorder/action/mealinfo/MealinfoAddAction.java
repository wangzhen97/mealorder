package com.epoint.mealorder.action.mealinfo;

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
import com.epoint.mealorder.bizlogic.mealinfo.MealinfoService;
import com.epoint.mealorder.bizlogic.mealinfo.domain.Mealinfo;

@WebServlet("/MealinfoAddAction")
public class MealinfoAddAction extends HttpServlet
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
        if (method.equals("addMealinfo")) {
            addMealinfo(req, resp);
        }else  if (method.equals("SetId")) {
            SetId(req, resp);
        }
    }
    /**
               * 生成菜品ID
     * @param req
     * @param resp
     * @throws IOException
     */
    private void SetId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // TODO Auto-generated method stub
        MealinfoService mealinfoService = new MealinfoService();
        Map<String, Object> json = new HashMap<String, Object>();
        String cpId = mealinfoService.FindMaxId();
        json.put("cpId", cpId);
        resp.getWriter().write(JSONObject.toJSONString(json));
    }
   /**
             * 添加菜品信息
    * @param req
    * @param resp
    * @throws IOException
    */
    private void addMealinfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // TODO Auto-generated method stub
        MealinfoService  mealinfoService = new  MealinfoService();
        
       
        String json = req.getParameter("data");
        Mealinfo mealInfo = JSON.parseObject(json, Mealinfo.class);
  
        String str =mealinfoService.insertMealInfo(mealInfo);
        resp.getWriter().write(str);
    }
}
