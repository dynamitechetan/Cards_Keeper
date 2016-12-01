package com.dynamitechetan.android.CardKeeper.widget;

import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.dynamitechetan.android.CardKeeper.R;
import com.dynamitechetan.android.CardKeeper.data.CardColumns;
import com.dynamitechetan.android.CardKeeper.data.CardProvider;


public class AppWidgetRemoteViewsService extends RemoteViewsService {
    private static final int INDEX_QUOTE_ID = 0;

    public final String LOG_TAG = AppWidgetRemoteViewsService.class.getSimpleName();

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {
            private Cursor data = null;

            @Override
            public void onCreate() {
                // Nothing to do
            }

            @Override
            public void onDataSetChanged() {
                if (data != null) {
                    data.close();
                }
                // This method is called by the app hosting the widget (e.g., the launcher)
                // However, our ContentProvider is not exported so it doesn't have access to the
                // data. Therefore we need to clear (and finally restore) the calling identity so
                // that calls use our process and permission
                final long identityToken = Binder.clearCallingIdentity();

                data = getContentResolver().query( CardProvider.Cards.CONTENT_URI, null, null, null, null);
                        Binder.restoreCallingIdentity(identityToken);
            }

            @Override
            public void onDestroy() {
                if (data != null) {
                    data.close();
                    data = null;
                }
            }

            @Override
            public int getCount() {
                return data == null ? 0 : data.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                if (position == AdapterView.INVALID_POSITION ||
                        data == null || !data.moveToPosition(position)) {
                    return null;
                }
                RemoteViews views = new RemoteViews(getPackageName(),
                        R.layout.widget_collection_item);

                views.setTextViewText(R.id.card_name,
                        data.getString(data.getColumnIndex(CardColumns.NAME)));

                final Intent fillInIntent = new Intent();
                fillInIntent.putExtra("CARD",
                        data.getString(data.getColumnIndex(CardColumns.NAME)));
                views.setOnClickFillInIntent(R.id.widget_list_item, fillInIntent);
                return views;
            }

            /*@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
            private void setRemoteContentDescription(RemoteViews views, String description) {
                views.setContentDescription(R.id.widget_icon, description);
            }*/

            @Override
            public RemoteViews getLoadingView() {
                return null;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                if (data.moveToPosition(position))
                    return data.getLong(INDEX_QUOTE_ID);
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }
}
