package com.tripledigitaldaggers;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/** One window, two screens: login, then the profile + matches screen. */
public class UserInterface {
    private static final String[] DAYS = {"Mon", "Tue", "Wed", "Thu", "Fri"};
    private static final Color BG = new Color(174, 204, 228);

    private final JFrame frame = new JFrame("GapMatch");
    private final CardLayout cards = new CardLayout();
    private final JPanel root = new JPanel(cards);

    private Student current;

    // login screen
    private final JTextField userField = new JTextField(15);
    // main screen
    private final JLabel welcome = new JLabel();
    private final JTextField nameField = new JTextField();
    private final JTextField programField = new JTextField();
    private final JTextField coursesField = new JTextField();
    private final DefaultListModel<TimeBlock> blockModel = new DefaultListModel<>();
    private final JList<TimeBlock> blockList = new JList<>(blockModel);
    private final JComboBox<String> dayBox = new JComboBox<>(DAYS);
    private final JSpinner startSpin = new JSpinner(new SpinnerNumberModel(13, 0, 23, 1));
    private final JSpinner endSpin = new JSpinner(new SpinnerNumberModel(15, 1, 24, 1));
    private final JTextArea results = new JTextArea();

    public UserInterface() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 700);
        frame.setLocationRelativeTo(null);
        root.add(buildLogin(), "login");
        root.add(buildMain(), "main");
        frame.setContentPane(root);
    }

    public void display() {
        frame.setVisible(true);
    }

    // ---------- Screen 1: login ----------
    private JPanel buildLogin() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(BG);
        JPanel box = new JPanel();
        box.setOpaque(false);
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Welcome to GapMatch!");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        JLabel sub = new JLabel("Turn your gaps between classes into study sessions.");
        sub.setForeground(Color.WHITE);
        JLabel ask = new JLabel("Please input your username:");
        ask.setForeground(Color.WHITE);
        JButton go = new JButton("Continue");
        go.addActionListener(e -> login());
        userField.addActionListener(e -> login());

        for (JComponent c : new JComponent[]{title, sub, new JLabel(" "), ask, userField,
                new JLabel(" "), go}) {
            c.setAlignmentX(Component.CENTER_ALIGNMENT);
            box.add(c);
        }
        userField.setMaximumSize(new Dimension(220, 28));
        p.add(box);
        return p;
    }

    private void login() {
        String user = userField.getText().trim();
        if (user.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a username.");
            return;
        }
        if (User.findUsername(user)) {
            current = User.getStudent(user);
            welcome.setText("Welcome back, " + user + "!");
        } else {
            current = new Student("", "", user);
            User.addStudent(current);
            welcome.setText("New account created for " + user + ". Let's get to know you!");
        }
        loadProfile();
        cards.show(root, "main");
    }

    // ---------- Screen 2: profile + matches ----------
    private JPanel buildMain() {
        JPanel p = new JPanel(new BorderLayout(8, 8));
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        p.setBackground(BG);

        // top: profile
        JPanel top = new JPanel(new BorderLayout(5, 5));
        top.setOpaque(false);
        welcome.setFont(new Font("Arial", Font.BOLD, 14));
        welcome.setForeground(Color.WHITE);
        JPanel form = new JPanel(new GridLayout(3, 2, 5, 5));
        form.setOpaque(false);
        form.add(label("Your name:"));                      form.add(nameField);
        form.add(label("Your program:"));                   form.add(programField);
        form.add(label("Your courses (comma separated):")); form.add(coursesField);
        top.add(welcome, BorderLayout.NORTH);
        top.add(form, BorderLayout.CENTER);

        // middle: free time
        JPanel mid = new JPanel(new BorderLayout(5, 5));
        mid.setOpaque(false);
        JPanel add = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add.setOpaque(false);
        JButton addBtn = new JButton("Add free block");
        addBtn.addActionListener(e -> addBlock());
        JButton removeBtn = new JButton("Remove selected");
        removeBtn.addActionListener(e -> {
            int i = blockList.getSelectedIndex();
            if (i >= 0) {
                current.getBlocks().remove(i);
                blockModel.remove(i);
            }
        });
        add.add(label("Free time:")); add.add(dayBox);
        add.add(label("from")); add.add(startSpin);
        add.add(label("to")); add.add(endSpin);
        add.add(addBtn); add.add(removeBtn);
        mid.add(add, BorderLayout.NORTH);
        JScrollPane blockScroll = new JScrollPane(blockList);
        blockScroll.setPreferredSize(new Dimension(100, 110));
        mid.add(blockScroll, BorderLayout.CENTER);

        // bottom: matches
        JPanel bottom = new JPanel(new BorderLayout(5, 5));
        bottom.setOpaque(false);
        JButton find = new JButton("Find my study partners");
        find.setFont(new Font("Arial", Font.BOLD, 14));
        find.addActionListener(e -> findMatches());
        results.setEditable(false);
        results.setLineWrap(true);
        results.setWrapStyleWord(true);
        results.setText("Fill in your profile and free time, then click the button above.");
        JScrollPane resScroll = new JScrollPane(results);
        resScroll.setPreferredSize(new Dimension(100, 300));
        bottom.add(find, BorderLayout.NORTH);
        bottom.add(resScroll, BorderLayout.CENTER);

        JPanel upper = new JPanel(new BorderLayout(8, 8));
        upper.setOpaque(false);
        upper.add(top, BorderLayout.NORTH);
        upper.add(mid, BorderLayout.CENTER);
        p.add(upper, BorderLayout.CENTER);
        p.add(bottom, BorderLayout.SOUTH);

        JButton logout = new JButton("Log out");
        logout.addActionListener(e -> {
            userField.setText("");
            cards.show(root, "login");
        });
        p.add(logout, BorderLayout.NORTH);
        return p;
    }

    private JLabel label(String text) {
        JLabel l = new JLabel(text);
        l.setForeground(Color.WHITE);
        return l;
    }

    private void loadProfile() {
        nameField.setText(current.getName());
        programField.setText(current.getProgram());
        coursesField.setText(current.coursesAsText());
        blockModel.clear();
        for (TimeBlock b : current.getBlocks()) blockModel.addElement(b);
        results.setText("Fill in your profile and free time, then click the button above.");
    }

    private void saveProfile() {
        current.setName(nameField.getText().trim());
        current.setProgram(programField.getText().trim());
        current.setCoursesFromText(coursesField.getText());
    }

    private void addBlock() {
        int s = (Integer) startSpin.getValue();
        int e = (Integer) endSpin.getValue();
        if (s >= e) {
            JOptionPane.showMessageDialog(frame, "The end hour must be after the start hour.");
            return;
        }
        TimeBlock b = new TimeBlock((String) dayBox.getSelectedItem(), s, e);
        current.getBlocks().add(b);
        blockModel.addElement(b);
    }

    private void findMatches() {
        saveProfile();
        if (current.getName().isEmpty() || current.getBlocks().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter your name and add at least one free block.");
            return;
        }
        List<MatchResult> matches = Matching.findMatches(current, User.all());
        if (matches.isEmpty()) {
            results.setText("No matches yet. Try adding more free time blocks or courses!");
            return;
        }
        StringBuilder sb = new StringBuilder("Your best study partners:\n\n");
        int rank = 1;
        for (MatchResult m : matches) {
            sb.append(rank++).append(". ").append(m.student.getName())
              .append(" (").append(m.student.getProgram()).append(")  -  score ").append(m.score).append("\n")
              .append("   Why: ").append(m.reason).append("\n\n");
        }
        results.setText(sb.toString());
        results.setCaretPosition(0);
    }
}
