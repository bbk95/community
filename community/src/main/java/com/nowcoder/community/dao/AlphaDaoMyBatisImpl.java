package com.nowcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary //调用的是接口，实现类变了，加的这个注解。让其首先执行，通过这种方式，降低耦合度
public class AlphaDaoMyBatisImpl implements AlphaDao{
    @Override
    public String select() {
        return "MyBatis";
    }
}
