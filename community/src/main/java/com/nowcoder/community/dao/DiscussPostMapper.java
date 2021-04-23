package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper// 写上注解，才能被容器扫描这个接口，才能自动实现，装配它
public interface DiscussPostMapper {

    // 这里是要做查询的功能，在这里声明查询的方法->配置文件里写与之有关的SQL
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);//offset 起始行号 limit每页多少数据.这是为了考虑分页情况

    // @Param注解用于给参数取别名
    //如果只有一个参数，并且在<if>里使用，则必须加别名
    int selectDiscussPostRows(@Param("userId") int userId);

}
