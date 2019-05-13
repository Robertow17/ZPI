package com.zpi.checklist.utils;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.zpi.R;
import com.zpi.checklist.activities.CheckListActivity;
import com.zpi.checklist.model.ToDo;

import java.util.List;

public class CheckListAdapterWithSwipe extends RecyclerSwipeAdapter<CheckListAdapterWithSwipe.SimpleViewHolder> {

    private Context mContext;
    private List<ToDo> mToDoList;

    public CheckListAdapterWithSwipe(Context context, List<ToDo> objects) {
        this.mContext = context;
        this.mToDoList = objects;
    }

    public void setToDoList(List<ToDo>objects)
    {
        mToDoList =objects;
    }



    @Override
    public CheckListAdapterWithSwipe.SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_do_row_layout, parent, false);
        return new CheckListAdapterWithSwipe.SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CheckListAdapterWithSwipe.SimpleViewHolder viewHolder, final int position) {
        final ToDo item = mToDoList.get(position);

        final TextView name=viewHolder.name;
        final CheckBox confirmed = viewHolder.done;

        name.setText(item.getName());
        confirmed.setChecked(item.isDone());
        confirmed.setEnabled(false);

        confirmed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                item.setDone(isChecked);
//                ((GuestsListActivity) mContext).setConfirmedAmount();

                //Problem! swipe layout działa tak że ta metoda jest wywoływana podczas scrollowania
            }
        });

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
                if(!item.isDone()){
//                    AlertDialog.Builder builder;
//                    builder = new AlertDialog.Builder(mContext);
//                    builder.setMessage("Czy chcesz potwierdzić przybycie gościa?")
//                            .setPositiveButton("TAK", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    item.setDone(true);
//                                    ((GuestsListActivity) mContext).setConfirmedAmount();
//                                    confirmed.setChecked(true);
//                                }
//                            })
//                            .setNegativeButton("NIE", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {}
//                            })
//                            .show();
                    item.setDone(true);
                    ((CheckListActivity) mContext).setDoneAmount();
                    confirmed.setChecked(true);
                }
                else{
//                    AlertDialog.Builder builder;
//                    builder = new AlertDialog.Builder(mContext);
//                    builder.setMessage("Czy chcesz anulować potwierdzenie przybycia gościa?")
//                            .setPositiveButton("TAK", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    item.setDone(false);
//                                    ((GuestsListActivity) mContext).setConfirmedAmount();
//                                    confirmed.setChecked(false);
//                                }
//                            })
//                            .setNegativeButton("NIE", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {}
//                            })
//                            .show();
                    item.setDone(false);
                    ((CheckListActivity) mContext).setDoneAmount();
                    confirmed.setChecked(false);
                }
            }
        });


        viewHolder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Alert dialog edit
                FragmentManager fm = ((AppCompatActivity) mContext).getSupportFragmentManager();
                EditToDoDialog egd = EditToDoDialog.newInstance(position, name.getText().toString());
                egd.show(fm,"editDialog");

            }
        });

        viewHolder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Alert dialog czy na pewno usunąć?

                mItemManger.removeShownLayouts(viewHolder.swipeLayout);
                mToDoList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mToDoList.size());
                mItemManger.closeAllItems();
                Toast.makeText(v.getContext(), "Usunięto " + item.getName(), Toast.LENGTH_SHORT).show();
                ((CheckListActivity) mContext).setDoneAmount();
                ((CheckListActivity) mContext).setToDoAmount();
            }
        });

        mItemManger.bindView(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mToDoList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.toDoSwipe;
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        public SwipeLayout swipeLayout;
        public ImageButton Delete;
        public ImageButton Edit;
        TextView name;
        CheckBox done;


        public SimpleViewHolder(View itemView) {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.toDoSwipe);
            Delete =  itemView.findViewById(R.id.Delete);
            Edit =  itemView.findViewById(R.id.Edit);
            name = itemView.findViewById(R.id.toDoName);
            done = itemView.findViewById(R.id.done);
        }
    }
}