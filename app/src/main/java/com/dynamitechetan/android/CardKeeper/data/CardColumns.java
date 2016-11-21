package com.dynamitechetan.android.CardKeeper.data;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;


public interface CardColumns {
    @DataType(DataType.Type.INTEGER) @PrimaryKey
    @AutoIncrement
    String _ID = "_id";
    @DataType(DataType.Type.TEXT) @NotNull String NAME = "name";
    @DataType(DataType.Type.TEXT) @NotNull String NUMBER = "number";
    @DataType(DataType.Type.TEXT) @NotNull String EMAIL = "email";
    @DataType(DataType.Type.TEXT) @NotNull String PHOTO = "photo";
    @DataType(DataType.Type.TEXT) @NotNull String WEBSITE = "website";
    @DataType(DataType.Type.TEXT) @NotNull String ADDRESS = "address";
}
