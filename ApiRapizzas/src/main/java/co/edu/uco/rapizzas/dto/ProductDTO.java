package co.edu.uco.rapizzas.dto;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.IntegerHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public class ProductDTO {
	
	private UUID productId;
	private String productName;
	private int price;
	private CategoryDTO category;
	private SizeDTO size;
	
	public ProductDTO() {
		setProductId(UUIDHelper.getUUIDHelper().getDefault());
		setProductName(TextHelper.getDefault());
		setPrice(IntegerHelper.getDefault());
		setCategory(CategoryDTO.getDefaultValue());
		setSize(SizeDTO.getDefaultValue());
	}
	
	public ProductDTO(final UUID productId) {
		setProductId(productId);
		setProductName(TextHelper.getDefault());
		setPrice(IntegerHelper.getDefault());
		setCategory(CategoryDTO.getDefaultValue());
		setSize(SizeDTO.getDefaultValue());
	}
	
	public ProductDTO(final UUID productId, final String productName, final int price, 
			final CategoryDTO category, final SizeDTO size) {
		setProductId(productId);
		setProductName(productName);
		setPrice(price);
		setCategory(category);
	    setSize(size);
	}
	
	static ProductDTO getDefaultValue() {
		return new ProductDTO();
	}
	
	static ProductDTO getDefaultValue(final ProductDTO product) {
		return ObjectHelper.getDefault(product, getDefaultValue());
	}
	
	public UUID getProductId() {
		return productId;
	}
	
	public void setProductId(final UUID productId) {
		this.productId = ObjectHelper.getDefault(productId, UUIDHelper.getUUIDHelper().getDefault());
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(final String productName) {
		this.productName = TextHelper.getDefaultWithTrim(productName);
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(final int price) {
		this.price = IntegerHelper.getDefault(price);
	}
	
	public CategoryDTO getCategory() {
		return category;
	}
	
	public void setCategory(final CategoryDTO category) {
		this.category = CategoryDTO.getDefaultValue(category);
	}
	
	public SizeDTO getSize() {
		return size;
	}
	
	public void setSize(final SizeDTO size) {
		this.size = SizeDTO.getDefaultValue(size);
	}
	
}
