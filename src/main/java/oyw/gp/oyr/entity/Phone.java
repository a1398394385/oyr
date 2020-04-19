package oyw.gp.oyr.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("phone")
public class Phone extends Model<Phone> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 型号
     */
    @TableField("model")
    private String model;

    /**
     * 颜色
     */
    @TableField("color")
    private String color;

    /**
     * 存储规格
     */
    @TableField("storage")
    private String storage;

    /**
     * 价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 图片
     */
    @TableField("image")
    private String image;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
