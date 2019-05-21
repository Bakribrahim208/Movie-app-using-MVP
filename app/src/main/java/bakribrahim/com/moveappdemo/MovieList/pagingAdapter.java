package bakribrahim.com.moveappdemo.MovieList;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import bakribrahim.com.moveappdemo.Models.Movie;
import bakribrahim.com.moveappdemo.R;

public class pagingAdapter extends PagedListAdapter<Movie,pagingAdapter.FeedViewHolder> {    List<Movie> movieList =new ArrayList<>();
    popularFragment activity;

    public pagingAdapter(popularFragment activity) {
        super(movieItemCallback);
        this.activity=activity;
    }
     public static DiffUtil.ItemCallback<Movie>movieItemCallback =new DiffUtil.ItemCallback<Movie>() {
         @Override
         public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
             return oldItem.getId()==newItem.getId();
         }

         @Override
         public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
             return oldItem.equals(newItem);
         }
     };
    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public pagingAdapter.FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movecell, parent, false);
        pagingAdapter.FeedViewHolder feedViewHolder = new pagingAdapter.FeedViewHolder(view);
        return feedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final pagingAdapter.FeedViewHolder holder, final int position) {
        holder.title.setText(movieList.get(position).getTitle());
        holder.description.setText(movieList.get(position).getOverview());
        holder.date.setText(movieList.get(position).getReleaseDate());
        holder.rate.setText(String.valueOf(movieList.get(position).getRating()));
        Glide.with(activity)
                .load("https://image.tmdb.org/t/p/w200/"+ movieList.get(position).getThumbPath())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .apply(new RequestOptions().placeholder(R.drawable.placeholder).error(R.drawable.placeholder))
                .into(holder.poster);
        // holder.type.setText(String.valueOf("Adult : " + movieList.get(position).get()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.MovieItemclick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder
    {

        TextView title, description, date,type,rate;
        ImageView poster ;
        ProgressBar progressBar;
        public FeedViewHolder(View itemView)
        {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            date = (TextView) itemView.findViewById(R.id.date);
            type = (TextView) itemView.findViewById(R.id.type);
            rate = (TextView) itemView.findViewById(R.id.rate);
            poster = (ImageView) itemView.findViewById(R.id.item_image_img);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar2);
        }



    }









}
