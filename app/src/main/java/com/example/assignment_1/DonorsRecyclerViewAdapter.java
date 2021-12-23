package com.example.assignment_1;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

public class DonorsRecyclerViewAdapter extends RecyclerView.Adapter<DonorsRecyclerViewAdapter.ViewHolder> implements Filterable {

    private Context mContext;
    ImageButton btnOutgoingCall;
    EditText contactInfo;
    private ArrayList<Donors> alldonors;
    private ArrayList<Donors> filteredDonors;

    public Donors getItem(int position) {
        return filteredDonors.get(position);
    }

    public int getCount() {
        return filteredDonors.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Donors donor = getItem(position);

        View mView = convertView ;
        if(mView == null){
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = vi.inflate(R.layout.donors_list,null);
        }

        String cantDonateNow = "Can't Donate now!";
        String hidden = "Hidden!";
        SpannableString ss = new SpannableString(hidden);
        ForegroundColorSpan fcsGray = new ForegroundColorSpan(17170432);
        ss.setSpan(fcsGray,0,7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //View mView = convertView;
        TextView donationStatusText = (TextView) mView.findViewById(R.id.donation_status_text);
        TextView availableText = (TextView) mView.findViewById(R.id.available_text);
        if(donationStatusText.getText().toString().contains("Can't Donate now!".toLowerCase()))
        {
            availableText.setText(ss);
        }
        return mView;
    }


    public DonorsRecyclerViewAdapter(ArrayList<Donors> list) {
        //this.mContext = context;
        this.filteredDonors = list;
        this.alldonors = new ArrayList<>(filteredDonors);
    }

    @NonNull
    @Override
    public DonorsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donors_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonorsRecyclerViewAdapter.ViewHolder holder, int position) {
        Donors donor = alldonors.get(position);
        holder.donor_name.setText(donor.getName());
        holder.city_text.setText(donor.getCity());
        holder.donation_status_text.setText(donor.getDonation_status());
        holder.available_text.setText(donor.getAvailability_status());
        holder.bloodGroup_image.setImageResource(donor.getBlood_group_image());
    }

    @Override
    public int getItemCount() {
        return alldonors.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView bloodGroup_image;

        public ImageView contact_image;
        public TextView donor_name;

        public ImageView city_image;
        public TextView city_text;

        public ImageView donate_image;
        public TextView donation_status_text;

        public ImageView available_image;
        public TextView available_text;

        public ImageView phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bloodGroup_image = itemView.findViewById(R.id.bloodGroup_image);
            //contact_image=itemView.findViewById(R.id.contact_image);
            donor_name = itemView.findViewById(R.id.donor_name);
            //city_image=itemView.findViewById(R.id.city_image);
            city_text = itemView.findViewById(R.id.city_text);
            //donate_image=itemView.findViewById(R.id.donate_image);
            donation_status_text = itemView.findViewById(R.id.donation_status_text);
            //available_image=itemView.findViewById(R.id.available_image);
            available_text = itemView.findViewById(R.id.available_text);
            //phone=itemView.findViewById(R.id.phone);
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }



    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Donors> filteredList=new ArrayList<>();

            if(constraint.toString().isEmpty()){
                filteredList.addAll(filteredDonors);
            }
            else {
                for(Donors donor:filteredDonors){
                    if(donor.getName().toLowerCase().contains(constraint.toString().toLowerCase())
                            || donor.getCity().toLowerCase().contains(constraint.toString().toLowerCase())
                            || donor.getBlood_group().toLowerCase().contains(constraint.toString().toLowerCase()))
                    {
                        filteredList.add(donor);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            alldonors.clear();
            alldonors.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };
}
