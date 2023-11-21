package com.jennifer.meritperk.usecase.services;

import com.jennifer.meritperk.usecase.payload.request.PayStackTransactionRequest;
import com.jennifer.meritperk.usecase.payload.response.PayStackTransactionResponse;


public interface PaymentService {
     PayStackTransactionResponse initTransaction(PayStackTransactionRequest request) throws Exception;
}

