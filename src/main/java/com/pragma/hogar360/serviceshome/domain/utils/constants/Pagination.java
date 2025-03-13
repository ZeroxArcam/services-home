package com.pragma.hogar360.serviceshome.domain.utils.constants;
import java.util.List;
import java.util.Objects;

public class Pagination<T> {
    private final List<T> items;
    private final long totalElements;
    private final int totalPages;
    private final int pageNumber;
    private final int pageSize;

    public Pagination(List<T> items, long totalElements, int totalPages, int pageNumber, int pageSize) {
        this.items = items;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;

    }
    public List<T> getItems() {
        return items;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagination<?> that = (Pagination<?>) o;
        return totalElements == that.totalElements &&
                totalPages == that.totalPages &&
                pageNumber == that.pageNumber &&
                pageSize == that.pageSize &&
                Objects.equals(items, that.items);
    }
    @Override
    public int hashCode() {
        return Objects.hash(items, totalElements, totalPages, pageNumber, pageSize);
    }


}
