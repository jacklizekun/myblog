package cn.gdut.common.utils;

import cn.gdut.system.entity.dto.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desctiption TODO
 * @Date 2019/11/25/025
 **/
public class TreeUtils {

    /**
     * 构建一颗树
     * @param nodes
     * @param <T>
     * @return
     */
    public static <T> List<Tree<T> > build(List<Tree<T>> nodes){
        // 为空，直接返回
        if (nodes == null){
            return new ArrayList<>();
        }
        List<Tree<T>> tree = new ArrayList<>();
        // 遍历节点
        nodes.forEach(node -> {
            // 获取父节点
            Long pId = node.getPId();
            if (pId == null || pId.equals(0L)){
                tree.add(node);
                return;
            }
            // 否则，遍历
            for (Tree<T> c : nodes){
                Long id = c.getId();
                if (id != null && id.equals(pId)){
                    c.getChildren().add(node);
                    return;
                }
            }
        } );
        return tree;
    }
}
