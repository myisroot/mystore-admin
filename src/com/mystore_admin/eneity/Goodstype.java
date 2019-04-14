package  com.mystore_admin.eneity;

import java.io.Serializable;
public class Goodstype implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer cid;
	private String cname;

	public void setCid(Integer cid){
		this.cid=cid;
	}
	public Integer getCid(){
		return cid;
	}
	public void setCname(String cname){
		this.cname=cname;
	}
	public String getCname(){
		return cname;
	}

	@Override
	public String toString() {
		return "Goodstype{" +
				"cid=" + cid +
				", cname='" + cname + '\'' +
				'}';
	}
}

