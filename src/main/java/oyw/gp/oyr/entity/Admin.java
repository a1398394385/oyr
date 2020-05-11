package oyw.gp.oyr.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author OuYangWei
 * @since 2020-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("admin")
public class Admin extends Model<Admin> {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 管理员name
     */
    @TableField("name")
    private String name;

    /**
     * 管理员密码
     */
    @TableField("password")
    private String password;

    /**
     * 管理员权限，0：无权限、1：普通权限、2：超级权限
     */
    @TableField("authority")
    private String authority;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    /**
     * @param name
     * @param password
     * @param authority
     */
    public Admin(String name, String password, String authority) {
        this.name = name;
        this.password = password;
        this.authority = authority;
    }

    public Admin() {
    }

}
