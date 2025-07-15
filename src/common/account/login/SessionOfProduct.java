package common.account.login;

import common.database.model.CustomerModel;
import common.database.model.ProductModel;

public class SessionOfProduct {
	// 현재 선택된 상품을 저장하는 클래스입니다.
	
	private static ProductModel currProduct;

    public static void setProduct(ProductModel product) {
        currProduct = product;
    }

    public static ProductModel getProduct() {
        return currProduct;
    }

    public static void clear() {
        currProduct = null;
    }
}
