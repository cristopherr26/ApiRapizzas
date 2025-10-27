package co.edu.uco.rapizzas.business.domain;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.IntegerHelper;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class ProductOrderDomain extends Domain {
	
	private int amount;
	private ProductDomain product;
	private OrderDomain order;
	
	ProductOrderDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setAmount(IntegerHelper.getDefault());
		setProduct(ProductDomain.getDefaultValue());
		setOrder(OrderDomain.getDefaultValue());
	}

	public ProductOrderDomain(final UUID id) {
		super(id);
		setAmount(IntegerHelper.getDefault());
		setProduct(ProductDomain.getDefaultValue());
		setOrder(OrderDomain.getDefaultValue());
	}

	public ProductOrderDomain(final UUID id, final int amount, 
			final ProductDomain product, final OrderDomain order) {
		super(id);
		setAmount(amount);
		setProduct(product);
		setOrder(order);
	}
	
	protected static ProductOrderDomain getDefaultValue() {
		return new ProductOrderDomain();
	}
	
	static ProductOrderDomain getDefaultValue(final ProductOrderDomain productOrder) {
		return ObjectHelper.getDefault(productOrder, getDefaultValue());
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(final int amount) {
		this.amount = IntegerHelper.getDefault(amount);
	}

	public ProductDomain getProduct() {
		return product;
	}

	public void setProduct(final ProductDomain product) {
		this.product = ProductDomain.getDefaultValue(product);
	}

	public OrderDomain getOrder() {
		return order;
	}

	public void setOrder(final OrderDomain order) {
		this.order = OrderDomain.getDefaultValue(order);
	}

}
