package com.axual.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
public class FailedTransaction {

    private Long reference;

    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FailedTransaction that = (FailedTransaction) o;
        return reference.equals(that.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference);
    }

    @Override
    public String toString() {
        return reference + "," + description;
    }
}
