package customer.payment.gui.components;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;

public class CardNavButton extends PaymentDefaultButton {

    public CardNavButton(String label, CardSwitcher mainSwitcher, String targetPanelName) {
        super(label);

        this.addActionListener(e -> {
        	mainSwitcher.showCard(targetPanelName);
        });
    }
}