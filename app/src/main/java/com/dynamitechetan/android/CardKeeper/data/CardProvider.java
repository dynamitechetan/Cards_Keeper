package com.dynamitechetan.android.CardKeeper.data;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

@ContentProvider(authority = CardProvider.AUTHORITY, database = CardDatabase.class)
public class CardProvider {
    public static final String AUTHORITY = "com.dynamitechetan.android.CardKeeper.data.CardProvider";
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    interface Path {
        String CARDS = "cards";
    }

    private static Uri buildUri(String... paths) {
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
        for (String path : paths) {
            builder.appendPath(path);
        }
        return builder.build();
    }

    @TableEndpoint(table = CardDatabase.CARDS) public static class Cards {

        @ContentUri(
                path = Path.CARDS,
                type = "vnd.android.cursor.dir/card",
                defaultSort = CardColumns.NAME + " ASC")
        public static final Uri CONTENT_URI = buildUri(Path.CARDS);

        @InexactContentUri(
                path = Path.CARDS + "/#",
                name = "CARD_ID",
                type = "vnd.android.cursor.item/card",
                whereColumn = CardColumns._ID,
                pathSegment = 1)
        public static Uri withId(long id) {
            return buildUri(Path.CARDS, String.valueOf(id));
        }

        public static Uri withCardID(String cardid){
            return buildUri(Path.CARDS, cardid);
        }
    }
}
