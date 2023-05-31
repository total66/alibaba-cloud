package com.ebanma.cloud.lambda;

import com.alibaba.fastjson.JSON;
import com.ebanma.cloud.lambda.cart.*;
import org.junit.Test;

import java.util.List;

public class Version5Test {

    @Test
    public void filterSkus() {
        List<Sku> cartSkuList = BaseCartService.getCartSkuList();

        // 过滤服装类商品
        List<Sku> result = CartV4Service.filterSkus(
                cartSkuList, new SkuPredicate() {
                    @Override
                    public boolean test(Sku sku) {
                        return sku.getSkuCategory().equals(SkuCategoryEnum.CLOTHING);
                    }
                });

        System.out.println(JSON.toJSONString(
                result, true));
    }

}