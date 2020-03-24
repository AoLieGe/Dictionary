package com.example.dictionary.domain;

public abstract class Mapper<From, To> {
    protected abstract To mapImpl(From from);

    public To map(From from) {
        if (from == null) throw new IllegalArgumentException("Mapper 'from' argument is null");

        return mapImpl(from);
    }
}
