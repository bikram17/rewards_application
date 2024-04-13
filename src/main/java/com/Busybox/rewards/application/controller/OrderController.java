//package com.Busybox.rewards.application.controller;
//
//import java.util.*;
//import java.util.ArrayList;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.Busybox.rewards.application.dao.InvoiceRepository;
//import com.Busybox.rewards.application.dao.OrderDao;
//import com.Busybox.rewards.application.dao.ProductListingRepository;
//import com.Busybox.rewards.application.model.CustomerModel;
//import com.Busybox.rewards.application.model.Invoice;
//import com.Busybox.rewards.application.model.OrderModel;
//import com.Busybox.rewards.application.model.OrderModelNew;
//import com.Busybox.rewards.application.model.ProductListing;
//import com.Busybox.rewards.application.service.CustomerService;
//import com.Busybox.rewards.application.service.InvoiceService;
//import com.Busybox.rewards.application.service.OrderService;
//import com.Busybox.rewards.application.service.ProductListingService;
//
//
//@CrossOrigin("*")
//@RestController	
//@RequestMapping("api/order")
//public class OrderController {
//	
//	
//	@Autowired private OrderDao orderdao;
//	@Autowired private OrderService  orderservice;
//	@Autowired private InvoiceService invoiceService;
//	@Autowired private InvoiceRepository invoicerepo;	
//	@Autowired private ProductListingRepository productListingRepository;
//	@Autowired private ProductListingService productListingService;
//	@Autowired private ProductListingRepository productlistingrepo;
//	@Autowired private OrderDao orderRepository;
//	@Autowired private CustomerService customerService;
//
//////////////////////////////GET ORDER HISTORY of A PRTICULAR CUSTOMER BY CUSTOMER PHONE NO///////////////////////////////////
//	
//	@GetMapping("orderHistorYandDetails/{customer_mobno}")
//	public ResponseEntity<?>findByPhoneNumber(@PathVariable String customer_mobno){
//		
//		List<OrderModel> orderModel = orderdao.findByPhoneNumber(customer_mobno);
//		
//		return  ResponseHandler.generateResponse(orderModel,HttpStatus.OK, "Fectched Successfully");
//		
//	}
//
//////////////////////////////////GET ORDER HISTORY OF A PARTICULAR CUSTOMER BY ORDER-ID//////////////////////////////////////	
//	@GetMapping("orderHistorY/{order_id}")
//	public ResponseEntity<?>findByOrderid(@PathVariable String order_id){
//		OrderModel orderModel = orderdao.findByOrderid(order_id);
//		return ResponseHandler.generateResponse(orderModel,HttpStatus.OK, "Fetched Successfully");
//	}
//	
//	
//	
//	
//	
//	@PostMapping("putOrder")
//	public ResponseEntity<?>saveOrder(@RequestBody OrderModel ordermodel){
//			
//		return ResponseHandler.generateResponse(orderservice.saveOrder(ordermodel),HttpStatus.OK, "Order Placed Succefullys");
//		}
//	
/////////// localhost:8080/api/order/PlaceOrder///////////	
//	
///*	{
//	   "customer_mobno":"9064515502",
//	   "customer_name":"Sugato Goswami",
//	   "payment_type":"COD",
//	   "shipping_address":"webel It Park",
//	   "product_id":"SAMPLE1236"
//	}
//	
//	
//	*/
//	
//	
//	//////////Proper Payload for Placing Order/////////////////////////////
//	/*
//	{
//	    "request_id": "31315",
//	    "request_type": "NEW_ORDER",
//	    "customer_mobno": "9064515502",
//	    "customer_name": "Sugato Goswami",
//	    "payment_type": "COD",
//	    "shipping_address": "webel It Park",
//	    "item": [
//	        {
//	            "quantity": "1",
//	            "product_id": "SAMPLE1236"
//	        },
//	        {
//	            "quantity": "1",
//	            "product_id": "SAMPLE1236"
//	        },
//	        {
//	            "quantity": "1",
//	            "product_id": "SAMPLE1236"
//	        }
//	    ]
//	}
//	
//	*/
//	
//	//---------------------------------------------------------------------------------------------------------------------------------\
//	@PostMapping("PlaceOrderMultiples")
//	public ResponseEntity<?> saveOrder2(@RequestBody OrderModelNew ordermodel) {
//	    List<String> uniqueIds = UniqueIdGenerator.generateUniqueIds();
//
//	    if (!uniqueIds.isEmpty()) {
//	        String order_id = uniqueIds.get(0);
//	        String invoice_id = uniqueIds.get(1);
//	        
//	        
//	        
//			
//			  CustomerModel customer =customerService.getCustomerByPhoneNumber(ordermodel.getCustomer_MobNo(null));
//			  if(customer == null) { return
//			  ResponseHandler.generateResponse(null,HttpStatus.
//			  NOT_FOUND,"Customer Not Found"); }
//			  
//			  
//			  ProductListing product =
//			  productlistingrepo.findByproductId(ordermodel.getProduct_id());
//			  
//			  if (product == null) { return
//			  ResponseHandler.generateResponse(null,HttpStatus.
//			  NOT_FOUND,"Product not found"); }
//			 
//
//	       
//
//	        return ResponseHandler.generateResponse(ordermodel,HttpStatus.OK,"Order Placed Succesfully");
//	    } else {
//	        return ResponseHandler.generateResponse(null,HttpStatus.INTERNAL_SERVER_ERROR,"Order Not Placed");
//	    }
//	}
//}
//
//	@PostMapping("PlaceOrderMultiples856")
//	public ResponseEntity<?> saveOrder856(@RequestBody OrderModel ordermodel) {
//	    List<String> uniqueIds = UniqueIdGenerator.generateUniqueIds();
//
//	    if (!uniqueIds.isEmpty()) {
//	        String order_id = uniqueIds.get(0);
//	        String invoice_id = uniqueIds.get(1);
//
//	        CustomerModel customer = customerService.getCustomerByPhoneNumber(ordermodel.getCustomer_mobno());
//	        if (customer == null) {
//	            return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, "Customer Not Found");
//	        }
//
//	        ProductListing product = productlistingrepo.findByproductId(ordermodel.getProduct_id());
//
//	        if (product == null) {
//	            return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, "Product not found");
//	        }
//
//	        double productPrice = product.getProductPrice();
//	        ordermodel.setAmount(productPrice);
//	        ordermodel.setOrder_id(order_id);
//	        ordermodel.setInvoice_id(invoice_id);
//	        orderservice.saveOrder(ordermodel);
//	       double totalOrderAmount  =+ productPrice;
//
//            // Create an Order entity and add it to the list of orders
//            OrderModel order = new OrderModel();
//            // Map orderModel fields to order fields
//            // Example: order.setOrderId(orderModel.getOrderId());
//            // ...
//            order.add(order);
//
//	        
//	        
//	        
//	        Invoice invoice = new Invoice();
//	        invoice.setInvoiceId(invoice_id);
//	        invoice.setItemId(ordermodel.getProduct_id());
//	        invoice.setAmount(productPrice);
//
//	        invoicerepo.saveInvoice(invoice_id, ordermodel.getProduct_id(), productPrice);
//
//	        // Build the response map with header and orders
//	        Map<String, Object> response = new HashMap<>();
//	        response.put("header", getHeaderInfo(ordermodel)); 
//	        response.put("orders", Collections.singletonList(ordermodel)); 
//
//	        return ResponseHandler.generateResponse(response, HttpStatus.OK, "Order Placed Successfully");
//	    } else {
//	        return ResponseHandler.generateResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "Order Not Placed");
//	    }
//	}
//
//	// Define a method to get the header info using the OrderModel
//	private Map<String, Object> getHeaderInfo(OrderModel ordermodel) {
//	    Map<String, Object> headerInfo = new HashMap<>();
//	    headerInfo.put("customer_mobno", ordermodel.getCustomer_mobno());
//	    headerInfo.put("request_id", ordermodel.getRequest_id());
//	    headerInfo.put("customer_name", ordermodel.getCustomer_name());
//	    headerInfo.put("shipping_address", ordermodel.getShipping_address());
//	    return headerInfo;
//	}
//
//	
//	}
//	
//	
//	
//	
//	
//
//
//	
//	
//
//	
//	
//	
//	
//	
//
