package com.example.demo.service;

public interface AssemblyService {

    int canAssemble(long productId, int quantity);

    int canDisassemble(long productId, int quantity);

    void assembleNumberOfProducts(long productId, int quantity);

    void disassembleNumberOfProducts(long productId, int quantity);
}
