package employee.product.model;

import java.io.File;
import java.util.List;

import common.database.model.ProductModel;

/***
 * 이곳에서 프로그램이 살아있는 동안 상품페이지에 필요한 정보들을 담아둔다.
 */
public class ProductInfoModel {
	private int productId = 0;
	private File imageFile;
	private ProductModel product;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public File getImageFile() {
		return imageFile;
	}

	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	
}
