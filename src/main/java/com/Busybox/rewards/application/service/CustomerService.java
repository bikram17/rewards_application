package  com.Busybox.rewards.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Busybox.rewards.application.model.CustomerModel;

@Service
public interface CustomerService {
			
	public List<CustomerModel>getAllCustomer(String s);

	public CustomerModel getCustomerById(Integer customer_id);

	public CustomerModel editcustomer(CustomerModel customerModel, Integer customer_id);

	public CustomerModel getCustomerByPhoneNumber(String phoneNumber);
	

	public void deleteCustomerModel(Integer customer_id);
                                   
	public CustomerModel saveCustomerModel(CustomerModel customerModel);

	public Object getAllCustomerList(List<Long> liststoreIds);
}
