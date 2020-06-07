package com.antaladrien;

public class Fuvar implements Comparable<Fuvar>{
    public int taxiAzonosito;
    public String indulasIdopontja;
    public int utazasIdotartama;
    public double megtettTavolsag;
    public double vitelDij;
    public double borraValo;
    public String fizetesModja;

    public Fuvar(int taxiAzonosito, String indulasIdopontja, int utazasIdotartama, double megtettTavolsag, double vitelDij, double borraValo, String fizetesModja) {
        this.taxiAzonosito = taxiAzonosito;
        this.indulasIdopontja = indulasIdopontja;
        this.utazasIdotartama = utazasIdotartama;
        this.megtettTavolsag = megtettTavolsag;
        this.vitelDij = vitelDij;
        this.borraValo = borraValo;
        this.fizetesModja = fizetesModja;
    }

    @Override
    public String toString() {
        return "Fuvar{" +
                "taxiAzonosito=" + taxiAzonosito +
                ", indulasIdopontja='" + indulasIdopontja + '\'' +
                ", utazasIdotartama=" + utazasIdotartama +
                ", megtettTavolsag=" + megtettTavolsag +
                ", vitelDij=" + vitelDij +
                ", borraValo=" + borraValo +
                ", fizetesModja='" + fizetesModja + '\'' +
                '}';
    }

    @Override
    public int compareTo(Fuvar fuvar) {
        return this.indulasIdopontja.compareTo(fuvar.indulasIdopontja);
    }
}
