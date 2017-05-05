package com.github.apolo92.issues;

public class ItemNotFound extends RuntimeException {

    public ItemNotFound() {
        super("Element not found in databases");
    }

}

