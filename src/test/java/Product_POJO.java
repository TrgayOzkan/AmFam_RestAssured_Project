import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.nashorn.internal.ir.annotations.Ignore;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product_POJO {

    private String name;
    private float price;

    //private String photo_url;
    private String category_url;
    private String vendor_url;

    public String getName(){
        return name;
    }

    public float getPrice(){
        return price;
    }

//    public String getPhoto_url(){
//        return photo_url;
//    }

    public String getCategory_url(){
        return category_url;
    }

    public String getVendor_url(){
        return vendor_url;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(float price){
        this.price = price;
    }

//    public void setPhoto_url(String photo_url){
//        this.photo_url = photo_url;
//    }

    public void setCategory_url(String category_url){
        this.category_url = category_url;
    }

    public void setVendor_url(String vendor_url){
        this.vendor_url = vendor_url;
    }

    public Product_POJO(){

    }
    public Product_POJO(String name, float price, String photo_url, String category_url, String vendor_url){
        this.name = name;
        this.price = price;
       // this.photo_url = photo_url;
        this.category_url = category_url;
        this.vendor_url = vendor_url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                //", photo_url='" + photo_url + '\'' +
                ", category_url='" + category_url + '\'' +
                ", vendor_url='" + vendor_url + '\'' +
                '}';
    }
}
