package com.pragma.hogar360.serviceshome.domain.utils.constants;
import java.util.Objects;

public class PaginationMetadata {

    private final long totalElements;
    private final int totalPages;
    private final int pageNumber;
    private final int pageSize;

    public PaginationMetadata(long totalElements, int totalPages, int pageNumber, int pageSize) {
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
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
        PaginationMetadata that = (PaginationMetadata) o;
        return totalElements == that.totalElements &&
                totalPages == that.totalPages &&
                pageNumber == that.pageNumber &&
                pageSize == that.pageSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalElements, totalPages, pageNumber, pageSize);
    }

    @Override
    public String toString() {
        return "PaginationMetadata{" +
                "totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }
}