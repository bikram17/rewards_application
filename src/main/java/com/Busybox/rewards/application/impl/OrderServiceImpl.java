/*
 * package com.Busybox.rewards.application.impl;
 * 
 * import java.util.ArrayList; import java.util.HashMap; import java.util.List;
 * import java.util.Map;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.stereotype.Service;
 * 
 * import com.Busybox.rewards.application.controller.ResponseHandler; import
 * com.Busybox.rewards.application.controller.UniqueIdGenerator; import
 * com.Busybox.rewards.application.dao.InvoiceRepository; import
 * com.Busybox.rewards.application.dao.OrderDao; import
 * com.Busybox.rewards.application.dao.ProductListingRepository; import
 * com.Busybox.rewards.application.model.CustomerModel; import
 * com.Busybox.rewards.application.model.Invoice; import
 * com.Busybox.rewards.application.model.OrderModel; import
 * com.Busybox.rewards.application.model.ProductListing; import
 * com.Busybox.rewards.application.service.CustomerService; import
 * com.Busybox.rewards.application.service.OrderService;
 * 
 * import jakarta.persistence.criteria.Order;
 * 
 * @Service
 * 
 * public class OrderServiceImpl implements OrderService {
 * 
 * @Autowired OrderDao orderdao;
 * 
 * @Autowired private InvoiceRepository invoicerepo;
 * 
 * @Autowired private ProductListingRepository productlistingrepo;
 * 
 * @Autowired private CustomerService customerService;
 * 
 * @Override public ResponseEntity<?> saveOrder(OrderModel ordermodel) {
 * 
 * return ResponseHandler.generateResponse(orderdao.save(ordermodel),HttpStatus.
 * OK,"Order Placed Successfully"); }
 * 
 * 
 * public ResponseEntity<?> placeMultipleOrders(List<OrderModel> orderModels) {
 * List<Order> orders = new ArrayList<>(); List<String> uniqueIds =
 * UniqueIdGenerator.generateUniqueIds();
 * 
 * if (uniqueIds.isEmpty()) { return ResponseHandler.generateResponse(null,
 * HttpStatus.INTERNAL_SERVER_ERROR, "Order Not Placed"); }
 * 
 * String order_id = uniqueIds.get(0); String invoice_id = uniqueIds.get(1);
 * 
 * double totalOrderAmount = 0.0; // Initialize total order amount
 * 
 * for (OrderModel orderModel : orderModels) { CustomerModel customer =
 * customerService.getCustomerByPhoneNumber(orderModel.getCustomer_mobno()); if
 * (customer == null) { return ResponseHandler.generateResponse(null,
 * HttpStatus.NOT_FOUND, "Customer Not Found"); }
 * 
 * ProductListing product =
 * productlistingrepo.findByProductId(orderModel.getProduct_id()); if (product
 * == null) { return ResponseHandler.generateResponse(null,
 * HttpStatus.NOT_FOUND, "Product not found"); }
 * 
 * double productPrice = product.getProductPrice();
 * orderModel.setAmount(productPrice); orderModel.setOrder_id(order_id);
 * orderModel.setInvoice_id(invoice_id);
 * 
 * OrderModel order = new OrderModel(); // Map orderModel fields to order fields
 * // Example: order.setOrderId(orderModel.getOrderId()); // ... //
 * orders.add(order); // Add the product price to the total order amount
 * totalOrderAmount += productPrice;
 * 
 * Invoice invoice = new Invoice(); invoice.setInvoiceId(invoice_id);
 * invoice.setItemId(orderModel.getProduct_id());
 * invoice.setAmount(productPrice); invoicerepo.save(invoice); }
 * 
 * orderdao.saveAll(orders);
 * 
 * // Build the response map with header and orders Map<String, Object> response
 * = new HashMap<>(); response.put("header", getHeaderInfo(orderModels.get(0)));
 * // Assuming all orders have the same header response.put("orders",
 * orderModels); response.put("totalOrderAmount", totalOrderAmount); // Add
 * total order amount to the response
 * 
 * return ResponseHandler.generateResponse(response, HttpStatus.OK,
 * "Orders Placed Successfully"); }
 * 
 * // Define a method to get the header info using the first orderModel private
 * Map<String, Object> getHeaderInfo(OrderModel orderModel) { Map<String,
 * Object> headerInfo = new HashMap<>(); headerInfo.put("customer_mobno",
 * orderModel.getCustomer_mobno()); headerInfo.put("request_id",
 * orderModel.getRequest_id()); headerInfo.put("customer_name",
 * orderModel.getCustomer_name()); headerInfo.put("shipping_address",
 * orderModel.getShipping_address()); return headerInfo; }
 * 
 * }
 * 
 * 
 */