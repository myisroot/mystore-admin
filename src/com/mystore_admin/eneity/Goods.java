package  com.mystore_admin.eneity;

import java.io.Serializable;
import java.math.*;

public class Goods implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private BigDecimal price;
	private String image;
	private String desc;
	private Integer is_host;
	private Integer cid;

	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return id;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setPrice(BigDecimal price){
		this.price=price;
	}
	public BigDecimal getPrice(){
		return price;
	}
	public void setImage(String image){
		this.image=image;
	}
	public String getImage(){
		return image;
	}
	public void setDesc(String desc){
		this.desc=desc;
	}
	public String getDesc(){
		return desc;
	}
	public void setIs_host(Integer is_host){
		this.is_host=is_host;
	}
	public Integer getIs_host(){
		return is_host;
	}
	public void setCid(Integer cid){
		this.cid=cid;
	}
	public Integer getCid(){
		return cid;
	}

	@Override
	public String toString() {
		return "Goods{" +
				"id=" + id +
				", name='" + name + '\'' +
				", price=" + price +
				", image='" + image + '\'' +
				", desc='" + desc + '\'' +
				", is_host=" + is_host +
				", cid=" + cid +
				'}';
	}
}

