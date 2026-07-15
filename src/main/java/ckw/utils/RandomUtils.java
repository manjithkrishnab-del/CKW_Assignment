package ckw.utils;

import ckw.models.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RandomUtils {

    public static List<Product> getRandomProducts(int min, int max) {

        List<Product> products = Arrays.asList(
                Product.BACKPACK,
                Product.BIKE_LIGHT,
                Product.BOLT_TSHIRT,
                Product.FLEECE,
                Product.ONESIE
        );

        Collections.shuffle(products);

        int count = min + (int) (Math.random() * (max - min + 1));

        return products.subList(0, count);
    }
    public static List<Product> getRandomProductsExcluding(
            List<Product> excludedProducts,
            int count) {

        List<Product> availableProducts =
                new ArrayList<>(Arrays.asList(Product.values()));

        availableProducts.removeAll(excludedProducts);

        Collections.shuffle(availableProducts);

        return availableProducts.subList(
                0,
                Math.min(count, availableProducts.size())
        );
    }
}