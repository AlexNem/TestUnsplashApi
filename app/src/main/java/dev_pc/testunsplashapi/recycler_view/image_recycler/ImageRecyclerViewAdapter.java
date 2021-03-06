package dev_pc.testunsplashapi.recycler_view.image_recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import dev_pc.testunsplashapi.ui.fragment.IListFragment;
import dev_pc.testunsplashapi.R;
import dev_pc.testunsplashapi.model.Photo;

public class ImageRecyclerViewAdapter extends RecyclerView.Adapter<ImageRecyclerViewAdapter.ViewHolder> {

    private final List<Photo> mValues;
    private final IListFragment.Presenter mListener;
    private Context context;

    public ImageRecyclerViewAdapter(List<Photo> items, IListFragment.Presenter listener) {
        mValues = items;
        mListener = listener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        android.view.View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mPhoto = mValues.get(position);
        context = holder.mImageView.getContext();
        Glide.with(context)
                .load(holder.mPhoto.getUrls().getRegular())
                .into(holder.mImageView);
        holder.mLikeCount.setText(holder.mPhoto.getLikes());
        if (holder.mPhoto.isLikedByUser()){
            Glide.with(context)
                    .load(R.drawable.ic_favorite_red_24dp)
                    .into(holder.mLike);
        }else Glide.with(context)
                .load(R.drawable.ic_faworite_empty)
                .into(holder.mLike);
        holder.mLike.setOnClickListener(v -> mListener.onLike(holder.mPhoto));

//        holder.mView.setOnClickListener(new android.view.View.OnClickListener() {
//            @Override
//            public void onClick(android.view.View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mPhoto);
//                }
//            }
//        });

    }

    @Override
    public int getItemCount(){
        if (mValues == null) return 0;
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final android.view.View mView;
        final ImageView mImageView, mLike, mDownload, mCollections;
        final TextView mLikeCount;
        Photo mPhoto;

        ViewHolder(android.view.View view) {
            super(view);
            mView = view;
            mImageView = view.findViewById(R.id.image);
            mLike = view.findViewById(R.id.like_image);
            mDownload = view.findViewById(R.id.download_image);
            mCollections = view.findViewById(R.id.collections);
            mLikeCount = view.findViewById(R.id.like_count);

        }
    }
}
