
package com.kasir.model;


public class Tabungan extends Rekening {
     public Tabungan(int id, double saldo) {
        super(id, saldo);
    }

    @Override
    public double hitungBunga() {
        return saldo * 0.03;
    }
}
