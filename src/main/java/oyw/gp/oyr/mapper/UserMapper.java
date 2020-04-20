package oyw.gp.oyr.mapper;

import org.apache.ibatis.annotations.*;
import oyw.gp.oyr.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author OuYangWei
 * @since 2020-04-19
 */
public interface UserMapper extends BaseMapper<User>
{

    @Select("select * from user where username=#{username}")
    @Results({
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password"),
            @Result(column = "telphone",property = "telphone"),
            @Result(column = "address",property = "address"),
            @Result(column = "create_time",property = "createTime")
    })
     User findUserByName(@Param("username")String username);

    @Insert("insert into user values(#{id},#{username},#{password},#{telphone},#{address},#{createTime})")
     void insertUser(User user);
}
