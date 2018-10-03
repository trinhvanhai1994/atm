package com.homedirect.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTransaction is a Querydsl query type for Transaction
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTransaction extends EntityPathBase<Transaction> {

    private static final long serialVersionUID = -1722744916L;

    public static final QTransaction transaction = new QTransaction("transaction");

    public final StringPath content = createString("content");

    public final StringPath fromAccount = createString("fromAccount");

    public final NumberPath<Integer> Id = createNumber("Id", Integer.class);

    public final StringPath status = createString("status");

    public final DateTimePath<java.util.Date> time = createDateTime("time", java.util.Date.class);

    public final StringPath toAccount = createString("toAccount");

    public final NumberPath<Double> transferAmount = createNumber("transferAmount", Double.class);

    public final NumberPath<Byte> type = createNumber("type", Byte.class);

    public QTransaction(String variable) {
        super(Transaction.class, forVariable(variable));
    }

    public QTransaction(Path<? extends Transaction> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTransaction(PathMetadata metadata) {
        super(Transaction.class, metadata);
    }

}

