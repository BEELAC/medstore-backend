package com.beelac.medstorebackend.model;

import java.math.BigDecimal;

public class OrderItem {
    private int productId;
    private int quantity;
    private BigDecimal price;

    // getters and setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    @Override
    public String toString() {
        return "OrderItem [productId=" + productId + ", quantity=" + quantity + ", price=" + price + "]";
    }
}
