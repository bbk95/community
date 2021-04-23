package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper// 同@Repository，MyBatis用Mapper
public interface UserMapper {//接口
    //根据id查user
    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);
    // 插入 返回的是一组，故是User
    int insertUser(User user);
    //对user进行修改 状态
    int updateStatus(int id, int status);

    int updateHeader(int id, String headerUrl);

    int updatePassword(int id, String password);



}
