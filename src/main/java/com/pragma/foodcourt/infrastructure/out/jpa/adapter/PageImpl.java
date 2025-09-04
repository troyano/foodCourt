package com.pragma.foodcourt.infrastructure.out.jpa.adapter;

import com.pragma.foodcourt.domain.api.Page;

import java.util.List;

public class PageImpl<T> implements Page<T> {
    private final Page<T> springPage;

    public PageImpl(Page<T> springPage) {
        this.springPage = springPage;
    }

    @Override
    public List<T> getContent() {
        return springPage.getContent();
    }

    @Override
    public int getPageNumber() {
        return springPage.getPageNumber();
    }

    @Override
    public int getPageSize() {
        return springPage.getPageSize();
    }

    @Override
    public long getTotalElements() {
        return springPage.getTotalElements();
    }

    @Override
    public int getTotalPages() {
        return springPage.getTotalPages();
    }

    @Override
    public boolean isLast() {
        return springPage.isLast();
    }
}
