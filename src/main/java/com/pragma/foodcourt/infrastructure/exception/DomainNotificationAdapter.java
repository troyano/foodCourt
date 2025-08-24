package com.pragma.foodcourt.infrastructure.exception;

import com.pragma.foodcourt.domain.spi.IDomainNotificationPort;

public class DomainNotificationAdapter implements IDomainNotificationPort {
	@Override
	public void notifyError(String message) {
		throw new DomainNotificationException(message);
	}
}
