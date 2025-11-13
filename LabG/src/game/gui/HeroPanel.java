package game.gui;

import game.book.Book;
import game.character.Hero;
import game.core.Skill;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;


public class HeroPanel extends JPanel implements ActionListener{

    private JProgressBar healthBar;
    private JButton healButton;
    private BookListModel bookListModel;
    private SkillListModel skillListModel;
    private JournalListModel journalListModel;

    private Hero hero;

    public HeroPanel (Hero hero) {
        this.hero = hero;

        JPanel healthPanel = new JPanel();
        healthPanel.setLayout(new BoxLayout(healthPanel, BoxLayout.X_AXIS));
        healthPanel.setBorder(new TitledBorder("Hit points"));
        healthBar = new JProgressBar(0, hero.getMaxHitPoints());
        healthBar.setStringPainted(true);
        healButton = new JButton("Heal");
        healthPanel.add(healthBar);
        healthPanel.add(healButton);
        healButton.addActionListener(this);

        setBorder(new TitledBorder("Hero: " + hero.getName()));

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
        // TODO Task 1
        healthBar.setMaximum(hero.getMaxHitPoints());
        healthBar.setValue(hero.getHitPoints());
        healthBar.setString(hero.getHitPoints() + "");
        healButton.setEnabled(hero.canHeal());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        hero.heal();
        this.updateHealthPanel();;
    }
}
