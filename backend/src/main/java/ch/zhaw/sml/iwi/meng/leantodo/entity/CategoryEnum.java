package ch.zhaw.sml.iwi.meng.leantodo.entity;

public enum CategoryEnum {
    OPEN, INPROGRESS, DONE;

    @Override
    public String toString() {
        return name().toUpperCase();
    }

}
