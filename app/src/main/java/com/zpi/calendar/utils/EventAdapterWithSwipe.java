package com.zpi.calendar.utils;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.zpi.R;
import com.zpi.calendar.activities.CalendarActivity;
import com.zpi.calendar.model.WeddingEvent;

import java.util.List;

public class EventAdapterWithSwipe extends RecyclerSwipeAdapter<com.zpi.calendar.utils.EventAdapterWithSwipe.SimpleViewHolder> {

    private Context mContext;
    private List<WeddingEvent> mEventsList;



    public EventAdapterWithSwipe(Context context, List<WeddingEvent> objects) {
        this.mContext = context;
        this.mEventsList = objects;

    }

    public void setEventsList(List<WeddingEvent> objects) {
        mEventsList = objects;
    }


    @Override
    public com.zpi.calendar.utils.EventAdapterWithSwipe.SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_events_row_layout, parent, false);
        return new com.zpi.calendar.utils.EventAdapterWithSwipe.SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final com.zpi.calendar.utils.EventAdapterWithSwipe.SimpleViewHolder viewHolder, final int position) {
        final WeddingEvent item = mEventsList.get(position);

        final TextView date = viewHolder.date;
        final TextView title = viewHolder.title;
        String currentDate = String.format("%02d", item.getDay())+"."+String.format("%02d", item.getMonth()+1);
        date.setText(currentDate);
        title.setText(item.getTitle());

        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, viewHolder.swipeLayout.findViewById(R.id.bottom_wrapper1));
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewById(R.id.bottom_wraper));
        viewHolder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {

            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onClose(SwipeLayout layout) {

            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
            }
        });

        viewHolder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = ((AppCompatActivity) mContext).getSupportFragmentManager();
                ShowEventDialog egd = ShowEventDialog.newInstance(item.getTimeInMillis(), item.getTitle(), item.getDescription());
                egd.show(fm, "editDialog");
            }
//                if (!item.isConfirmed()) {
//                    AlertDialog.Builder builder;
//                    builder = new AlertDialog.Builder(mContext);
//                    builder.setMessage("Czy chcesz potwierdzić przybycie gościa?")
//                            .setPositiveButton("TAK", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    item.setConfirmed(true);
//                                    ((GuestsListActivity) mContext).setConfirmedAmount();
//                                    confirmed.setChecked(true);
//                                }
//                            })
//                            .setNegativeButton("NIE", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                }
//                            })
//                            .show();
//                } else {
//                    AlertDialog.Builder builder;
//                    builder = new AlertDialog.Builder(mContext);
//                    builder.setMessage("Czy chcesz anulować potwierdzenie przybycia gościa?")
//                            .setPositiveButton("TAK", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    item.setConfirmed(false);
//                                    ((GuestsListActivity) mContext).setConfirmedAmount();
//                                    confirmed.setChecked(false);
//                                }
//                            })
//                            .setNegativeButton("NIE", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                }
//                            })
//                            .show();
//                }
//            }
        });


        viewHolder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = ((AppCompatActivity) mContext).getSupportFragmentManager();
                EditEventDialog egd = EditEventDialog.newInstance(item.getTimeInMillis(), item.getTitle(), item.getDescription());
                egd.show(fm, "editDialog");

            }
        });

        viewHolder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((CalendarActivity) mContext).notifyRemovedEvent(position);
                mItemManger.removeShownLayouts(viewHolder.swipeLayout);
                ((CalendarActivity) mContext).removerEvent(mEventsList.get(position));
                mEventsList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mEventsList.size());
                mItemManger.closeAllItems();
                ((CalendarActivity) mContext).setEventsInfo();
                Toast.makeText(v.getContext(), "Usunięto " + ((WeddingEvent) item).getTitle(), Toast.LENGTH_SHORT).show();

            }
        });

        mItemManger.bindView(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mEventsList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.eventsSwipe;
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public SwipeLayout swipeLayout;
        public ImageButton Delete;
        public ImageButton Edit;
        TextView title;
        TextView date;


        public SimpleViewHolder(View itemView) {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.eventsSwipe);
            Delete = itemView.findViewById(R.id.Delete);
            Edit = itemView.findViewById(R.id.Edit);
            title = itemView.findViewById(R.id.evetTitle);
            date = itemView.findViewById(R.id.dateTextView);
        }
    }
}
