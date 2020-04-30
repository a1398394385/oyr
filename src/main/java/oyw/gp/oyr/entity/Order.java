package oyw.gp.oyr.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2020-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order")
public class Order extends Model<Order> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 回收价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 手机号
     */
    @TableField("telephone")
    private String telephone;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 订单状态
     */
    @TableField("state")
    private Boolean state;

    /**
     * 手机id
     */
    @TableField("phone_id")
    private Long phoneId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
