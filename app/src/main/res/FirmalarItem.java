package ;

public class FirmalarItem{
	private String createdAt;
	private String userId;
	private int id;
	private String title;
	private String mekanId;
	private String mekanFoto;
	private String updatedAt;

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setMekanId(String mekanId){
		this.mekanId = mekanId;
	}

	public String getMekanId(){
		return mekanId;
	}

	public void setMekanFoto(String mekanFoto){
		this.mekanFoto = mekanFoto;
	}

	public String getMekanFoto(){
		return mekanFoto;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	@Override
 	public String toString(){
		return 
			"firmalar{" +
			"createdAt = '" + createdAt + '\'' + 
			",user_id = '" + userId + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			",mekan_id = '" + mekanId + '\'' + 
			",mekan_foto = '" + mekanFoto + '\'' + 
			",updatedAt = '" + updatedAt + '\'' + 
			"}";
		}
}
