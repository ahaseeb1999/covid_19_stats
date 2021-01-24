package com.example.mad_projcovid19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;



public class MyAdaptor extends ArrayAdapter<Country_Data> {
    private Context context;
    private List<Country_Data> Country_DatasList;
    private List<Country_Data> Country_DatasListFiltered;

    public MyAdaptor(Context context, List<Country_Data> Country_DatasList) {
        super(context, R.layout.adaptor_custom_list, Country_DatasList);

        this.context = context;
        this.Country_DatasList = Country_DatasList;
        this.Country_DatasListFiltered = Country_DatasList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adaptor_custom_list,null,true);
        TextView tvCountryName = view.findViewById(R.id.tvCountryName);
        ImageView imageView = view.findViewById(R.id.imageFlag);

        tvCountryName.setText(Country_DatasListFiltered.get(position).getCountry());
        Glide.with(context).load(Country_DatasListFiltered.get(position).getFlag()).into(imageView);

        return view;
    }


    @Override
    public int getCount() {
        return Country_DatasListFiltered.size();
    }

    @Nullable
    @Override
    public Country_Data getItem(int position) {
        return Country_DatasListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = Country_DatasList.size();
                    filterResults.values = Country_DatasList;

                }else{
                    List<Country_Data> resultsModel = new ArrayList<>();
                    String stringSearch = constraint.toString().toLowerCase();

                    for(Country_Data itemsModel:Country_DatasList){
                        if(itemsModel.getCountry().toLowerCase().contains(stringSearch)){
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                Country_DatasListFiltered = (List<Country_Data>) results.values;
                CountryStats.Country_DatasList = (List<Country_Data>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}
