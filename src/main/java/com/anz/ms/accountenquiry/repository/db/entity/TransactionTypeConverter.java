package com.anz.ms.accountenquiry.repository.db.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import jakarta.validation.constraints.NotNull;

@Converter(autoApply = true)
public class TransactionTypeConverter implements AttributeConverter<TransactionType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TransactionType transactionType) {
        return transactionType.getId();
    }

    @Override
    public TransactionType convertToEntityAttribute(@NotNull Integer id) {
        for (TransactionType transactionType: TransactionType.values()) {
            if (transactionType.getId().equals(id)) {
                return transactionType;
            }
        }

        throw new IllegalArgumentException(String.format("Unknown transaction type id: %d", id));
    }
}
