package oyw.gp.oyr.entity;

import java.math.BigDecimal;
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
 * @since 2020-05-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("orders")
public class Orders extends Model<Orders> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 手机号
     */
    @TableField("telephone")
    private String telephone;

    /**
     * 回收价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 订单状态 0：订单创立 1：上门取货 2：收到手机 3：订单完成 4：退货中 5：退货完成
     */
    @TableField("state")
    private String state;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 手机id
     */
    @TableField("phone_id")
    private Long phoneId;

    /**
     * 手机型号
     */
    @TableField(exist = false)
    private String model;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
