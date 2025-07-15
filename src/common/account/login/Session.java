package common.account.login;

import common.database.model.CustomerModel;

public class Session {
	// 현재 로그인한 사용자를 저장하는 클래스입니다.
	
	private static CustomerModel loggedInCustomer;

    public static void setCustomer(CustomerModel customer) {
        loggedInCustomer = customer;
    }

    public static CustomerModel getCustomer() {
        return loggedInCustomer;
    }

    public static void clear() {
        //loggedInCustomer = null;
    }
}
