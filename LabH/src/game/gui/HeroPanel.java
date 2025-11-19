package game.gui;

import game.book.Book;
import game.character.Hero;
import game.core.Skill;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class HeroPanel extends JPanel implements ActionListener {

    private JProgressBar healthBar;
    private JButton healButton;
    private BookListModel bookListModel;
    private SkillListModel skillListModel;
    private JournalListModel journalListModel;
    private JLabel dodgeLabel;

    private Hero hero;

    public HeroPanel (Hero hero) {
        this.hero = hero;

        setBorder(new TitledBorder("Hero: " + hero.getName()));

        JPanel healthPanel = new JPanel();
        healthPanel.setLayout(new BoxLayout(healthPanel, BoxLayout.X_AXIS));
        healthPanel.setBorder(new TitledBorder("Hit points"));
        healthBar = new JProgressBar(0, hero.getHitPoints());
        healthBar.setStringPainted(true);
        healButton = new JButton("Heal");
        healthPanel.add(healthBar);
        healthPanel.add(healButton);
        healButton.addActionListener(this);

        dodgeLabel = new JLabel("    ");
        dodgeLabel.setBackground(Color.WHITE);
        dodgeLabel.setForeground(Color.RED.darker());
        dodgeLabel.setFont(dodgeLabel.getFont().deriveFont(Font.BOLD).deriveFont(24.0f));
        dodgeLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        bookListModel = new BookListModel(hero);
        JList<Book> readBooks = new JList<>(bookListModel);
        JScrollPane bookPane = new JScrollPane(readBooks);
        bookPane.setBorder(new TitledBorder("Books"));

        skillListModel = new SkillListModel(hero);
        JList<Skill> knownSkills = new JList<>(skillListModel);
        JScrollPane skillPane = new JScrollPane(knownSkills);
        skillPane.setBorder(new TitledBorder("Skills"));

        journalListModel = new JournalListModel(hero);
        JList<String> journal = new JList<>(journalListModel);
        JScrollPane journalPane = new JScrollPane(journal);
        journalPane.setBorder(new TitledBorder("Journal"));

        update();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalGlue());
        add(healthPanel);
        add(Box.createVerticalGlue());
        add(dodgeLabel);
        add(Box.createVerticalGlue());
        add(bookPane);
        add(skillPane);
        add(journalPane);
        add(Box.createVerticalGlue());
    }

    public void update() {
        updateHealthPanel();

        bookListModel.update();
        skillListModel.update();
        journalListModel.update();

        if (GameWindow.getInstance() != null) {
            GameWindow.getInstance().repaint();
        }
    }

    private void updateHealthPanel() {
        healthBar.setMaximum(hero.getMaxHitPoints());
        healthBar.setValue(hero.getHitPoints());
        healthBar.setString(hero.getHitPoints() + "/" + hero.getMaxHitPoints());
        healButton.setEnabled(hero.canHeal() && hero.getHitPoints() < hero.getMaxHitPoints());
        if (hero.getHitPoints() == 0 & !hero.canHeal()) {
            GameWindow.getInstance().gameOver(false);
        }
    }

    public void enableDodge(boolean dodging) {
        dodgeLabel.setText(dodging ? "DODGE NOW" : "   ");
        update();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == healButton && hero.canHeal()) {
            hero.heal();
            update();
        }
    }

}
