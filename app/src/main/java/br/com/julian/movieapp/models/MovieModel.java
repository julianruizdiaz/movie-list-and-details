package br.com.julian.movieapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MovieModel implements Parcelable {
    @SerializedName("id")
    Integer movieId;
    @SerializedName("title")
    String title;
    @SerializedName("original_title")
    String originalTitle;
    @SerializedName("poster_path")
    String posterPath;
    @SerializedName("vote_count")
    Integer voteCount;
    @SerializedName("vote_average")
    Float voteAverage;
    @SerializedName("overview")
    String overview;
    @SerializedName("release_date")
    String releaseDate;

    public Boolean hasReleaseDate() {
        return this.releaseDate != null;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public MovieModel() {}

    protected MovieModel(Parcel in) {
        movieId = in.readByte() == 0x00 ? null : in.readInt();
        title = in.readString();
        originalTitle = in.readString();
        posterPath = in.readString();
        voteCount = in.readByte() == 0x00 ? null : in.readInt();
        voteAverage = in.readByte() == 0x00 ? null : in.readFloat();
        overview = in.readString();
        releaseDate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (movieId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(movieId);
        }
        dest.writeString(title);
        dest.writeString(originalTitle);
        dest.writeString(posterPath);
        if (voteCount == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(voteCount);
        }
        if (voteAverage == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeFloat(voteAverage);
        }
        dest.writeString(overview);
        dest.writeString(releaseDate);
    }

    public static final Parcelable.Creator<MovieModel> CREATOR = new Parcelable.Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };
}