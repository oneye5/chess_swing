package Renderer;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

class MainWindow
{
    private JFrame frame;
    private JPanel panel;
    public MainWindow(int width, int height, Consumer<Graphics> renderConsumer)
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
                renderConsumer.accept(g);
            }
        };

        frame.add(panel);
        frame.setVisible(true);
    }
}
