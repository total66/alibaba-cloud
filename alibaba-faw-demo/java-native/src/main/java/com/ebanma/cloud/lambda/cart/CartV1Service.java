package com.ebanma.cloud.lambda.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 购物车服务类
 */
public class CartV1Service {

    // 加入到购物车中的商品信息
    private static List<Sku> cartSkuList =
            new ArrayList<Sku>(){
        {
            add(new Sku(654032, "无人机",
                    4999, 1,
                    4999, SkuCategoryEnum.ELECTRONICS));

            add(new Sku(642934, "VR一体机",
                    2299, 1,
                    2299, SkuCategoryEnum.ELECTRONICS));

            add(new Sku(645321, "纯色衬衫",
                    409, 3,
                    1227, SkuCategoryEnum.CLOTHING));

            add(new Sku(654327, "牛仔裤",
                    528, 1,
                    528, SkuCategoryEnum.CLOTHING));

            add(new Sku(675489, "跑步机",
                    2699, 1,
                    2699, SkuCategoryEnum.SPORTS));

            add(new Sku(644564, "Java编程思想",
                    79, 1,
                    79, SkuCategoryEnum.BOOKS));

            add(new Sku(678678, "Java核心技术",
                    149, 1,
                    149, SkuCategoryEnum.BOOKS));

            add(new Sku(697894, "数据结构与算法",
                    78, 1,
                    78, SkuCategoryEnum.BOOKS));

            add(new Sku(696968, "TensorFlow进阶指南",
                    85, 1,
                    85, SkuCategoryEnum.BOOKS));
        }
    };

    /**
     * 获取商品信息列表
     * @return
     */
    public static List<Sku> getCartSkuList() {
        return cartSkuList;
    }

    /**
     * Version 1.0
     * 找出购物车中所有电子产品
     * @param cartSkuList
     * @return
     */
    public static List<Sku> filterElectronicsSkus(
            List<Sku> cartSkuList) {

        List<Sku> result = new ArrayList<Sku>();
        for (Sku sku: cartSkuList) {
            // 如果商品类型 等于 电子类
            if (SkuCategoryEnum.ELECTRONICS.
                    equals(sku.getSkuCategory())) {
                result.add(sku);
            }
        }
        return result;
    }

}