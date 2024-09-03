package in.pandit.model;

public class Lead {
	private int id;
	private String name;
	private String email;
	private String address;
	private String mobile;
	private String source;
	private String date;
	private String time;
	private String owner;
	private String currentowner;
	private String status;
	public Lead() {
		
	}
	public Lead(int id, String name, String email, String address, String mobile, String source, String date,
			String time, String owner, String currentowner, String status) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.mobile = mobile;
		this.source = source;
		this.date = date;
		this.time = time;
		this.owner = owner;
		this.currentowner = currentowner;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getCurrentowner() {
		return currentowner;
	}
	public void setCurrentowner(String currentowner) {
		this.currentowner = currentowner;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
