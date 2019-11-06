package com.order.webservice.domain.po.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * created by zhangdingping at 2019/10/23
 */

@Data
public class Role {

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    private String name;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime lastTime;
}
