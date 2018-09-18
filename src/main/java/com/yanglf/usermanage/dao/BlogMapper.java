package com.yanglf.usermanage.dao;

import com.yanglf.usermanage.domain.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface BlogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKeyWithBLOBs(Blog record);

    int updateByPrimaryKey(Blog record);
    @Select({
            "select",
            "a.id, comments, a.create_time, likes, reading, summary, title, user_id, content, ",
            "html_content,avator,email,name,update_time,username",
            "from blog a,blog_user b",
            "where a.user_id = b.id"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="comments", property="comments", jdbcType=JdbcType.BIGINT),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="likes", property="likes", jdbcType=JdbcType.BIGINT),
            @Result(column="reading", property="reading", jdbcType=JdbcType.BIGINT),
            @Result(column="summary", property="summary", jdbcType=JdbcType.VARCHAR),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR),
            @Result(column="html_content", property="htmlContent", jdbcType=JdbcType.LONGVARCHAR),
            @Result(column="avator", property="user.avator", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="user.email", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="user.name", jdbcType=JdbcType.VARCHAR),
            @Result(column="update_time", property="user.updateTime", jdbcType=JdbcType.VARCHAR),
            @Result(column="username", property="user.username", jdbcType=JdbcType.VARCHAR)

    })
    List<Blog> selectAll();
}