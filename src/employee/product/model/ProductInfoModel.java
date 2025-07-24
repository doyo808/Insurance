package employee.product.model;

import java.io.File;
import java.util.List;

import common.database.model.ProductCoverageDetailModel;
import common.database.model.ProductModel;

/***
 * 이곳에서 프로그램이 살아있는 동안 상품페이지에 필요한 정보들을 담아둔다.
 */
public class ProductInfoModel {
	private int productId = 0;
	private File imageFile;
	private ProductModel product;
	private List<ProductModel> products;
	private List<ProductCoverageDetailModel> covers;

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

	public List<ProductCoverageDetailModel> getCovers() {
		return covers;
	}

	public void setCovers(List<ProductCoverageDetailModel> covers) {
		this.covers = covers;
	}

	public List<ProductModel> getProducts() {
		return products;
	}

	public void setProducts(List<ProductModel> products) {
		this.products = products;
	}

	
}
