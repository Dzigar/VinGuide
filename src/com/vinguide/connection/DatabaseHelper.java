package com.vinguide.connection;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.vinguide.dao.implementation.PlaceDAOImpl;
import com.vinguide.dao.interfaces.PlaceDAO;
import com.vinguide.model.Place;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String TAG = DatabaseHelper.class.getSimpleName();

	private static final String DATABASE_NAME = "vinguide.db";

	public static int DATABASE_VERSION = 1;

	private static final AtomicInteger usageCounter = new AtomicInteger(0);

	private PlaceDAO placeDAO = null;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, Place.class);
		} catch (SQLException e) {
			Log.e(TAG, "error creating DB " + DATABASE_NAME);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVer, int newVer) {
		Log.d("deb", "db onUpgrade ");
		try {
			TableUtils.createTable(connectionSource, Place.class);
			Log.d("deb", "db Upgraded");
		} catch (SQLException e) {
			Log.e(TAG, "error upgrading db " + DATABASE_NAME + "from ver "
					+ oldVer);
			throw new RuntimeException(e);
		}

	}

	public PlaceDAO getPlaceDAO() throws SQLException {
		if (placeDAO == null) {
			try {
				placeDAO = new PlaceDAOImpl(getConnectionSource(), Place.class);
			} catch (Exception e) {
				Log.i(getClass().getName(), e.getLocalizedMessage());
			}
		}
		return placeDAO;
	}

	@Override
	public void close() {
		try {
			if (usageCounter.decrementAndGet() <= 0) {
				HelperFactory.releaseHelper();
				placeDAO = null;
				super.close();
			}
		} catch (Exception e) {
			Log.i(getClass().getName(), e.getLocalizedMessage());
		}
	}
}