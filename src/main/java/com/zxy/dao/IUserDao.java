package com.zxy.dao;

import com.zxy.pojo.QueryVo;
import com.zxy.pojo.User;

import java.util.List;
import java.util.Queue;

/**
 * @Description: 用户的持久层接口
 * @Author: zhangxy
 * @Date: Created in 2019/11/24
 * @Modified By:
 */
public interface IUserDao {
    /**
     * 查询所有用户信息
     * @return
     */
    List<User> findAll();

    /**
     * 保存用户信息
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id删除用户
     * @param userId
     */
    void deleteUser(Integer userId);

    /**
     * 根据id 查询用户信息
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 根据姓名模糊查询用户信息
     * @param userName
     * @return
     */
    List<User> findByName(String userName);

    /**
     * 查询总用户数
     * @return
     */
    int findTotal();

    /**
     * 根据QueryVO中的查询条件查询vo
     * @param vo
     * @return
     */
    List<User> findUserByVO(QueryVo vo);
}
