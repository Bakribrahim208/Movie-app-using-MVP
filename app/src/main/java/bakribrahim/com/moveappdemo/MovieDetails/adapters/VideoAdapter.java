package bakribrahim.com.moveappdemo.MovieDetails.adapters;
 import android.content.ActivityNotFoundException;
 import android.content.Context;
 import android.content.Intent;
 import android.graphics.drawable.Drawable;
 import android.net.Uri;
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

 import androidx.annotation.Nullable;
 import androidx.recyclerview.widget.RecyclerView;
 import bakribrahim.com.moveappdemo.CallBack.VideoCallBack;
 import bakribrahim.com.moveappdemo.Models.Videos.Video;
 import bakribrahim.com.moveappdemo.R;



public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.FeedViewHolder>
{

     VideoCallBack videoCallBack;
     List<Video>Videolist =new ArrayList<>();
    Context context;
    public class FeedViewHolder extends RecyclerView.ViewHolder
    {
         ImageView poster ;
        ProgressBar progressBar;
        TextView video_name;
        public FeedViewHolder(View itemView)
        {
            super(itemView);
            poster =  itemView.findViewById(R.id.Video_image);
            video_name=  itemView.findViewById(R.id.txt_videoname);
            poster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }




    }

    public VideoAdapter(Context con , VideoCallBack videoCallBack)
    {
         this.videoCallBack=videoCallBack;
        this.context=con;

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setVideolist(List<Video> videolist) {
        Videolist = videolist;
        notifyDataSetChanged();
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        FeedViewHolder feedViewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tairl_item, parent, false);
        feedViewHolder = new FeedViewHolder(view);
        return feedViewHolder;
    }
    @Override
    public void onBindViewHolder(final FeedViewHolder holder, int position)
    {
        holder.video_name.setText(Videolist.get(position).getName());
        // loading cast profile pic using Glide library
        Glide.with(context)
                .load("https://img.youtube.com/vi/"+Videolist.get(position).getKey()+"/3.jpg")
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.poster.setVisibility(View.GONE);
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

        holder.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoCallBack.VideoSHow(Videolist.get(position).getKey());
            }
        });
     }
    @Override
    public int getItemCount()
    {
        return Videolist.size();
    }

 }
