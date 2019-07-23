package com.example.lenovo.belajarsqllite.model;

public class Barang {
    private long id;
    private String name;
    private String price;
    private String brand;

    public Barang() {
    }

    @Override
    public String toString() {
        return "Barang " +
                "nama = " + name +
                ", harga = " + price +
                ", merk = " + brand;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
