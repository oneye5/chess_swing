package Renderer;
import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * MainWindow,
 * provides abstraction for creating a window for rendering to by the 'Renderer'
 *
 * @see Renderer
 * @author Owan Lazic
 */

class MainWindow
{
    private final JPanel panel;
    public MainWindow(int width, int height, Consumer<Graphics> renderConsumer)
    {
        JFrame frame = new JFrame("MAIN WINDOW");
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

    public JPanel getPanel() {return panel;}
}
