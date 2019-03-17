package com.zpi.budget.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.zpi.R;
import com.zpi.budget.activities.DeleteExpenseActivity;
import com.zpi.budget.activities.EditExpenseActivity;
import com.zpi.budget.model.Expense;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ExpenseAdapterWithSwipe extends RecyclerSwipeAdapter<ExpenseAdapterWithSwipe.SimpleViewHolder> {


    private static final int EDIT_EXPENSE_ACTIVITY_REQUEST_CODE = 1;
    private static final int DELETE_EXPENSE_ACTIVITY_REQUEST_CODE = 21;
    private Context mContext;
    private List<Expense> mExpenseList;
    String [] categories;
    int [] categioriesImageId;
    String currency;

    public ExpenseAdapterWithSwipe(Context context, List<Expense> objects,String [] categories,int [] categioriesImageId,String currency) {
        this.mContext = context;
        this.mExpenseList = objects;
        this.categories = categories;
        this.categioriesImageId = categioriesImageId;
        this.currency = currency;

    }

    public void setExpensesList(List<Expense>objects)
    {
        mExpenseList=objects;
    }

    public void setCurrency(String currency){this.currency=currency;}


    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.budget_rv_expense_layout, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
        final Expense item = mExpenseList.get(position);



        TextView expense=viewHolder.expense;
        final TextView category=viewHolder.category;
        ImageView categoryImage=viewHolder.categoryImage;
        TextView price=viewHolder.price;
        final TextView date=viewHolder.date;


        //SET PRICE

        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.GERMAN);
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#0.00",otherSymbols);

        price.setText(String.valueOf(df.format(item.getPrice()))+" "+currency);

        price.setTextColor(mContext.getColor(R.color.red));


        //SET EXPENSE

        expense.setText(item.getName());

        //SET DATE

        //date.setText(String.valueOf(item.getDate()));
        Calendar cal = Calendar.getInstance();
        cal.setTime(item.getDate());
        int year = cal.get(Calendar.YEAR)-1900;
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        date.setText(day+"."+(month+1)+"."+year);



        //SET CATEGORY
        category.setText(item.getCategory());


        // SET IMAGE

        int index = 0;
            for (int i=0;i<categories.length;i++)
            {
                if (item.getCategory().equals(categories[i]))
                {
                    index = i;
                }
            }
        categoryImage.setImageResource(categioriesImageId[index]);

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
                Toast.makeText(mContext, item.getName(), Toast.LENGTH_SHORT).show();
            }
        });


        viewHolder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(mContext, EditExpenseActivity.class);

                    Expense expense = mExpenseList.get(position);

                    int pos = position;

                    editIntent.putExtra("expense",expense);
                    editIntent.putExtra("categories",categories);
                    editIntent.putExtra("position",pos);
                    ((Activity) mContext).startActivityForResult(editIntent,EDIT_EXPENSE_ACTIVITY_REQUEST_CODE);

                Log.d("aktywnosc", "Edit");
                mItemManger.closeItem(position);

                // Toast.makeText(view.getContext(), "Clicked on Edit  " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deleteIntent = new Intent(mContext, DeleteExpenseActivity.class);
                mItemManger.removeShownLayouts(viewHolder.swipeLayout);
                mExpenseList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mExpenseList.size());
                mItemManger.closeAllItems();
                Toast.makeText(v.getContext(), "Deleted " + item.getName(), Toast.LENGTH_SHORT).show();

             //   deleteIntent.putExtra("expense", item);
                ((Activity) mContext).startActivityForResult(deleteIntent,DELETE_EXPENSE_ACTIVITY_REQUEST_CODE);

            }
        });

        mItemManger.bindView(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mExpenseList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        public SwipeLayout swipeLayout;
        public ImageButton Delete;
        public ImageButton Edit;

        TextView expense;
        TextView category;
        ImageView categoryImage;
        TextView price;
        TextView date;


        public SimpleViewHolder(View itemView) {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);;
            Delete =  itemView.findViewById(R.id.Delete);
            Edit =  itemView.findViewById(R.id.Edit);
            expense = itemView.findViewById(R.id.expenseInfoTextView);
            category = itemView.findViewById(R.id.categoryTextView);
            categoryImage = itemView.findViewById(R.id.categoryImageView);
            price = itemView.findViewById(R.id.priceTextView);
            date = itemView.findViewById(R.id.nameTextView);

        }
    }
}