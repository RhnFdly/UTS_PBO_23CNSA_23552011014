/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kasir.model;

/**
 *
 * @author rai
 */
public class Transaksi {
     private int rekeningId;
    private String tipe;
    private double jumlah;
     public Transaksi(int rekeningId, String tipe, double jumlah) {
        this.rekeningId = rekeningId;
        this.tipe = tipe;
        this.jumlah = jumlah;
    }

    public int getRekeningId() {
        return rekeningId;
    }

    public String getTipe() {
        return tipe;
    }

    public double getJumlah() {
        return jumlah;
    }
}
