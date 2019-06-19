package bakribrahim.com.moveappdemo.MovieDetails.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import bakribrahim.com.moveappdemo.CallBack.ReviewCallback;
import bakribrahim.com.moveappdemo.Models.Reviews.Review;
import bakribrahim.com.moveappdemo.MovieDetails.UI.ViewDialog;
import bakribrahim.com.moveappdemo.R;

public class Review_Adapter  extends RecyclerView.Adapter<Review_Adapter.MyViewHolder> {
    ReviewCallback reviewCallback;
    private Context mContext;
    private List<Review> Review= new ArrayList<>();
    public Review_Adapter(Context mContext   ,  ReviewCallback reviewCallback) {
        this.mContext = mContext;
        this.reviewCallback=reviewCallback;

    }

    public void setReview(List<bakribrahim.com.moveappdemo.Models.Reviews.Review> review) {
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
        final Review review = Review.get(position);
        holder.author.setText(review.getAuthor());
        holder.content.setText(review.getContent());
        //addreadmore( holder.content, review.getContent());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                reviewCallback.openReview(review);

            }
        });

    }
//           public void addreadmore(final TextView textView , final String text )
//           {
//               textView.post(new Runnable() {
//                   @Override
//                   public void run() {
//                       // Past the maximum number of lines we want to display.
//                       if (textView.getLineCount() > 3) {
//                           int lastCharShown = textView.getLayout().getLineVisibleEnd(3 - 1);
//
//                           textView.setMaxLines(3);
//
//                           String moreString = "read more";
//                           String suffix = "  " + moreString;
//
//                           // 3 is a "magic number" but it's just basically the length of the ellipsis we're going to insert
//                           String actionDisplayText = text.substring(0, lastCharShown - suffix.length() - 3) + "..." + suffix;
//
//                           SpannableString truncatedSpannableString = new SpannableString(actionDisplayText);
//                           int startIndex = actionDisplayText.indexOf(moreString);
//                           truncatedSpannableString.setSpan(new ForegroundColorSpan(Color.BLUE), startIndex, startIndex + moreString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                           textView.setText(truncatedSpannableString);
//                       }
//                   }
//               });
//           }
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
