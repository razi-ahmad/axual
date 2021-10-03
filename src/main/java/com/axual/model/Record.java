package com.axual.model;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@Builder
public class Record implements Serializable {

    private Long trxReference;

    private String accountNumber;

    private Double startBalance;

    private Double mutation;

    private String description;

    private Double endBalance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return trxReference.equals(record.trxReference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trxReference);
    }
}
