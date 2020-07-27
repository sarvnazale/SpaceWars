package ISU;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class SpaceWars extends JFrame{
    public SpaceWars() {

        initUI();
    }
    
    private void initUI() {

        //add(new Board());

        setTitle("Space Invaders");
        setSize(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {

            var ex = new SpaceWars();
            ex.setVisible(true);
        });
    }	
}