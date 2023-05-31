package com.ebanma.cloud.lambda;

import com.alibaba.fastjson.JSON;
import com.ebanma.cloud.lambda.cart.*;
import org.junit.Test;

import java.util.List;

public class Version6Test {

    @Test
    public void filterSkus() {
        List<Sku> cartSkuList = BaseCartService.getCartSkuList();

        // 过滤商品总价大于3000的商品
        List<Sku> result = CartV4Service.filterSkus(
                cartSkuList, (Sku sku) -> sku.getTotalPrice()>3000 );


        System.out.println(JSON.toJSONString(
                result, true));
    }

    @Test
    public void filterSkusWithT(){
        List<Sku> cartSkuList = BaseCartService.getCartSkuList();
        List<Sku> result = CartV7Service.filter(
                cartSkuList, (Sku sku) -> sku.getTotalPrice()>3000 );
        System.out.println(JSON.toJSONString(
                result, true));
    }

}