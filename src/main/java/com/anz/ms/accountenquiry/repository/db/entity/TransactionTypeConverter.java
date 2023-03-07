package com.anz.ms.accountenquiry.repository.db.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import javax.validation.constraints.NotNull;

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
