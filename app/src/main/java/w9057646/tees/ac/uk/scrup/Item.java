package w9057646.tees.ac.uk.scrup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Item extends AppCompatActivity {
    private int id;
    private String itemName;
    private String itemColor;
    private int itemQuantity;
    private int itemSize; // 3, 4 months...12 months...
    private String dateItemAdded;
    private byte itemImage;

    public Item() {
    }

    public Item(String itemName, String itemColor, int itemQuantity, int itemSize, String dateItemAdded, byte itemImage) {
        this.itemName = itemName;
        this.itemColor = itemColor;
        this.itemQuantity = itemQuantity;
        this.itemSize = itemSize;
        this.dateItemAdded = dateItemAdded;
        this.itemImage = itemImage;
    }

    public Item(int id, String itemName, String itemColor, int itemQuantity, int itemSize, String dateItemAdded, byte itemImage) {
        this.id = id;
        this.itemName = itemName;
        this.itemColor = itemColor;
        this.itemQuantity = itemQuantity;
        this.itemSize = itemSize;
        this.dateItemAdded = dateItemAdded;
        this.itemImage = itemImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemColor() {
        return itemColor;
    }

    public void setItemColor(String itemColor) {
        this.itemColor = itemColor;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getItemSize() {
        return itemSize;
    }

    public void setItemSize(int itemSize) {
        this.itemSize = itemSize;
    }

    public String getDateItemAdded() {
        return dateItemAdded;
    }

    public void setDateItemAdded(String dateItemAdded) {
        this.dateItemAdded = dateItemAdded;
    }

}
