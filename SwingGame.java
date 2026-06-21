import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingGame extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;

    private String playerName = "";
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public SwingGame() {
        setTitle("Java Game Project - Week 1");
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

        JLabel titleLabel = new JLabel(" Welcome To Zombie Survival Choice Game", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
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
                    playerName = "Player 1";
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

        startBtn.addActionListener(e -> cardLayout.show(cardPanel, "Gameplay"));
        instBtn.addActionListener(e -> cardLayout.show(cardPanel, "Instructions"));
        exitBtn.addActionListener(e -> System.exit(0));

        cardPanel.add(menuPanel, "MainMenu");
    }

    private void createInstructionScreen() {
        JPanel instPanel = new JPanel(new BorderLayout());
        instPanel.setBackground(new Color(44, 62, 80));

        JTextArea text = new JTextArea();
        text.setText("\n\n   ============ GAME INSTRUCTIONS ============\n\n" +
                     "   1. This is a Swing GUI based game prototype.\n" +
                     "   2. Use your mouse to click buttons and navigate.\n" +
                     "   3. Core gameplay mechanics will be added in Week 2.");
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
        gamePanel.setBackground(new Color(22, 160, 133));

        JLabel gameLabel = new JLabel("Game is running...", JLabel.CENTER);
        gameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gameLabel.setForeground(Color.WHITE);

        JButton backBtn = new JButton("Quit to Menu");
        backBtn.setFont(new Font("Arial", Font.BOLD, 14));
        backBtn.addActionListener(e -> cardLayout.show(cardPanel, "MainMenu"));

        gamePanel.add(gameLabel, BorderLayout.CENTER);
        gamePanel.add(backBtn, BorderLayout.SOUTH);

        cardPanel.add(gamePanel, "Gameplay");
    }

    private void styleButton(JButton btn) {
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(200, 40));
        btn.setBackground(new Color(241, 196, 15));
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SwingGame());
    }
}