package com.order.webservice.common.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.order.webservice.domain.vo.PageResponseVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * created by zhangdingping at 2019/10/29
 */
public class CommonConverter {

    public static <IN, OUT> List<OUT> convertList(List<IN> inList, Function<IN, OUT> mapper) {

        List<OUT> outList = new ArrayList<>(inList.size());
        inList.forEach(in -> outList.add(mapper.apply(in)));
        return outList;
    }

    public static <T> T map2Object(Map<String, Object> map, Class<T> beanClass) {

        if (map == null)
            return null;

        T obj;
        try {
            obj = beanClass.newInstance();
            BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
            BeanUtils.populate(obj, map);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static <T> PageResponseVo<T> toPage(IPage<T> page) {

        PageResponseVo<T> vo = new PageResponseVo<>();
        vo.setNumber(page.getCurrent());
        vo.setSize(page.getSize());
        vo.setTotalElements(page.getTotal());
        vo.setTotalPages(page.getPages());
        /*vo.setFirst(page.hasPrevious());
        vo.setLast(page.hasNext());*/
        vo.setContent(page.getRecords());
        return vo;
    }
}
