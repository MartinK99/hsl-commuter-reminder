package fi.metropolia.martikas.commuterreminder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import fi.metropolia.martikas.commuterreminder.model.Feature;
import fi.metropolia.martikas.commuterreminder.model.SearchResultStructure;

/**
 * The adapter for the recycler list view in SearchbarActivity.
 * It recieves custom Objects from the REST-API response called Features and shows attributes from it, while preserving others.
 * That way additional information not shown can be retrieved.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    public static class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView textView;
        private Feature data;

        public SearchViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.search_item);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(data);
        }

    }

    public interface ClickListener {
        void onItemClick(Feature item);
    }

    private static ClickListener clickListener;
    private SearchResultStructure searchList;

    public SearchAdapter() {
        super();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_text_view, parent, false);
        //Give view as it is
        SearchViewHolder vh = new SearchViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.data = searchList.getFeatures()[position];
        holder.textView.setText(searchList.getFeatures()[position].getProperties().getLabel());
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        SearchAdapter.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        if (searchList != null)
            return searchList.getFeatures().length;
        else
            return 0;
    }

    public void setSearchList(SearchResultStructure searchList) {
        this.searchList = searchList;
    }
}
