package customer.payment.method;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdatepicker.impl.UtilDateModel;

import common.account.login.Session;
import common.database.dao.InquireDAO;
import common.database.dao.PaymentDAO;
import common.database.dao.UnpaidDAO;
import common.database.model.CustomerModel;
import common.database.model.InquireModel;
import common.database.model.PaymentModel;
import common.database.model.UnpaidModel;
import common.method.InsuranceTeamConnector;
import customer.payment.gui.inquire.InquirePanel2;

public class InquirePanel2Handler {

    private final InquirePanel2 panel;

    public InquirePanel2Handler(InquirePanel2 panel) {
        this.panel = panel;
    }

    public static void setDateRange(InquirePanel2 panel, String label) {
        LocalDate today = LocalDate.now();
        LocalDate from = switch (label) {
            case "1일" -> today.minusDays(1);
            case "7일" -> today.minusDays(7);
            case "15일" -> today.minusDays(15);
            case "30일" -> today.minusDays(30);
            case "6개월" -> today.minusMonths(6);
            case "1년" -> today.minusYears(1);
            default -> LocalDate.of(2000, 1, 1);
        };

        Date start = Date.from(from.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());

        UtilDateModel startModel = (UtilDateModel) panel.startDatePicker.getModel();
        UtilDateModel endModel = (UtilDateModel) panel.endDatePicker.getModel();

        startModel.setValue(start);
        startModel.setSelected(true);
        endModel.setValue(end);
        endModel.setSelected(true);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        panel.startDatePicker.getJFormattedTextField().setText(sdf.format(start));
        panel.endDatePicker.getJFormattedTextField().setText(sdf.format(end));
    }
    


    public static List<String[]> loadData(Date start, Date end, Integer contract_id) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	try(Connection conn = InsuranceTeamConnector.getConnection()) {
    		List<String[]> datas = new ArrayList<String[]>();
        	
        	List<InquireModel> im = inquire(Session.getCustomer(), contract_id, start, end);
        	if (im == null) return null;
        	
        	int count = 1;
        	for(InquireModel model : im) {
        		String[] data = new String[7];
        		data[0] = String.valueOf(count++);
        		data[1] = sdf.format(model.getUnpaid_date());
        		data[2] = sdf.format(model.getPayment_date());
        		data[3] = String.valueOf(model.getUnpaid_count());
        		data[4] = String.valueOf(model.getUnpaid_amount());
        		data[5] = String.valueOf(model.getPaid_amount());
        		data[6] = String.valueOf(model.getPaid_method());
        		datas.add(data);
        	}
        	
            return datas;
            
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
//        		new Object[][] {
//            {"3", "2025.07", "2025.07.02", 2, "50,000", "30,000", "계좌이체"},
//            {"2", "2025.07", "2025.07.01", 2, "50,000", "20,000", "계좌이체"},
//            {"1", "2025.06", "2025.06.03", 1, "50,000", "50,000", "계좌이체"}
//        };
    }
    
 // 납입내역 조회기능(유저, 계약, 보고싶은 시작일, 종료일)
 	/**
 	 * 납입내역 조회기능입니다.
 	 * (결제 내역과는 표시되는 컬럼이 약간 상이)
 	 * @param customer_Id
 	 * @param start 조회 시작일
 	 * @param end 조회 종료일
 	 */
 	public static List<InquireModel> inquire(CustomerModel cm, Integer contract_id, Date start, Date end) {
 		List<InquireModel> inquiredList = new ArrayList<>();
 		int count = 1; // 구분
 		
 		
 		try (Connection conn = InsuranceTeamConnector.getConnection())
 		{
 			// 조회할 내용 리스트 결제 내역 
 			inquiredList = InquireDAO.getInquireList(cm.getCustomer_id(), contract_id,
 					new java.sql.Date(start.getTime()), new java.sql.Date(end.getTime()), conn);
 		} catch (SQLException e) {
 			e.printStackTrace();
 			System.out.println("서버를 찾을 수 없습니다");
 		}
 		
 		// 기본 출력
 		System.out.println("구분\t상품이름\t\t\t납입월분\t\t입금일자\t\t납입횟수\t대상보험료\t실입금액\t입금방법");
 		
 		for (int i = 0; i < inquiredList.size(); i++) {
 			InquireModel im = inquiredList.get(i);
 			System.out.printf("%d\t%s\t%s\t%s\t%d\t%d\t%d\t%s", 
 					count++,
 					im.getProduct_name(),
 					im.getUnpaid_date(),
 					im.getPayment_date(),
 					im.getUnpaid_count(),
 					im.getUnpaid_amount(),
 					im.getPaid_amount(),
 					im.getPaid_method()
 					);
 		}
 		return inquiredList;
 	}
 	
}
