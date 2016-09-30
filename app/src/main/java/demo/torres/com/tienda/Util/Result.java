
package demo.torres.com.tienda.Util;

import java.util.ArrayList;
import java.util.List;
public class Result {

    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public ProductImage getProductImage() {
        return productImage;
    }

    public void setProductImage(ProductImage productImage) {
        this.productImage = productImage;
    }


    public List<ProductImage> getProductGalleryImages() {
        return productGalleryImages;
    }

    public void setProductGalleryImages(List<ProductImage> productGalleryImages) {
        this.productGalleryImages = productGalleryImages;
    }

    public String url;
    public String name;
    public String price;
    public String currency;
    public String description;
    public String brief;
    public String code;
    public String model;
    public ProductImage productImage;
    public List<ProductImage> productGalleryImages = new ArrayList<ProductImage>();

}
