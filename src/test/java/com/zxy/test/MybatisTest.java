package com.zxy.test;

import com.zxy.dao.IUserDao;
import com.zxy.pojo.QueryVo;
import com.zxy.pojo.User;
import jdk.internal.util.xml.impl.Input;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.rmi.server.UnicastServerRef;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @Description: 测试mybatis的CRUD
 * @Author: zhangxy
 * @Date: Created in 2019/11/24
 * @Modified By:
 */
public class MybatisTest {
    private InputStream input;
    private SqlSession session;
    private IUserDao userDao;

    /**
     * 初始化操作
     *
     * @throws Exception
     */
    @Before // 用于在测试方法执行之前执行
    public void init() throws Exception {
        // 1.读取配置文件
        input = Resources.getResourceAsStream("Mybatis-config.xml");
        // 2.获取SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(input);
        // 3.获取SqlSession 对象
        session = factory.openSession();
        // 4.获取dao接口的代理对象
        userDao = session.getMapper(IUserDao.class);
    }


    @After // 用于在测试方法执行之后执行
    public void destroy() throws Exception {
        // 提交事务
        session.commit();
        // 释放资源
        session.close();
        input.close();
    }

    /**
     * 测试查询所有信息
     */
    @Test
    public void testFindAll() {

        // 执行查询方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }

    }

    /**
     * 保存用户信息
     */
    @Test
    public void testSave() throws Exception {
        User user = new User();
        user.setUserName("mybatis 保存2266 last_insert_id");
        user.setUserAddress("西咸新区");
        user.setUserSex("男");
        user.setUserBirthday(new Date());

        System.out.println("保存之前： " + user);
        // 执行保存方法
        userDao.saveUser(user);
        System.out.println("保存之后： " + user);
    }

    /**
     * 更新用户信息
     */
    @Test
    public void testUpdate() throws Exception {
        User user = new User();
        user.setUserId(56);
        user.setUserName("mybatis 更新");
        user.setUserAddress("兰州新区");
        user.setUserSex("女");
        user.setUserBirthday(new Date());

        // 执行保存方法
        userDao.updateUser(user);
    }

    /**
     * 删除用户信息
     */
    @Test
    public void testDelete() throws Exception {
        userDao.deleteUser(50);
    }

    /**
     * 查询一个用户信息
     */
    @Test
    public void testFind() throws Exception {
        User user = userDao.findById(52);
        System.out.println(user);
    }

    /**
     * 根据姓名模糊查询
     */
    @Test
    public void testFindByName() throws Exception {
         List<User> users = userDao.findByName("%架%");
        // List<User> users = userDao.findByName("架");
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 查询用户个数
     */
    @Test
    public void testFindTotal() throws Exception {
        int total = userDao.findTotal();
        System.out.println(total);
    }


    /**
     * 使用QueryVO 作为查询条件进行查询
     */
    @Test
    public void testFindByVO() throws Exception {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("%产%");
        vo.setUser(user);
        // List<User> users = userDao.fingByName("%架%");
        List<User> users = userDao.findUserByVO(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }
}
