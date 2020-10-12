package br.com.julian.movieapp.ui.movieList;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import br.com.julian.movieapp.databinding.ItemMovieBinding;
import br.com.julian.movieapp.models.MovieModel;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder> {

    private List<MovieModel> mMovieModelList;
    private MovieListViewModel mMovieListViewModel;

    public MovieListAdapter(
            List<MovieModel> movieModelList,
            MovieListViewModel movieListViewModel
    ) {
        mMovieModelList = movieModelList;
        mMovieListViewModel = movieListViewModel;
    }

    public static class MovieListViewHolder extends RecyclerView.ViewHolder {
        public ItemMovieBinding itemMovieBinding;

        public MovieListViewHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            itemMovieBinding = binding;
        }
    }

    @NotNull
    @Override
    public MovieListAdapter.MovieListViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemMovieBinding binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MovieListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MovieListViewHolder holder, int position) {
        holder.itemMovieBinding.setMovieModel(mMovieModelList.get(position));
        holder.itemMovieBinding.setViewModel(mMovieListViewModel);
        holder.itemMovieBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mMovieModelList.size();
    }
}
