package model;

import java.util.List;

public class Order {
    private int id;
    private Client client;
    private List<Product> productList;
    private String deliveryNotes;

    public Order(int id, Client client, List<Product> productList, String deliveryNotes) {
        this.id = id;
        this.client = client;
        this.productList = productList;
        this.deliveryNotes = deliveryNotes;
    }

    public Order(Client client, List<Product> productList, String deliveryNotes) {
        this.client = client;
        this.productList = productList;
        this.deliveryNotes = deliveryNotes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getDeliveryNotes() {
        return deliveryNotes;
    }

    public void setDeliveryNotes(String deliveryNotes) {
        this.deliveryNotes = deliveryNotes;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", client=" + client + ", productList=" + productList.toString() + ", deliveryNotes=" + deliveryNotes + "]";
    }
}
