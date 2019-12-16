package com.wk.mapper.master;

import com.wk.entity.master.UserBean;
import com.wk.entity.master.UserBeanExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserBeanMapper1 {
    int countByExample(UserBeanExample example);

    int deleteByExample(UserBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserBean record);

    int insertSelective(UserBean record);

    List<UserBean> selectByExample(UserBeanExample example);

    UserBean selectByPrimaryKey(Integer id);

    List<UserBean> selectByIdOrderDesc();

    List<UserBean> selectByIdOrderAsc();

    /**
     * descripiton:
     *   mysql 分页查询
     * @author: wk
     * @params: offset : 偏移；  limits: 设置总数
     * @returns:
     * @time: 16:47
     * @modifier:
     * @since:
     */
    List<UserBean> selectPages(@Param("offset") Integer offset,@Param("limits") Integer limits);
    /**
     * descripiton:
     * 分页查询 使用map传参
     * @author: wk
     * @params:
     * @returns:
     * @time: 16:49
     * @modifier:
     * @since:
     */
    List<UserBean> selectPagesWithMap(Map<String,Object> map);

    int updateByExampleSelective(@Param("record") UserBean record, @Param("example") UserBeanExample example);

    int updateByExample(@Param("record") UserBean record, @Param("example") UserBeanExample example);

    int updateByPrimaryKeySelective(UserBean record);

    int updateByPrimaryKey(UserBean record);
}