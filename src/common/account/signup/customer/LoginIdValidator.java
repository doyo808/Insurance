package common.account.signup.customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import common.database.dao.CustomerDAO;
import common.method.InsuranceTeamConnector;

public class LoginIdValidator {
	// id를 입력받고, 만약 이미 있다면: 이미있는 아이디 -> 로그인 창으로 이동하시겠습니까?
	// id가 형식에 맞지않으면 다시 입력시키기
	
	public static int getVerifiedLoginId(String login_id) {

	    try (Connection conn = InsuranceTeamConnector.getConnection()) {

            if (!isValidLoginId(login_id)) {
                //System.out.println("아이디 형식이 올바르지 않습니다. 다시 입력해주세요.");
                return -1;
            } else if (CustomerDAO.getCustomerByLoginId(login_id, conn) != null) {
                //System.out.println(login_id + "는 이미 있는 아이디입니다.");
                return 0;
            } else {
                //System.out.println(login_id + "는 사용가능합니다.");
                return 1;
            }
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return -1;
    }

	
	// 아이디 형식 검증 (알파벳+숫자 4~12자리)
	private static boolean isValidLoginId(String id) {
	    return id != null && id.matches("[a-zA-Z0-9]{4,12}");
	}
}
