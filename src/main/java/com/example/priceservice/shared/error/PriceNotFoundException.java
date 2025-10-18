package com.example.priceservice.shared.error;

public class PriceNotFoundException extends RuntimeException{
    private final long brandId;
    private final long productId;
    private final String date;

    public PriceNotFoundException(long brandId, long productId, String date) {
        super("No applicable price found for product %d, brand %d, date %s"
                .formatted(productId, brandId, date));
        this.brandId = brandId;
        this.productId = productId;
        this.date = date;
    }

    public long getBrandId() {
        return brandId;
    }

    public long getProductId() {
        return productId;
    }

    public String getDate() {
        return date;
    }
}
