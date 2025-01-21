import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SnakeMenu extends JFrame {
//    // Переменная для хранения максимального количества съеденных яблок
//    private int maxApplesEaten = 0;

    public SnakeMenu() {
        setLayout(new BorderLayout());

        // Панель для меню
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        // Кнопки меню
        JButton startButton = new JButton("Начать игру");
        JButton settingsButton = new JButton("Настройки");
        JButton recordsButton = new JButton("Рекорды");
        JButton instructionsButton = new JButton("Инструкции");
        JButton exitButton = new JButton("Выход");
//        //JButton recordsButton = new JButton("Рекорды");
//        recordsButton.addActionListener(e -> showRecords());
//        menuPanel.add(recordsButton);

        JButton developerButton = new JButton("Разработчик");
        developerButton.addActionListener(e -> showDeveloperInfo());
        menuPanel.add(developerButton);

        instructionsButton.addActionListener(e -> showInstructions());
        menuPanel.add(instructionsButton);

        // Добавление кнопок в меню
        menuPanel.add(startButton);
        menuPanel.add(settingsButton);
        menuPanel.add(recordsButton);
        menuPanel.add(instructionsButton);
        menuPanel.add(exitButton);

        add(menuPanel, BorderLayout.CENTER);

        // Обработка событий кнопок
        startButton.addActionListener(e -> {
            // Запуск игры
            dispose();

            JFrame frame = new JFrame("Змейка");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new SnakeGame());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        });

//
//        frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//
//                frame.dispose();
//
//
//                menuPanel.setVisible(true);
//            }
//        });
//
        recordsButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(null, "Таблица рекордов");
        });

        instructionsButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(null, "Инструкции по игре");
        });

        exitButton.addActionListener(e -> {

            System.exit(0);
        });

        setSize(200, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void showInstructions() {
        String instructions = "Инструкции по игре:\n" +
                "1. Управляйте змейкой с помощью клавиш со стрелками.\n" +
                "2. Съедайте красные яблоки, чтобы увеличить длину змейки.\n" +
                "3. Избегайте столкновений со стенами и с телом змейки.\n" +
                "4. Игра заканчивается, если змейка врезается в стену или в себя.\n" +
                "5. После окончания игры вы можете сыграть еще раз.";
        JOptionPane.showMessageDialog(null, instructions);
    }
    private void showDeveloperInfo() {
        String developerInfo = "Информация о разработчике:\n" +
                "Имя: Купцов Роман\n" +
                "Контактная информация: CODE LLC\n" +
                "Описание проекта: Игра 'Змейка' ";
        JOptionPane.showMessageDialog(null, developerInfo);
    }

//    private void showRecords() {
//        String records = "Рекорды:\n" +
//                "Максимальное количество яблок съедено: " + maxApplesEaten;
//        JOptionPane.showMessageDialog(null, records);
//    }



    public static void main(String[] args) {

        new SnakeMenu();

    }
}
