package com.ebanma.cloud.lambda;

import com.alibaba.fastjson.JSON;
import com.ebanma.cloud.lambda.cart.BaseCartService;
import com.ebanma.cloud.lambda.cart.CartV3Service;
import com.ebanma.cloud.lambda.cart.Sku;
import org.junit.Test;

import java.util.List;

/**
 * @author yuqintao
 * @version $ Id: com.ebanma.cloud.lambda.Version3Test, v 0.1 2023/02/21 15:30 banma-0018 Exp $
 */
public class Version3Test {

    @Test
    public void filterSkus() {
        List<Sku> cartSkuList = BaseCartService.getCartSkuList();

        // 根据商品总价过滤超过2000元的商品列表
        List<Sku> result = CartV3Service.filterSkus(
                cartSkuList, null,
                2000, false);

        System.out.println(JSON.toJSONString(
                result, true));
    }

}
