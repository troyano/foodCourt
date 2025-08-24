package com.pragma.foodcourt.domain.spi;

public interface IDomainNotificationPort {
	void notifyError(String message);
}
