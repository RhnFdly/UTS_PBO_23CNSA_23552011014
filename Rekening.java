/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kasir.model;

import kasir.service.LayananKeuangan;
public abstract class Rekening implements LayananKeuangan{
    protected int id;
    protected double saldo;

    public Rekening(int id, double saldo) {
        this.id = id;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setor(double jumlah) {
        saldo += jumlah;
    }

    public void tarik(double jumlah) {
        if (saldo >= jumlah) saldo -= jumlah;
        else System.out.println("Saldo tidak cukup.");
    }

    public abstract double hitungBunga();
}
