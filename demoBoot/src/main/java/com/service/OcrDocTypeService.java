package com.service;


import com.bean.ApiResult;

/**
 * @auther jiajun.xiong
 * @Description 证件类型服务接口
 * @Date 2020/10/12 15:20
 */
public interface OcrDocTypeService {



    /**
     *
     * @description: 查询文档类型树

     * @return: com.hex.goframe.base.model.ApiResult
     * @author: youlan.zhu
     * @date: 2022/5/7 9:16
     */
    ApiResult selectDocTypeTree();

}
