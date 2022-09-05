package ru.job4j.cinema.model;

import java.util.Objects;

public class Ticket {
    private int id;
    private int session_id;
    private int pos_row;
    private int cell;
    private int user_id;

    public Ticket() {
    }

    public Ticket(int id, int session_id, int pos_row, int cell, int user_id) {
        this.id = id;
        this.session_id = session_id;
        this.pos_row = pos_row;
        this.cell = cell;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public int getPos_row() {
        return pos_row;
    }

    public void setPos_row(int pos_row) {
        this.pos_row = pos_row;
    }

    public int getCell() {
        return cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Ticket{"
                + "id=" + id
                + ", session_id=" + session_id
                + ", pos_row=" + pos_row
                + ", cell=" + cell
                + ", user_id=" + user_id
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return id == ticket.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
