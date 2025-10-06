package co.edu.uco.rapizzas.entity;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.IntegerHelper;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class ProductOrderEntity{
	
	private  UUID productOrderId;
	private int amount;
	private ProductEntity product;
	private OrderEntity order;
	
	public ProductOrderEntity() {
		setProductOrderId(UUIDHelper.getUUIDHelper().getDefault());
		setAmount(IntegerHelper.getDefault());
		setProduct(ProductEntity.getDefaultValue());
		setOrder(OrderEntity.getDefaultValue());
	}

	public ProductOrderEntity(final UUID id) {
		setProductOrderId(id);
		setAmount(IntegerHelper.getDefault());
		setProduct(ProductEntity.getDefaultValue());
		setOrder(OrderEntity.getDefaultValue());
	}

	public ProductOrderEntity(final UUID id, final int amount, final ProductEntity product, final OrderEntity order) {
		setProductOrderId(id);
		setAmount(amount);
		setProduct(product);
		setOrder(order);
	}
	
	static ProductOrderEntity getDefaultValue() {
		return new ProductOrderEntity();
	}
	
	static ProductOrderEntity getDefaultValue(final ProductOrderEntity productOrder) {
		return ObjectHelper.getDefault(productOrder, getDefaultValue());
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(final int amount) {
		this.amount = IntegerHelper.getDefault(amount);
	}

	public UUID getProductOrderId() {
		return productOrderId;
	}

	public void setProductOrderId(final UUID productOrderId) {
		this.productOrderId = UUIDHelper.getUUIDHelper().getDefault(productOrderId);
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(final ProductEntity product) {
		this.product = ProductEntity.getDefaultValue(product);
	}

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(final OrderEntity order) {
		this.order = OrderEntity.getDefaultValue(order);
	}

	
	
	

}
