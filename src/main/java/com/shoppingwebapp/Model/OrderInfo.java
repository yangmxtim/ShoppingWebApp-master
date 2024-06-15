package com.shoppingwebapp.Model;

public class OrderInfo {
    private String orderId;
    private String totalAmount;
    private String itemName;
    private String tradeDesc;
//    private int skuId;

    // Getter 和 Setter
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getTradeDesc() {
        return tradeDesc;
    }

    public void setTradeDesc(String tradeDesc) {
        this.tradeDesc = tradeDesc;
    }

//    public int getskUid() {
//        return skuId;
//    }
//
//    public void setSkuid(int skuid) {
//        this.skuId = skuid;
//    }

//    @Override
//    public String toString() {
//        return "OrderInfo{" +
//                "skuid=" + skuId +
//                '}';

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderId='" + orderId + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", itemName='" + itemName + '\'' +
                ", tradeDesc='" + tradeDesc + '\'' +
                '}';
    }
}
