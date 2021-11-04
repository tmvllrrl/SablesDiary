package com.example.sablesdiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sablesdiary.database.DiaryEntry;

import org.w3c.dom.Text;

import java.util.List;

public class DiaryListAdapter extends RecyclerView.Adapter<DiaryListAdapter.DiaryViewHolder> {

    private ItemClickListener mClickListener;
    private List<DiaryEntry> mDiaryEntries;
    private Context mContext;

    public DiaryListAdapter(Context context, ItemClickListener clickListener) {
        mContext = context;
        mClickListener = clickListener;
    }

    @Override
    public DiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.mainactivity_rv_listitem_test, parent, false);
        return new DiaryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DiaryViewHolder holder, int position) {
       DiaryEntry entry = mDiaryEntries.get(position);
       String weightText = entry.getWeight() + " g";
       String foodBeforeText = entry.getFoodOriginally() + " g";
       String foodAfterText = entry.getFoodAfter() + " g";
       String waterBeforeText = entry.getWaterOriginally() + " mL";
       String waterAfterText = entry.getWaterAfter() + " mL";

       holder.date.setText(entry.getDate());
       holder.weight.setText(weightText);
       holder.foodBefore.setText(foodBeforeText);
       holder.foodAfter.setText(foodAfterText);
       holder.waterBefore.setText(waterBeforeText);
       holder.waterAfter.setText(waterAfterText);

    }

    void setEntries(List<DiaryEntry> entries){
        mDiaryEntries = entries;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mDiaryEntries != null)
            return mDiaryEntries.size();
        else return 0;
    }

    class DiaryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView date;
        private TextView weight;
        private TextView foodBefore;
        private TextView foodAfter;
        private TextView waterBefore;
        private TextView waterAfter;

        private DiaryViewHolder(View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.tv_listitem_dateheader);
            weight = itemView.findViewById(R.id.tv_listitem_weight);
            foodBefore = itemView.findViewById(R.id.tv_listItem_foodBefore);
            foodAfter = itemView.findViewById(R.id.tv_listItem_foodAfter);
            waterBefore = itemView.findViewById(R.id.tv_listItem_waterBefore);
            waterAfter = itemView.findViewById(R.id.tv_listItem_waterAfter);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int itemId = mDiaryEntries.get(getAdapterPosition()).getID();
            if (mClickListener != null) mClickListener.onItemClick(itemId);
        }

        @Override
        public boolean onLongClick(View v) {
            int itemId = mDiaryEntries.get(getAdapterPosition()).getID();
            if (mClickListener != null) mClickListener.onItemLongClick(itemId);
            return true;
        }
    }

    public interface ItemClickListener {
        void onItemClick(int id);
        void onItemLongClick(int id);
    }

}
