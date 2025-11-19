package game.book;

public class CursedBookException extends Exception {

    private CurseType curseType;

    public CursedBookException(String message, CurseType curseType) {
        super(message);
        this.curseType = curseType;
    }

    public CurseType getCurseType() {
        return this.curseType;
    }

}
