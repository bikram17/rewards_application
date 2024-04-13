package com.Busybox.rewards.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Busybox.rewards.application.dao.Tjn_Voucher_Cupons_DAO;
import com.Busybox.rewards.application.model.Tjn_Voucher_Coupons;
import com.Busybox.rewards.application.service.Tjn_Voucher_Rewards_Service;

@Service
public class Tjn_voucher_impl implements Tjn_Voucher_Rewards_Service {

	@Autowired private Tjn_Voucher_Cupons_DAO voucherREPO;
	

	@Override
	public Object buyvouchar(Tjn_Voucher_Coupons voucharmodel) {
		
		return voucherREPO.save(voucharmodel);
	}
	
	@Override
	public List<Tjn_Voucher_Coupons> getAllVouchers() {
		return voucherREPO.findAll();
//		return null; 
	}

	@Override
	public Tjn_Voucher_Coupons getVoucherByvoucherId(Long voucherId) {
		return voucherREPO.findById(voucherId).orElse(null);
	}

	@Override
	public Tjn_Voucher_Coupons editVoucher(Tjn_Voucher_Coupons newVoucher, Long voucherId) {
		Tjn_Voucher_Coupons tvc = voucherREPO.findById(voucherId).orElse(null);
		if( newVoucher == tvc ) {System.out.println("Enter new value"); return null;}
		else return voucherREPO.save(newVoucher);
	}

	@Override
	public Tjn_Voucher_Coupons addVoucher(Tjn_Voucher_Coupons newVoucher) {
		// TODO Auto-generated method stub
		return voucherREPO.save(newVoucher);
	}

	@Override
	public void deleteVoucher(Long voucherId) {
		// TODO Auto-generated method stub
		voucherREPO.deleteById(voucherId);
	}

}
