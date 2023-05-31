package com.ebanma.cloud.lambda;

import com.alibaba.fastjson.JSON;
import com.ebanma.cloud.lambda.cart.BaseCartService;
import com.ebanma.cloud.lambda.cart.CartV2Service;
import com.ebanma.cloud.lambda.cart.Sku;
import com.ebanma.cloud.lambda.cart.SkuCategoryEnum;
import org.junit.Test;

import java.util.List;

/**
 * @author yuqintao
 * @version $ Id: com.ebanma.cloud.lambda.Version2Test, v 0.1 2023/02/21 15:24 banma-0018 Exp $
 */
public class Version2Test {

    @Test
    public void filterSkusByCategory() {
        List<Sku> cartSkuList = BaseCartService.getCartSkuList();

        // 查找购物车中图书类商品集合
        List<Sku> result = CartV2Service.filterSkusByCategory(
                cartSkuList, SkuCategoryEnum.BOOKS);

        System.out.println(JSON.toJSONString(
                result, true));
    }

}
