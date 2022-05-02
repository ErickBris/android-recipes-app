/*
 * Generated by Robotoworks Mechanoid
 */
package eu.masconsult.template.recipes.content;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import eu.masconsult.template.recipes.content.RecipesDBContract.Search;
import eu.masconsult.template.recipes.content.RecipesDBContract.Search.Builder;
import com.robotoworks.mechanoid.util.Closeables;
import com.robotoworks.mechanoid.db.ActiveRecord;
import com.robotoworks.mechanoid.db.ActiveRecordFactory;
import com.robotoworks.mechanoid.Mechanoid;
import com.robotoworks.mechanoid.db.AbstractValuesBuilder;

public class SearchRecord extends ActiveRecord implements Parcelable {

	private static ActiveRecordFactory<SearchRecord> sFactory = new ActiveRecordFactory<SearchRecord>() {
		@Override
		public SearchRecord create(Cursor c) {
			return fromCursor(c);
		}
		
		@Override
		public String[] getProjection() {
			return PROJECTION;
		}
	};
	
	public static ActiveRecordFactory<SearchRecord> getFactory() {
		return sFactory;
	}

    public static final Parcelable.Creator<SearchRecord> CREATOR 
    	= new Parcelable.Creator<SearchRecord>() {
        public SearchRecord createFromParcel(Parcel in) {
            return new SearchRecord(in);
        }

        public SearchRecord[] newArray(int size) {
            return new SearchRecord[size];
        }
    };
    
    public static String[] PROJECTION = {
    	Search._ID,
    	Search.STRING
    };
    
    public interface Indices {
    	int _ID = 0;
    	int STRING = 1;
    }
    
    private String mString;
    private boolean mStringDirty;
    
    @Override
    protected String[] _getProjection() {
    	return PROJECTION;
    }
    
    public void setString(String string) {
    	mString = string;
    	mStringDirty = true;
    }
    
    public String getString() {
    	return mString;
    }
    
    public SearchRecord() {
    	super(Search.CONTENT_URI);
	}
	
	private SearchRecord(Parcel in) {
    	super(Search.CONTENT_URI);
    	
		setId(in.readLong());
		
		mString = in.readString();
		
		boolean[] dirtyFlags = new boolean[1];
		in.readBooleanArray(dirtyFlags);
		mStringDirty = dirtyFlags[0];
	}
	
	@Override
	public int describeContents() {
	    return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(getId());
		dest.writeString(mString);
		dest.writeBooleanArray(new boolean[] {
			mStringDirty
		});
	}
	
	@Override
	protected AbstractValuesBuilder createBuilder() {
		Builder builder = Search.newBuilder();

		if(mStringDirty) {
			builder.setString(mString);
		}
		
		return builder;
	}
	
    @Override
	public void makeDirty(boolean dirty){
		mStringDirty = dirty;
	}

	@Override
	protected void setPropertiesFromCursor(Cursor c) {
		setId(c.getLong(Indices._ID));
		setString(c.getString(Indices.STRING));
	}
	
	public static SearchRecord fromCursor(Cursor c) {
	    SearchRecord item = new SearchRecord();
	    
		item.setPropertiesFromCursor(c);
		
		item.makeDirty(false);
		
	    return item;
	}
	
	public static SearchRecord fromBundle(Bundle bundle, String key) {
		bundle.setClassLoader(SearchRecord.class.getClassLoader());
		return bundle.getParcelable(key);
	}
	
	public static SearchRecord get(long id) {
	    Cursor c = null;
	    
	    ContentResolver resolver = Mechanoid.getContentResolver();
	    
	    try {
	        c = resolver.query(Search.CONTENT_URI.buildUpon()
			.appendPath(String.valueOf(id)).build(), PROJECTION, null, null, null);
	        
	        if(!c.moveToFirst()) {
	            return null;
	        }
	        
	        return fromCursor(c);
	    } finally {
	        Closeables.closeSilently(c);
	    }
	}
}
