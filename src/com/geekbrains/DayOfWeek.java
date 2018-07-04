package com.geekbrains;

public enum DayOfWeek {
    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота"),
    SUNDAY("Воскресенье");

    String ruName;

    DayOfWeek(String ruName) {
        this.ruName = ruName;
    }

    public String getRuName() {
        return ruName;
    }
}
