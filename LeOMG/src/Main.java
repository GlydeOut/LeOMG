import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Main extends JPanel {
    // Set this to work with your system
    static String pythonDirectory = "/home/greetings/PycharmProjects/LeOMG/.venv/bin/python";

    static String pythonMainDirectory = System.getProperty("user.dir") + "/src/main.py";

    static JPanel mainPanel;
    static JFrame frame;
    static String deck = "Default";

    static ArrayList<Translation> subst = new ArrayList<Translation>();
    static ArrayList<Translation> verb = new ArrayList<Translation>();
    static ArrayList<Translation> adjadv = new ArrayList<Translation>();
    static ArrayList<Translation> praep = new ArrayList<Translation>();

    static boolean displaySubst = true;
    static boolean displayVerb = true;
    static boolean displayAdjadv = true;
    static boolean displayPraep = true;

    static DefaultListModel<Translation> listModel = new DefaultListModel<>();
    static JList<Translation> translationList = new JList<>(listModel);



    public Main() {
        setFocusable(true);
        setPreferredSize(new Dimension(1200, 900));
    }

    public static void main(String[] args) throws Exception {

        System.out.println(System.getProperty("user.dir"));
        mainPanel = new Main();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        frame = new JFrame("LeOMG");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel deckLabel = new JLabel("Enter deck name:");
        deckLabel.setFont(new Font(deckLabel.getFont().getFontName(), Font.PLAIN, 25));
        deckLabel.setPreferredSize(new Dimension(250,50));
        deckLabel.setMaximumSize(new Dimension(250,50));
        JTextField deckTextField = new JTextField(20);
        deckTextField.setFont(new Font(deckTextField.getFont().getFontName(), Font.PLAIN, 25));
        deckTextField.setPreferredSize(new Dimension(200,50));
        deckTextField.setMaximumSize(new Dimension(200,50));
        JButton deckButton = new JButton("Set deck name");
        deckButton.setFont(new Font(deckButton.getFont().getFontName(), Font.PLAIN, 25));
        row1.add(deckLabel);
        row1.add(deckTextField);
        row1.add(deckButton);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel currentDeckLabel = new JLabel("Current deck name: " + deck);
        currentDeckLabel.setFont(new Font(currentDeckLabel.getFont().getFontName(), Font.PLAIN, 25));
        row2.add(currentDeckLabel);

        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel wordLabel = new JLabel("Enter word:");
        wordLabel.setFont(new Font(wordLabel.getFont().getFontName(), Font.PLAIN, 25));
        wordLabel.setPreferredSize(new Dimension(175, 50));
        JTextField wordTextField = new JTextField(20);
        wordTextField.setPreferredSize(new Dimension(200,50));
        wordTextField.setMaximumSize(new Dimension(200,50));
        wordTextField.setFont(new Font(wordTextField.getFont().getFontName(), Font.PLAIN, 25));
        JButton wordButton = new JButton("Search");
        wordButton.setFont(new Font(wordButton.getFont().getFontName(), Font.PLAIN, 25));
        wordButton.setPreferredSize(new Dimension(175, 50));
        row3.add(wordLabel);
        row3.add(wordTextField);
        row3.add(wordButton);

        JPanel row3AndAHalf = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JCheckBox substCheckBox = new JCheckBox("Nouns");
        substCheckBox.setPreferredSize(new Dimension(150, 30));
        substCheckBox.setMaximumSize(new Dimension(150, 30));
        substCheckBox.setFont(new Font(substCheckBox.getFont().getFontName(), Font.PLAIN, 25));
        JCheckBox verbCheckBox = new JCheckBox("Verbs");
        verbCheckBox.setPreferredSize(new Dimension(150, 30));
        verbCheckBox.setMaximumSize(new Dimension(150, 30));
        verbCheckBox.setFont(new Font(verbCheckBox.getFont().getFontName(), Font.PLAIN, 25));
        substCheckBox.setSelected(displaySubst);
        verbCheckBox.setSelected(displayVerb);
        JCheckBox adjadvCheckBox = new JCheckBox("Adjectives / Adverbs");
        adjadvCheckBox.setPreferredSize(new Dimension(325, 30));
        adjadvCheckBox.setMaximumSize(new Dimension(325, 30));
        adjadvCheckBox.setFont(new Font(adjadvCheckBox.getFont().getFontName(), Font.PLAIN, 25));
        JCheckBox praepCheckBox = new JCheckBox("Prepositions / Pronouns");
        praepCheckBox.setPreferredSize(new Dimension(325, 30));
        praepCheckBox.setMaximumSize(new Dimension(325, 30));
        praepCheckBox.setFont(new Font(praepCheckBox.getFont().getFontName(), Font.PLAIN, 25));
        adjadvCheckBox.setSelected(displayAdjadv);
        praepCheckBox.setSelected(displayPraep);
        substCheckBox.addActionListener(e -> {
            displaySubst = substCheckBox.isSelected();
        });
        verbCheckBox.addActionListener(e -> {
            displayVerb = verbCheckBox.isSelected();
        });
        adjadvCheckBox.addActionListener(e -> {
            displayAdjadv = adjadvCheckBox.isSelected();
        });
        praepCheckBox.addActionListener(e -> {
            displayPraep = praepCheckBox.isSelected();
        });
        row3AndAHalf.add(substCheckBox);
        row3AndAHalf.add(verbCheckBox);
        row3AndAHalf.add(adjadvCheckBox);
        row3AndAHalf.add(praepCheckBox);

        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        listModel = new DefaultListModel<>();
        translationList = new JList<>(listModel);
        translationList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        translationList.setFont(new Font(translationList.getFont().getFontName(), Font.PLAIN, 20));
        JScrollPane scrollPane = new JScrollPane(translationList);
        scrollPane.setPreferredSize(new Dimension(1000, 400));
        scrollPane.setMaximumSize(new Dimension(1000, 400));
        row4.add(scrollPane);

        JPanel row5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addCard = new JButton("Add Card");
        addCard.setPreferredSize(new Dimension(200, 50));
        addCard.setMaximumSize(new Dimension(200, 50));
        addCard.setFont(new Font(addCard.getFont().getFontName(), Font.PLAIN, 25));
        row5.add(addCard);

        JPanel row0 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainPanel.add(row1);
        mainPanel.add(row2);
        mainPanel.add(row3);
        mainPanel.add(row3AndAHalf);
        mainPanel.add(row4);
        mainPanel.add(row5);
        frame.add(mainPanel);


        frame.pack();
        frame.setVisible(true);

        ActionListener setDeck = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deck = deckTextField.getText();
                currentDeckLabel.setText("Current Deck Name: " + deck);
            }
        };

        ActionListener enterWord = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subst.clear();
                verb.clear();
                adjadv.clear();
                praep.clear();
                listModel.clear();
                try {
                    getTranslations(wordTextField.getText());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                if (displaySubst) {
                    for (Translation t : subst) {
                        listModel.addElement(t);
                    }
                }
                if (displayVerb) {
                    for (Translation t : verb) {
                        listModel.addElement(t);
                    }
                }
                if (displayAdjadv) {
                    for (Translation t : adjadv) {
                        listModel.addElement(t);
                    }
                }
                if (displayPraep) {
                    for (Translation t : praep) {
                        listModel.addElement(t);
                    }
                }

            }
        };
        ActionListener addCardEvent = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.List<Translation> selectedEntries = translationList.getSelectedValuesList();
                if (selectedEntries.isEmpty()) {
                    System.out.println("Empty selection");
                    return;
                }
                for (Translation entry : selectedEntries) {
                    try {
                        AnkiManager.addCard(deck, entry.english, entry.german);
//                        subst.remove(entry);
                    } catch (IOException | InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        };


        deckTextField.addActionListener(setDeck);
        deckButton.addActionListener(setDeck);
        wordTextField.addActionListener(enterWord);
        addCard.addActionListener(addCardEvent);
        wordButton.addActionListener(enterWord);
    }


    public static String runPython(String input) throws Exception {

        ProcessBuilder pb = new ProcessBuilder(pythonDirectory, pythonMainDirectory);

        pb.redirectErrorStream(true);

        Process process = pb.start();

        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(process.getOutputStream()));
        writer.write(input);
        writer.newLine();
        writer.flush();
        writer.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String result = reader.readLine();
        reader.close();

        process.waitFor();
        return result;
    }


    static String getLeo(String word) throws Exception {
        String translation = runPython(word);
        return translation;
    }

    static void getTranslations(String translation) throws Exception {
//        System.out.println(getLeo(translation));
        String[] parsed = getLeo(translation).split("}]");

        ArrayList<Translation> translations = new ArrayList<Translation>();

        int pos = -1;
        for (String group: parsed) {
            String[] terms = group.split("\\{");
            for (String term : terms) {
//                System.out.println(term);
                if (!term.isEmpty() && !term.contains("\"") && !term.equals("}")) {
                    String keyWord = term;
                    keyWord = keyWord.substring(keyWord.indexOf("'") + 1);
                    keyWord = keyWord.substring(0, keyWord.indexOf("'"));
//                System.out.println(keyWord);
                    if (keyWord.equals("subst")) {
                        pos = 0;
                    }
                    else if (keyWord.equals("verb")) {
                        pos = 1;
                    }
                    else if (keyWord.equals("adjadv")) {
                        pos = 2;
                    }
                    else if (keyWord.equals("praep")) {
                        pos = 3;
                    }
                    else if (keyWord.equals("definition")) {
                        pos = 4;
                    }
                    else if (keyWord.equals("phrase")) {
                        pos = 5;
                    }
                    else if (keyWord.equals("example")) {
                        pos = 6;
                    }
                    if (keyWord.equals("en")) {
                        String english = term.substring(5);
                        english = english.substring(english.indexOf("'") + 1);
                        english = english.substring(0, english.indexOf("'"));

                        String german = term.substring(english.length() + 14);
                        german = german.substring(german.indexOf("'") + 1);
                        german = german.substring(0, german.indexOf("'"));

                        translations.add(new Translation(english, german, pos));
//                    System.out.println("Added: " + new Translation(english, german));
                    }
                }
            }
        }
        for (Translation term : translations) {
            switch (term.pos) {
                case 0 -> subst.add(term);
                case 1 -> verb.add(term);
                case 2 -> adjadv.add(term);
                case 3 -> praep.add(term);
            }
        }
    }
}