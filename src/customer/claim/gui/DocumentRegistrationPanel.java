package customer.claim.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import common.database.model.NewClaimDataModel;

public class DocumentRegistrationPanel extends JPanel {
	private JPanel parentCardPanel;
	private JLabel selectedFileLabel; // 선택된 파일명을 화면에 보여주는 텍스트라벨
	private JPanel fileListP; // 파일 이름 목록을 담을 패널
	private List<File> selectedFiles = new ArrayList<>();

	public DocumentRegistrationPanel(JPanel parentCardPanel, String previousPanelName, NewClaimDataModel claimData) {
		this.parentCardPanel = parentCardPanel;
		CardLayout cl = (CardLayout) (parentCardPanel.getLayout());
		setLayout(new BorderLayout());
		setVisible(true);

		TitlePanel title = new TitlePanel("서류등록");
		add(title, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setVisible(true);

		// 이미지 형식 안내 (좌측 정렬)
		JLabel imageLabel = new JLabel("JPG, JPEG, PNG 유형의 파일을 등록해주세요.");
		imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerPanel.add(imageLabel);

		JPanel buttonWrapperP = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));

		JButton fileB = new JButton("이미지 파일 첨부");
		fileB.setPreferredSize(new Dimension(150, 40));

		JButton 상황별필요서류버튼 = new JButton("상황별 필요서류 안내");
		상황별필요서류버튼.setPreferredSize(new Dimension(150, 40)); // 사진처럼

		buttonWrapperP.add(fileB);
		buttonWrapperP.add(상황별필요서류버튼);
		buttonWrapperP.setVisible(true);

		centerPanel.add(buttonWrapperP);

		fileListP = new JPanel();
		fileListP.setLayout(new BoxLayout(fileListP, BoxLayout.Y_AXIS));
		fileListP.setPreferredSize(new Dimension(300, 80));
		fileListP.setMaximumSize(new Dimension(300, 300)); // 최대 크기
		fileListP.setMinimumSize(new Dimension(300, 100)); // 최소 크기

		JScrollPane scrollP = new JScrollPane(fileListP);
		scrollP.setBorder(BorderFactory.createTitledBorder("첨부한 파일 목록"));
		scrollP.setPreferredSize(new Dimension(300, 150)); // 스크롤 영역 크기 설정
		centerPanel.add(scrollP);

		fileB.addActionListener((e) -> {
			JFileChooser fc = new JFileChooser(); // 파일 선택창 준비
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png"); 
			fc.setFileFilter(filter);
			fc.setMultiSelectionEnabled(true);

			int returnValue = fc.showOpenDialog(this.parentCardPanel); // DocumentRegistrationPanel 위에 파일 선택창을 보여줌
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File[] Files = fc.getSelectedFiles();
				imageLabel.setText("");

				for (File file : Files) {
					if (!selectedFiles.contains(file)) {
						selectedFiles.add(file);

						JPanel fileP = new JPanel();
						fileP.setLayout(new BoxLayout(fileP, BoxLayout.X_AXIS));

						JLabel fileNameL = new JLabel("📎 " + file.getName());
						JButton deleteB = new JButton("삭제");
						deleteB.setMargin(new Insets(2, 5, 2, 5)); // 버튼 여백 조절

						deleteB.addActionListener((e1) -> {
							// 1. 리스트에서 파일 제거
							fileListP.remove(fileP);
							// 2. 패널에서 해당 파일 항목 제거
//	    					  selectedFiles.remove(file);
							fileListP.revalidate();
							fileListP.repaint();
						});

						fileP.add(fileNameL);
						fileP.add(Box.createHorizontalStrut(10));
						fileP.add(deleteB);

						fileListP.add(fileP);
					}
				}
			}
			fileListP.revalidate();
			fileListP.repaint();
			fileListP.setVisible(true);
		});

		// JLabel 첨부한파일목록라벨 = new JLabel("첨부한 파일 목록");
//	      fileListP.add(첨부한파일목록라벨);

		JTextArea 안내문 = new JTextArea("서류 등록시 안내사항\n\n" + "1. 이미지 파일첨부: 원본서류를 촬영하거나 컬러로 스캔하여 첨부해 주십시오. 첨부파일찾기 "
				+ "버튼을 눌러 파일을 업로드 한 후 등록 버튼을 클릭하면 접수가 완료됩니다.\n"
				+ "2. 의료비 청구 시 영수증 상 비급여 금액이 존재하는 경우 반드시 진료비세부내역서를 제출하셔야 합니다.\n"
				+ "3. 카드매출전표(카드내역문자), 납입확인서는 보험금 청구서류로 사용할 수 없습니다.\n"
				+ "4. 5천만원 이상 또는 사망보험금 청구건, 보험금을 위임하시는 경우에는 우편/방문을 통한 원본 서류 제출이 필요합니다.");
		안내문.setEditable(false); // 텍스트 수정못함
		안내문.setLineWrap(true); // 줄이 길어지면 자동 줄빠굼
		안내문.setWrapStyleWord(true); // 단어단위로 줄바꿈
		안내문.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		안내문.setBackground(new Color(245, 245, 245)); // 배경색
		안내문.setMargin(new Insets(10, 10, 10, 10)); // 안쪽 여백(상,하,좌,우)
		centerPanel.add(안내문);

		상황별필요서류버튼.addActionListener((e) -> {
			RequiredDocumentsInfo infoPanel = new RequiredDocumentsInfo(parentCardPanel, "DocumentRegistrationPanel");
			parentCardPanel.add(infoPanel, "RequiredDocumentsInfo");
			cl.show(parentCardPanel, "RequiredDocumentsInfo");
		});

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));

		JButton previousButton = new JButton("이전");
		JButton nextButton = new JButton("다음");
		buttonPanel.add(previousButton);
		buttonPanel.add(nextButton);

		previousButton.addActionListener((e) -> {
			resetPanel();
			cl.show(parentCardPanel, previousPanelName);
		});

		nextButton.addActionListener((e) -> {
			List<String> filesNames = new ArrayList<>();
			for (File file : selectedFiles) {
				filesNames.add(file.getName());
			}
			claimData.setDocument_type_name(filesNames);
			System.out.println(claimData.toString()); // 디버깅용
			
			    // 여기서 패널 제거 & 새 패널 추가
			    for (Component comp : parentCardPanel.getComponents()) {
			        if (comp instanceof CheckFinalClaimDetails) {
			            parentCardPanel.remove(comp);  // 기존 패널 제거
			            break;
			        }
			    }
			    CheckFinalClaimDetails newPanel = new CheckFinalClaimDetails(parentCardPanel, claimData);
			    parentCardPanel.add(newPanel, "CheckFinalClaimDetails");

			    cl.show(parentCardPanel, "CheckFinalClaimDetails");
			    parentCardPanel.revalidate();
			    parentCardPanel.repaint();
		});

		buttonPanel.add(previousButton);
		buttonPanel.add(nextButton);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	 public void resetPanel() {
		 fileListP.removeAll(); // 기존에 첨부한 목록 지우기
	 }
}
