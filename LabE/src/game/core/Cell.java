package game.core;

import java.util.ArrayList;
import java.util.List;
import game.core.Interactable;


public class Cell {

  private List<Interactable> interactables;
  private int x, y;

  public Cell(int x, int y) {
    this.x = x;
    this.y = y;
    interactables = new ArrayList<>();
  }

  public void addInteractable(Interactable interactable) {
    interactables.add(interactable);
  }

  public List<Interactable> getInteractables() {
    return new ArrayList<>(interactables);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public String toString() {
    List<String> summary = new ArrayList<>();
    for (Interactable inter : interactables) {
      summary.add(inter.getSummaryString());
    }
    return String.format("%-24.24s", "(" + x + "," + y + "): " + summary);
  }

}
