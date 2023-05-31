package com.ebanma.cloud.lambda;

import com.alibaba.fastjson.JSON;
import com.ebanma.cloud.lambda.cart.CartV1Service;
import com.ebanma.cloud.lambda.cart.Sku;
import org.junit.Test;

import java.util.List;

/**
 * @author yuqintao
 * @version $ Id: com.ebanma.cloud.lambda.Version1Test, v 0.1 2023/02/21 15:15 banma-0018 Exp $
 */
public class Version1Test {

    @Test
    public void filterElectronicsSkus() {
        List<Sku> cartSkuList = CartV1Service.getCartSkuList();

        // 查找购物车中数码类商品
        List<Sku> result =
                CartV1Service.filterElectronicsSkus(cartSkuList);

        System.out.println(
                JSON.toJSONString(result, true));
    }

}
