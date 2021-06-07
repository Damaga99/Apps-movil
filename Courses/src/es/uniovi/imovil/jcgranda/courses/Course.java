package es.uniovi.imovil.jcgranda.courses;

import android.os.Parcel;
import android.os.Parcelable;

public class Course implements Parcelable {
	
	private String mName;
	private String mTeacher;
	private String mDescription;
	
	public Course(String name, String teacher, String description) {
		
		if (name == null || teacher == null || name.isEmpty() || teacher.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		mName = name;
		mTeacher = teacher;
		mDescription = description;
	}

	public String getName() {
		
		return mName;
	}

	public String getTeacher() {
		
		return mTeacher;
	}
	
	public String getDescription() {
		
		return mDescription;
	}
	
	public Course(Parcel parcel) {
		
		readFromParcel(parcel);
	}
	
	@Override
	public int describeContents() {
		
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		
		dest.writeString(mName);
		dest.writeString(mTeacher);
		dest.writeString(mDescription);
	}
	
	private void readFromParcel(Parcel parcel) {
		
		mName = parcel.readString();
		mTeacher = parcel.readString();
		mDescription = parcel.readString();
	}
	
	public static final Parcelable.Creator<Course> CREATOR = new Parcelable.Creator<Course>() {  
	    
        public Course createFromParcel(Parcel in) { 
        	
            return new Course(in);  
        }  
   
        public Course[] newArray(int size) {  
        	
            return new Course[size];  
        }            
    };    
}
