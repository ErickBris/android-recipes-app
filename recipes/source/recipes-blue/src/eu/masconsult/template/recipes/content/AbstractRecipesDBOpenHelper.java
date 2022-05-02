/*
 * Generated by Robotoworks Mechanoid
 */
package eu.masconsult.template.recipes.content;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.robotoworks.mechanoid.db.MechanoidSQLiteOpenHelper;
import com.robotoworks.mechanoid.db.SQLiteMigration;

import eu.masconsult.template.recipes.content.migrations.DefaultRecipesDBMigrationV1;
import eu.masconsult.template.recipes.content.migrations.DefaultRecipesDBMigrationV2;

public abstract class AbstractRecipesDBOpenHelper extends MechanoidSQLiteOpenHelper {
	private static final String DATABASE_NAME = "RecipesDB.db";

	public static final int VERSION = 2;

	public interface Sources {
		String RECIPES = "recipes";
		String INGREDIENTS = "ingredients";
		String CATEGORIES = "categories";
		String SEARCH = "search";
		String SEARCH_WITH_RECIPE = "search_with_recipe";
	}

	public AbstractRecipesDBOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}

	public AbstractRecipesDBOpenHelper(Context context, String name) {
		super(context, name, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		applyMigrations(db, 0, VERSION);
	}
	

	@Override
	protected SQLiteMigration createMigration(int version) {
		switch(version) {
			case 0:
				return createRecipesDBMigrationV1();
			case 1:
				return createRecipesDBMigrationV2();
			default:
				throw new IllegalStateException("No migration for version " + version);
		}
	}
	
	protected SQLiteMigration createRecipesDBMigrationV1() {
		return new DefaultRecipesDBMigrationV1();
	}
	protected SQLiteMigration createRecipesDBMigrationV2() {
		return new DefaultRecipesDBMigrationV2();
	}
}
