package com.dynamitechetan.android.CardKeeper.data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

@Database(version = CardDatabase.VERSION)
public class CardDatabase {
    public static final int VERSION = 1;

    @Table(CardColumns.class) public static final String CARDS = "cards";
}
