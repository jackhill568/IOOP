public class LoreBook extends Book {
    private String loreNote;

    public LoreBook(String title, String author, int numPages, String loreNote) {
        super(title, author, numPages);
        this.loreNote = loreNote;
    }

    @Override
    public void doRead(Hero hero) {
        if (hero.addJournalEntry(loreNote)) {
            System.out.println(hero.getName() + " has read " + title + " and discovers:" + loreNote);
        } else {
            System.out.println(hero.getName() + " has read " + title + " but their brain is full");
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Lore: " + loreNote;
    }

}
