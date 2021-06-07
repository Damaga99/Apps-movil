package es.imovildani.tanteofinal;

import android.os.Parcel;
import android.os.Parcelable;


public class TeamPoints implements Parcelable{
    private int de1, de2, de3;



    public TeamPoints() {
        de1=0;
        de2=0;
        de3=0;
    }


    protected TeamPoints(Parcel in){
        de1 = in.readInt();
        de2 = in.readInt();
        de3 = in.readInt();

    }


    public static final Creator<TeamPoints> CREATOR = new Creator<TeamPoints>()    {
        @Override
        public TeamPoints createFromParcel(Parcel in) {
            return new TeamPoints(in);
        }

        @Override
        public TeamPoints[] newArray(int size){
            return new TeamPoints[size];
        }

    };



    public int getDe1(){return de1;}

    public void setDe1(int de1){this.de1=de1;}

    public int getDe2(){return de2;}

    public void setDe2(int de2){this.de2=de2;}

    public int getDe3(){return de3;}

    public void setDe3(int de3){this.de3=de3;}

    public int getPoints(){return de1 + de2*2 + de3*3;}


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.de1);
        dest.writeInt(this.de2);
        dest.writeInt(this.de3);

    }




}
