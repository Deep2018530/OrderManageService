package com.order.webservice.domain.vo;

import com.order.webservice.common.converter.CommonConverter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.function.Function;

/**
 * created by zhangdingping at 2019/10/29
 */
@Data
@ApiModel(description = "分页数据相关响应VO")
public class PageResponseVo<T> {

    @ApiModelProperty(value = "第几页")
    private long number;

    @ApiModelProperty(value = "每页条数")
    private long size;

    @ApiModelProperty(value = "总数")
    private long totalElements;

    @ApiModelProperty(value = "总页数")
    private long totalPages;

//    @ApiModelProperty(value = "是否存在上一页")
//    private boolean first;
//
//    @ApiModelProperty(value = "是否存在下一页")
//    private boolean last;

    @ApiModelProperty(value = "内容/数据")
    private List<T> content;

    public <E> PageResponseVo<E> map(Function<T, E> mapper) {

        PageResponseVo<E> newVo = new PageResponseVo<>();
        newVo.setContent(CommonConverter.convertList(this.content, mapper));
//        newVo.setFirst(this.first);
//        newVo.setLast(this.last);
        newVo.setNumber(this.number);
        newVo.setSize(this.size);
        newVo.setTotalElements(this.totalElements);
        newVo.setTotalPages(this.totalPages);
        return newVo;
    }

}
