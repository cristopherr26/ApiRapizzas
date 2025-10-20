package co.edu.uco.rapizzas.dto;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.crosscuting.helper.IntegerHelper;

public final class ProductOrderDTO {
	
	private UUID productOrderId;
	private int amount;
	private ProductDTO product;
	private OrderDTO order;
	
	public ProductOrderDTO() {
		setProductOrderId(UUIDHelper.getUUIDHelper().getDefault());
		setAmount(IntegerHelper.getDefault());
		setProduct(ProductDTO.getDefaultValue());
		setOrder(OrderDTO.getDefaultValue());
	}
	
	public ProductOrderDTO(final UUID productOrderId) {
		setProductOrderId(productOrderId);
		setAmount(IntegerHelper.getDefault());
		setProduct(ProductDTO.getDefaultValue());
		setOrder(OrderDTO.getDefaultValue());
	}
	
	public ProductOrderDTO(final UUID productOrderId, final int amount, 
			final ProductDTO product, final OrderDTO order) {
		setProductOrderId(productOrderId);
		setAmount(amount);
		setProduct(product);
		setOrder(order);
	}
	
	static ProductOrderDTO getDefaultValue() {
		return new ProductOrderDTO();
	}
	
	static ProductOrderDTO getDefaultValue(final ProductOrderDTO productOrder) {
		return ObjectHelper.getDefault(productOrder, getDefaultValue());
	}
	
	public UUID getProductOrderId() {
		return productOrderId;
	}
	
	public void setProductOrderId(final UUID productOrderId) {
		this.productOrderId = UUIDHelper.getUUIDHelper().getDefault(productOrderId);
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(final int amount) {
		this.amount = IntegerHelper.getDefault(amount);
	}
	
	public ProductDTO getProduct() {
		return product;
	}
	
	public void setProduct(final ProductDTO product) {
		this.product = ProductDTO.getDefaultValue(product);
	}
	
	public OrderDTO getOrder() {
		return order;
	}
	
	public void setOrder(final OrderDTO order) {
		this.order = OrderDTO.getDefaultValue(order);
	}

}
