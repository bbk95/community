package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired //controller调用service，将service注入进来
    private AlphaService alphaService;


    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello Spring Boot.";
    }

    //模拟进行处理请求
    //浏览器要访问这个方法的前提是要有注解说明，说明它的路径

    @RequestMapping("/data")
    @ResponseBody
    public String getDate(){
        return alphaService.find();//把find的结果返回给浏览器
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + " : " + value);

        }
        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try(
                PrintWriter writer = response.getWriter();
        ) {
           // PrintWriter writer = response.getWriter();
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //GET请求

    // /student?current=1&limit=20    这种是？加参数
    @RequestMapping(path = "/students",method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
        System.out.println(current);
        System.out.println(limit);

        return "some students";
    }

    // /student/123       参数是路径的一部分的访问方式，{id} 指代的是123这一级数据，是变量
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }
    //get请求也可以请求，但是1、是在明面上传的，会显示在url中2、传参有长度限制，
    //POST请求  需要有网页向服务器提交数据，创建一个静态文件演示,,在static中
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    // 相应HTML数据

    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    //不加@ResponseBody这个注解，默认返回的是HTML
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        //模板有几个数据，就几个add
        mav.addObject("name","张三");
        mav.addObject("age",30);
        //往对象设置一个模板的路径和名字
        mav.setViewName("/demo/view");
        return mav;
    }

    //这种方式简洁，方便使用
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","北京大学");
        model.addAttribute("age",80);
        return "/demo/view";//返回的类型是String,指的是返回的是view的路径
    }


    // 响应JSON数据(异步请求)
    // Java对象 -> JSON字符串 -> JS对象

    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEmp(){ //DispatcherServlet调此方法时：一看有这个注解，并且返回的是这个类型，会把map转成json字符串发送给浏览器
        Map<String,Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary",8000.00);
        return emp;
    }

    @RequestMapping(path = "/emps",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getEmps(){
        List<Map<String,Object>> list = new ArrayList<>();

        Map<String,Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary",8000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "李四");
        emp.put("age", 24);
        emp.put("salary",9000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "王五");
        emp.put("age", 25);
        emp.put("salary",10000.00);
        list.add(emp);

        return list;
    }



}
