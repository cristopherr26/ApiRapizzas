package co.edu.uco.rapizzas.entity;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.IntegerHelper;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class ProductEntity{
	
	private UUID productId;
	private String productName;
	private int price;
	private CategoryEntity category;
	private SizeEntity size;
	
	public ProductEntity() {
		setProductId(UUIDHelper.getUUIDHelper().getDefault());
		setProductName(TextHelper.getDefault());
		setPrice(IntegerHelper.getDefault());
		setCategory(CategoryEntity.getDefaultValue());
		setSize(SizeEntity.getDefaultValue());
	}
	
	public ProductEntity(final UUID id) {
		setProductId(id);
		setProductName(TextHelper.getDefault());
		setPrice(IntegerHelper.getDefault());
		setCategory(CategoryEntity.getDefaultValue());
		setSize(SizeEntity.getDefaultValue());
	}
	
	public ProductEntity(final UUID id, final String productName, final int price, 
			final CategoryEntity category, final SizeEntity size) {
		setProductId(id);
		setProductName(productName);
		setPrice(price);
		setCategory(category);
	    setSize(size);
	}
	
	static ProductEntity getDefaultValue() {
		return new ProductEntity();
	}
	
	static ProductEntity getDefaultValue(final ProductEntity product) {
		return ObjectHelper.getDefault(product, getDefaultValue());
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

	public UUID getProductId() {
		return productId;
	}

	public void setProductId(final UUID productId) {
		this.productId = UUIDHelper.getUUIDHelper().getDefault(productId);
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(final CategoryEntity category) {
		this.category = CategoryEntity.getDefaultValue(category);
	}

	public SizeEntity getSize() {
		return size;
	}

	public void setSize(final SizeEntity size) {
		this.size = SizeEntity.getDefaultValue(size);
	}

}
