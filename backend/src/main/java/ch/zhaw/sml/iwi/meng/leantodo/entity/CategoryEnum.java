package ch.zhaw.sml.iwi.meng.leantodo.entity;

public enum CategoryEnum {
    PRIVATE, BUSINESS, OTHERS;

    @Override
    public String toString() {
        return name().toUpperCase();
    }

}
