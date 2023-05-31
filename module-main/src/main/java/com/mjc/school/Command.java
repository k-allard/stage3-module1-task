package com.mjc.school;

public enum Command {
    GET_ALL(1),
    GET_BY_ID(2),
    CREATE(3),
    UPDATE(4),
    REMOVE_BY_ID(5),
    EXIT(0);

    public final int code;

    Command(int code) {
        this.code = code;
    }
}
