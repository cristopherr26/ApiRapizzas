package co.edu.uco.rapizzas.business.domain;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.IntegerHelper;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class ProductDomain extends Domain {
	
	private String productName;
	private int price;
	private CategoryDomain category;
	private SizeDomain size;
	
	public ProductDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setProductName(TextHelper.getDefault());
		setPrice(IntegerHelper.getDefault());
		setCategory(CategoryDomain.getDefaultValue());
		setSize(SizeDomain.getDefaultValue());
	}
	
	public ProductDomain(final UUID id) {
		super(id);
		setProductName(TextHelper.getDefault());
		setPrice(IntegerHelper.getDefault());
		setCategory(CategoryDomain.getDefaultValue());
		setSize(SizeDomain.getDefaultValue());
	}
	
	

	public ProductDomain(final UUID id, final String productName, final int price, final CategoryDomain category, final SizeDomain size) {
		super(id);
		setProductName(productName);
		setPrice(price);
		setCategory(category);
	    setSize(size);
	}
	
	static ProductDomain getDeafaultValue() {
		return new ProductDomain();
	}
	
	static ProductDomain getDefaultvalue(final ProductDomain product) {
		return ObjectHelper.getDefault(product, getDeafaultValue());
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

	public CategoryDomain getCategory() {
		return category;
	}

	public void setCategory(final CategoryDomain category) {
		this.category = ObjectHelper.getDefault(category, CategoryDomain.getDefaultValue());
	}

	public SizeDomain getSize() {
		return size;
	}

	public void setSize(final SizeDomain size) {
		this.size = ObjectHelper.getDefault(size, SizeDomain.getDefaultValue());
	}
	
	
	
	
	
	
	
	

}
