package com.zpi.guests.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.zpi.R;
import com.zpi.guests.activities.GuestsListActivity;
import com.zpi.guests.model.Data;
import com.zpi.guests.model.Guest;

import java.util.List;

public class GuestsAdapterWithSwipe extends RecyclerSwipeAdapter<com.zpi.guests.utils.GuestsAdapterWithSwipe.SimpleViewHolder> {

    private Context mContext;
    private List<Guest> mGuestsList;
    Data data;


    public GuestsAdapterWithSwipe(Context context, List<Guest> objects) {
        this.mContext = context;
        this.mGuestsList = objects;
        this.data = new Data();
    }

    public void setGuestsList(List<Guest>objects)
    {
        mGuestsList=objects;
    }



    @Override
    public com.zpi.guests.utils.GuestsAdapterWithSwipe.SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guests_row_layout, parent, false);
        return new com.zpi.guests.utils.GuestsAdapterWithSwipe.SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final com.zpi.guests.utils.GuestsAdapterWithSwipe.SimpleViewHolder viewHolder, final int position) {
        final Guest item = mGuestsList.get(position);

        final TextView name=viewHolder.name;
        final CheckBox confirmed = viewHolder.confirmed;

        name.setText(item.getName());
        confirmed.setChecked(item.isConfirmed());
        confirmed.setEnabled(false);

//        confirmed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                item.setConfirmed(isChecked);
//                ((GuestsListActivity) mContext).setConfirmedAmount();
//
//                //Problem! swipe layout działa tak że ta metoda jest wywoływana podczas scrollowania
//
//            }
//        });

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
                if(!item.isConfirmed()){
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(mContext);
                    builder.setMessage("Czy chcesz potwierdzić przybycie gościa?")
                            .setPositiveButton("TAK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    item.setConfirmed(true);
                                    ((GuestsListActivity) mContext).setConfirmedAmount();
                                    confirmed.setChecked(true);
                                }
                            })
                            .setNegativeButton("NIE", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {}
                            })
                            .show();
                }
                else{
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(mContext);
                    builder.setMessage("Czy chcesz anulować potwierdzenie przybycia gościa?")
                            .setPositiveButton("TAK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    item.setConfirmed(false);
                                    ((GuestsListActivity) mContext).setConfirmedAmount();
                                    confirmed.setChecked(false);
                                }
                            })
                            .setNegativeButton("NIE", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {}
                            })
                            .show();
                }
            }
        });


        viewHolder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Alert dialog edit
                FragmentManager fm = ((AppCompatActivity) mContext).getSupportFragmentManager();
                EditGuestDialog egd = EditGuestDialog.newInstance(position, name.getText().toString());
                egd.show(fm,"editDialog");

            }
        });

        viewHolder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Alert dialog czy na pewno usunąć?

                mItemManger.removeShownLayouts(viewHolder.swipeLayout);
                mGuestsList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mGuestsList.size());
                mItemManger.closeAllItems();
                Toast.makeText(v.getContext(), "Usunięto " + item.getName(), Toast.LENGTH_SHORT).show();
                ((GuestsListActivity) mContext).setConfirmedAmount();
                ((GuestsListActivity) mContext).setGuestsAmount();
            }
        });

        mItemManger.bindView(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mGuestsList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.guestsSwipe;
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        public SwipeLayout swipeLayout;
        public ImageButton Delete;
        public ImageButton Edit;
        TextView name;
        CheckBox confirmed;


        public SimpleViewHolder(View itemView) {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.guestsSwipe);
            Delete =  itemView.findViewById(R.id.Delete);
            Edit =  itemView.findViewById(R.id.Edit);
            name = itemView.findViewById(R.id.nameTextView);
            confirmed = itemView.findViewById(R.id.confirmed);
        }
    }
}