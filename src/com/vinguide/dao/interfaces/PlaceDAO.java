package com.vinguide.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.vinguide.model.Place;

public interface PlaceDAO {

	public int createPlace(Place place) throws SQLException;

	public List<Place> getPlacesByCategory(int category) throws SQLException;

	public Place getPlaceById(int id) throws SQLException;

	public List<Place> getPlaces() throws SQLException;

	public int deleteAll() throws SQLException;

	public void updatePlace(Place place) throws SQLException;

	public List<Place> getFavorites() throws SQLException;
	
	public void updateViaActivity(Place place) throws SQLException;
}
