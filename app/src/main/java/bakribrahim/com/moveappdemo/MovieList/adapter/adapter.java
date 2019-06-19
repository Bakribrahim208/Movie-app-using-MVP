package bakribrahim.com.moveappdemo.MovieList.adapter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.ColorUtils;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;
import bakribrahim.com.moveappdemo.Models.Movie.Movie;
import bakribrahim.com.moveappdemo.MovieList.UI.popularFragment;
import bakribrahim.com.moveappdemo.R;

public class adapter  extends RecyclerView.Adapter<adapter.FeedViewHolder> {

    List<Movie> movieList =new ArrayList<>();
    popularFragment activity;

    public adapter(popularFragment activity) {
        this.activity=activity;
     }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }


    public void ResetMovieList() {
        this.movieList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movecell, parent, false);
        adapter.FeedViewHolder feedViewHolder = new adapter.FeedViewHolder(view);
        return feedViewHolder;
     }

    @Override
    public void onBindViewHolder(@NonNull final FeedViewHolder holder, final int position) {
        holder.title.setText(movieList.get(position).getTitle());
         holder.date.setText(movieList.get(position).getReleaseDate());
        holder.rate.setText(String.valueOf(movieList.get(position).getRating()));
           RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.NORMAL)
                .dontAnimate()
                .dontTransform();

        Glide.with(activity)
                .asBitmap()
                .load("https://image.tmdb.org/t/p/w200/"+ movieList.get(position).getThumbPath())
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        //put animation view when failed to load image
                        holder.animation_view.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target,
                                                   DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        holder.animation_view.setVisibility(View.GONE);
                        holder.poster.setImageBitmap(resource);
                        Palette.from(resource).generate(palette -> {
                            assert palette != null;
                            if (palette.getDarkMutedSwatch() != null) {
                                int rgb = ColorUtils.blendARGB(
                                        palette.getDarkMutedSwatch().getRgb(),
                                        Color.BLACK,
                                        0.3f
                                );

                                holder.Linearlayout.setBackgroundColor(rgb);
                            } else if (palette.getMutedSwatch() != null) {
                                int rgb = ColorUtils.blendARGB(
                                        palette.getMutedSwatch().getRgb(),
                                        Color.BLACK,
                                        0.3f
                                );

                                holder.Linearlayout.setBackgroundColor(rgb);
                            } else if (palette.getDarkVibrantSwatch() != null) {
                                int rgb = ColorUtils.blendARGB(
                                        palette.getDarkVibrantSwatch().getRgb(),
                                        Color.BLACK,
                                        0.3f
                                );

                                holder.Linearlayout.setBackgroundColor(rgb);
                            }
                        });
                        return false;
                    }
                }).apply(options)
                .into(holder.poster);;


       // holder.type.setText(String.valueOf("Adult : " + movieList.get(position).get()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    activity.MovieItemclick(position, v);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder
    {

        TextView title, date,rate;
        ImageView poster ;
        ProgressBar progressBar;
        LinearLayout Linearlayout;
        LottieAnimationView animation_view;
        public FeedViewHolder(View itemView)
        {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
             date = (TextView) itemView.findViewById(R.id.date);
             rate = (TextView) itemView.findViewById(R.id.rate);
            poster = (ImageView) itemView.findViewById(R.id.item_image_img);
            Linearlayout= (LinearLayout) itemView.findViewById(R.id.Linearlayout);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar2);
            animation_view= itemView.findViewById(R.id.animation_view);
         }



        }









}
