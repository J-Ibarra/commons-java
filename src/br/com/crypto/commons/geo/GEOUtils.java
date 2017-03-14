package br.com.crypto.commons.geo;

import org.junit.Test;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

public class GEOUtils {

	private static final GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyCg9BKoFH-seSldMkPK3OFgUoLqi5ZMw4s");
	private static final int EARTHRADIUS = 6371;

	public static GeocodingResult getGeocoding(String endereco) {

		GeocodingResult[] results = null;
		try {

			results = GeocodingApi.geocode(context, endereco).await();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return results[0];

	}

	public static Double getKMDistanceByCoordinates(Double[] latitudeXLongitudeStartPoint, Double[] latitudeXLongitudeEndPoint) {

		Double distancia = EARTHRADIUS * Math.acos(Math.cos(Math.PI * (90 - latitudeXLongitudeEndPoint[0]) / 180)
				* Math.cos((90 - latitudeXLongitudeStartPoint[0]) * Math.PI / 180)
				+ Math.sin((90 - latitudeXLongitudeEndPoint[0]) * Math.PI / 180)
						* Math.sin((90 - latitudeXLongitudeStartPoint[0]) * Math.PI / 180)
						* Math.cos((latitudeXLongitudeStartPoint[1] - latitudeXLongitudeEndPoint[1]) * Math.PI / 180));
		return distancia;

	}

}
