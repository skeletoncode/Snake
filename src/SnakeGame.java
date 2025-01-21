import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SnakeGame extends JPanel implements KeyListener {

    private final int WIDTH = 400;
    private final int HEIGHT = 400;
    private final int UNIT_SIZE = 20;
    private final int DELAY = 100;

    private int[] x = new int[WIDTH / UNIT_SIZE];
    private int[] y = new int[HEIGHT / UNIT_SIZE];
    private int bodyParts = 6;
    private int applesEaten;
    private int appleX;
    private int appleY;
    private char direction = 'R';
    private boolean running = false;
    private Timer timer;
    private Random random;


    public SnakeGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocus();
        addKeyListener(this);

        random = new Random();
        startGame();
    }

    private void startGame() {
        running = true;
        bodyParts = 6;
        applesEaten = 0;
        direction = 'R';
        x[0] = UNIT_SIZE * 3;
        y[0] = UNIT_SIZE * 3;
        for (int i = 1; i < bodyParts; i++) {
            x[i] = x[i - 1] - UNIT_SIZE;
            y[i] = y[i - 1];
        }
        spawnApple();
        timer = new Timer(DELAY, e -> move());
        timer.start();
    }

    private void spawnApple() {
        appleX = random.nextInt((int) Math.floor(WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) Math.floor(HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    private void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U':
                y[0] -= UNIT_SIZE;
                break;
            case 'D':
                y[0] += UNIT_SIZE;
                break;
            case 'L':
                x[0] -= UNIT_SIZE;
                break;
            case 'R':
                x[0] += UNIT_SIZE;
                break;
        }

        if (x[0] < 0 || x[0] > WIDTH - UNIT_SIZE || y[0] < 0 || y[0] > HEIGHT - UNIT_SIZE) {
            running = false;
        }

        for (int i = 1; i < bodyParts; i++) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
        }

        if (x[0] == appleX && y[0] == appleY) {
            bodyParts++;
            applesEaten++;
            spawnApple();
        }

        if (!running) {
            timer.stop();
            JOptionPane.showMessageDialog(null, "Игра окончена. Вы съели " + applesEaten + " яблок.");
            startGame();
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (running) {
            g.setColor(Color.RED);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.CYAN);
                }
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
        } else {
            g.setColor(Color.WHITE);
            g.drawString("Игра окончена", WIDTH / 2 - 50, HEIGHT / 2);
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (direction != 'D') {
                    direction = 'U';
                }
                break;
            case KeyEvent.VK_DOWN:
                if (direction != 'U') {
                    direction = 'D';
                }
                break;
            case KeyEvent.VK_LEFT:
                if (direction != 'R') {
                    direction = 'L';
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != 'L') {
                    direction = 'R';
                }
                break;
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
    }



//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Змейка");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(new SnakeGame());
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
}
