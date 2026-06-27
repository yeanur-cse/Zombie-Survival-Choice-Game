import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingGame extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 450;

    private String playerName = "";
    private JPanel cardPanel;
    private CardLayout cardLayout;
    
    private JTextArea storyTextArea;
    private JButton choice1Button;
    private JButton choice2Button;
    private int currentScenario = 1;

    public SwingGame() {
        setTitle("Zombie Survival Choice Game - Week 2");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        createRegistrationScreen();
        createMainMenuScreen();
        createInstructionScreen();
        createGameplayScreen();

        add(cardPanel);
        setVisible(true);
    }

    private void createRegistrationScreen() {
        JPanel regPanel = new JPanel();
        regPanel.setLayout(new GridBagLayout());
        regPanel.setBackground(new Color(44, 62, 80));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("WELCOME TO ZOMBIE SURVIVAL", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        regPanel.add(titleLabel, gbc);

        JLabel nameLabel = new JLabel("Enter Your Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        nameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        regPanel.add(nameLabel, gbc);

        JTextField nameField = new JTextField(15);
        nameField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1; gbc.gridy = 1;
        regPanel.add(nameField, gbc);

        JButton submitBtn = new JButton("SUBMIT");
        submitBtn.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        regPanel.add(submitBtn, gbc);

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerName = nameField.getText().trim();
                if (playerName.isEmpty()) {
                    playerName = "Survivor";
                }
                cardLayout.show(cardPanel, "MainMenu");
            }
        });

        cardPanel.add(regPanel, "Registration");
    }

    private void createMainMenuScreen() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(52, 73, 94));

        menuPanel.add(Box.createVerticalStrut(40));

        JLabel menuTitle = new JLabel("MAIN MENU");
        menuTitle.setFont(new Font("Arial", Font.BOLD, 26));
        menuTitle.setForeground(Color.WHITE);
        menuTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(menuTitle);

        menuPanel.add(Box.createVerticalStrut(30));

        JButton startBtn = new JButton("1. Start Game");
        JButton instBtn = new JButton("2. Instructions");
        JButton exitBtn = new JButton("3. Exit");

        styleButton(startBtn);
        styleButton(instBtn);
        styleButton(exitBtn);

        menuPanel.add(startBtn);
        menuPanel.add(Box.createVerticalStrut(15));
        menuPanel.add(instBtn);
        menuPanel.add(Box.createVerticalStrut(15));
        menuPanel.add(exitBtn);

        startBtn.addActionListener(e -> {
            loadScenario(1);
            cardLayout.show(cardPanel, "Gameplay");
        });
        instBtn.addActionListener(e -> cardLayout.show(cardPanel, "Instructions"));
        exitBtn.addActionListener(e -> System.exit(0));

        cardPanel.add(menuPanel, "MainMenu");
    }

    private void createInstructionScreen() {
        JPanel instPanel = new JPanel(new BorderLayout());
        instPanel.setBackground(new Color(44, 62, 80));

        JTextArea text = new JTextArea();
        text.setText("\n\n   ============ GAME INSTRUCTIONS ============\n\n" +
                     "   1. This is a choice-based zombie survival game.\n" +
                     "   2. Read each scenario carefully and make your decision.\n" +
                     "   3. Every choice you make will change your destiny.\n" +
                     "   4. Try to stay alive as long as possible!");
        text.setFont(new Font("Arial", Font.PLAIN, 16));
        text.setForeground(Color.WHITE);
        text.setBackground(new Color(44, 62, 80));
        text.setEditable(false);

        JButton backBtn = new JButton("Back to Menu");
        backBtn.setFont(new Font("Arial", Font.BOLD, 14));
        backBtn.addActionListener(e -> cardLayout.show(cardPanel, "MainMenu"));

        instPanel.add(text, BorderLayout.CENTER);
        instPanel.add(backBtn, BorderLayout.SOUTH);

        cardPanel.add(instPanel, "Instructions");
    }

    private void createGameplayScreen() {
        JPanel gamePanel = new JPanel(new BorderLayout());
        gamePanel.setBackground(new Color(30, 30, 30));

        storyTextArea = new JTextArea();
        storyTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        storyTextArea.setForeground(Color.WHITE);
        storyTextArea.setBackground(new Color(30, 30, 30));
        storyTextArea.setEditable(false);
        storyTextArea.setLineWrap(true);
        storyTextArea.setWrapStyleWord(true);
        storyTextArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBackground(new Color(30, 30, 30));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        choice1Button = new JButton();
        choice2Button = new JButton();
        JButton quitBtn = new JButton("Quit to Menu");

        styleChoiceButton(choice1Button);
        styleChoiceButton(choice2Button);
        
        quitBtn.setFont(new Font("Arial", Font.BOLD, 14));
        quitBtn.setBackground(new Color(192, 57, 43));
        quitBtn.setForeground(Color.WHITE);

        buttonPanel.add(choice1Button);
        buttonPanel.add(choice2Button);
        buttonPanel.add(quitBtn);

        gamePanel.add(storyTextArea, BorderLayout.CENTER);
        gamePanel.add(buttonPanel, BorderLayout.SOUTH);

        choice1Button.addActionListener(e -> handleChoice(1));
        choice2Button.addActionListener(e -> handleChoice(2));
        quitBtn.addActionListener(e -> cardLayout.show(cardPanel, "MainMenu"));

        cardPanel.add(gamePanel, "Gameplay");
    }

    private void loadScenario(int id) {
        currentScenario = id;
        switch (id) {
            case 1:
                storyTextArea.setText("Hello " + playerName + ". You wake up in an abandoned room. You hear strange groans outside the door. You have limited supplies.");
                choice1Button.setText("Option 1: Open the door and look outside.");
                choice2Button.setText("Option 2: Stay hidden and search the room.");
                choice1Button.setVisible(true);
                choice2Button.setVisible(true);
                break;
            case 2:
                storyTextArea.setText("You open the door slowly. A zombie immediately spots you and rushes forward!");
                choice1Button.setText("Option 1: Fight back with an empty bottle.");
                choice2Button.setText("Option 2: Run down the dark corridor.");
                break;
            case 3:
                storyTextArea.setText("You stay inside and search the cabinets. You find a flashlight and a pocket knife. The door handle begins to shake.");
                choice1Button.setText("Option 1: Hold the door shut.");
                choice2Button.setText("Option 2: Hide inside the wardrobe.");
                break;
            case 4:
                storyTextArea.setText("GAME OVER! You tried to fight without a proper weapon, and the zombie overwhelmed you. Be more careful next time.");
                choice1Button.setVisible(false);
                choice2Button.setVisible(false);
                break;
            case 5:
                storyTextArea.setText("YOU SURVIVED (For Now)! You managed to escape into a safe area. Your decisions saved your life today.");
                choice1Button.setVisible(false);
                choice2Button.setVisible(false);
                break;
        }
    }

    private void handleChoice(int choiceNum) {
        if (currentScenario == 1) {
            if (choiceNum == 1) loadScenario(2);
            else if (choiceNum == 2) loadScenario(3);
        } else if (currentScenario == 2) {
            if (choiceNum == 1) loadScenario(4);
            else if (choiceNum == 2) loadScenario(5);
        } else if (currentScenario == 3) {
            if (choiceNum == 1) loadScenario(4);
            else if (choiceNum == 2) loadScenario(5);
        }
    }

    private void styleButton(JButton btn) {
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(200, 40));
        btn.setBackground(new Color(241, 196, 15));
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
    }

    private void styleChoiceButton(JButton btn) {
        btn.setFont(new Font("Arial", Font.PLAIN, 14));
        btn.setBackground(new Color(52, 152, 219));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SwingGame());
    }
}