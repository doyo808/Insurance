package customer.contract.textSources;

import java.util.ArrayList;
import java.util.List;

public class CoverageFactory {

    private static final List<Coverage> coverageList = new ArrayList<>();

    static {
        coverageList.add(new Coverage("암 진단금", 900.0, 50000000.0, "최초 암 진단 시 1회 지급"));
        coverageList.add(new Coverage("입원일당", 450.0, 3600000.0, "입원 시 1일당 3만원 (최대 120일)"));
        coverageList.add(new Coverage("수술비 보장", 750.0, 2000000.0, "수술 종류에 따라 최대 지급"));
        coverageList.add(new Coverage("골절 진단금", 380.0, 500000.0, "골절 진단 시 연 1회 한정"));
        coverageList.add(new Coverage("응급실 내원 보장", 290.0, 100000.0, "응급실 내원 시 1회 지급"));
        coverageList.add(new Coverage("뇌졸중 진단금", 1100.0, 20000000.0, "뇌졸중 최초 진단 시 지급"));
        coverageList.add(new Coverage("심근경색 진단금", 1050.0, 30000000.0, "심근경색 최초 진단 시 지급"));
        coverageList.add(new Coverage("사망 보험금", 1600.0, 100000000.0, "사망 시 유족에게 지급"));
        coverageList.add(new Coverage("장기요양 보장", 550.0, 30000000.0, "장기요양 1등급 판정 시 지급"));
        coverageList.add(new Coverage("화상 진단금", 310.0, 1000000.0, "화상 진단 시 최대 지급"));
    }

    public static List<Coverage> getAllCoverages() {
        return coverageList;
    }

    public static Coverage getCoverageByName(String name) {
        return coverageList.stream()
                .filter(c -> c.name.equals(name))
                .findFirst()
                .orElse(null);
    }
}