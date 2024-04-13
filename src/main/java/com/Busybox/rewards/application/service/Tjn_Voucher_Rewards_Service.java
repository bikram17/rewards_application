package com.Busybox.rewards.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Busybox.rewards.application.model.Tjn_Voucher_Coupons;

@Service
public interface Tjn_Voucher_Rewards_Service {
	
	public List<Tjn_Voucher_Coupons> getAllVouchers();
	
	public Tjn_Voucher_Coupons getVoucherByvoucherId(Long voucherId);
	
	public Tjn_Voucher_Coupons editVoucher(Tjn_Voucher_Coupons newVoucher, Long voucherID);
	
	public Tjn_Voucher_Coupons addVoucher(Tjn_Voucher_Coupons updateVoucher);
	
	public void deleteVoucher(Long voucherId);
	
	Object buyvouchar(Tjn_Voucher_Coupons voucharmodel);
	
	}
