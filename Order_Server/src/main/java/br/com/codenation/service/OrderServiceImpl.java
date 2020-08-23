package br.com.codenation.service;

import java.util.*;
import java.util.stream.Collectors;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

public class OrderServiceImpl implements OrderService {

	private ProductRepository productRepository = new ProductRepositoryImpl();

	private List<Optional<OrderItem>> getOptionalListOrderItem(List<OrderItem> items) {
		return items.stream()
				.map(Optional::ofNullable)
				.collect(Collectors.toList());
	}

	private Double getSumValueOfEachOrderItem(OrderItem item) {
		Optional<Product> optionalProduct = this.productRepository.findById(item.getProductId());
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			Double productValue = Optional.ofNullable(product.getValue()).isPresent() ?
					(product.getIsSale() ? product.getValue() * 0.8 : product.getValue()) : 0.0;
			return Optional.ofNullable(item.getQuantity()).isPresent() ?
					(item.getQuantity() * productValue) : 0.0 * productValue;
		}
		return 0.0;
	}

	/**
	 * Calculate the sum of all OrderItems
	 */
	@Override
	public Double calculateOrderValue(List<OrderItem> items) {
		return Optional.ofNullable(items).isPresent() ?
				getOptionalListOrderItem(items).stream()
						.filter(Optional::isPresent)
						.map(Optional::get)
						.map(this::getSumValueOfEachOrderItem)
						.reduce(0.0, Double::sum) : 0.0;
	}

	/**
	 * Map from idProduct List to Product Set
	 */
	@Override
	public Set<Product> findProductsById(List<Long> ids) {
		return Optional.ofNullable(ids).isPresent() ?
				ids.stream()
						.map(id -> this.productRepository.findById(id))
						.filter(Optional::isPresent)
						.map(Optional::get)
						.collect(Collectors.toSet()) : new HashSet<>();
	}

	/**
	 * Calculate the sum of all Orders(List<OrderIten>)
	 */
	@Override
	public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
		return Optional.ofNullable(orders).isPresent() ? orders.stream()
				.map(this::calculateOrderValue)
				.reduce(0.0, Double::sum) : 0.0;
	}

	/**
	 * Group products using isSale attribute as the map key
	 */
	@Override
	public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {

		return Optional.ofNullable(productIds).isPresent() ?
				findProductsById(productIds).stream()
						.collect(Collectors.groupingBy(Product::getIsSale))
				: new HashMap<>();
	}
}