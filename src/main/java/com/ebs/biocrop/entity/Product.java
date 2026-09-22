package com.ebs.biocrop.entity;

import com.ebs.biocrop.entity.enums.PaymentMethod;
import com.ebs.biocrop.entity.enums.ProductStatus;
import com.ebs.biocrop.entity.enums.ProductUnit;
import com.ebs.biocrop.entity.enums.ShippingMethod;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "products")
public class Product {

    @Id
    private String id;

    private String name;

    @Indexed
    private String productCode;

    private ProductStatus status = ProductStatus.ACTIVE;

    private String company;

    private String category;

    private String subCategory;

    private String subSubCategory;

    private String keywords;

    private Integer gst;

    private String hsnCode;

    @Indexed(unique = true)
    private String variationCode;

    private ProductUnit unit;

    private Integer unitQty;

    private Double price;

    private Double discountRs;

    private Double salePrice;

    private Double discountPercent;

    private Double courierCharge;

    private Double productWeightGm;

    private Integer stockQty;

    private ShippingMethod shippingThrough;

    private PaymentMethod paymentMethod;

    private Boolean inStock = true;

    private Integer minOrderQty = 1;

    private String shippedBy;

    private Double sellerWillGet;

    private Double length;

    private Double width;

    private Double height;

    private Integer displayOrder;

    private String notes;

    private Boolean isDefault = false;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Product() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getSubSubCategory() {
        return subSubCategory;
    }

    public void setSubSubCategory(String subSubCategory) {
        this.subSubCategory = subSubCategory;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getGst() {
        return gst;
    }

    public void setGst(Integer gst) {
        this.gst = gst;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    public String getVariationCode() {
        return variationCode;
    }

    public void setVariationCode(String variationCode) {
        this.variationCode = variationCode;
    }

    public ProductUnit getUnit() {
        return unit;
    }

    public void setUnit(ProductUnit unit) {
        this.unit = unit;
    }

    public Integer getUnitQty() {
        return unitQty;
    }

    public void setUnitQty(Integer unitQty) {
        this.unitQty = unitQty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscountRs() {
        return discountRs;
    }

    public void setDiscountRs(Double discountRs) {
        this.discountRs = discountRs;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Double getCourierCharge() {
        return courierCharge;
    }

    public void setCourierCharge(Double courierCharge) {
        this.courierCharge = courierCharge;
    }

    public Double getProductWeightGm() {
        return productWeightGm;
    }

    public void setProductWeightGm(Double productWeightGm) {
        this.productWeightGm = productWeightGm;
    }

    public Integer getStockQty() {
        return stockQty;
    }

    public void setStockQty(Integer stockQty) {
        this.stockQty = stockQty;
    }

    public ShippingMethod getShippingThrough() {
        return shippingThrough;
    }

    public void setShippingThrough(ShippingMethod shippingThrough) {
        this.shippingThrough = shippingThrough;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public Integer getMinOrderQty() {
        return minOrderQty;
    }

    public void setMinOrderQty(Integer minOrderQty) {
        this.minOrderQty = minOrderQty;
    }

    public String getShippedBy() {
        return shippedBy;
    }

    public void setShippedBy(String shippedBy) {
        this.shippedBy = shippedBy;
    }

    public Double getSellerWillGet() {
        return sellerWillGet;
    }

    public void setSellerWillGet(Double sellerWillGet) {
        this.sellerWillGet = sellerWillGet;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
