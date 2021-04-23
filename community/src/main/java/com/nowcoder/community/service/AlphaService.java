package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service //业务组件，用这个注解。这个bean希望由容器来管理，不仅是创建、还有初始化和销毁的方法
//@Scope("prototype") //通常都是用默认的singleton,需要实现多个实例需要加上prototype。加上之后，每次访问bean都会创建一个新的实例
public class AlphaService {

    //controller调service，service调dao。
    //它们之间存在这种依赖关系
    @Autowired //通过这种方式，进行依赖注入。整合---：处理查询业务的时候就可以调用了
    private AlphaDao alphaDao;


    public AlphaService(){
        System.out.println("实例化AlphaService");
    }

    //1、首先，增加bean的初始化方法。
    //2、要想要容器管理这个方法，就是要在合适的时候，自动调用这个方法。在其之前加上注解。
    @PostConstruct //意思是这个方法会在构造器初始化之后调用。
    // 初始化方法通常是在构造器之后调用
    public void init(){
        System.out.println("初始化AlphaService");
    }

    @PreDestroy //在销毁对象之前去调用它，对象销毁之后再调就没办法了
    public void destroy(){
        System.out.println("销毁AlphaService");
    }

    //service依赖于dao的处理方式 整合---
    public String find(){
        return alphaDao.select();
    }

}
