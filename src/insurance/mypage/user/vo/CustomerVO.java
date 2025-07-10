package insurance.mypage.user.vo;

public class CustomerVO {
	private int customer_id;
	private String customer_name;
	private String personal_id;
	private String email;
	private String zip_code;
	private String address_1;
	private String address_2;
	private String phone_number;
	private String job;
	private String company_name;
	private String job_zip_code;
	private String job_address1;
	private String job_address2;
	private String job_phone_number;
	private String agree_yn;
	private String login_id;
	private String password_hash;
	private String password_salt;
	private String created_at;
	
	public CustomerVO() {
		
	}
	
	public CustomerVO(int customer_id, String customer_name, String personal_id, String email, String zip_code,
			String address_1, String address_2, String phone_number, String job, String company_name,
			String job_zip_code, String job_address1, String job_address2, String job_phone_number, String agree_yn,
			String login_id, String password_hash, String password_salt, String created_at) {
		super();
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.personal_id = personal_id;
		this.email = email;
		this.zip_code = zip_code;
		this.address_1 = address_1;
		this.address_2 = address_2;
		this.phone_number = phone_number;
		this.job = job;
		this.company_name = company_name;
		this.job_zip_code = job_zip_code;
		this.job_address1 = job_address1;
		this.job_address2 = job_address2;
		this.job_phone_number = job_phone_number;
		this.agree_yn = agree_yn;
		this.login_id = login_id;
		this.password_hash = password_hash;
		this.password_salt = password_salt;
		this.created_at = created_at;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getPersonal_id() {
		return personal_id;
	}

	public void setPersonal_id(String personal_id) {
		this.personal_id = personal_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public String getAddress_1() {
		return address_1;
	}

	public void setAddress_1(String address_1) {
		this.address_1 = address_1;
	}

	public String getAddress_2() {
		return address_2;
	}

	public void setAddress_2(String address_2) {
		this.address_2 = address_2;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getJob_zip_code() {
		return job_zip_code;
	}

	public void setJob_zip_code(String job_zip_code) {
		this.job_zip_code = job_zip_code;
	}

	public String getJob_address1() {
		return job_address1;
	}

	public void setJob_address1(String job_address1) {
		this.job_address1 = job_address1;
	}

	public String getJob_address2() {
		return job_address2;
	}

	public void setJob_address2(String job_address2) {
		this.job_address2 = job_address2;
	}

	public String getJob_phone_number() {
		return job_phone_number;
	}

	public void setJob_phone_number(String job_phone_number) {
		this.job_phone_number = job_phone_number;
	}

	public String getAgree_yn() {
		return agree_yn;
	}

	public void setAgree_yn(String agree_yn) {
		this.agree_yn = agree_yn;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getPassword_hash() {
		return password_hash;
	}

	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}

	public String getPassword_salt() {
		return password_salt;
	}

	public void setPassword_salt(String password_salt) {
		this.password_salt = password_salt;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
	

}
