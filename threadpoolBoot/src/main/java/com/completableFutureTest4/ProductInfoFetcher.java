package com.completableFutureTest4;

/**
 * @Author ming.li
 * @Date 2025/4/10 14:06
 * @Version 1.0
 */
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// 模拟获取商品基本信息
class ProductBasicInfoService {
    public static CompletableFuture<String> getBasicInfo(String productId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // 模拟耗时操作
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Product " + productId + " basic info: This is a great product.";
        });
    }
}

// 模拟获取商品价格信息
class ProductPriceService {
    public static CompletableFuture<String> getPriceInfo(String productId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // 模拟耗时操作
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Product " + productId + " price info: $99.99";
        });
    }
}

// 模拟获取商品库存信息
class ProductStockService {
    public static CompletableFuture<String> getStockInfo(String productId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // 模拟耗时操作
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Product " + productId + " stock info: 10 in stock";
        });
    }
}

public class ProductInfoFetcher {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String productId = "P001";

        // 异步获取商品基本信息
        CompletableFuture<String> basicInfoFuture = ProductBasicInfoService.getBasicInfo(productId);
        // 异步获取商品价格信息
        CompletableFuture<String> priceInfoFuture = ProductPriceService.getPriceInfo(productId);
        // 异步获取商品库存信息
        CompletableFuture<String> stockInfoFuture = ProductStockService.getStockInfo(productId);

        // 等待所有任务完成并组合结果
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(basicInfoFuture, priceInfoFuture, stockInfoFuture);

        // 当所有任务完成后，处理结果
        CompletableFuture<String> combinedFuture = allFutures.thenApply(v -> {
            try {
                return basicInfoFuture.get() + "\n" + priceInfoFuture.get() + "\n" + stockInfoFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return null;
            }
        });

        // 打印最终结果
        combinedFuture.thenAccept(result -> System.out.println(result));

        // 为了让主线程等待异步任务完成
        try {
            allFutures.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start));
    }
}
