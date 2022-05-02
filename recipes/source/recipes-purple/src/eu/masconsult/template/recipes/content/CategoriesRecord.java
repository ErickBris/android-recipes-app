/*
 * Generated by Robotoworks Mechanoid
 */
package eu.masconsult.template.recipes.content;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import eu.masconsult.template.recipes.content.RecipesDBContract.Categories;
import eu.masconsult.template.recipes.content.RecipesDBContract.Categories.Builder;
import com.robotoworks.mechanoid.util.Closeables;
import com.robotoworks.mechanoid.db.ActiveRecord;
import com.robotoworks.mechanoid.db.ActiveRecordFactory;
import com.robotoworks.mechanoid.Mechanoid;
import com.robotoworks.mechanoid.db.AbstractValuesBuilder;

public class CategoriesRecord extends ActiveRecord implements Parcelable {

	private static ActiveRecordFactory<CategoriesRecord> sFactory = new ActiveRecordFactory<CategoriesRecord>() {
		@Override
		public CategoriesRecord create(Cursor c) {
			return fromCursor(c);
		}
		
		@Override
		public String[] getProjection() {
			return PROJECTION;
		}
	};
	
	public static ActiveRecordFactory<CategoriesRecord> getFactory() {
		return sFactory;
	}

    public static final Parcelable.Creator<CategoriesRecord> CREATOR 
    	= new Parcelable.Creator<CategoriesRecord>() {
        public CategoriesRecord createFromParcel(Parcel in) {
            return new CategoriesRecord(in);
        }

        public CategoriesRecord[] newArray(int size) {
            return new CategoriesRecord[size];
        }
    };
    
    public static String[] PROJECTION = {
    	Categories._ID,
    	Categories.CATEGORY
    };
    
    public interface Indices {
    	int _ID = 0;
    	int CATEGORY = 1;
    }
    
    private String mCategory;
    private boolean mCategoryDirty;
    
    @Override
    protected String[] _getProjection() {
    	return PROJECTION;
    }
    
    public void setCategory(String category) {
    	mCategory = category;
    	mCategoryDirty = true;
    }
    
    public String getCategory() {
    	return mCategory;
    }
    
    public CategoriesRecord() {
    	super(Categories.CONTENT_URI);
	}
	
	private CategoriesRecord(Parcel in) {
    	super(Categories.CONTENT_URI);
    	
		setId(in.readLong());
		
		mCategory = in.readString();
		
		boolean[] dirtyFlags = new boolean[1];
		in.readBooleanArray(dirtyFlags);
		mCategoryDirty = dirtyFlags[0];
	}
	
	@Override
	public int describeContents() {
	    return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(getId());
		dest.writeString(mCategory);
		dest.writeBooleanArray(new boolean[] {
			mCategoryDirty
		});
	}
	
	@Override
	protected AbstractValuesBuilder createBuilder() {
		Builder builder = Categories.newBuilder();

		if(mCategoryDirty) {
			builder.setCategory(mCategory);
		}
		
		return builder;
	}
	
    @Override
	public void makeDirty(boolean dirty){
		mCategoryDirty = dirty;
	}

	@Override
	protected void setPropertiesFromCursor(Cursor c) {
		setId(c.getLong(Indices._ID));
		setCategory(c.getString(Indices.CATEGORY));
	}
	
	public static CategoriesRecord fromCursor(Cursor c) {
	    CategoriesRecord item = new CategoriesRecord();
	    
		item.setPropertiesFromCursor(c);
		
		item.makeDirty(false);
		
	    return item;
	}
	
	public static CategoriesRecord fromBundle(Bundle bundle, String key) {
		bundle.setClassLoader(CategoriesRecord.class.getClassLoader());
		return bundle.getParcelable(key);
	}
	
	public static CategoriesRecord get(long id) {
	    Cursor c = null;
	    
	    ContentResolver resolver = Mechanoid.getContentResolver();
	    
	    try {
	        c = resolver.query(Categories.CONTENT_URI.buildUpon()
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
