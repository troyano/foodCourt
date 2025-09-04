package com.pragma.foodcourt.domain.api;

import java.util.List;

public interface Page<T> {
    List<T> getContent();
    int getPageNumber();
    int getPageSize();
    long getTotalElements();
    int getTotalPages();
    boolean isLast();
}
