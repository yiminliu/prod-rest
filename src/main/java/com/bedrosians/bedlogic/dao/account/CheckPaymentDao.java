package com.bedrosians.bedlogic.dao.account;

import java.util.List;

import com.bedrosians.bedlogic.domain.account.CheckPayment;

public interface CheckPaymentDao {
	List<CheckPayment> getCheckPaymentsForAccount(String custcd);
}
