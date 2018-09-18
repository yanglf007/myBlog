package com.yanglf.usermanage.dao;

import com.yanglf.usermanage.domain.Authority;
import com.yanglf.usermanage.domain.UserAuthority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface UserAuthorityMapper {
    int insert(UserAuthority record);

    int insertSelective(UserAuthority record);
    @Select({
            "select",
            "id, name",
            "from authority a,user_authority b",
            "where a.id=b.authority_id and b.id = #{id,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    List<Authority> selectByuserId(Integer id);

}