package common.account.login;

import common.database.model.CustomerModel;

public class Session {
	// 현재 로그인한 사용자를 저장하는 클래스입니다.
	
	private static CustomerModel loggedInCustomer;
	private static String loggedIn_id;

    public static void setCustomer(CustomerModel customer) {
        loggedInCustomer = customer;
        loggedIn_id = customer.getLogin_id();
    }

    public static CustomerModel getCustomer() {
        return loggedInCustomer;
    }
    public static String getLoggedIn_id() {
        return loggedIn_id;
    }    
    
    public static void clear() {

        loggedInCustomer = null;
        loggedIn_id = null;
    }
}
