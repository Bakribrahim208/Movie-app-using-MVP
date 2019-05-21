package bakribrahim.com.moveappdemo.MovieDetails;

import android.content.Context;
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
import androidx.recyclerview.widget.RecyclerView;
import bakribrahim.com.moveappdemo.Models.Cast;
import bakribrahim.com.moveappdemo.Models.Review;
import bakribrahim.com.moveappdemo.R;

public class Review_Adapter  extends RecyclerView.Adapter<Review_Adapter.MyViewHolder> {

    private Context mContext;
    private List<Review> Review= new ArrayList<>();

    public Review_Adapter(Context mContext) {
        this.mContext = mContext;

    }

    public void setReview(List<bakribrahim.com.moveappdemo.Models.Review> review) {
        Review = review;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Review_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_item, parent, false);

        return new Review_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final Review_Adapter.MyViewHolder holder, final int position) {
        Review review = Review.get(position);
        holder.author.setText(review.getAuthor());
        holder.content.setText(review.getContent());

    }

    @Override
    public int getItemCount() {
        return Review.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView author;

        public TextView content;



        public MyViewHolder(View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.text_author);
            content = itemView.findViewById(R.id.text_content);

        }
    }
}
