package JADevelopmentTeam.client;

import JADevelopmentTeam.common.DataPackage;
import JADevelopmentTeam.common.Intersection;
import JADevelopmentTeam.common.TerritoryStates;

import javax.swing.*;
import java.awt.*;

public class ConfigurationScreen extends JPanel {

    private static ConfigurationScreen configurationScreen;

    public ConfigurationScreen() {
        super();
        configurationScreen = this;
        this.setSize(500, 500);
        this.setVisible(true);
        this.setBackground(new Color(224, 172, 105));
    }

    public void handleInput(DataPackage dataPackage) {

    }

}
