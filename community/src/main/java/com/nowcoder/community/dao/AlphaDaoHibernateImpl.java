package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

@Repository("alphaHibernate")   // 扫描数据库的注解bean,("alphaHibernate")这就是bean 的名字
public class AlphaDaoHibernateImpl implements AlphaDao {

    @Override
    public String select() {
        return "Hibernate";
    }
}
