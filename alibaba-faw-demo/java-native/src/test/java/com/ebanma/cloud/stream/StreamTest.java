package com.ebanma.cloud.stream;

import com.alibaba.fastjson.JSON;
import com.ebanma.cloud.lambda.cart.BaseCartService;
import com.ebanma.cloud.lambda.cart.Sku;
import com.ebanma.cloud.lambda.cart.SkuCategoryEnum;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author yuqintao
 * @version $ Id: Test, v 0.1 2023/02/21 16:38 banma-0018 Exp $
 */
public class StreamTest {

    @Test
    public void oldCartHandle() {
        List<Sku> cartSkuList = BaseCartService.getCartSkuList();

        // 1 打印所有商品
        for (Sku sku: cartSkuList) {
            System.out.println(JSON.toJSONString(sku, true));
        }

        // 2 图书类过滤掉
        List<Sku> notBooksSkuList = new ArrayList<Sku>();
        for (Sku sku: cartSkuList) {
            if (!SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory())) {
                notBooksSkuList.add(sku);
            }
        }

        // 排序
        notBooksSkuList.sort(new Comparator<Sku>() {
            @Override
            public int compare(Sku sku1, Sku sku2) {
                if (sku1.getTotalPrice() > sku2.getTotalPrice()) {
                    return -1;
                } else if (sku1.getTotalPrice() < sku2.getTotalPrice()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        // TOP2
        List<Sku> top2SkuList = new ArrayList<Sku>();
        for (int i = 0; i < 2; i++) {
            top2SkuList.add(notBooksSkuList.get(i));
        }

        // 4 求两件商品的总价
        Double money = 0.0;
        for (Sku sku: top2SkuList) {
            // money = money + sku.getTotalPrice();
            money += sku.getTotalPrice();
        }

        // 获取两件商品的名称
        List<String> resultSkuNameList = new ArrayList<String>();
        for (Sku sku: top2SkuList) {
            resultSkuNameList.add(sku.getSkuName());
        }

        System.out.println(
                JSON.toJSONString(resultSkuNameList, true));
        System.out.println("商品总价：" + money);
    }

    @Test
    public void newCartHandle() {
        AtomicReference<Double> money =
                new AtomicReference<>(Double.valueOf(0.0));
        List<String> resultSkuNameList =
                BaseCartService.getCartSkuList()
                        .stream()
                        // 1 打印商品信息
                        .peek(sku1 -> System.out.println(
                                JSON.toJSONString(sku1, true)))
                        .peek(opq->System.out.println("-------------"))
                        // 2 过滤掉所有图书类商品
                        .filter(sku -> !SkuCategoryEnum.BOOKS.equals(
                                sku.getSkuCategory()))
                        .filter(sku -> !SkuCategoryEnum.SPORTS.equals(
                                sku.getSkuCategory()))
                        // 排序
                        .sorted(Comparator.
                                comparing(Sku::getTotalPrice).reversed())
                        // TOP2
                        .limit(2)
                        // 累加商品总金额
                        .peek(sku -> money.set(money.get() + sku.getTotalPrice()))
                        // 获取商品名称
                        .map(sku -> sku.getSkuName())
                        // 收集结果
                        .collect(Collectors.toList());

        System.out.println(
                JSON.toJSONString(resultSkuNameList, true));
        System.out.println("商品总价：" + money.get());
    }

}


