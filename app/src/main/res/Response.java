package ;

import java.util.List;

public class Response{
	private List<FirmalarItem> firmalar;
	private String status;

	public void setFirmalar(List<FirmalarItem> firmalar){
		this.firmalar = firmalar;
	}

	public List<FirmalarItem> getFirmalar(){
		return firmalar;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"firmalar = '" + firmalar + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}