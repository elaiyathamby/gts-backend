package ch.zhaw.sml.iwi.meng.leantodo.entity;

public enum StatusEnum {
    OPEN, INPROGRESS, DONE;

    @Override
    public String toString() {
        return name().toUpperCase();
    }

}
