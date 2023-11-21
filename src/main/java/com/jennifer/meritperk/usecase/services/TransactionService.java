package com.jennifer.meritperk.usecase.services;

import com.jennifer.meritperk.usecase.payload.response.TransactionResponse;

import java.util.List;

public interface TransactionService {

    List<TransactionResponse> getStudentTransactions(int offset, int pageSize);

    List<TransactionResponse> getTeacherTransactions(int offset, int pageSize);
}
