package Renderer;

import ChessEngine.MoveSearchAlgorithms.BfsBruteForce;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

import static javax.swing.JOptionPane.showMessageDialog;

public enum UserInterface {
    INSTANCE;

    private final JPanel sidePanel;
    private int depth = 4; // default engine depth
    private String searchAlgorithm = "BFS"; // default engine algorithm

    private Runnable UIChanged = ()->System.out.println("runnable (UserInterface.java) has not been set");
    private Runnable makeMovePressed = ()->System.out.println("runnable (UserInterface.java) has not been set");
    private Runnable findMovePressed = ()->System.out.println("runnable (UserInterface.java) has not been set");
    private Runnable newGamePressed = ()->System.out.println("runnable (UserInterface.java) has not been set");

    String[] algorithms = {BfsBruteForce.class.toString()}; // search algorithms

    UserInterface() {
        sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // depth Control
        JPanel depthPanel = new JPanel();
        depthPanel.setLayout(new BoxLayout(depthPanel, BoxLayout.Y_AXIS));
        JLabel depthLabel = new JLabel("Depth: " + depth);
        JSlider depthSlider = new JSlider(JSlider.HORIZONTAL, 1, 8, depth);

        depthPanel.add(depthLabel);
        depthPanel.add(depthSlider);

        // search Algorithm Dropdown
        JPanel algorithmPanel = new JPanel();
        algorithmPanel.setLayout(new BoxLayout(algorithmPanel, BoxLayout.Y_AXIS));
        JLabel algorithmLabel = new JLabel("Search Algorithm:");
        JComboBox<String> algorithmComboBox = new JComboBox<>(algorithms);

        algorithmPanel.add(algorithmLabel);
        algorithmPanel.add(algorithmComboBox);

        // buttons
        JButton newGameButton = new JButton("New Game");
        JButton findBestMoveButton = new JButton("Find Best Move");
        JButton makeMoveButton = new JButton("Make Move");

        // ADD EVENT LISTENERS
        depthSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                depth = depthSlider.getValue();
                depthLabel.setText("Depth: " + depth);
                UIChanged.run();
            }
        });
        algorithmComboBox.addActionListener(e -> {
            searchAlgorithm = (String) algorithmComboBox.getSelectedItem();
            UIChanged.run();
        });
        newGameButton.addActionListener(e -> newGamePressed.run());
        findBestMoveButton.addActionListener(e -> findMovePressed.run());
        makeMoveButton.addActionListener(e -> makeMovePressed.run());

        // Set fixed width for all components
        Dimension fixedSize = new Dimension(180, 30);

        depthPanel.setMaximumSize(new Dimension(180, 60));
        algorithmPanel.setMaximumSize(new Dimension(180, 60));

        depthSlider.setMaximumSize(fixedSize);
        algorithmComboBox.setMaximumSize(fixedSize);
        newGameButton.setMaximumSize(fixedSize);
        findBestMoveButton.setMaximumSize(fixedSize);
        makeMoveButton.setMaximumSize(fixedSize);

        // Set minimum sizes to match maximum sizes
        sidePanel.setMinimumSize(new Dimension(200, 300));
        sidePanel.setPreferredSize(new Dimension(200, 300));
        sidePanel.setMaximumSize(new Dimension(200, 300));

        // add components to side panel with spacing
        sidePanel.add(depthPanel);
        sidePanel.add(Box.createVerticalStrut(10));
        sidePanel.add(algorithmPanel);
        sidePanel.add(Box.createVerticalStrut(10));
        sidePanel.add(newGameButton);
        sidePanel.add(Box.createVerticalStrut(5));
        sidePanel.add(findBestMoveButton);
        sidePanel.add(Box.createVerticalStrut(5));
        sidePanel.add(makeMoveButton);
        sidePanel.add(Box.createVerticalGlue());

        // align components
        depthLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        depthSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
        algorithmLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        algorithmComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        newGameButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        findBestMoveButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        makeMoveButton.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public String getAlgorithm() {return searchAlgorithm;}
    public int getDepth() {return depth;}
    public String getSearchAlgorithm() {return searchAlgorithm;}
    public JPanel getPanel() {return sidePanel;}

    public void onWin(Boolean white)
    {
        if(white)
            showMessageDialog(null, "White wins!");
        else
            showMessageDialog(null, "black wins!");
    }

    public void setOnUIChangedRunnable(Runnable UIChanged) {this.UIChanged = UIChanged;}
    public void setOnNewGamePressed(Runnable pressed)      {this.newGamePressed = pressed;}
    public void setOnFindMovePressed(Runnable pressed)     {this.findMovePressed = pressed;}
    public void setOnMakeMovePressed(Runnable pressed)     {this.makeMovePressed = pressed;}
}