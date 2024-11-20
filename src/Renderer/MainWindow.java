package Renderer;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

class MainWindow
{
    private JFrame frame;
    private JPanel panel;
    public MainWindow(int width, int height, BiConsumer<JFrame, JPanel> renderBiConsumer)
    {
        frame = new JFrame("CHESS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);

        panel = new JPanel()
        {
            @Override
            public void paint(Graphics g)
            {
                super.paint(g);
                renderBiConsumer.accept(frame, panel);
            }
        };

        frame.add(panel);
        frame.setVisible(true);
    }
}
