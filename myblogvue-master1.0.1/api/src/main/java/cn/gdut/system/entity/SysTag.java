package cn.gdut.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Desctiption TODO
 * @Date 2019/11/18/018
 **/
@Data
@TableName("tb_tag")
public class SysTag implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
}
