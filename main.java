import java.awt.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class main {
    // Add color constants for consistent styling
    private static final Color PRIMARY_COLOR = new Color(70, 130, 180); // Steel Blue
    private static final Color SECONDARY_COLOR = new Color(176, 196, 222); // Light Steel Blue
    private static final Color BACKGROUND_COLOR_1 = new Color(245, 245, 255);
    private static final Color BACKGROUND_COLOR_2 = new Color(230, 230, 250);
    private static final Color TEXT_COLOR = new Color(44, 62, 80);
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Number Sum Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800); // Increased window size
        frame.setLocationRelativeTo(null);

        // Update main panel gradient
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, BACKGROUND_COLOR_1, 0, h, BACKGROUND_COLOR_2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // Enhanced Title Panel with Welcome Message
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);
        
        JLabel titleLabel = new JLabel("Welcome to Number Sum Calculator");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subtitleLabel = new JLabel("Your Simple Solution for Processing Numerical Data");
        subtitleLabel.setFont(new Font("Segoe UI", Font.ITALIC, 18));
        subtitleLabel.setForeground(TEXT_COLOR);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add developer credit
        JLabel developerLabel = new JLabel("Developed by: JOHN MIKO P. SARSALIJO");
        developerLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        developerLabel.setForeground(new Color(100, 100, 100));
        developerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(10));
        titlePanel.add(subtitleLabel);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(developerLabel);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        String instructions = "<html><div style='text-align: center;'>" +
                            "<h2 style='color: " + String.format("#%02x%02x%02x", PRIMARY_COLOR.getRed(), 
                                                                PRIMARY_COLOR.getGreen(), 
                                                                PRIMARY_COLOR.getBlue()) + 
                            "; margin-bottom: 15px; font-size: 32px; text-shadow: 2px 2px 4px rgba(0,0,0,0.1); " +
                            "border-bottom: 3px solid " + String.format("#%02x%02x%02x", PRIMARY_COLOR.getRed(), 
                                                                      PRIMARY_COLOR.getGreen(), 
                                                                      PRIMARY_COLOR.getBlue()) + 
                            "; padding-bottom: 10px;'>" +
                            "INSTRUCTIONS</h2>" +
                            "<div style='background-color: white; padding: 20px; border-radius: 10px; " +
                            "box-shadow: 0 2px 5px rgba(0,0,0,0.1); margin: 0 20px;'>" +
                            "<p style='margin: 10px 0; color: " + String.format("#%02x%02x%02x", TEXT_COLOR.getRed(), 
                                                                              TEXT_COLOR.getGreen(), 
                                                                              TEXT_COLOR.getBlue()) + 
                            "; font-size: 14px;'>" +
                            "Welcome to this professional grade numerical data processing application. " +
                            "Designed for efficiency and accuracy in handling your numerical data files.</p>" +
                            "<h3 style='color: " + String.format("#%02x%02x%02x", PRIMARY_COLOR.getRed(), 
                                                                PRIMARY_COLOR.getGreen(), 
                                                                PRIMARY_COLOR.getBlue()) + 
                            "; margin: 10px 0;'>Usage Instructions:</h3>" +
                            "<ol style='text-align: left; margin: 5px 30px; color: " + 
                            String.format("#%02x%02x%02x", TEXT_COLOR.getRed(), TEXT_COLOR.getGreen(), TEXT_COLOR.getBlue()) + 
                            "; font-size: 14px;'>" +
                            "<li>Select your input file using the 'Choose File' button</li>" +
                            "<li>Compatible formats: .txt, .csv, or .dat</li>" +
                            "<li>View comprehensive calculation results</li>" +
                            "<li>Process multiple files as needed</li>" +
                            "</ol>" +
                            "<p style='margin: 10px 0; color: " + String.format("#%02x%02x%02x", TEXT_COLOR.getRed(), 
                                                                              TEXT_COLOR.getGreen(), 
                                                                              TEXT_COLOR.getBlue()) + 
                            "; font-size: 12px; font-style: italic;'>" +
                            "2025 KING JOHN MIKO P. SARSALIJO. lAZY CS STUDENT.</p>" +
                            "</div></div></html>";

        // Instructions Panel
        JPanel instructionsPanel = new JPanel(new BorderLayout());
        instructionsPanel.setOpaque(false);
        
        JLabel instructionsLabel = new JLabel(instructions);
        instructionsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        instructionsPanel.add(instructionsLabel, BorderLayout.CENTER);
        instructionsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Enhanced Result Panel
        JPanel resultPanel = new JPanel();
        resultPanel.setOpaque(false);
        resultPanel.setBorder(BorderFactory.createEmptyBorder(25, 0, 25, 0));
        JLabel resultLabel = new JLabel("No file processed yet");
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        resultLabel.setForeground(PRIMARY_COLOR);
        resultPanel.add(resultLabel);

        // Enhanced Button styling
        JButton chooseFileButton = new JButton("Choose File");
        chooseFileButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        chooseFileButton.setPreferredSize(new Dimension(200, 50));
        chooseFileButton.setBackground(new Color(255, 215, 0)); // Yellow color
        chooseFileButton.setForeground(new Color(0, 0, 0));    // Black text
        chooseFileButton.setFocusPainted(false);
        chooseFileButton.setBorder(new RoundedBorder(25));

        // Center align the button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(chooseFileButton);

        // Result Panel and Button Panel combined with proper spacing
        JPanel southPanel = new JPanel();
        southPanel.setOpaque(false);
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        
        resultPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        southPanel.add(Box.createVerticalStrut(10));
        southPanel.add(resultPanel);
        southPanel.add(Box.createVerticalStrut(20));
        southPanel.add(buttonPanel);
        southPanel.add(Box.createVerticalStrut(20));

        // Update hover effect
        chooseFileButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chooseFileButton.setBackground(new Color(255, 200, 0)); // Darker yellow on hover
                chooseFileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                chooseFileButton.setBackground(new Color(255, 215, 0)); // Back to original yellow
            }
        });

        // Add all panels to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(instructionsPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);  

        frame.add(mainPanel);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a Text File");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Text Files", "txt", "csv", "dat");
        fileChooser.setFileFilter(filter);
        fileChooser.setPreferredSize(new Dimension(800, 600));

        chooseFileButton.addActionListener(e -> {
            int result = fileChooser.showOpenDialog(frame);
            
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                processFile(selectedFile, resultLabel);
            }
        });

        frame.setVisible(true);
    }

    // Custom rounded border for button
    private static class RoundedBorder extends AbstractBorder {
        private int radius;
        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2d.dispose();
        }
    }

    // Update the processFile method to show more professional results
    private static void processFile(File file, JLabel resultLabel) {
        if (!isValidFileType(file)) {
            resultLabel.setText("Error: Invalid file type! Please select .txt, .csv, or .dat file");
            resultLabel.setForeground(Color.RED);
            return;
        }

        try {
            FileProcessingResult result = calculateFirstNumberSum(file);
            String resultText = String.format(
                "<html><div style='text-align: center; padding: 15px; " +
                "background-color: white; border-radius: 10px; " +
                "box-shadow: 0 2px 5px rgba(0,0,0,0.1);'>" +
                "<p style='color: %s; font-size: 16px; margin: 5px 0;'>File: %s</p>" +
                "<p style='color: %s; font-size: 18px; margin: 10px 0;'>" +
                "<b>Sum of first numbers: %.2f</b></p>" +
                "<p style='color: %s; font-size: 14px; margin: 5px 0;'>" +
                "Total lines processed: %d</p>" +
                "<p style='color: %s; font-size: 14px; margin: 5px 0;'>" +
                "Valid numbers found: %d</p>" +
                "</div></html>",
                TEXT_COLOR, file.getName(),
                PRIMARY_COLOR, result.sum,
                TEXT_COLOR, result.totalLines,
                TEXT_COLOR, result.validNumbers);
            resultLabel.setText(resultText);
            resultLabel.setForeground(new Color(0, 102, 0));
        } catch (Exception ex) {
            showError(resultLabel, ex.getMessage());
        }
    }

    // New method to show errors consistently
    private static void showError(JLabel label, String message) {
        label.setText(String.format(
            "<html><div style='text-align: center; padding: 15px; " +
            "background-color: #ffebee; border-radius: 10px; " +
            "border: 1px solid #ffcdd2;'>" +
            "<p style='color:rgb(29, 8, 8); font-size: 16px; margin: 0;'>" +
            "Error: %s</p></div></html>", message));
    }

    // Add new method to validate file type
    private static boolean isValidFileType(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".txt") || 
               fileName.endsWith(".csv") || 
               fileName.endsWith(".dat");
    }

    // Add new class to hold processing results
    private static class FileProcessingResult {
        double sum;
        int totalLines;
        int validNumbers;

        FileProcessingResult(double sum, int totalLines, int validNumbers) {
            this.sum = sum;
            this.totalLines = totalLines;
            this.validNumbers = validNumbers;
        }
    }

    // Update the calculation method
    private static FileProcessingResult calculateFirstNumberSum(File file) throws FileNotFoundException {
        double sum = 0;
        int totalLines = 0;
        int validNumbers = 0;
        
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                totalLines++;
                
                if (!line.isEmpty()) {
                    try {
                        double number = extractFirstNumber(file, line);
                        sum += number;
                        validNumbers++;
                    } catch (NumberFormatException e) {
                        // Skip invalid numbers
                        continue;
                    }
                }
            }
        }
        
        if (validNumbers == 0) {
            throw new RuntimeException("No valid numbers found in the file");
        }
        
        return new FileProcessingResult(sum, totalLines, validNumbers);
    }

    // Add new method to extract first number based on file type
    private static double extractFirstNumber(File file, String line) throws NumberFormatException {
        if (file.getName().toLowerCase().endsWith(".csv")) {
            String[] values = line.split(",");
            if (values.length > 0) {
                return Double.parseDouble(values[0].trim());
            }
        } else {
            String[] numbers = line.split("\\s+");
            if (numbers.length > 0) {
                return Double.parseDouble(numbers[0].trim());
            }
        }
        throw new NumberFormatException("No valid number found in line");
    }
}