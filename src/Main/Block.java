package Main;

import UI.Cell;

import java.util.ArrayList;

public class Block {
    public ArrayList<Cell> cells;
    public String operation;
    public int risultatoValore;
    private int idBlocco;

    public Block(int blockId) {
        this.idBlocco = blockId;
        this.cells = new ArrayList<Cell>();
    }

    public Block(Block bl) {
        this.idBlocco = bl.idBlocco;
        this.cells = bl.cells;
        this.operation=bl.operation;
        this.risultatoValore=bl.risultatoValore;
    }

    public void setAll(String OP, int val) {
        operation = OP;
        risultatoValore = val;
    }

    public int getId() {
        return idBlocco;
    }

    public Cell addCell(int x, int y) {
        Cell gameCell;
        gameCell = new Cell(x, y, this);
        cells.add(gameCell);
        Mediator.getInstance().getFrame().getContentPane().add(gameCell);
        return gameCell;
    }

    public void bloccoCompletato(boolean comp) {
        for (Cell c : cells) {
            c.completed = comp;
            c.repaint();
        }
    }

    public Cell cellaVuota() {
        Cell result = null;
        for (Cell c : cells) {
            if (c.n == 0) {
                result = c;
                break;
            }
        }
        return result;
    }

    public boolean verificaBlocco() {
        int result = 0;
        switch (operation) {
            case "+":
                for (Cell c : cells) {
                    result = result + c.n;
                    if (result > risultatoValore) return false;
                    if (result == risultatoValore) {
                        return true;
                    }
                }
                break;
            case "*":
                for (Cell c : cells) {
                    if (c.n == 0) {
                        result = 0;
                        return false;
                    }
                    if (result == 0) result = c.n;
                    else result = result * c.n;
                    if (result > risultatoValore) return false;
                    if (result == risultatoValore) {
                        return true;
                    }

                }
                break;
            case "-":
                for (Cell c : cells) {
                    if (c.n != 0) {
                        result = c.n;
                        for (Cell c2 : cells) {
                            if (c != c2) {
                                result -= c2.n;
                            }
                        }
                        if (result == risultatoValore) {
                            return true;
                        }
                    }
                }
                break;
            case "/":
                for (Cell c : cells) {
                    if (c.n != 0) {
                        result = c.n;
                        for (Cell c2 : cells) {
                            if (c.n != c2.n) {
                                result /= c2.n;
                            }
                        }
                        if (result == risultatoValore) {
                            return true;
                        }
                    }
                }
                break;
        }
        return false;
    }
}

