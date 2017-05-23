package com.example.uberv.expandablerecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ExpandableAdapter extends RecyclerView.Adapter<ExpandableAdapter.ViewHolder> {
    public static final String LOG_TAG = ExpandableAdapter.class.getSimpleName();

    private List<Person> data;
    private int mExpandedPosition = -1;
    private Context context;
    private RecyclerView recyclerView;

    public ExpandableAdapter(Context ctx, RecyclerView recyclerView) {
        context = ctx;
        this.recyclerView = recyclerView;
    }

    public void setData(List<Person> data) {
        this.data = data;
    }

    public List<Person> getData() {
        return data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View personView = inflater.inflate(R.layout.item_person, viewGroup, false);
        ViewHolder personVH = new ViewHolder(personView);

        return personVH;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final boolean isExpanded = position == mExpandedPosition;
        holder.hiddenArea.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.root.setElevation(isExpanded ? 16 : 0);
        holder.itemView.setActivated(isExpanded);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1 : position;
                TransitionManager.beginDelayedTransition(recyclerView);
                notifyDataSetChanged();
            }
        });
        holder.phoneNumberTv.setText(data.get(position).getPhoneNumber());
        holder.nameSurnameTv.setText(data.get(position).getFirstName() + " " + data.get(position).getLastName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameSurnameTv;
        public TextView phoneNumberTv;
        public Button callBtn;
        public View hiddenArea;
        public View root;

        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            nameSurnameTv = (TextView) itemView.findViewById(R.id.name_surname_tv);
            phoneNumberTv = (TextView) itemView.findViewById(R.id.phone_number_tv);
            callBtn = (Button) itemView.findViewById(R.id.call_btn);
            hiddenArea = itemView.findViewById(R.id.hidden_content);
        }
    }
}
