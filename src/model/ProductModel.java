package model;

public class ProductModel {

    private String productName;
    private String productCategory;
    private int productCode;
    private int productStock;
    private double sellPrice;
    private double costPrice;

    public ProductModel(String productName, String productCategory, int productCode, int productStock, double sellPrice, double costPrice) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.productCode = productCode;
        this.productStock = productStock;
        this.sellPrice = sellPrice;
        this.costPrice = costPrice;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "productName='" + productName + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", productCode=" + productCode +
                ", productStock=" + productStock +
                ", sellPrice=" + sellPrice +
                ", costPrice=" + costPrice +
                '}';
    }
}