package com.ebanma.cloud.stream;

import com.alibaba.fastjson.JSON;
import com.ebanma.cloud.lambda.cart.BaseCartService;
import com.ebanma.cloud.lambda.cart.Sku;
import com.ebanma.cloud.lambda.cart.SkuCategoryEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class StreamOperator {
  
    List<Sku> list;
  
    @Before
    public void init() {  
        list = BaseCartService.getCartSkuList();
    }  
 
    @Test
    public void filterTest() {  
        list.stream()  
                .filter(sku ->  
                        SkuCategoryEnum.BOOKS
                                .equals(sku.getSkuCategory()))  
                .forEach(item ->  
                        System.out.println(  
                                JSON.toJSONString(
                                        item, true)));
    }
}