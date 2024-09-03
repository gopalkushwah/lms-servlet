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
	private String priority;
	private String company_id;
	private String user_id;
	private String leads_id;
	
	public Lead() {
		super();
	}
	public Lead(int id, String name, String email, String address, String mobile, String source, String date,
			String time, String owner, String currentowner, String status, String priority, String company_id,
			String user_id,String leads_id) {
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
		this.priority = priority;
		this.company_id = company_id;
		this.user_id = user_id;
		this.leads_id = leads_id;
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
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getLeads_id() {
		return leads_id;
	}
	public void setLeads_id(String leads_id) {
		this.leads_id = leads_id;
	}
}
