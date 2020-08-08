package cn.gdut.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Desctiption TODO
 * @Date 2019/11/18/018
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryPage implements Serializable {

    private int page;
    private int limit;
}
