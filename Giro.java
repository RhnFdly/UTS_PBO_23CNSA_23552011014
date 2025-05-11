/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kasir.model;

/**
 *
 * @author rai
 */
public class Giro extends Rekening{
     public Giro(int id, double saldo) {
        super(id, saldo);
    }

    @Override
    public double hitungBunga() {
        return saldo * 0.01;
    }
}
