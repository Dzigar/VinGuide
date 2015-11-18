package com.vinguide.dao.implementation;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.vinguide.activity.MainActivity;
import com.vinguide.dao.interfaces.PlaceDAO;
import com.vinguide.model.Place;
import com.vinguide.string.Strings;

public class PlaceDAOImpl extends BaseDaoImpl<Place, Integer> implements
		PlaceDAO {

	public PlaceDAOImpl(ConnectionSource connectionSource,
			Class<Place> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}

	@Override
	public List<Place> getPlacesByCategory(int i) throws SQLException {
		QueryBuilder<Place, Integer> queryBuilder = this.queryBuilder();
		queryBuilder.where().eq(Strings.COLUMN_CATEGORY, i);
		PreparedQuery<Place> preparedQuery = queryBuilder.prepare();
		return query(preparedQuery);
	}

	@Override
	public Place getPlaceById(int id) throws SQLException {
		return queryForId(id);
	}

	@Override
	public int createPlace(Place place) throws SQLException {
		return super.create(place);
	}

	@Override
	public List<Place> getPlaces() throws SQLException {
		return super.queryForAll();
	}

	@Override
	public int deleteAll() throws SQLException {
		return super.delete(queryForAll());
	}

	@Override
	public void updatePlace(Place place) throws SQLException {
		super.update(place);
	}

	@Override
	public List<Place> getFavorites() throws SQLException {
		QueryBuilder<Place, Integer> queryBuilder = this.queryBuilder();
		queryBuilder.where().eq(Strings.COLUMN_FAVORITES, true);
		PreparedQuery<Place> preparedQuery = queryBuilder.prepare();
		MainActivity.openLiked = false;
		return query(preparedQuery);
	}

	@Override
	public void updateViaActivity(Place place) throws SQLException {
		super.update(place);
	}

}
