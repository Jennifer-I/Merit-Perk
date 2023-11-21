package com.jennifer.meritperk.usecase.services;

import com.jennifer.meritperk.usecase.payload.request.FundWalletRequest;
import com.jennifer.meritperk.usecase.payload.response.PaymentResponse;
import com.jennifer.meritperk.usecase.payload.response.WalletResponse;


public interface WalletService {
    WalletResponse getStudentWalletBalance ();
    PaymentResponse fundWallet(FundWalletRequest fundWalletRequest) throws Exception;
    WalletResponse getTeacherWalletBalance();

}
