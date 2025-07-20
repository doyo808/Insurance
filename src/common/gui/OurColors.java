package common.gui;

import java.awt.Color;

public class OurColors {
	    // 선택된 버튼 배경색 (아보카도색)
	    public static final Color SELECTED = new Color(185, 225, 135);
	    
	    // 선택 안된 버튼 배경색 (회색)
	    public static final Color UNSELECTED = new Color(238, 238, 238);
	    
	    // 기본 배경색 (컨트롤 하이라이트 등)
	    public static final Color BACKGROUND = Color.decode("#F5F5F5"); // 연한 회색
	    
	    // 타이틀 텍스트 색상
	    public static final Color TITLE_TEXT = Color.WHITE;
	    
	    // 다음화면으로 가는 버튼 배경색 (주황)
	    public static final Color UNHOVER = new Color(255, 160, 90);
	    
	    // 이전화면으로 가는 버튼 배경색 (주황)
	    public static final Color HOVER = new Color(255, 130, 60);
	    
	    private OurColors() {
	        // 인스턴스 생성 방지
	    }
}

