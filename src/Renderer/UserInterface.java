package Renderer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class UserInterface
{
    private final JPanel sidePanel;
    private int depth = 3; // Default depth
    private String searchAlgorithm = "BFS"; // Default algorithm

    public UserInterface()
    {
        sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Depth Control
        JPanel depthPanel = new JPanel();
        depthPanel.setLayout(new BoxLayout(depthPanel, BoxLayout.Y_AXIS));
        JLabel depthLabel = new JLabel("Depth: " + depth);
        JSlider depthSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, depth);

        depthSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                depth = depthSlider.getValue();
                depthLabel.setText("Depth: " + depth);
            }
        });

        depthPanel.add(depthLabel);
        depthPanel.add(depthSlider);

        // Search Algorithm Dropdown
        JPanel algorithmPanel = new JPanel();
        algorithmPanel.setLayout(new BoxLayout(algorithmPanel, BoxLayout.Y_AXIS));
        JLabel algorithmLabel = new JLabel("Search Algorithm:");
        String[] algorithms = {"BFS", "A*"};
        JComboBox<String> algorithmComboBox = new JComboBox<>(algorithms);

        algorithmComboBox.addActionListener(e -> {
            searchAlgorithm = (String) algorithmComboBox.getSelectedItem();
        });

        algorithmPanel.add(algorithmLabel);
        algorithmPanel.add(algorithmComboBox);

        // Buttons
        JButton newGameButton = new JButton("New Game");
        JButton findBestMoveButton = new JButton("Find Best Move");
        JButton makeMoveButton = new JButton("Make Move");

        // Add components to side panel with spacing
        sidePanel.add(depthPanel);
        sidePanel.add(Box.createVerticalStrut(20));
        sidePanel.add(algorithmPanel);
        sidePanel.add(Box.createVerticalStrut(20));
        sidePanel.add(newGameButton);
        sidePanel.add(Box.createVerticalStrut(10));
        sidePanel.add(findBestMoveButton);
        sidePanel.add(Box.createVerticalStrut(10));
        sidePanel.add(makeMoveButton);

        // Set preferred sizes for consistent layout
        Dimension buttonSize = new Dimension(150, 30);
        newGameButton.setMaximumSize(buttonSize);
        findBestMoveButton.setMaximumSize(buttonSize);
        makeMoveButton.setMaximumSize(buttonSize);
        algorithmComboBox.setMaximumSize(buttonSize);

        // Align components
        depthLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        depthSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
        algorithmLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        algorithmComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        newGameButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        findBestMoveButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        makeMoveButton.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    // Getters for engine parameters
    public int getDepth() {
        return depth;
    }

    public String getSearchAlgorithm() {
        return searchAlgorithm;
    }

    // Method to get the panel for adding to main frame
    public JPanel getPanel() {
        return sidePanel;
    }
}